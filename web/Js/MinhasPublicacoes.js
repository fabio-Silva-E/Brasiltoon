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
 function confirmarExclusao() {
            return confirm("Deseja realmente excluir a historia não tera como desfazer?");
        }
function realizarBusca() {
    $.ajax({
        type: 'GET',
        url: 'HomeController?operacao=6',
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
        const table = $('<table>').css('flex', '1');

       const thead = $('<thead>');
        const tituloRow = $('<tr>');
        const tituloCell = $('<td>').text(historia.titulo)
        .css({
    'font-size': '0.7em',
    'padding': '1px',
    'text-align': 'center'// Adicionado para alinhar horizontalmente
});
        tituloRow.append(tituloCell);

        const generoRow = $('<tr>');
        const generoCell = $('<td>').text(historia.genero)
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

        const imagemLink = $('<a>').attr('href', 'HistoriasServlet?operacao=1&id=' + historia.id+'&idhc='+historia.capa);
        const imagem = $('<img>').attr('src', historia.capa).css({'width': '200px', 'height': '200px'});
        imagemLink.append(imagem);
        imagemCell.append(imagemLink);
        imagemRow.append(imagemCell);

        const autorRow = $('<tr>');
        const autorCell = $('<td>');
        const autorLink = $('<a>')
                .attr('href', 'inserirCapitulo.jsp?id=' + historia.id)
                .text('adicionar')
                .css('color', 'white');
        autorCell.append(autorLink);
        autorRow.append(autorCell);
        
         const excluirRow = $('<tr>');
        const excluirCell = $('<td>');
        const excluirLink = $('<a>') 
                .attr('href', `HistoriasServlet?operacao=12&id=${historia.id}`)
                .attr('onclick', `return confirmarExclusao()`)
                .text('remover')
                .css('color', 'red');
        excluirCell.append(excluirLink);
        excluirRow.append(excluirCell);
        

        tbody.append(imagemRow, autorRow,excluirRow);

        table.append(thead, tbody);
        tabelaHistorias.append(table);
    });
    
}
/* <div style="display: flex; flex-wrap: wrap;">
    <%
    for (Historias produto : produtos) {
    %>
    <script>
    const imagem = document.getElementById("link-<%= produto.getId() %>");
    
    imagem.addEventListener("click", function(event) {
        event.preventDefault(); // Evite a navegação padrão para a URL do botão
        document.getElementById("botao-<%= produto.getId() %>").click(); // Simule o clique no botão "Ler"
    });
</script>

    <table  style="flex: 1">
        <thead>
            <tr>
                <td><%= produto.getTitulo() %></td>
            </tr>
            <tr>
                <td><%= produto.getGenero() %></td>
            </tr>
        </thead>
        <tr>
            <td class="imagem-cell">
    <a href="HistoriasServlet?operacao=4&id=<%= produto.getId() %>" id="link-<%= produto.getId() %>">
        <img src="<%= produto.getCapa() %>" style="width: 200px; height: 200px;">
    </a>
</td>
            </tr>
        <tbody>
           
            <tr>
               <td><a href="inserirCapitulo.jsp?id=<%= produto.getId() %>" class="btn btn-primary">adicionar</a></td>
            </tr>
        </tbody>
    </table>
    <%
    }
    %>
</div>
*/