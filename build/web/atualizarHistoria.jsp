<%-- 
    Document   : atualizarHistoria
    Created on : 20/11/2023, 22:01:28
    Author     : fabio
--%>
<%@page import="Vo.Historias"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <link rel="stylesheet" href="./css/menurodape.css">
        <link rel="stylesheet" href="./css/nav.css">
        <script src="Js/acessar.js" defer></script>
        <script src="Js/InvalidatSession.js" defer></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="UTF-8">
        <title>Alteração de História</title>
        <script src="Js/capa.js" defer></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <style>
            body {
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0;
            }
        </style>
        <%
    Historias h = (Historias) request.getAttribute("historia");
%>

    </head>  <% int idCapitulo = Integer.parseInt(request.getParameter("id")); %>
    <body>
        <main>
            <section id="postagem-section" class="container">              
                <h2 class="mb-4">Alterar História</h2>
               <form name="frm" method="post" action="CapaServlet?operacao=1" enctype="multipart/form-data">
    <input type="hidden" name="id" id="id" value="<%= idCapitulo %>">
    <div class="mb-3">
        <label for="titulo" class="form-label">Título da História:</label>
        <input type="text" name="titulo" id="titulo" class="form-control" value="<%=h.getTitulo()%>">
    </div>
    <!-- Adicione um campo oculto para armazenar o valor atual da capa -->
    <input type="hidden" name="capaAtual" id="capaAtual" value="<%=h.getCapa()%>">
    <div class="mb-3">
        <label for="capa" class="input-group-text">
            <i class="fas fa-upload"></i> Insira a capa
            <input type="file" name="capa" id="capa" onchange="previewImage(this)" class="form-control d-none">
        </div>
        <div>
            <img id="preview" alt="Preview da capa" class="img-fluid" style="width: 100px; height: 100px; border: 1px solid #000;" src="<%=h.getCapa()%>">
            <div class="mb-3">
                <label for="descricao" class="form-label">Descrição:</label>
                <textarea name="descricao" id="descricao" class="form-control" required rows="4"><%=h.getDescricao()%></textarea>
            </div>
        </div>
        <div class="mb-3">
            <label for="genero" class="form-label">Selecione o gênero:</label>
            <select name="genero" id="genero" class="form-select" required>
                <option value="Terror" <%=h.getGenero().equals("Terror") ? "selected" : ""%>>Terror</option>
                <option value="Suspense" <%=h.getGenero().equals("Suspense") ? "selected" : ""%>>Suspense</option>
                <option value="Drama" <%=h.getGenero().equals("Drama") ? "selected" : ""%>>Drama</option>
                <option value="Romance" <%=h.getGenero().equals("Romance") ? "selected" : ""%>>Romance</option>
                <option value="Sci-Fi" <%=h.getGenero().equals("Sci-Fi") ? "selected" : ""%>>Sci-Fi</option>
                <option value="Ação" <%=h.getGenero().equals("Ação") ? "selected" : ""%>>Ação</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Alterar</button>
    </form>


                            </section>
                         
                            </main>
                            </body>

                            </html>
