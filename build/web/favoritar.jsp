  
<%@page import="java.util.List" %>
<%@page import="Vo.Capitulos"%>
<script src="Js/favoritar.js" defer></script>

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




     
<form id="favoritarForm" style="margin-top:  10px;">
            <input type="hidden" name="historia" value="<%= historia %>" >
            <input type="button" value="Favoritar" onclick="favoritarHistoria()" class="favoritar-button">
        </form>

               
