function favoritarHistoria() {
        var historia = document.querySelector("[name='historia']").value;

        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'HomeController?operacao=2', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                var resposta = xhr.responseText;

                if (resposta === 'favoritada') {
                    alert('Esta história já está favoritada.');
                } else if (resposta === 'sucesso') {
                    alert('História favoritada com sucesso!');
                    // Você pode adicionar aqui a lógica para atualizar a parte da página que deseja
                } else {
                    alert('Ocorreu um erro ao favoritar a história.');
                }
            } else {
                alert('Ocorreu um erro ao favoritar a história.');
            }
        };

        xhr.onerror = function () {
            alert('Erro de rede ao favoritar a história.');
        };

        xhr.send('historia=' + historia);
    }