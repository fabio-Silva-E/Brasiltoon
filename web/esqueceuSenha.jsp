<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/EsqueceuSenha.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <title>Recuperação de Senha</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <main>
            <table class="center center-table">
                <tr>
                    <td>
                        <h1>Recuperação de Senha</h1>
                    </td>
                </tr>
                <form action="EmailSenderServlet" method="post">
                    <tr>
                        <td>
                            <label for="email">
                                <span>Digite seu e-mail:</span>
                                <input type="text" id="email" name="to" required>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Enviar" id="button">
                        </td>
                    </tr>
                </form>
                <tr>
                    <td>
                        <a href="index.html" class="btn btn-primary">Voltar</a>
                    </td>
                </tr>
            </table>
        </main>
    </div>
</body>
</html>
