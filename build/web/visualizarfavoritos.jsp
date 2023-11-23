<%@page import="java.util.List" %>
<%@page import="Vo.Capitulos"%>
   
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>   
        <meta name="viewport" content="width=device-width, initial-scale=1">  
        <title>leitura</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Js/favoritar.js" defer></script>
        <!-- Use links genéricos do PDF.js -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://unpkg.com/pdfjs-dist@2.10.377/build/pdf.js"></script>
        <script src="https://unpkg.com/pdfjs-dist@2.10.377/build/pdf_viewer.js"></script>
        <script src="Js/capa.js" defer></script>
        <!-- Especifique o WorkerSrc -->
        <script>
            pdfjsLib.GlobalWorkerOptions.workerSrc = 'https://unpkg.com/pdfjs-dist@2.10.377/build/pdf.worker.js';
        </script>
        <link rel="stylesheet" href="./css/visualizar.css">
           <%
                String resultado = request.getParameter("idhc");
                        
            %>
        <style>
            .scrollable-content {
    background-image: url('<%= resultado%>'); /* Substitua pelo caminho da sua imagem */
    background-size: cover; /* Ajusta o tamanho da imagem para cobrir toda a div */
    background-position: center; /* Centraliza a imagem na div */
    background-repeat: no-repeat; /* Evita a repetição da imagem */
  height: auto;
     
        align-items: center; /* Isso centralizará verticalmente os elementos */
        padding: 0; /* Adapte conforme necessário para espaçamento */
   padding-top: 75%;
      z-index: 1;
        margin-right: 2px; /* Ajuste o valor para aproximar mais os botões */
        text-decoration: none; /* Remova a decoração de texto dos links, se necessário */
            }
            .conteudo-frente {
                 background-size: cover; 
                z-index: 2; /* Valor de z-index maior para o conteúdo que deve ficar à frente */
             
            }

        </style>
    </head>
    <body>
        <div class="scrollable">
            <div class="scrollable-content">
             <td><a href="MinhaBiblioteca.jsp">voltar</a></td>
   
               <jsp:include page="curtirhistorias.jsp" />  
                 <jsp:include page="capitulos.jsp" />  
            
                     </div>
                
              <div class="conteudo-frente">
                 <jsp:include page="Componenteleitura.jsp" />
        </div> 
         </div>
    </body>
</html>