function previewImage(input) {
    var preview = document.getElementById('preview');
    var file = input.files[0];
    var reader = new FileReader();

    reader.onloadend = function () {
        var image = new Image();
        image.src = reader.result;

        // Define a largura e altura desejadas (200x200 pixels)
        var targetSize = 200;

        image.onload = function () {
            var width = image.width;
            var height = image.height;

            // Calcula a proporção para redimensionar a imagem para um quadrado
            var aspectRatio = width / height;
            var newWidth, newHeight;

            if (width > height) {
                newWidth = targetSize;
                newHeight = targetSize / aspectRatio;
            } else {
                newHeight = targetSize;
                newWidth = targetSize * aspectRatio;
            }

            // Define a largura e altura na imagem
            preview.style.width = newWidth + 'px';
            preview.style.height = newHeight + 'px';

            preview.src = reader.result;
            preview.style.display = 'block';
        };
    };

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = '';
        preview.style.display = 'none';
    }
}
