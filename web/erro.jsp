<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erro</title>
    <link rel="stylesheet" href="./css/formPesquisa.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <div class="message-container">
            <%
                int resultado = Integer.parseInt(request.getParameter("resultado"));
                if (resultado == 1) {           
            %>
                <h2>Erro na alteração revise seus dados</h2>
                <a href="minhasPublicacoes.jsp" class="btn btn-primary">Voltar</a>
            <%
                } else if (resultado == 2) {
                  int idCapitulo = 0;
                  idCapitulo = Integer.parseInt(request.getParameter("id"));
            %>
                <h2>Erro ao postar capítulo, revise seus dados </h2>
                <a href="inserirCapitulo.jsp?id=<%= idCapitulo %>" class="btn btn-primary">Voltar</a>
            <%
                } else if (resultado == 3) {
            %>
                <h2>Erro na inserção da história</h2>
                <a href="inserirhistoria.html" class="btn btn-primary">Voltar</a>
            <%
                } else if (resultado == 0) {
            %>
                <h2>Erro ao cadastrar</h2>
                <a href="cadastro.html" class="btn btn-primary">Voltar</a>      
            <%
                } else if (resultado == 4) {
            %>
                <h2>Erro no envio. Revise seu e-mail.</h2>
                <a href="index.html" class="btn btn-primary">Voltar</a>  
            <%
                } else if (resultado == 5) {
            %>
                <h2>Arquivo inválido</h2>
                <a href="index.html" class="btn btn-primary">Voltar</a>
            <%
                } else if (resultado == 6) {
            %>
                <h2>Erro na alteração, tente novamente e revise seus dados</h2>
                <a href="LogoutServlet" class="btn btn-primary">Voltar</a>
            <%
                } else if (resultado == 7) {
            %>
                <h2>Email e/ou senha inválido(s)</h2>
                <a href="solicitacao.html" class="btn btn-primary">Voltar</a>
            <%
                } else if (resultado == 8) {
            %>
                <h2>Errro na alteração</h2>
                <a href="index.html" class="btn btn-primary">Voltar</a>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
