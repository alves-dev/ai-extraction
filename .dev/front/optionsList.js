function createButtons(wordList) {
      const buttonContainer = document.getElementById('button-container');

      wordList.forEach(word => {
          const button = document.createElement('button');
          button.textContent = word;
          button.className = 'word-button';
          button.addEventListener('click', () => {
              // Aqui você pode adicionar a lógica para o que acontece ao clicar no botão
              console.log(`Botão "${word}" clicado.`);
          });
          buttonContainer.appendChild(button);
      });
  }