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
            <div class="container-search">
                <div class="search">
                    <input placeholder="Search..." type="text">
                    <button type="submit">Go</button>
                </div>
                <div class="select">
                    <select>
                        <option value="1">Toutes catégories</option>
                        <option value="2">RPG</option>
                        <option value="3">MMO</option>
                    </select>
                </div>
            </div>

            <div class="container-picture">
                <a href="article.php"></a>
                    <img class="jaquette" src="images/image.jpg" alt="jaquette">
                </a>
                <a href="article.php">
                    <img class="jaquette" src="images/image.jpg" alt="jaquette">
                </a>
                <a href="article.php">
                    <img class="jaquette" src="images/image.jpg" alt="jaquette">
                </a>
                <a href="article.php">
                    <img class="jaquette" src="images/image.jpg" alt="jaquette">
                </a>
                <a href="article.php">
                    <img class="jaquette" src="images/image.jpg" alt="jaquette">
                </a>
            </div>

            <div class="centered">
                <div class="pagination">
                    <a href="#">&laquo;</a>
                    <a href="#">1</a>
                    <a href="#" class="active">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">6</a>
                    <a href="#">&raquo;</a>
                </div>
            </div>
        </main>
        <?footer_template();?>
    </body>
</html>
<?closeDB($mysqli);?>