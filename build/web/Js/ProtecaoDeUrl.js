// Verifica se a URL foi alterada
window.onpopstate = function(event) {
    redirectToLoginPage();
};

function redirectToLoginPage() {
    // Redireciona o usuário para a página de login
    window.location.href = "login.jsp"; // Substitua "login.jsp" pela URL da sua página de login
}



