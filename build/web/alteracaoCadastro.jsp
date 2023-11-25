<%@ page import="Vo.Usuario" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cadastro</title>
    
    <link rel="stylesheet" href="./css/cadastro.css">
    <script src="Js/alteracao.js" defer></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
      <%
            Usuario p = (Usuario) request.getAttribute("login");
        %>
<body>
    <div class="container mt-5">
        <div class="text-center">
            <h1>Cadastro</h1>
        </div>
        <form name="frm" method="post" action="AtualizarUsuarioServlet" onsubmit="return confirmarAlteracao()">
            <input value="<%= p.getId()%>" type="hidden" name="id">
            <div class="mb-3">
                <label for="nome" class="form-label">Nome</label>
                <input value="" type="text" id="nome" name="nome" class="form-control">
            </div>
            <div class="mb-3">
                <label for="telefone" class="form-label">Telefone</label>
                <input value="<%= p.getTel()%>" type="tel" id="telefone" name="telefone" pattern="[0-9]*" inputmode="numeric" class="form-control">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">E-mail</label>
                <input value="<%= p.getEmail()%>" type="email" id="email" name="email" class="form-control">
            </div>
            <div class="mb-3">
                <label for="senha" class="form-label">Senha</label>
                <input value="<%= p.getSenha()%>" type="password" id="senha" name="senha" class="form-control">
            </div>
            <div class="mb-3">
                <label for="Confirmarsenha" class="form-label">Confirmar Senha</label>
                <input type="password" id="Confirmarsenha" name="Confirmarsenha" value="<%= p.getSenha()%>" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Alterar Cadastro</button>
            <a class="btn btn-secondary" href="HomeController?operacao=1&id=<%= p.getId()%>">Voltar</a>
        </form>
    </div>
</body>

</html>
