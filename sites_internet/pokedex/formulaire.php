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

$sql = "SELECT id_pokemon, nom FROM pokemon";
$result = $mysqli->query($sql);
?>
<head>
        
    <?head_template();?>
</head>
<body>
    <?header_template();
    nav_template();?>
    <div class="container">
        <div style= "margin-top:20px;margin-bottom:20px;">
            <div class="formulaire">
                <div class="card_header">Mise à jour du Pokedex</div>
                    <form action="modificateur.php" method="post">
                        <div>
                            <label for="pokemon" class="form-label">Choisir le Pokémon :</label>
                            <select name="pokemon" id="pokemon" class="choix_pok">
                                <?php while ($row = $result->fetch_assoc()): ?>
                                    <option value="<?php echo $row['id_pokemon']; ?>"><?php echo $row['nom']; ?></option>
                                <?php endwhile; ?>
                            </select>
                        </div>
                        <div>
                            <label for="nbVue" class="form_label">Modifier le nombre de vues de ce pokémon:</label>
                            <input type="number" name="nbVue" id="nbVue" class="choix_pok">
                        </div>
                        <div>
                            <label for="nbAttrape" class="form-label">Modifier le nombre de captures de ce pokémon:</label>
                            <input type="number" name="nbAttrape" id="nbAttrape" class="choix_pok">
                        </div>
                        <button type="submit" class="envoyer">Envoyer</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>