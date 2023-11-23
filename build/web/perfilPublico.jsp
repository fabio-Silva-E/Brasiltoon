<%@page import="Vo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
     <%
            Usuario p = (Usuario) request.getAttribute("login");
        %>
         <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Perfil</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
   <link rel="stylesheet" href="./css/perfil.css">
</head>
  <jsp:include page="componentelogo.jsp" />

<body>
       
  <jsp:include page="perfilcomponente.jsp" />
        <br>
        <a href="bibliotecaPublica.jsp">Voltar</a>
    </div>
</body>
</html>
