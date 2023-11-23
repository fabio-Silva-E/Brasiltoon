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
        url: 'HistoriasServlet?operacao=5',
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

    let historias;  // Declarada uma única vez aqui

    if (Array.isArray(data)) {
        // Já é um array, não precisa fazer o parse
        historias = data;
    } else if (typeof data === 'string') {
        // Se ainda for uma string, faça o parse
        try {
            historias = JSON.parse(data);
            console.log("Histórias após o parse:", historias);
        } catch (error) {
            console.error('Erro ao fazer o parse do JSON:', error);
            return;  // Se houver erro no parse, encerrar a função
        }
    } else {
        console.error('Tipo de dado inesperado:', typeof data);
        return;  // Encerrar a função se o tipo de dado for inesperado
    }

    // Seleciona o elemento da tabela no DOM
    const tabelaHistorias = $('#tabelaHistorias');

    // Limpa o conteúdo atual da tabela
    tabelaHistorias.empty();

    // Itera sobre as histórias e adiciona as tabelas à tabelaHistorias
    historias.forEach(historia => {
       // const table = $('<table>').css('flex', '1').css('text-align', 'center');
   //const table = $('<table>').css('flex', '1');//.css('border-collapse', 'collapse');
      // const table = $('<table>').css('flex', '1').css('max-width', '300px'); // Ajuste o valor conforme necessário
    const table = $('<table>').css({
    'flex': '1',
    'text-align': 'center',
    'border-collapse': 'collapse'
});

        const thead = $('<thead>');
        const tituloRow = $('<tr>');
        const tituloCell = $('<td>').text(historia.titulo).css({
    'font-size': '0.7em',
    'padding': '1px',
    'text-align': 'center'// Adicionado para alinhar horizontalmente
});

       
        tituloRow.append(tituloCell);

        const generoRow = $('<tr>');
       const generoCell = $('<td>').text(historia.genero).css({
    'font-size': '0.7em',
    'color': '#D3D3D3',
    'padding': '1px',
    'text-align': 'center' // Adicionado para alinhar horizontalmente
});        generoRow.append(generoCell);

        thead.append(tituloRow, generoRow);

        const tbody = $('<tbody>');
        const imagemRow = $('<tr>');
        const imagemCell = $('<td>').addClass('imagem-cell');

        const imagemLink = $('<a>').attr('href', 'HistoriasServlet?operacao=6&id=' + historia.id+'&idhc='+historia.capa);
        const imagem = $('<img>').attr('src', historia.capa).css({'width': '200px', 'height': '200px'});
        imagemLink.append(imagem);
        imagemCell.append(imagemLink);
        imagemRow.append(imagemCell);

        const autorRow = $('<tr>');
        const autorCell = $('<td>');
        const autorLink = $('<a>').attr('href', 'HistoriasServlet?operacao=2&id=' + historia.autorid)
                .text('Autor') 
                .css('color', 'white');
        autorCell.append(autorLink);
        autorRow.append(autorCell);

        tbody.append(imagemRow, autorRow);

        table.append(thead, tbody);
        tabelaHistorias.append(table);
    });
    
}
