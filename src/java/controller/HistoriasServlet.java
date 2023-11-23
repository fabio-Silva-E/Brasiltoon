/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Dao.BibliotecaDao;
import Dao.HistoriasDao;
import Dao.LikesDao;
import Dao.UsuarioDao;
import Vo.Capitulos;
import Vo.Curtidas;
import Vo.Historias;
import Vo.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabio
 */
public class HistoriasServlet extends HttpServlet {
    BibliotecaDao b = new BibliotecaDao();
    HistoriasDao p = new HistoriasDao();
     LikesDao d= new LikesDao();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            String idAutor = (String) session.getAttribute("idAutor");

            /*if (session.getAttribute("idAutor") == null) {
    response.sendRedirect("Usuario.jsp"); // Redireciona para a página de Usuario
    return; // Encerra o processamento do servlet
}*/
// Se o usuário estiver autenticado, continue com as operações normais
            int operacao = Integer.parseInt(request.getParameter("operacao"));
            Historias e = new Historias();
           
            UsuarioDao l = new UsuarioDao();
            Usuario o = new Usuario();
            Curtidas c = new Curtidas();
            switch (operacao) {
                case 1: // lista
                      int idh = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("Historias", p.Historia(idh));
                    RequestDispatcher rh = request.getRequestDispatcher("/visualizarminhaspublicacoes.jsp");
                    rh.forward(request, response);
                    break;
                case 2:
                    String id = request.getParameter("id");
                    request.setAttribute("login", l.CadastroPublico(id));
                    RequestDispatcher rs = request.getRequestDispatcher("/perfilPublico.jsp");
                    rs.forward(request, response);

                    break;
                case 3:
                    String usuario = (String) session.getAttribute("idAutor");
                    id = request.getParameter("id");

                    boolean Curtido = d.AutorJaCurtido(id, usuario);

                    if (Curtido) {
                        request.setAttribute("login", l.CadastroPublico(id));
                        request.setAttribute("total", d.CurtidasparaAutor(id));
                        request.setAttribute("curtirStatus", "curtir"); // Adiciona a resposta ao request
                    } else {
                        request.setAttribute("login", l.CadastroPublico(id));
                        request.setAttribute("total", d.CurtidasparaAutor(id));
                        request.setAttribute("curtirStatus", "descurtir"); // Adiciona a resposta ao request
                    }

                    rs = request.getRequestDispatcher("/perfil.jsp");
                    rs.forward(request, response);
                    break;
                case 4:
                     usuario = (String) session.getAttribute("idAutor");
                    idh = Integer.parseInt(request.getParameter("id"));
                     String capa =request.getParameter("idhc");
                    Curtido = d.HistoriaJaCurtida(idh, usuario);

                    if (Curtido) {
                        request.setAttribute("Historias", p.Historia(idh));
                        request.setAttribute("total", d.CurtidasparaHistoria(idh));
                        request.setAttribute("curtirStatus", "curtir"); // Adiciona a resposta ao request
                    } else {
                        request.setAttribute("Historias", p.Historia(idh));
                        request.setAttribute("total", d.CurtidasparaHistoria(idh));
                        request.setAttribute("curtirStatus", "descurtir"); // Adiciona a resposta ao request
                    }

                      request.setAttribute("capa", capa);
                     rh = request.getRequestDispatcher("/visualizar.jsp");
                    rh.forward(request, response);
                    break;
                case 5: // Pesquisar por título
                    String genero = request.getParameter("genero");
                    String letrasIniciais = request.getParameter("letrasIniciais");

                    // Supondo que p.buscarHistoriaPorTitulo2(genero, letrasIniciais) retorna uma lista de objetos Historias
                    List<Historias> historias = p.buscarHistoriaPorTitulo2(genero, letrasIniciais);

                    // Converter a lista de objetos Historias em uma string JSON
                    String json = new Gson().toJson(historias);

                    // Configurar a resposta
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);

