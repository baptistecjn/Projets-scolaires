<?php
require_once('functions-DB.php');
require_once("php/functions_structure.php");
require_once("./login.php");

function getPokedex($mysqli){
    $sql_input = 'SELECT pokemon.id_pokemon,description,numero,nom,chemin FROM pokemon JOIN image ON image.id_pokemon = pokemon.id_pokemon WHERE chemin LIKE "images/pokemon/%"';
    $result = readDB($mysqli,$sql_input);
    return $result;
    
}

function getPokemonInfo($mysqli,$pokemon_id){
    $sql = "SELECT * FROM pokemon WHERE id_pokemon = ?";
    $stmt= $mysqli->prepare($sql) ; 
    $stmt->bind_param("i", $pokemon_id);  
    $stmt->execute(); 
    $result = $stmt->get_result(); 
    if (!$row = $result->fetch_assoc()) { 
        echo 'Erreurr: impossible de trouver un pokemon d\'ID : ' . $pokemon_id;
        exit();    
    }
    return $row;
}
function getPokemonType($mysqli, $pokemon_id){
    $sql = "SELECT t.libelle FROM esttype et JOIN type t ON et.id_type = t.id_type WHERE et.id_pokemon = ?";
    $stmt= $mysqli->prepare($sql);
    $stmt->bind_param("i", $pokemon_id);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows === 0) {
        echo 'pad de type ' . $pokemon_id;
        exit();
    }

    $types = array();

    while ($row = $result->fetch_assoc()) {
        $types[] = $row['libelle'];
    }

    return $types;
}

function getPokemonImage($mysqli,$pokemon_id){
    $sql = "SELECT chemin FROM image WHERE id_pokemon = ?";
    $stmt= $mysqli->prepare($sql) ; 
    $stmt->bind_param("i", $pokemon_id);
    $stmt->execute(); 
    $result = $stmt->get_result(); 
    if (!$row = $result->fetch_assoc()) { 
        echo 'pas d image ' . $pokemon_id;
        exit();    
    }
    return $row;
}
function getPokemonAttaques($mysqli, $pokemon_id){
    $sql = "SELECT c.id_capacite, c.libelle_capacite, c.pp_capacite, c.precision_capacite, c.puissance_capacite, t.libelle AS type 
            FROM capacite c 
            JOIN lance l ON c.id_capacite = l.id_capacite 
            JOIN type t ON c.id_type = t.id_type
            WHERE l.id_pokemon = ?";
    $stmt = $mysqli->prepare($sql);
    $stmt->bind_param("i", $pokemon_id);
    $stmt->execute();
    $result = $stmt->get_result();
    $attaques = array();
    while ($row = $result->fetch_assoc()) {
        $attaques[] = $row;
    }
    $stmt->close();
    return $attaques;
}

function getPokemonEvolution($mysqli, $pokemon_id) {
    $sql = 'SELECT p.nom, e.niveau,
    (SELECT i.chemin FROM image i WHERE i.id_pokemon = p.id_pokemon LIMIT 1) AS chemin
            FROM evolue e
            INNER JOIN pokemon p ON e.id_pokemon_evolue = p.id_pokemon
            WHERE e.id_pokemon_base = ?';

    $stmt = $mysqli->prepare($sql);
    $stmt->bind_param("i", $pokemon_id);
    
    $stmt->execute();
    
    $result = $stmt->get_result();

    $evolution = array();

    while ($row = $result->fetch_assoc()) {
        $evolution[] = $row;
    }
    return $evolution;
}
    

function dresseurExist($mysqli, $login, $mdp) {
    $sql = "SELECT * FROM dresseur WHERE nom_dresseur = '$login' AND mdp_dresseur = '$mdp'";
    $result = readDB($mysqli, $sql);
    return $result;
}

function nbVuPoke($mysqli, $pokemon_id,$dresseur_id){
    $sql = "SELECT nbVue, nbAttrape FROM pokedex WHERE id_pokemon = $pokemon_id AND id_dresseur = $dresseur_id";
    $result = readDB($mysqli, $sql);
    return $result;
}

function getPokedexDresseur($mysqli, $dresseur_id) {
    $sql = "SELECT p.id_pokemon, p.description, p.numero, p.nom, i.chemin AS chemin_image, pd.nbVue, pd.nbAttrape 
            FROM pokemon p
            LEFT JOIN pokedex pd ON p.id_pokemon = pd.id_pokemon AND pd.id_dresseur = $dresseur_id
            JOIN image i ON i.id_pokemon = p.id_pokemon
            WHERE i.chemin LIKE 'images/pokemon/%'";
    $result = readDB($mysqli, $sql);
    return $result;
}


?>
