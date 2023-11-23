<%@ page import="java.util.List" %>
<%@ page import="Vo.Historias" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <!-- Outros scripts dependentes do jQuery -->
        <!-- Bootstrap TouchSpin -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-touchspin/4.3.0/jquery.bootstrap-touchspin.min.js"></script>
        <link rel="stylesheet" href="./css/formPesquisa.css">
        <script src="Js/InvalidatSession.js" defer></script>
        <script src="Js/BibliotecaPublica.js" defer></script>
        <meta charset="utf-8">
        <!-- ... outros elementos do cabeçalho ... -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-touchspin/4.3.0/jquery.bootstrap-touchspin.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-touchspin/4.3.0/jquery.bootstrap-touchspin.min.css">
        <!-- ... outros elementos do cabeçalho ... -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="Js/acessar.js" defer></script>
        <link rel="stylesheet" href="./css/biblioteca.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!-- ... outros elementos do corpo ... -->
        <title>Brasiltoon</title>
    </head>
    <body>
        <div class="fixed-content" >
            <ul class="nav nav-pills nav-fill gap-2 p-1 small bg-primary rounded-5 shadow-sm" id="pillNav2" role="tablist" style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-primary); --bs-nav-pills-link-active-bg: var(--bs-white);">
                <li class="nav-item" role="presentation">
                    <button class="nav-link  rounded-5" id="home-tab2" data-bs-toggle="tab" type="button" role="tab" aria-selected="false" data-href="login.jsp">Logar</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link active rounded-5" id="profile-tab2" data-bs-toggle="tab" type="button" role="tab" aria-selected="true" data-href="bibliotecaPublica.jsp">historias</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link  rounded-5" id="contact-tab2" data-bs-toggle="tab" type of="button" role="tab" aria-selected="true" data-href="cadastro.html">Cadastrar-se</button>
                </li>
            </ul>
        </div>
        <jsp:include page="componentelogo.jsp" />
        <jsp:include page="carrocel.jsp" />
        <jsp:include page="formPesquisa.jsp" />
         <a href="login.jsp" class="btn btn-primary">Nova História</a>
        <h1 class="text-center" >Histórias</h1>
    </div>
    <!-- As tabelas de histórias geradas pela função atualizarTabela serão adicionadas aqui a tabela esta em um js -->
    <div  style="max-height: 600px; overflow-y: auto;">
        <div id="tabelaHistorias" >
        </div>
</body>
</html>
