 const buttons = document.querySelectorAll('button[data-href]');

    // Adicione um ouvinte de evento de clique a cada botão.
    buttons.forEach(button => {
      button.addEventListener('click', () => {
        // Obtenha o URL do atributo 'data-href' do botão clicado.
        const href = button.getAttribute('data-href');
        
        // Redirecione para a nova página.
        window.location.href = href;
      });
    });