<%@page import="Vo.Usuario"%>
<%@page import="java.net.URLEncoder"%>
<%@ page import="java.util.List" %>
<%@ page import="Vo.MinhaBiblioteca" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>

        <%
            Usuario p = (Usuario) request.getAttribute("login");
        %>
        <link rel="stylesheet" href="./css/logo.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="./css/nav.css">

        <script src="Js/acessar.js" defer></script>
        <script src="Js/InvalidatSession.js" defer></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="./css/perfil.css">
        <link rel="stylesheet" href="./css/menurodape.css">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>   

        <div class="fixed-content">
            <jsp:include page="componentelogo.jsp" />
        </div>

        <div class="profile-container">

            Meu Perfil


            <div class="profile-image-container">
                <img src="<%=p.getCaminho()%>" alt="Foto de Perfil" class="profile-image clickable-image" id="profile-image">
            </div>
            <div class="container mt-5">
                <form name="frm" method="post" action="upload?operacao=2" enctype="multipart/form-data" class="mt-3 centered-content">
                    <input type="hidden" name="id" value="<%=p.getId()%>">  
                    <div class="input-group">
                        <input type="file" name="novaFoto" id="novaFoto" accept="image/*" class="form-control d-none">
                        <label for="novaFoto" class="input-group-text">
                            <i class="fas fa-upload"></i> alterar foto de perfil
                        </label>
                    </div>


                    <button type="submit" class="btn btn-primary">Atualizar Foto</button>
                </form>

                <table class="mt-3 centered-content">

                    <tr>
                        <th>Nome</th>
                    </tr>
                    <tr>
                        <td><%=p.getNome()%></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                    </tr>
                    <tr>
                        <td><%=p.getEmail()%></td>
                    </tr>
                    <tr>
                        <th>Telefone</th>
                    </tr>
                    <tr>
                        <td><%= String.valueOf(p.getTel())%></td>
                    </tr>
                </table>

            </div>
        </div>
        <br><br><br><br><br><br>

        <script>

            const profileImage = document.getElementById("profile-image");
            let isImageLarge = false;

            profileImage.addEventListener("click", function () {
                if (isImageLarge) {
                    profileImage.style.transform = "scale(1.0)";
                } else {
                    profileImage.style.transform = "scale(1.2)";
                }
                isImageLarge = !isImageLarge;
            });
        </script>


        <div class="fixed-bottom">
            <div class="nav-bottom"> <!-- Barra de navegação -->

                <ul class="nav nav-pills nav-fill" id="pillNav2" role="tablist">
                    <li >
                        <button class="nav-link active rounded-5"  type="button" aria-selected="false" data-href="HomeController?operacao=1" >Home</button>
                    </li>
                    <li >
                        <button class="nav-link  rounded-5"  type="button"aria-selected="true" data-href="inserirhistoria.html">Nova historias</button>
                    </li>
                    <li >
                <button class="nav-link  rounded-5"  data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="LogoutServlet" >Sair</button>
                    <li >
                        <button class="nav-link  rounded-5"   type="button"  aria-selected="true" data-href="biblioteca.jsp" >Histórias</button>
                    <li >
                        <button class="nav-link  rounded-5"  type="button"  aria-selected="true" data-href="MinhaBiblioteca.jsp" >Favoritos</button>
                    <li >
                        <button class="nav-link  rounded-5"   type="button" aria-selected="true" data-href="solicitacao.html" >Alterar Cadastro</button>
                    <li >
                        <button class="nav-link  rounded-5"   type="button"  aria-selected="true" data-href="minhasPublicacoes.jsp" >Minhas Publicações</button>

                    </li>

                </ul>

            </div>

        </div>
    </body>
</html>
