  
<%@page import="java.util.List" %>
<%@page import="Vo.Capitulos"%>


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




     
    <a class="destacado" href="HistoriasServlet?operacao=11&id=<%= historia %>">alterar</a>

               
