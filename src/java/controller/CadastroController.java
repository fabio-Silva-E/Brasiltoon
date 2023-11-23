/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Dao.UsuarioDao;
import Vo.Usuario;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author fabio
 */
@WebServlet("/upload")
@MultipartConfig
public class CadastroController extends HttpServlet {

    private static final String UPLOAD_DIR = "E:\\5º_semestre\\programação_web\\Brasiltoon\\web\\perfil";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contentType = request.getContentType();

        // Este é um upload de arquivo, trate-o como tal
        Usuario e = new Usuario();
        UsuarioDao p = new UsuarioDao();
        String nomeArquivo = null;
        String caminhoCompleto = null;
        String caminho = "perfil/fundo.jpg";
        String Caminho = null;

        for (Part part : request.getParts()) {
            nomeArquivo = extractFileName(part);
            if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
                caminhoCompleto = UPLOAD_DIR + File.separator + nomeArquivo;
                caminho = "perfil/" + nomeArquivo;
                Caminho = "perfil/" + nomeArquivo;
                part.write(caminhoCompleto);
            }
        }

        int operacao = Integer.parseInt(request.getParameter("operacao"));

        switch (operacao) {
            case 1: // foto
                String nome = request.getParameter("nome");
                String TelString = request.getParameter("telefone");
                String cpfs = request.getParameter("cpf");
                String emails = request.getParameter("email");
                String senhas = request.getParameter("senha");

                e.setCaminho(caminho);
                e.setNome(nome);

                if (TelString != null && !TelString.isEmpty()) {
                    try {
                        // Tentar converter a string para Long
                        Long Tel = Long.parseLong(TelString);
                        // Configurar o valor diretamente, sem converter para int
                        e.setTel(Tel);
                    } catch (NumberFormatException ex) {
                        // Lidar com a situação em que a string não é um número válido
                        e.setTel(0);  // ou outro valor padrão, dependendo dos requisitos
                    }
                } else {
                    // Lide com a situação em que a string de telefone está vazia
                    e.setTel(0);  // ou outro valor padrão, dependendo dos requisitos
                }

                e.setEmail(emails);
                e.setSenha(senhas);

                if (p.Cadastrar(e)) {
                    // Cadastro bem-sucedido, redirecione para a página de sucesso passando o ID
                    response.sendRedirect("sucesso.jsp?resultado=1");
                } else {
                    // Falha no cadastro
                    response.sendRedirect("erro.jsp?resultado=0");
                }
                break;
            case 2:
                HttpSession session = request.getSession();
                String id = request.getParameter("id");

                // Armazenar o ID na sessão como atributo
                session.setAttribute("idAutor", id);

                // Aqui, você deve primeiro recuperar os dados existentes com base no ID
                // e, em seguida, atualizar apenas os campos necessários antes de chamar o método Cadastrar novamente
                UsuarioDao loginDao = new UsuarioDao();
                // Supondo que você já tenha obtido o ID do usuário e o novo caminho da foto
                String idUsuario = id;
                String novoCaminhoFoto = caminho.trim().isEmpty() ? null : Caminho;

                // Verifique se o caminho é vazio ou nulo e mantenha o original se necessário
                if (novoCaminhoFoto == null) {
                    // Recupere o caminho original do usuário com base no ID
                    // Exemplo: loginDao.obterCaminhoFoto(idUsuario);
                    novoCaminhoFoto = loginDao.obterCaminhoFoto(idUsuario);
                }

                if (loginDao.atualizarFotoPerfil(idUsuario, novoCaminhoFoto)) {
                    // Atualização bem-sucedida
                    response.sendRedirect("HomeController?operacao=1&id=" + idUsuario);
                } else {
                    // Falha na atualização
                    response.sendRedirect("HomeController?operacao=1&id=" + idUsuario);
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
}
