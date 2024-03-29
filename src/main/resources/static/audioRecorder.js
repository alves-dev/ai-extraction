// Variável para armazenar o objeto MediaRecorder
let mediaRecorder;

// Função para gravar áudio e enviar via requisição POST
function gravarEEnviarAudio() {
    const statusMessageElement = document.getElementById('status-message');
    const contadorElement = document.getElementById('contador');
    const recordButton = document.getElementById('record-button');
    statusMessageElement.textContent = 'Iniciando gravação de áudio...';

    // Define o tempo total de gravação em segundos (por exemplo, 30 segundos)
    const tempoTotalSegundos = 12;
    let tempoAtualSegundos = tempoTotalSegundos;
    let timerInterval;

    // Atualiza o contador a cada segundo
    function atualizarContador() {
        const minutos = Math.floor(tempoAtualSegundos / 60).toString().padStart(2, '0');
        const segundos = (tempoAtualSegundos % 60).toString().padStart(2, '0');
        contadorElement.textContent = `${minutos}:${segundos}`;
        tempoAtualSegundos--;

        // Verifica se o tempo acabou
        if (tempoAtualSegundos < 0) {
            clearInterval(timerInterval);
            mediaRecorder.stop();
            recordButton.textContent = 'Gravar Áudio e Enviar';
            gravacaoEmAndamento = false;
        }
    }

    // Inicia o contador
    atualizarContador();
    timerInterval = setInterval(atualizarContador, 1000);

    // Verifica se o navegador suporta a API de gravação de mídia
    if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
        // Solicita permissão para acessar o microfone
        navigator.mediaDevices.getUserMedia({ audio: true })
            .then(function(stream) {
                // Cria um novo objeto MediaRecorder para gravar o áudio
                mediaRecorder = new MediaRecorder(stream);
                const chunks = [];

                // Evento que é disparado quando há dados disponíveis para a gravação
                mediaRecorder.ondataavailable = function(event) {
                    chunks.push(event.data);
                };

                // Evento que é disparado quando a gravação é encerrada
                mediaRecorder.onstop = function() {
                    statusMessageElement.textContent = 'Gravação de áudio terminada.';

                    // Concatena os chunks de áudio em um único Blob
                    const blob = new Blob(chunks, { type: 'audio/webm' });

                    // Cria um objeto FormData para enviar o áudio via requisição POST
                    const formData = new FormData();
                    formData.append('audioFile', blob, 'audio.webm');
                    formData.append('themeSelected', themeSelected);

                    // Substitua 'sua_url_de_destino' pela URL do seu endpoint de destino
                    fetch( domain + '/ai/upload-audio', {
                        method: 'POST',
                        body: formData
                    })
                    .then(response => {
                        if (response.ok) {
                            statusMessageElement.textContent = 'Áudio enviado com sucesso.';
                        } else {
                            statusMessageElement.textContent = 'Erro ao enviar áudio.';
                        }
                    })
                    .catch(error => {
                        statusMessageElement.textContent = 'Erro ao enviar áudio: ' + error;
                    });

                    clearInterval(timerInterval);
                };

                // Inicia a gravação quando o MediaRecorder está disponível
                mediaRecorder.start();
            })
            .catch(function(error) {
                statusMessageElement.textContent = 'Erro ao acessar o microfone: ' + error;
            });
    } else {
        statusMessageElement.textContent = 'Seu navegador não suporta a gravação de áudio.';
    }
}
