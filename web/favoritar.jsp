  
<%@page import="java.util.List" %>
<%@page import="Vo.Capitulos"%>
<script src="Js/favoritar.js" defer></script>

<%
    String pdfPath = ""; // Inicialize o caminho do PDF como uma string vazia
    int historia = -1; // Inicialize a vari�vel com um valor padr�o
    List<Capitulos> produtos = (List<Capitulos>) request.getAttribute("Historias");

    // Obtenha o primeiro cap�tulo como padr�o
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

               
