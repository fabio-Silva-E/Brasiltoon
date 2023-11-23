<%@ page import="java.util.List" %>
<%@ page import="Vo.Curtidas" %>

<!DOCTYPE html>
<html>
    <head>
        <style>
            .slider {
                display: flex;
                justify-content: center;
                align-items: center;

            }

            .slider .slide-container {
                position: relative;
                width: 100px;
                height: 100px;
                margin-right: 10px;
            }

            .slider img, .slider .like-icon {
                width: 100%;
                height: 100%;
                object-fit: contain;
                position: absolute;
                left: 0;
                bottom: 0;
            }

            .like-icon {
                font-size: 12px;
                color: blueviolet;
                text-align: center;
                top: 87px;
                bottom: 0;
            }
            .like-count {
                font-size: 12px;
                color: windowframe;
                 text-align: center;
                position: absolute; /* ou position: fixed; dependendo do seu layout */
                top: 83px;
                bottom: 0;
                left:  -175px;
                z-index: 2001;
                /* bottom: 0;  Remova ou ajuste conforme necessário */
            }
            .center {
                color: #fff;
            }
              @media only screen and (max-width: 600px) {
        .slider .slide-container {
            max-width: 100%; /* Ocupa a largura total da tela */
        }

        .like-icon,
        .like-count {
            font-size: 12px; /* Tamanhos ainda menores para telas muito estreitas */
        }
    }
        </style>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <!-- Slick Carousel CSS e JS -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css"/>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <a class="center">Top 5 Autores mais curtidos</a>
        <div class="slider"></div>

        <script>
            $(document).ready(function () {
                $.ajax({
                    url: 'CarrocelServlet?operacao=1',
                    type: 'GET',
                    dataType: 'json',
                    success: function (data) {
                        var slider = $('.slider');
                        $.each(data, function (index, item) {
                            var slideContainer = $('<div>').addClass('slide-container');

                            var img = $('<img>').attr({
                                'src': item.id_autor,
                                'alt': 'Imagem ' + item.id_autor
                            });

                            var icon = $('<i>').attr({
                                'id': 'iconeCurtir',
                                'class': 'fas fa-thumbs-up like-icon',
                            });
                            var likeCount = item.curtidas;

                            if (likeCount > 1000) {
                                // Reduz o número de curtidas substituindo os três últimos dígitos por "mil"
                                var formattedCount = Math.floor(likeCount / 1000) + ' mil';
                                likeCount = formattedCount;
                            }
                                if (likeCount > 100000) {
                                // Reduz o número de curtidas substituindo os três últimos dígitos por "mil"
                                var formattedCount = Math.floor(likeCount / 1000000) + ' mi';
                                likeCount = formattedCount;
                            }

                            var like = $('<a>').attr({
                                'class': 'like-count',
                            }).text(likeCount);

                            slideContainer.append(img, icon, like);
                            slider.append(slideContainer);
                        });

                        slider.slick({
                            autoplay: true,
                            autoplaySpeed: 2000,
                            dots: true,
                            infinite: true,
                            speed: 500,
                            slidesToShow: 1,
                            slidesToScroll: 1
                        });


                    }
                });
            });
        </script>

    </body>
</html>
