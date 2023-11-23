<%@page import="Vo.Capitulos"%>
<%@page import="java.util.List" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
     <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
                  <script src="Js/CurtirHistoria.js" defer></script>

<%
    String pdfPath = ""; // Inicialize o caminho do PDF como uma string vazia
    int historia = -1; // Inicialize a variável com um valor padrão
    List<Capitulos> produtos = (List<Capitulos>) request.getAttribute("Historias");

    // Obtenha o primeiro capítulo como padrão
    if (!produtos.isEmpty()) {
        Capitulos primeiroCapitulo = produtos.get(0);
        historia = primeiroCapitulo.getHistoriaid();
        pdfPath = primeiroCapitulo.getCaminhopdf();
    }
%>
<div id="curtirDiv" style="margin-top: 10px;">
    <!-- Substitua o botão de submit por um ícone de like -->
    <button id="curtirButton" type="button" class="curtir-button" 
            data-historia="<%= historia %>" style="border: none; background: none; padding: 0;">
        <i id="iconeCurtir" class="fas fa-heart" style="text-align: left;"></i>
        <span id="totalCurtidas"><%= request.getAttribute("total")%></span>
    </button>
</div>

<input type="hidden" id="curtirStatus" value="<%= request.getAttribute("curtirStatus")%>">

           
           

      <script>
   $(document).ready(function () {
    var iconeCurtir = $("#iconeCurtir");
    var totalElement = $("#totalCurtidas");
    var curtirStatus = $("#curtirStatus").val();

    // Aplica a cor do ícone com base na resposta do servidor
    if (curtirStatus && curtirStatus.trim() !== "") {
        if (curtirStatus.trim() === "curtir") {
            iconeCurtir.css("color", "blue");
        } else if (curtirStatus.trim() === "descurtir") {
            iconeCurtir.css("color", "grey");
        } else {
            console.error("Resposta inesperada do servidor");
        }
    }

    $("#curtirButton").click(function () {
        // Simula uma solicitação AJAX para o servidor
        $.ajax({
            type: "POST",
            url: "HistoriasServlet?operacao=10",
            data: { id: <%= historia %> },
            success: function (resposta) {
                if (resposta && resposta.trim() !== "") {
                    // Atualiza a cor do botão com base na resposta do servidor
                    if (resposta.trim() === "curtir") {
                        iconeCurtir.css("color", "blue");
                        totalElement.text(parseInt(totalElement.text()) + 1);
                    } else if (resposta.trim() === "descurtir") {
                        iconeCurtir.css("color", "grey");
                        totalElement.text(parseInt(totalElement.text()) - 1);
                    } else {
                        console.error("Resposta inesperada do servidor");
                    }
                } else {
                    console.error("Resposta vazia ou inválida do servidor");
                }
            },
            error: function (erro) {
                console.error("Erro na solicitação AJAX", erro);
            }
        });
    });

    // Restante do seu código para formatar o número de curtidas...
});

</script>

