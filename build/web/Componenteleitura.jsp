<%@page import="java.util.List" %>
<%@page import="Vo.Capitulos"%>

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

<div class="pdf-viewer" id="pdfViewer"></div>

<script>
    var pdfPath = '<%= pdfPath %>'; // Use o valor inicial de pdfPath
    var pdfViewerContainer = document.getElementById("pdfViewer");

    function carregarCapitulo(pdfPath) {
        if (pdfPath) {
            // Limpe o contêiner antes de carregar um novo capítulo
            pdfViewerContainer.innerHTML = "";

            var loadingTask = pdfjsLib.getDocument(pdfPath);
            loadingTask.promise.then(function (pdf) {
                for (var i = 1; i <= pdf.numPages; i++) {
                    renderizarPagina(pdf, i, pdfViewerContainer);
                }
            });
        }
    }

    function renderizarPagina(pdf, pageNumber, container) {
        pdf.getPage(pageNumber).then(function (page) {
            var viewport = page.getViewport({ scale: 1.0 }); // Use scale 1.0 para ocupar 100% da tela

            var canvas = document.createElement('canvas');
            var context = canvas.getContext('2d');
            canvas.height = viewport.height;
            canvas.width = viewport.width;

            container.appendChild(canvas);

            var renderContext = {
                canvasContext: context,
                viewport: viewport
            };
            page.render(renderContext);
        });
    }

    // Carregue o PDF inicial
    carregarCapitulo(pdfPath);

    // Atualize o tamanho do PDF ao redimensionar a janela
    window.addEventListener('resize', function () {
        // Recarregue a página atual para atualizar o tamanho do PDF
        carregarCapitulo(pdfPath);
    });
</script>

<style>
    .pdf-viewer canvas {
        width: 100%;
        height: 100%;
    }
</style>
