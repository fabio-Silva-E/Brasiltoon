<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-touchspin/4.3.0/jquery.bootstrap-touchspin.min.js"></script>
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="./css/formPesquisa.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<form id="searchForm">
    <div class="mb-4">
        <div class="input-group">
            <div class="form-group" style="flex: 1; display: flex; flex-direction: column; position: relative;">
                <div class="search-container">
                    <input type="text" id="letrasIniciais" name="letrasIniciais" class="form-control" autofocus>
                    <i class="fa fa-search" aria-hidden="true" style="position: absolute; top: 50%; right: 10px; transform: translateY(-50%); font-size: 18px; color: #aaa;"></i>
                </div>
            </div>

            <div class="form-group" style="flex: 1; display: flex; flex-direction: column;">
                <select name="genero" id="genero" class="form-select">
                    <option value="">Todos os gêneros</option>
                    <option value="Terror">Terror</option>
                    <option value="Suspense">Suspense</option>
                    <option value="Drama">Drama</option>
                    <option value="Romance">Romance</option>
                    <option value="Sci-Fi">Sci-fi</option>
                     <option value="Ação">Ação</option>
                </select>
                <input type="hidden" name="operacao" value="5">
            </div>
        </div>
    </div>
</form>
