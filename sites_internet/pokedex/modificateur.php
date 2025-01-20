<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

require_once("./php/functions_querry.php");
require_once("php/functions-DB.php");

session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['pokemon']) && isset($_POST['nbVue']) && isset($_POST['nbAttrape'])) {
        
        $pokemonId = $_POST['pokemon'];
        $nbVue = $_POST['nbVue'];
        $nbAttrape = $_POST['nbAttrape'];
        $dresseur_id = $_SESSION['id'];

        $verif = "SELECT * FROM pokedex WHERE id_pokemon = $pokemonId AND id_dresseur = $dresseur_id";
        $veriffin = $mysqli->query($verif);

        if($veriffin->num_rows > 0){
            $sql = "UPDATE pokedex SET nbVue = $nbVue, nbAttrape = $nbAttrape WHERE id_pokemon = $pokemonId AND id_dresseur=$dresseur_id";


        }else{
            $sql = "INSERT INTO pokedex (id_pokemon, id_dresseur, nbVue, nbAttrape) VALUES ($pokemonId, $dresseur_id, $nbVue, $nbAttrape)";

        }
        
        writeDB($mysqli, $sql);
        
        closeDB($mysqli);
        
    
    header("Location: index.php");
    exit();
    }
}
?>
