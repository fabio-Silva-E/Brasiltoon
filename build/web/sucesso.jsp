<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sucesso</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/formPesquisa.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="message-container">
        <%
            int resultado = Integer.parseInt(request.getParameter("resultado"));
            if (resultado == 1) {
               // int idCadastro = Integer.parseInt(request.getParameter("id"));
        %>
                <h2>Cadastro efetuado com sucesso</h2>
                 
                <a href="index.html">Voltar</a>
        <%
            } else if (resultado == 0) {
        %>
                <h2>Erro ao cadastrar</h2>
                <a href="cadastro.html">Voltar</a>
        <%
            } else if (resultado == 2) {
        %>
                <h2>Capitulo postado com sucesso</h2>
                <a href="minhasPublicacoes.jsp">Voltar</a>
        <%
            } else if (resultado == 3) {
        %>
                <h2>Historia Alterada</h2>
                <a href="minhasPublicacoes.jsp">Voltar</a>
        <%
            } else if (resultado == 4) {
        %>
               <h2>Senha enviada. Acesse seu e-mail.</h2>
                <a href="index.html">Voltar</a>
        <%
            } else if (resultado == 5) {
        %>
               <h2>Erro no envio. Revise seu e-mail.</h2>
                <a href="index.html">Voltar</a>
        <%
            }  else if (resultado == 6) {
                
        %>
               <h2>Cadastro atualizado faça o login novamente para validar as alterações</h2>
                
                <a href="LogoutServlet">Voltar</a>
        <%
            } 
        %>
        
    </div>
</body>
</html>
