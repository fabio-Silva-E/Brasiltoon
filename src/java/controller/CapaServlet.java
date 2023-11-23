/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Dao.HistoriasDao;
import Vo.Capitulos;
import Vo.Historias;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author fabio
 */
@MultipartConfig
public class CapaServlet extends HttpServlet {

   private static final String UPLOAD_DIR = "E:\\5º_semestre\\programação_web\\Brasiltoon\\web\\historias";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
        Historias e = new Historias();
        Capitulos c = new Capitulos(); // Adicionado objeto Capitulos
        HistoriasDao p = new HistoriasDao();
        String nomeArquivo = null;
        String caminhoCompleto = null;
        String caminho = null;
        String capa=null;
         capa=  request.getParameter("capaAtual");
        HttpSession session = request.getSession();

       
         for (Part part : request.getParts()) {
            nomeArquivo = extractFileName(part);
            if (nomeArquivo != null && !nomeArquivo.isEmpty() ) {
                caminhoCompleto = UPLOAD_DIR + File.separator + nomeArquivo;
                capa = "historias/" + nomeArquivo;
                part.write(caminhoCompleto);
            }
        }


        if (session.getAttribute("idAutor") == null) {
            response.sendRedirect("login.jsp"); // Redireciona para a página de login
            return; // Encerra o processamento do servlet
        }

     int operacao = Integer.parseInt(request.getParameter("operacao"));
     switch (operacao) {
         case 1:
             
                    int codigo = Integer.parseInt(request.getParameter("id"));
                    String Titulo = request.getParameter("titulo");
                    String Genero = request.getParameter("genero");
                    String Descricao = request.getParameter("descricao");
                   
                   
                     e.setCapa(capa);
                    
                   e.setTitulo(Titulo);
                    e.setGenero(Genero);
                    e.setDescricao(Descricao);
                    e.setId(codigo);
                    if (p.Update(e)) {
            response.sendRedirect("sucesso.jsp?resultado=3");
        } else {
            response.sendRedirect("erro.jsp?resultado=1");
        }                    
             break;

    case 2:
                    String autor = (String) session.getAttribute("idAutor");
                     Titulo = request.getParameter("titulo");
                     Genero = request.getParameter("genero");
                     Descricao = request.getParameter("descricao");
                    e.setTitulo(Titulo);
                    e.setAutorid(autor);
                    e.setGenero(Genero);
                    e.setDescricao(Descricao);
                    e.setCapa(capa);
                    if (p.inserirHistoria(e)) {
                        int historiaI = p.obterIdHistoriaPorTituloEAutor(Titulo, autor);
                        // Use ? para iniciar a seção de parâmetros e & para separar os parâmetros.
                        RequestDispatcher u = request.getRequestDispatcher("/inserirCapitulo.jsp?id=" + historiaI);
                        u.forward(request, response);
                    } else {
                        response.sendRedirect("erro.jsp?resultado=3");
                    }
                    break;

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

    
      private boolean isPNGFile(String fileName) {
        return fileName.toLowerCase().endsWith(".png");
    }
}
