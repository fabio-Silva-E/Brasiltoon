<%@page import="java.net.URLEncoder"%>
<%@ page import="java.util.List" %>
<%@ page import="Vo.Historias" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .imagem-cell img {
                max-width: 100px; /* Defina a largura máxima desejada, por exemplo, 100px */
                max-height: 100px; /* Defina a altura máxima desejada, igual à largura para criar um quadrado */
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <link rel="stylesheet" href="./css/formPesquisa.css">
        <link rel="stylesheet" href="./css/menurodape.css">

        <script src="Js/InvalidatSession.js" defer></script>
        <script src="Js/ProtecaoDeUrl.js" defer></script>
        <link rel="stylesheet" href="./css/logo.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <link rel="stylesheet" href="./css/biblioteca.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="Js/acessar.js" defer></script>
        <script src="Js/MinhasPublicacoes.js" defer></script>
        <link rel="stylesheet" href="./css/biblioteca.css">
        <title>Brasiltoon</title>
    </head>
    <body>
        <div class="fixed-content" >
            <jsp:include page="componentelogo.jsp" />  
            <jsp:include page="formPesquisa.jsp" />
            <h1 class="text-center">Minhas publicações</h1>     
            <div class="scrollable-content" style="max-height: 400px; overflow-y: auto;">
                <div id="tabelaHistorias" style="display: flex; flex-wrap: wrap;">              
                </div>
                <br><br><br><br><br><br>
            </div>  
            <div class="fixed-bottom">
                <div class="nav-bottom"> <!-- Barra de navegação -->
                    <ul class="nav nav-pills nav-fill" id="pillNav2" role="tablist">
                        <li >
                            <button class="nav-link  rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="false" data-href="HomeController?operacao=1">Home</button>
                        </li>
                        <li >
                            <button class="nav-link  rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="inserirhistoria.html">Nova historias</button>
                        </li>
                        <li >
                            <button class="nav-link  rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="LogoutServlet" >Sair</button>
                        <li >
                            <button class="nav-link  rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="biblioteca.jsp" >Histórias</button>
                        <li >
                            <button class="nav-link  rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="MinhaBiblioteca.jsp" >Favoritos</button>
                        <li >
                            <button class="nav-link  rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="solicitacao.html" >Alterar Cadastro</button>
                        <li >
                            <button class="nav-link active rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="minhasPublicacoes.jsp" >Minhas Publicações</button>
                        </li>
                    </ul>
                </div>
            </div>                
    </body>
</html>
