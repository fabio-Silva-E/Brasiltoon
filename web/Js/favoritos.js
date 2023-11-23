const letrasIniciaisInput = document.getElementById('letrasIniciais');
const generoSelect = document.getElementById('genero');
const savedLetrasIniciais = localStorage.getItem('letrasIniciais');
const savedGenero = localStorage.getItem('genero');
const searchForm = document.getElementById('searchForm');

if (savedLetrasIniciais) {
    letrasIniciaisInput.value = savedLetrasIniciais;
}

if (savedGenero) {
    generoSelect.value = savedGenero;
}

letrasIniciaisInput.addEventListener('keyup', function() {
    localStorage.setItem('letrasIniciais', letrasIniciaisInput.value);
});

generoSelect.addEventListener('change', function() {
    localStorage.setItem('genero', generoSelect.value);
});

letrasIniciaisInput.addEventListener('keyup', function() {
    realizarBusca();
});

generoSelect.addEventListener('change', function() {
    realizarBusca();
});

window.addEventListener('load', function() {
    letrasIniciaisInput.focus();
});

searchForm.addEventListener('submit', function(event) {
    event.preventDefault(); // Cancela o envio padrão do formulário
    realizarBusca();
    letrasIniciaisInput.focus();
});

window.addEventListener('load', function() {
    realizarBusca();
});

letrasIniciaisInput.addEventListener('focus', function() {
    setTimeout(function() {
        letrasIniciaisInput.focus();
    }, 500);
});

function realizarBusca() {
    $.ajax({
        type: 'GET',
        url: 'HomeController?operacao=3',
        data: $('#searchForm').serialize(),
        success: function(responseData) {
            try {
                console.log(responseData);
                atualizarTabela(responseData);
                setTimeout(function() {
                    letrasIniciaisInput.focus();
                }, 500);
            } catch (error) {
                console.error('Erro ao fazer o parse do JSON:', error);
            }
        },
        error: function(error) {
            console.error('Erro na requisição AJAX', error);
        }
    });
}





// Função para atualizar a tabela com os dados recebidos do servidor
function atualizarTabela(data) {
    console.log("Conteúdo da resposta:", data);
    console.log("Tipo de dado recebido:", typeof data);

    let minhaBibliotecas;  // Corrigido o nome da variável para camelCase

    if (Array.isArray(data)) {
        // Já é um array, não precisa fazer o parse
        minhaBibliotecas = data;
    } else if (typeof data === 'string') {
        // Se ainda for uma string, faça o parse
        try {
            minhaBibliotecas = JSON.parse(data);
            console.log("Histórias após o parse:", minhaBibliotecas);
        } catch (error) {
            console.error('Erro ao fazer o parse do JSON:', error);
            return;  // Se houver erro no parse, encerrar a função
        }
    } else {
        console.error('Tipo de dado inesperado:', typeof data);
        return;  // Encerrar a função se o tipo de dado for inesperado
    }

    // Seleciona o elemento da tabela no DOM
    const tabelaFavoritos = $('#tabelaFavoritos');

    // Limpa o conteúdo atual da tabela
    tabelaFavoritos.empty();

    // Itera sobre as histórias e adiciona as tabelas à tabelaHistorias
    minhaBibliotecas.forEach(minhaBiblioteca => {
        const table = $('<table>').css('flex', '1');

         const thead = $('<thead>');
        const tituloRow = $('<tr>');
        const tituloCell = $('<td>').text(minhaBiblioteca.titulo)
         .css({
    'font-size': '0.7em',
    'padding': '1px',
    'text-align': 'center'// Adicionado para alinhar horizontalmente
});
        tituloRow.append(tituloCell);

        const generoRow = $('<tr>');
        const generoCell = $('<td>').text(minhaBiblioteca.genero)
        .css('font-size', '0.7em')
       .css({
    'font-size': '0.7em',
    'color': '#D3D3D3',
    'padding': '1px',
    'text-align': 'center' // Adicionado para alinhar horizontalmente
});        generoRow.append(generoCell);

        generoRow.append(generoCell);

        thead.append(tituloRow, generoRow);

        const tbody = $('<tbody>');
        const imagemRow = $('<tr>');
        const imagemCell = $('<td>').addClass('imagem-cell');

        const imagemLink = $('<a>').attr('href', 'HistoriasServlet?operacao=9&id=' + minhaBiblioteca.idHistoria+'&idhc='+minhaBiblioteca.capa);
        const imagem = $('<img>').attr('src', minhaBiblioteca.capa).css({'width': '200px', 'height': '200px'});
        imagemLink.append(imagem);
        imagemCell.append(imagemLink);
        imagemRow.append(imagemCell);
        
         const idRow = $('<tr>');
        const idCell = $('<td>');
        const idLink = $('<a>').attr('href', 'HomeController?operacao=5&id=' + minhaBiblioteca.id)
                .addClass('')
                .text('excluir')
                .css('color', 'white');
        idCell.append(idLink);
        idRow.append(idCell);

        const autorRow = $('<tr>');
        const autorCell = $('<td>');
        const autorLink = $('<a>').attr('href', 'HistoriasServlet?operacao=8&id=' + minhaBiblioteca.idautor)
                .addClass('')
                .text('Autor')
                .css('color', 'white');
        autorCell.append(autorLink);
        autorRow.append(autorCell);

        tbody.append(imagemRow,idRow, autorRow);

        table.append(thead, tbody);
        tabelaFavoritos.append(table);
    });
}