                    break;
                case 6:
                    idh = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("Historias", p.Historia(idh));
                    RequestDispatcher r = request.getRequestDispatcher("/visualizarPublico.jsp");
                    r.forward(request, response);
                    break;
                case 7:
                    usuario = (String) session.getAttribute("idAutor");
                    String autor = request.getParameter("id");

                    c.setId_usuario(usuario);
                    c.setId_autor(autor);

                    Curtido = d.AutorJaCurtido(autor, usuario);

                    if (Curtido) {
                     
                        d.DescurtirAutor(autor, usuario);
                        response.getWriter().write("descurtir");

                    } else {
                        // Favoritar a história
                        if (d.CurtirAutor(c)) {
                            // curtido com sucesso, retorne uma resposta de sucesso
                            response.getWriter().write("curtir");
                        } else {
                            // Ocorreu ao curtir, retorne uma resposta de erro
                            response.getWriter().write("erro");
                        }
                    }
                    break;
                case 8:
                      usuario = (String) session.getAttribute("idAutor");
                    id = request.getParameter("id");
                    Curtido = d.AutorJaCurtido(id, usuario);

                    if (Curtido) {
                        request.setAttribute("login", l.CadastroPublico(id));
                        request.setAttribute("total", d.CurtidasparaAutor(id));
                        request.setAttribute("curtirStatus", "curtir"); // Adiciona a resposta ao request
                    } else {
                        request.setAttribute("login", l.CadastroPublico(id));
                        request.setAttribute("total", d.CurtidasparaAutor(id));
                        request.setAttribute("curtirStatus", "descurtir"); // Adiciona a resposta ao request
                    }
                    rs = request.getRequestDispatcher("/perfilfavoritos.jsp");
                    rs.forward(request, response);
                    break;
                     case 9:
                    usuario = (String) session.getAttribute("idAutor");
                    idh = Integer.parseInt(request.getParameter("id"));
                    Curtido = d.HistoriaJaCurtida(idh, usuario);
                    if (Curtido) {
                        request.setAttribute("Historias", p.Historia(idh));
                        request.setAttribute("total", d.CurtidasparaHistoria(idh));
                        request.setAttribute("curtirStatus", "curtir"); // Adiciona a resposta ao request
                    } else {
                        request.setAttribute("Historias", p.Historia(idh));
                        request.setAttribute("total", d.CurtidasparaHistoria(idh));
                        request.setAttribute("curtirStatus", "descurtir"); // Adiciona a resposta ao request
                    }
                    r = request.getRequestDispatcher("/visualizarfavoritos.jsp");
                    r.forward(request, response);
                    break;
                     case 10:
                     usuario = (String) session.getAttribute("idAutor");
                    idh = Integer.parseInt(request.getParameter("id"));
                    c.setId_usuario(usuario);
                    c.setId_historia(idh);
                    Curtido = d.HistoriaJaCurtida(idh, usuario);
                    if (Curtido) {                    
                       d.DescurtirHistoria(idh, usuario);
                        response.getWriter().write("descurtir");
                    } else {
                        // Favoritar a história
                        if (d.CurtirHistoria(c)) {
                            // curtido com sucesso, retorne uma resposta de sucesso
                            response.getWriter().write("curtir");
                        } else {
                            // Ocorreu ao curtir, retorne uma resposta de erro
                            response.getWriter().write("erro");
                        }
                    }
                         break;
                     case 11:
                           idh = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("historia", p.localizarHistoria(idh));
                     r = request.getRequestDispatcher("/atualizarHistoria.jsp");
                    r.forward(request, response); 
                         break;
                     case 12:
                          idh = Integer.parseInt(request.getParameter("id"));
                              b.excluirHistoria(idh);
                     r = request.getRequestDispatcher("/minhasPublicacoes.jsp");
                    r.forward(request, response); 
                         break;
                         
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
