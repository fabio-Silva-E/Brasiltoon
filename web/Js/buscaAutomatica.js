const letrasIniciaisInput = document.getElementById('letrasIniciais');
const generoSelect = document.getElementById('genero');
const savedLetrasIniciais = localStorage.getItem('letrasIniciais');
const savedGenero = localStorage.getItem('genero');
const searchForm = document.getElementById('searchForm'); // Substitua 'searchForm' pelo ID correto do seu formulário de pesquisa

if (savedLetrasIniciais) {
    letrasIniciaisInput.value = savedLetrasIniciais;
}

if (savedGenero) {
    generoSelect.value = savedGenero;
}

// Adicione um ouvinte de eventos para os campos
letrasIniciaisInput.addEventListener('keyup', function() {
    localStorage.setItem('letrasIniciais', letrasIniciaisInput.value);
});

generoSelect.addEventListener('change', function() {
    localStorage.setItem('genero', generoSelect.value);
});

// Envie automaticamente o formulário de pesquisa quando o usuário digitar algo
letrasIniciaisInput.addEventListener('keyup', function() {
    searchForm.submit();
});
generoSelect.addEventListener('change', function() {
    searchForm.submit();
});
// Mantenha o campo "letrasIniciais" focado ao recarregar a página
window.addEventListener('load', function() {
    letrasIniciaisInput.focus();
});