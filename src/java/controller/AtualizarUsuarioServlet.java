/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Connection.Conexao;
import Dao.UsuarioDao;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Vo.Usuario;
import java.sql.SQLException;

@WebServlet(name = "AtualizarUsuarioServlet", urlPatterns = {"/AtualizarUsuarioServlet"})
public class AtualizarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Recupere os dados do formulário
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            long telefone = Long.parseLong(request.getParameter("telefone"));

            // Crie um objeto de conexão (você deve implementar essa parte)
            Connection conn = new Conexao().conectar();// Substitua YourConnectionManager com sua própria lógica

            // Atualize os dados do usuário usando o DAO
            boolean sucesso = UsuarioDao.atualizarUsuario(conn, id, nome, email, senha, telefone);

            conn.close(); // Não se esqueça de fechar a conexão

            if (sucesso) {
                response.sendRedirect("sucesso.jsp?resultado=6" );
            } else {
                response.sendRedirect("erro.jsp?resultado=6" );
            }
        } catch (NumberFormatException | SQLException e) {
            response.sendRedirect("erro.jsp?resultado=6");
            e.printStackTrace();
        }
    }
}
