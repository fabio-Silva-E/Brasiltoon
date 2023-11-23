// Função para validar o formulário e atualizar a cor da borda do campo Confirmarsenha
function validarFormulario() {
  var senha = document.getElementById("senha").value;
  var confirmarSenha = document.getElementById("Confirmarsenha").value;
  var confirmarSenhaInput = document.getElementById("Confirmarsenha");

  // Verifica se as senhas são iguais
  if (senha !== confirmarSenha) {
    confirmarSenhaInput.style.borderColor = "red";
    alert("As senhas não estão iguais.");
    return false;
  } else {
    confirmarSenhaInput.style.borderColor = "blue";
  }

  // Verifica se a senha tem pelo menos 8 caracteres
  if (senha.length < 8) {
    alert("A senha deve ter exatamente 8 numeros.");
    return false;
  }

  return true;
}

// Função para atualizar a cor da borda do campo Confirmarsenha em tempo real
function atualizarCorBorda() {
  var senha = document.getElementById("senha").value;
  var confirmarSenha = document.getElementById("Confirmarsenha").value;
  var confirmarSenhaInput = document.getElementById("Confirmarsenha");

  if (senha === confirmarSenha) {
    confirmarSenhaInput.style.borderColor = "green";
  } else {
    confirmarSenhaInput.style.borderColor = "red";
  }
}

// Adiciona o evento de entrada ao campo Confirmarsenha
document.getElementById("Confirmarsenha").addEventListener("input", atualizarCorBorda);

// Adiciona o evento de submit ao formulário
document.querySelector("form").addEventListener("submit", function(event) {
  // Impede o envio do formulário se a validação falhar
  if (!validarFormulario()) {
    event.preventDefault();
  }
});
 function previewImage(input) {
            var preview = document.getElementById('preview');
            var file = input.files[0];
            var reader = new FileReader();

            reader.onloadend = function () {
                preview.src = reader.result;
                preview.style.display = 'block';
            };

            if (file) {
                reader.readAsDataURL(file);
            } else {
                preview.src = '';
                preview.style.display = 'none';
            }
        }
         function confirmarAlteracao() {
                return confirm("Tem certeza de que deseja realizar a alteração?");
            }
             function confirmarCadastroa() {
                return confirm("Confirmar Cadastro?");
            }