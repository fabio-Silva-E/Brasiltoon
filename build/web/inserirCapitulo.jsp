<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
     <link rel="stylesheet" href="./css/nav.css">
     <link rel="stylesheet" href="./css/menurodape.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Brasiltoon</title>
    <script src="Js/capa.js" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>     
<body>
    
    <% int id = Integer.parseInt(request.getParameter("id")); %>
      <section id="postagem-section" class="container"> 
    <form name="frm" method="post" action="capitulosServlet" enctype="multipart/form-data">
        <input type="text" name="idhistoria" id="idhistoria" value="<%=id%>">
        <div class="mb-3">
            <label for="capa" class="input-group-text">
            <i class="fas fa-upload"></i> insira a capa
            <input type="file" name="capa" id="capa" onchange="previewImage(this)" class="form-control d-none">                    
        </div>
        <div>
            <img id="preview" alt="Preview da capa" class="img-fluid" style="width: 100px; height: 100px; border: 1px solid #000;" src="historias/fundoverde.jpg">
        </div>
        <div class="mb-3">
           
             <label for="historia" class="input-group-text">
            <i class="fas fa-upload"></i> Insira a Historia (PDF)
            <input type="file" name="historiaFile" id="historia" accept=".pdf" class="form-control d-none">
            
        </div>
        <input type="submit" value="Enviar História">
        <div>
        <a href="minhasPublicacoes.jsp">Voltar</a>
        </div>
    </form>
      </section>
</body>
</html>
