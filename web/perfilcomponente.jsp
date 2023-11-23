<%@page import="Vo.Usuario"%> 
 <link rel="stylesheet" href="./css/perfil.css">
<%
            Usuario p = (Usuario) request.getAttribute("login");
        %>
        <div class="profile-container">
            <div class="border-text2">
                Perfil

            </div>

            <img src="<%=p.getCaminho()%>" alt="Foto de Perfil">

            <table>

                <th>Nome</th>
                </tr>
                <tr>
                    <td><%=p.getNome()%></td>
                </tr>
                <tr>
                    <th>Email</th>
                </tr>
                <tr>
                    <td><%=p.getEmail()%></td>
                </tr>
                <th>telefone</th>
                </tr>
                <tr>
                    <td><%= String.valueOf(p.getTel())%></td>
                </tr>

               

    