<%-- 
    Document   : login
    Created on : 22/10/2023, 15:04:52
    Author     : fabio
--%>
  <%
  String erro = (String)request.getAttribute("erro");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="Js/ProtecaoDeUrl.js" defer></script>
         <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="Js/ProtecaoDeUrl.js" defer></script>
        <!-- <link rel="stylesheet" href="css.css"> -->
        <link rel="stylesheet" href="./css/logar.css">
      <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <title>Brasiltoon</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="Js/acessar.js" defer></script>
    </head>
    <body>
        <div class="nav-bar"> <!-- Barra de navegação -->
        <ul class="nav nav-pills nav-fill gap-2 p-1 small bg-primary rounded-5 shadow-sm" id="pillNav2" role="tablist" style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-primary); --bs-nav-pills-link-active-bg: var(--bs-white);">
            <li class="nav-item" role="presentation">
                <button class="nav-link active rounded-5" id="home-tab2" data-bs-toggle="tab" type="button" role="tab" aria-selected="false" data-href="login.jsp">Logar</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link rounded-5" id="profile-tab2" data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="bibliotecaPublica.jsp">historias</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link  rounded-5" id="contact-tab2" data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="cadastro.html">Cadastrar-se</button>
            </li>
        </ul>
        
         <jsp:include page="componentelogo.jsp" />
   
    </div>
        <main>
            <table class="center center-table">
                
                <form method="Post" action="LoginServlet">
                <tr><td>
                        <label for="email">
                            <span>EMAIL</span>
                            <input type="email" id="email" placeholder="Digite seu email" name="email" required>
                        </label>
                    </td></tr>
                <tr><td>
                        <label for="password">
                            <span>SENHA</span>
                            <input type="password" id="senha" placeholder="Digite sua senha" name="senha" maxlength="8" required>
                        </label>
                    </td></tr>
                <tr><td>
                        <input type="submit" value="Entrar" id="button"></form>
                    </td></tr>
                  <tr><td>
                        <a  href="esqueceuSenha.jsp">Esqueci a senha</a> </td></tr>
                
               <tr><td>   <p><%
                      if(erro != null)
                      out.print(erro);%></p></td></tr>
            </table>
        </main>
    </body>
</html>
