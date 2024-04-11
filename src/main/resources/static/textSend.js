// Função para gravar áudio e enviar via requisição POST
function sendText() {
    const statusMessageElement = document.getElementById('status-message');
    const areaResult = document.getElementById('area-result');

    const formData = new FormData();
    formData.append('text', areaResult.value);
    formData.append('themeSelected', themeSelected);

    fetch( domain + '/ai/extract-from-text', {
        method: 'POST',
        body: formData
    })
        .then(response => {
        if (response.ok) {
            statusMessageElement.textContent = 'Text sent successfully.';
            response.text().then(text => {
                areaResult.value = text;
            });
        } else {
            statusMessageElement.textContent = 'Erro ao enviar.';
        }
    })
        .catch(error => {
        statusMessageElement.textContent = 'Erro ao enviar: ' + error;
    });
}
