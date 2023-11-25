/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Dao.BibliotecaDao;
import Dao.HistoriasDao;
import Dao.UsuarioDao;
import Vo.Historias;
import Vo.MinhaBiblioteca;
import Vo.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fabio
 */
@WebServlet(name = "HomeController", urlPatterns = {"/"})
public class HomeController extends HttpServlet {

    public class UpdateSuccessHandler {

        public void handle(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
            // Lógica para lidar com o sucesso na atualização
            response.sendRedirect("sucesso.jsp?resultado=6&id=" + id);
        }
    }

    public class UpdateErrorHandler {

        public void handle(HttpServletRequest request, HttpServletResponse response, String id) throws IOException {
            // Lógica para lidar com erros na atualização
            response.sendRedirect("erro.jsp?resultado=6&id=" + id);
            System.err.println("Erro no update para o ID: " + id);
        }
    }

    public class NumberFormatErrorHandler {

        public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
            // Lógica para lidar com erros na conversão do número
            response.sendRedirect("erro.jsp?resultado=6");
            System.err.println("Erro na conversão de ID no update.");
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String idAutor = (String) session.getAttribute("idAutor");

            if (session.getAttribute("idAutor") == null) {
                response.sendRedirect("login.jsp"); // Redireciona para a página de Usuario
                return; // Encerra o processamento do servlet
            }
            int operacao = Integer.parseInt(request.getParameter("operacao"));
            MinhaBiblioteca b = new MinhaBiblioteca();
            BibliotecaDao l = new BibliotecaDao();
            Usuario o = new Usuario();
            Historias e = new Historias();
            HistoriasDao p = new HistoriasDao();

            UsuarioDao L = new UsuarioDao();
            switch (operacao) {
                case 1:
                    String id = (String) session.getAttribute("idAutor");

                    // Armazenar o ID na sessão como atributo
                    //session.setAttribute( "idAutor",id);
                    request.setAttribute("login", L.Meucadastro(id));
                    RequestDispatcher rs = request.getRequestDispatcher("/MeuPerfil.jsp");
                    rs.forward(request, response);
                    break;
                case 2:

                    String usuario = (String) session.getAttribute("idAutor");
                    int historia = Integer.parseInt(request.getParameter("historia"));

                    b.setIdUsuario(usuario);
                    b.setIdHistoria(historia);

                    boolean favoritada = l.historiaJaFavoritada(usuario, historia);

                    if (favoritada) {
                        // História já está favoritada, retorne uma resposta indicando isso
                        response.getWriter().write("favoritada");
                    } else {
                        // Favoritar a história
                        if (l.Favoritar(b)) {
                            // História favoritada com sucesso, retorne uma resposta de sucesso
                            response.getWriter().write("sucesso");
                        } else {
                            // Ocorreu um erro ao favoritar a história, retorne uma resposta de erro
                            response.getWriter().write("erro");
                        }
                    }

                    break;
                case 3:
                    String genero = request.getParameter("genero");
                    usuario = (String) session.getAttribute("idAutor");
                    String idus = request.getParameter("letrasIniciais");

                    // Supondo que p.buscarHistoriaPorTitulo(genero,usuario, letrasIniciais) retorna uma lista de objetos Historias
                    List<MinhaBiblioteca> MinhaBiblioteca = l.buscarHistoriaPorTitulo(genero, usuario, idus);

                    // Converter a lista de objetos Historias em uma string JSON
                    String json = new Gson().toJson(MinhaBiblioteca);

                    // Configurar a resposta
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json);

                    break;
                case 4:
                    String email = request.getParameter("email");
                    String senha = request.getParameter("senha");

                    o = L.localizarRegistroCadastro(senha, email);

                    if (o != null) {
                        // Usuário encontrado, encaminhe para a página de alteração de cadastro
                        request.setAttribute("login", o);
                        RequestDispatcher r = request.getRequestDispatcher("/alteracaoCadastro.jsp");
                        r.forward(request, response);
                    } else {
                        // Usuário não encontrado, redirecione para uma página de erro ou exiba uma mensagem
                        response.sendRedirect("erro.jsp?resultado=7");
                        // Ou envie uma mensagem de erro para a página atual
                        // response.getWriter().println("Email ou senha incorretos");
                    }

                    break;
                case 5:
                    int idH = Integer.parseInt(request.getParameter("id"));
                    usuario = (String) session.getAttribute("idAutor");
                    l.excluirFavorito(idH);
                    RequestDispatcher r = request.getRequestDispatcher("/MinhaBiblioteca.jsp");
                    r.forward(request, response);
                    break;
                case 6: // Pesquisar histórias por título
                    genero = request.getParameter("genero");
                    String letrasIniciais = request.getParameter("letrasIniciais");
                    idAutor = (String) request.getSession().getAttribute("idAutor"); // Voc
                    // Supondo que p.buscarHistoriaPorTitulo(genero,usuario, letrasIniciais) retorna uma lista de objetos Historias
                    List<Historias> Historias = l.buscarMinhasHistoriaPorTitulo(genero, letrasIniciais, idAutor);

                    // Converter a lista de objetos Historias em uma string JSON
                    String json2 = new Gson().toJson(Historias);

                    // Configurar a resposta
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(json2);

                    break;
                case 7:
                    String autor = (String) session.getAttribute("idAutor");
                    String Titulo = request.getParameter("titulo");
                    String Genero = request.getParameter("genero");

                    e.setTitulo(Titulo);
                    e.setAutorid(autor);
                    e.setGenero(Genero);

                    if (p.inserirHistoria(e)) {
                        int historiaId = p.obterIdHistoriaPorTituloEAutor(Titulo, autor);
                        // Use ? para iniciar a seção de parâmetros e & para separar os parâmetros.
                        RequestDispatcher u = request.getRequestDispatcher("/inserirCapitulo.jsp?id=" + historiaId);
                        u.forward(request, response);
                    } else {
                        response.sendRedirect("erro.jsp?resultado=3");
                    }
                    break;
                case 8:
                    String token = request.getParameter("token");
                    String novaSenha = request.getParameter("novaSenha");

                    UsuarioDao dao = new UsuarioDao();
                    boolean sucesso = dao.redefinirSenhaComToken(token, novaSenha);

                    if (sucesso) {
                        // Redirecionar para a página de sucesso após a redefinição de senha
                        response.sendRedirect("sucesso.jsp?resultado=5");
                    } else {
                        response.sendRedirect("erro.jsp?resultado=8");
                    }

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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
