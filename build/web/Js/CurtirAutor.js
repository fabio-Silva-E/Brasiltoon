$(document).ready(function () {
    var iconeCurtir = $("#iconeCurtir");

    // Recupera a resposta e a cor do ícone do localStorage
    var respostaAnterior = localStorage.getItem("respostaAnterior");
    var corAnterior = localStorage.getItem("corBotaoCurtir");

    // Aplica a cor do ícone salva anteriormente
    if (corAnterior) {
        iconeCurtir.css("color", corAnterior);
    }

    // Restante do seu código JavaScript...

       
      
    

    // Recupera a resposta do caso 3 da página
    var respostaDoCase3 = $("#curtirStatus").val();

    // Verifica se há uma resposta do caso 3 e a atribui à variável respostaAnterior
    if (respostaDoCase3 && respostaDoCase3.trim() !== "") {
        respostaAnterior = respostaDoCase3;
        localStorage.setItem("respostaAnterior", respostaDoCase3);

        // Atualiza a cor do botão com base na resposta do caso 3
        if (respostaDoCase3.trim() === "curtir") {
            iconeCurtir.css("color", "blue");
        } else if (respostaDoCase3.trim() === "descurtir") {
            iconeCurtir.css("color", "grey");
        } else {
            console.error("Resposta inesperada do servidor (caso 3)");
        }

        // Salva a cor do botão no localStorage
        localStorage.setItem("corBotaoCurtir", iconeCurtir.css("color"));
    }

    $("#curtirForm").submit(function (event) {
        event.preventDefault(); // Evita o comportamento padrão de envio do formulário

        // Simula uma solicitação AJAX para o servidor
        $.ajax({
            type: "POST",
            url: "HistoriasServlet?operacao=7",
            data: $("#curtirForm").serialize(),
            success: function (resposta) {
                // Verifica se a resposta está presente e não é vazia
                if (resposta && resposta.trim() !== "") {
                    // Atualiza a cor do botão com base na resposta do servidor
                    if (resposta.trim() === "curtir") {
                        iconeCurtir.css("color", "blue");
                    } else if (resposta.trim() === "descurtir") {
                        iconeCurtir.css("color", "grey");
                    } else {
                        console.error("Resposta inesperada do servidor");
                    }

                    // Verifica se a resposta mudou antes de salvar a cor no localStorage
                    if (resposta !== respostaAnterior) {
                        localStorage.setItem("corBotaoCurtir", iconeCurtir.css("color"));
                        localStorage.setItem("respostaAnterior", resposta);
                        
                    }
                } else {
                    console.error("Resposta vazia ou inválida do servidor");
                } location.reload();
                
            },
            
            error: function (erro) {
                console.error("Erro na solicitação AJAX", erro);
            }
        });
    });
});