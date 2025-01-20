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

session_start();

$mysqli = connectionDB();

$sql_input1 = "SELECT numero,nom FROM pokemon";
$tab = readDB($mysqli,$sql_input1);
$tab2 = getPokedex($mysqli);



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
        
        if (isset($_SESSION['connecte']) && $_SESSION['connecte'] === true) {
            $tabDresseur = getPokedexDresseur($mysqli, $_SESSION['id']);

            displayPokedexDresseur($tabDresseur);
        } else {
            
            displayPokedex($tab2);
            
        }


       ?>

    </body>
</html>

<?closeDB($mysqli);
?>