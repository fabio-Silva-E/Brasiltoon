<%@page import="java.util.List" %>
<%@page import="Vo.Capitulos"%>
   
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://unpkg.com/pdfjs-dist@2.10.377/build/pdf.js"></script>
        <script src="https://unpkg.com/pdfjs-dist@2.10.377/build/pdf_viewer.js"></script>
        <script src="Js/capa.js" defer></script>
        
<%
    String pdfPath = ""; // Inicialize o caminho do PDF como uma string vazia
    int historia = -1; // Inicialize a variável com um valor padrão
    List<Capitulos> produtos = (List<Capitulos>) request.getAttribute("Historias");

    // Obtenha o primeiro capítulo como padrão
    if (!produtos.isEmpty()) {
        Capitulos primeiroCapitulo = produtos.get(0);
        historia = primeiroCapitulo.getHistoriaid();
        pdfPath = primeiroCapitulo.getCaminhopdf();
    }
%>

        <form id="toggleForm" style="margin-top:  10px;">
            <button type="button" onclick="toggleLista()">Episodios</button>
        </form>
    
   

<div id="listaCapitulos" onclick="ocultarLista()">
    <% for (Capitulos p : produtos) { %>
        <table style="display: block;">
            <tr>
                <td onclick="carregarCapitulo('<%= p.getCaminhopdf() %>')" style="cursor: pointer;">
                    <img src="<%= p.getCapa()%>" style="width: 100px; height: 100px;">
                </td>
                <td>Capitulo:<%= p.getNumeroCapitulo()%></td>
            </tr>
        </table>
    <% } %>
</div>



<script>
   function toggleLista() {
    const listaCapitulos = document.getElementById('listaCapitulos');
    const displayStatus = window.getComputedStyle(listaCapitulos).display;

    if (displayStatus === 'none') {
        listaCapitulos.style.display = 'block';
        listaCapitulos.classList.add('vertical-list');
    } else {
        listaCapitulos.style.display = 'none';
        listaCapitulos.classList.remove('vertical-list');
    }
}

// Adicione um event listener para um botão ou elemento que acionará a função toggleLista
const seuBotao = document.getElementById('listaCapitulos'); // Substitua 'seuBotao' pelo ID do seu botão ou elemento
seuBotao.addEventListener('click', toggleLista);
</script>