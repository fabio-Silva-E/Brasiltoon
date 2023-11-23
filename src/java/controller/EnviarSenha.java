package com.example.servlets;

import Dao.UsuarioDao;
import Vo.Usuario;
import java.io.IOException;
import static java.lang.ProcessBuilder.Redirect.to;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmailSenderServlet")
public class EnviarSenha extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDao l = new UsuarioDao();
        Usuario o = new Usuario();
        // Configurações para o servidor de e-mail (substitua pelos seus dados)
        final String username = "fs2894488@gmail.com";
        final String password = "hjfn iacs biss qqxg";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Autenticação
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String toEmail = request.getParameter("to");
        Usuario user = l.localizarsenha(toEmail);

        if (user != null) {
            String mensagem = "Esta e sua senha não compartilhe com ninguem: " + user.getSenha();

            try {
                // Criação da mensagem
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(request.getParameter("to")));
                message.setSubject("Sua senha para acesso ao Brasiltoon");
                message.setText(mensagem);

                // Envio da mensagem
                Transport.send(message);

                // Redirecionar para a página de sucesso
                response.sendRedirect("sucesso.jsp?resultado=4");
            } catch (MessagingException e) {
                throw new ServletException(e);
            }
        } else {
            // Trate o caso em que o usuário não foi encontrado
            response.sendRedirect("erro.jsp?resultado=4");
        }

    }
}
