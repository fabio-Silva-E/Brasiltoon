<%@ page import="java.util.List" %>
<%@ page import="Vo.MinhaBiblioteca" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .imagem-cell img {
                max-width: 100px; /* Defina a largura máxima desejada, por exemplo, 100px */
                max-height: 100px; /* Defina a altura máxima desejada, igual à largura para criar um quadrado */
            }
        </style>
        <script src="Js/ProtecaoDeUrl.js" defer></script>

        <script src="Js/favoritos.js" defer></script>
        <script src="Js/acessar.js" defer></script>
        <script src="Js/InvalidatSession.js" defer></script>
        <meta charset="utf-8">
        <link rel="stylesheet" href="./css/formPesquisa.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Favoritos - Brasiltoon</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="./css/menurodape.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

        <link rel="stylesheet" href="./css/biblioteca.css">
    </head>
    <body>
        <div class="fixed-content" >
            <jsp:include page="componentelogo.jsp" />       

            <jsp:include page="formPesquisa.jsp" />

            <h2 class="text-center">Favoritos</h2>
  
            
        <div class="scrollable-content" style="max-height: 400px; overflow-y: auto;">
            <div id="tabelaFavoritos" style="display: flex; flex-wrap: wrap;">

            </div>
            <br><br><br>

        </div>   
        <div class="fixed-bottom">
            <div class="nav-bottom"> <!-- Barra de navegação -->
                <ul class="nav nav-pills nav-fill" id="pillNav2" role="tablist">
                    <li >
                        <button class="nav-link  rounded-5"   type="button" role="tab" aria-selected="false" data-href="HomeController?operacao=1">Home</button>
                    </li>
                    <li >
                        <button class="nav-link  rounded-5"   type="button" role="tab" aria-selected="true" data-href="inserirhistoria.html">Nova historias</button>
                    </li>
                    <li >
                        <button class="nav-link  rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="LogoutServlet" >Sair</button>
                    <li >
                        <button class="nav-link  rounded-5"  type="button" role="tab" aria-selected="true" data-href="biblioteca.jsp" >Histórias</button>
                    <li >
                        <button class="nav-link active rounded-5"  type="button" role="tab" aria-selected="true" data-href="MinhaBiblioteca.jsp" >Favoritos</button>
                    <li >
                        <button class="nav-link  rounded-5"   type="button" role="tab" aria-selected="true" data-href="solicitacao.html" >Alterar Cadastro</button>
                    <li >
                        <button class="nav-link  rounded-5"   type="button" role="tab" aria-selected="true" data-href="minhasPublicacoes.jsp" >Minhas Publicações</button>

                    </li>

                </ul>

            </div>

        </div>

    </div>

</body>
</html>
