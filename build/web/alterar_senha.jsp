<%-- 
    Document   : alterar_senha
    Created on : 24/11/2023, 22:12:18
    Author     : fabio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>       
        <link rel="stylesheet" href="./css/nav.css">
        <script src="Js/alteracao.js" defer></script>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="./css/menurodape.css">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Acesso</title>
        <!-- <link rel="stylesheet" href="css.css"> -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/logar.css">
    </head>
    <body>
         <%
           String token =  request.getParameter("token");
        %>
        <main>
            <table class="center center-table">
                <tr><td>Altere sua senha</td></tr>
                <tr><td><form name="frm" method="post" action="redefinicao_de_senha"  >
              <input type="text" name="token" id="token" value="<%= token %>">
                            <label for="senha" class="form-label">
                                <span>Senha</span>
                <input type="password" id="senha" placeholder="Digite sua senha" name="novaSenha" class="form-control" required maxlength="8">
                   </label>
                    </td></tr>
                <tr><td>
                       <label for="Confirmarsenha" class="form-label">
                           <span>Confirmar Senha</span>
                <input type="password" id="Confirmarsenha" placeholder="Confirme a senha" name="Confirmarsenha" class="form-control" maxlength="8">
            </label>
                    </td></tr>
                <tr><td>
                        <input type="submit" value="alterar" >
                        
                        </form>
                    </td></tr>
            </table>
                 
        </main>
    </body>
</html>
