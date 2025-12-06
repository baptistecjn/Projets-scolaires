<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL); 
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

require_once("./includes/constantes.php");
require_once("./php/functions-DB.php");
require_once("./php/functions_querry.php");
require_once("./static/header.php");
require_once("./static/nav.php");
require_once("./php/functions_structure.php");

$mysqli = connectionDB();

session_start();

if (!isset($_GET['pokemon_id'])) {
    header("Location: index.php");
    exit();
}

$pokemon_id = $_GET['pokemon_id'];
$pokemon_info = getPokemonInfo($mysqli, $pokemon_id);
$type = getPokemonType($mysqli, $pokemon_id);
$image = getPokemonImage($mysqli, $pokemon_id);
$attaques = getPokemonAttaques($mysqli, $pokemon_id);
$evo = getPokemonEvolution( $mysqli, $pokemon_id);


?>
<!DOCTYPE html>
<html lang="fr">
    <head>
        
        <?head_template();?>
    </head>
    <body>
        <?header_template();
        nav_template();
        if (isset($_SESSION['connecte']) && $_SESSION['connecte'] === true) {
            $dresseur_id = $_SESSION['id'];
            $nbVuAttrape = nbVuPoke($mysqli, $pokemon_id, $dresseur_id);
            if(!empty($nbVuAttrape)){
            
                $nbVue = $nbVuAttrape[0]['nbVue'];
                $nbAttrape = $nbVuAttrape[0]['nbAttrape'];
                displayPokemonDresseur($image, $pokemon_info,$type,$attaques,$evo,$nbVue,$nbAttrape);
            }else{
                displayPokemonDresseur($image, $pokemon_info,$type,$attaques,$evo,null,null);
                }
        } else {
            
            displayPokemon($image, $pokemon_info,$type,$attaques,$evo);
            
        }
        closeDB($mysqli);

        ?>

    </body>
</html>