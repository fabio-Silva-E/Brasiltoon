package controller;

import Dao.HistoriasDao;
import Vo.Capitulos;
import Vo.Historias;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig/*(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)    // 50MB*/
public class capitulosServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "E:\\5º_semestre\\programação_web\\Brasiltoon\\web\\historias";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Historias e = new Historias();
        Capitulos c = new Capitulos(); // Adicionado objeto Capitulos
        HistoriasDao p = new HistoriasDao();
        String nomeArquivo = null;
        String nomeCapa = null;
        String caminhoCompleto = null;
        String caminhoCompletoCapa = null;
        String caminho = null;

        HttpSession session = request.getSession();
        int historiaId = Integer.parseInt(request.getParameter("idhistoria"));

        String caminhoCapa = null;

        for (Part part : request.getParts()) {
            nomeArquivo = extractFileName(part);
            nomeCapa = extractFileCapa(part);

            if (nomeArquivo != null && !nomeArquivo.isEmpty() && isPDFFile(nomeArquivo)) {
                caminhoCompleto = UPLOAD_DIR + File.separator + nomeArquivo;
                caminho = "historias/" + nomeArquivo;
                part.write(caminhoCompleto);
            } else if (nomeCapa != null && !nomeCapa.isEmpty()) {
                caminhoCompletoCapa = UPLOAD_DIR + File.separator + nomeCapa;
                caminhoCapa = "historias/" + nomeCapa;
                part.write(caminhoCompletoCapa);
            }
        }
        if (session.getAttribute("idAutor") == null) {
            response.sendRedirect("login.jsp"); // Redireciona para a página de login
            return; // Encerra o processamento do servlet
        }
          int ultimoNumeroCapitulo=0;
        // Recupere o último número de capítulo da história
         ultimoNumeroCapitulo = p.getUltimoNumeroCapitulo(historiaId);

        // Adicione 1 ao número do último capítulo para obter o próximo número de capítulo
        int proximoNumeroCapitulo = ultimoNumeroCapitulo + 1;

        c.setHistoriaid(historiaId);
        c.setNumeroCapitulo(proximoNumeroCapitulo); // Número do próximo capítulo
        c.setCaminhopdf(caminho);
        c.setCapa(caminhoCapa);
        if (p.inserirCapitulo(c)) {
            response.sendRedirect("sucesso.jsp?resultado=2");
        } else {
            response.sendRedirect("erro.jsp?resultado=2&id=" + historiaId);
        }

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }

    private String extractFileCapa(Part partCapa) {
        String contentDisp = partCapa.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return null;
    }

    private boolean isPDFFile(String fileName) {
        return fileName.toLowerCase().endsWith(".pdf");
    }

    private boolean isPNGFile(String fileName) {
        return fileName.toLowerCase().endsWith(".png");
    }
}
