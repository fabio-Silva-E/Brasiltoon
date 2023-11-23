function logout() {
    // Limpar o sessionStorage para invalidar a sessão
    sessionStorage.removeItem('sessionActive');
    // Redirecionar o usuário para a página de login ou qualquer outra página apropriada
    window.location.href = 'index.html';
}
