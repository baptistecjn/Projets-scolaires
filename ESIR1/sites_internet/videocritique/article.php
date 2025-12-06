<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL); 
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
require_once("./static/header.php");
require_once("./static/nav.php");
require_once("./static/footer.php");
require_once("./php/functions-DB.php");
require_once("./php/functions_query.php");
require_once("./php/functions_structure.php");

$mysqli = connectionDB();
session_start();

?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <?head_template();?>
</head>
<body>
    <header>
        <?header_template();?>
    </header>
    <?nav_template();?>
    <main>
    <div class="titre-container">
            <div class="article-title">
                Dauntless
            </div>
            <div class="star-rating">
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star"></span>
                <span class="fa fa-star"></span>
                <span class="fa fa-star"></span>
                <span class="fa fa-star"></span>
            </div>
        </div>
        <div class="caracteristique-container">
            <div class="caracterstique">
                Prix : Gratuit
            </div>
            <div class="caracterstique">
                Date de sortie : 15/07/2017
            </div>
            <div class="caracterstique">
                Catégories : MMO/RPG
            </div>
            <div class="caracterstique">
                Supports : PC
            </div>
        </div>
        <div class="synopsis">
            Synopsis : Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.
        </div>
        <div class="article-avis-container">
            <div class="article-container">
                <div class="texte-article">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?</div>
                <img src="images/image.jpg" alt="jaquette">
            </div>

            <div class="liste-avis-container">
                <div class="moyenne-avis">
                    Note moyenne :
                    <div class="star-rating">
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star checked"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                        <span class="fa fa-star"></span>
                    </div>
                </div>
                <div class="avis-container">
                    <div class="avis-star-container">
                        <a href="profil2.html">
                            <div class="pdp-pseudo">
                                <img class="pdp-avis" src="images/siphano.jpg" alt="photo de profil">
                                Siphano
                            </div>
                        </a>
                        <div class="star-rating">
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                        </div>
                    </div>
                    <div class="titre-avis">Sed ut perspiciatis unde omnis</div>
                    <div class="texte-avis">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</div>
                </div>
                <div class="avis-container">
                    <div class="avis-star-container">
                        <a href="profil.html">
                            <div class="pdp-pseudo">
                                <img class="pdp-avis" src="images/siphano.jpg" alt="photo de profil">
                                Siphano
                            </div>
                        </a>
                        <div class="star-rating">
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                        </div>
                    </div>
                    <div class="titre-avis">Sed ut perspiciatis unde omnis</div>
                    <div class="texte-avis">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</div>
                </div>
            </div>
        </div>
    </main>
    <?footer_template();?>
    </body>
</html>
<?closeDB($mysqli);?>