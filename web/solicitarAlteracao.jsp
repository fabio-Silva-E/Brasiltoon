  
<%@page import="java.util.List" %>
<%@page import="Vo.Capitulos"%>


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




     
    <a class="destacado" href="HistoriasServlet?operacao=11&id=<%= historia %>">alterar</a>

               
