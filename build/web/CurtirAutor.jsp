 
<%@page import="Vo.Usuario"%> 

<%
            Usuario p = (Usuario) request.getAttribute("login");
        %>
<form id="curtirForm" method="post" action="HistoriasServlet?operacao=7" style="text-align: left;">
                    <input type="hidden" name="id" value="<%= p.getId()%>">
                    <!-- Substitua o botão de submit por um ícone de like -->
                    <button type="submit" style="border: none; background: none; padding: 0; ">

                        Curtidas  <i id="iconeCurtir" class="fas fa-thumbs-up" style="text-align: left;"></i>
                       <span id="totalCurtidas"><%= request.getAttribute("total")%></span>
                    </button>

                </form>

            </table>
            <input type="hidden" id="curtirStatus" value="<%= request.getAttribute("curtirStatus")%>">

       <script>
    document.addEventListener('DOMContentLoaded', function () {
        const totalElement = document.getElementById('totalCurtidas');
        const total = parseInt(totalElement.innerText);

        if (!isNaN(total) && total > 1000) {
            const totalFormatted = (total / 1000).toFixed(1) + ' mil';
            totalElement.innerText = totalFormatted;
        }
        if (!isNaN(total) && total > 100000) {
            const totalFormatted = (total / 1000000).toFixed(1) + ' mi';
            totalElement.innerText = totalFormatted;
        }
    });
</script>


