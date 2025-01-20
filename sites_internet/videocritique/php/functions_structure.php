<?php

function displayPokedex($pokedex)
{
    foreach ($pokedex as $pokemon) {
        echo "        
        <a class='card' href=pokemon.php?id=" . $pokemon['id_pokemon'] . ">
            <div class='image'>
                <img class='pokemon' src=" . $pokemon['chemin'] . " alt='image'>
            </div>
            <span class='title'>" . $pokemon['nom'] . "</span>
            <span class='price'>" . $pokemon['numero'] . "</span>
        </a>
        ";
    }
}
?>