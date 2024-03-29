function alternarGravacao() {
    if (gravacaoEmAndamento === false) {
        gravacaoEmAndamento = true;
        // Se não estiver gravando, iniciar a gravação
        gravarEEnviarAudio();
        // Atualizar o texto do botão
        document.getElementById('record-button').textContent = 'Parar Gravação';
    } else {
        gravacaoEmAndamento = false;
        // Se estiver gravando, parar a gravação
        mediaRecorder.stop();
        // Atualizar o texto do botão
        document.getElementById('record-button').textContent = 'Gravar Áudio e Enviar';
    }
}