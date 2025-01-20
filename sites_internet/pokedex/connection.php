<?php
//affichage des erreurs côté PHP et côté MYSQLI
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL); 
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

//Import du site - A completer

require_once("./includes/constantes.php");
require_once("./static/nav.php");
require_once("./php/functions-DB.php");
require_once("./php/functions_querry.php");
require_once("./static/header.php");
require_once("./php/functions_structure.php");


$mysqli = connectionDB();


closeDB($mysqli);
?>
<!DOCTYPE html>
<html lang="fr">
    <head>

        <?
        head_template();
        ?>

    </head>
    <body>

        <?
        header_template();
        nav_template();
        
        ;
       ?>
        <h2>Connexion</h2>
        <form action="./login.php" method="post">
            <label for="login">Login:</label><br>
            <input type="text" id="login" name="login"><br>
            <label for="password">Mot de passe:</label><br>
            <input type="password" id="password" name="password"><br><br>
            <input type="submit" value="Se connecter">
        </form>

    </body>
</html>

