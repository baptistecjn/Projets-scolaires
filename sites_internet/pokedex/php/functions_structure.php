<?php
require_once("php/functions_querry.php");



function displayPokedex($pokedex) {
    echo "<div class='bienvenu'> Auncun dresseur n'est connecté.</div>";

    foreach ($pokedex as $pokemon) { 
        
        echo '<div class="card">';
        echo '<div class="image"><a href="./pokemon.php?pokemon_id=' . $pokemon['id_pokemon'] . '"><img src="' . $pokemon['chemin'] . '" /></a></div>';
        echo '<span class="title">' . $pokemon['nom'] . '</span>';
        echo '<span class="price">' . $pokemon['id_pokemon'] . '</span>';
        echo '</div>';
    }
}

function displayPokedexDresseur($pokedex_dresseur){
    echo "<div class='bienvenu'>Bienvenu " . $_SESSION['nom'] . " vous pouvez consulter votre pokedex !</div>";

    foreach ($pokedex_dresseur as $pokemon) { 
        
        echo '<div class="card">';       

        if(empty($pokemon['nbVue'])){
            echo '<div class="imageflou"><a href="./pokemon.php?pokemon_id=' . $pokemon['id_pokemon'] . '"><img src="' . $pokemon['chemin_image'] . '" /></a></div>';

        }else{
            echo '<div class="image"><a href="./pokemon.php?pokemon_id=' . $pokemon['id_pokemon'] . '"><img src="' . $pokemon['chemin_image'] . '" /></a></div>';
        }

        echo '<span class="title">' . $pokemon['nom'] .'</span>';
        echo '<span class="price">#' . $pokemon['id_pokemon'] . '</span>';
        if(!empty($pokemon['nbVue'])){
            echo '<span class = "nbv">nombre de vues :'.$pokemon['nbVue'].'</span>';
            if($pokemon['nbAttrape'] == '1'){
                echo '<span class="attrape">✅</span>';
            }else{
                echo '<span class="attrape">❌</span>';

            }
        }else{
            echo '<span class = "nbv">Ce pokemon n\' pas encore été vu.</span>';

            echo '<span class="attrape">❌</span>';
        }
        echo '</div>';
    }
}
function displayPokemon($image, $pokemon_info, $type,$attaques,$evolution){
    echo '<div class="nom">' . $pokemon_info['nom'] . '</div>
    <div class="pokemon">
        <img class="image_pok" src="' . $image['chemin'] . '"/>
        <div class="description-container">
            <div class="description">Description</div>
            <div class="desc">' . $pokemon_info['description'] . '</div>
        </div>
    </div>
    <div class="typ">Types</div>
    <div class="types">';
    foreach ($type as $typeItem) {
        $chemin = './images/types/Miniature_Type_'.$typeItem.'_EV.png';
        
        echo '<img src="' . $chemin . '"/>';
    }
    echo'</div>
        <div class="atk">Attaques</div>
        <div class="attaques">
            
            <table>
                <tr>
                    <th>numero</th>
                    <th>Nom</th>
                    <th>PP</th>
                    <th>Précision (%)</th>
                    <th>Puissance</th>
                    <th>Type</th>
                </tr>';
                
                foreach ($attaques as $attaque) {
                    echo '<tr>';
                    echo '<td>' . $attaque['id_capacite'] . '</td>';
                    echo '<td>' . $attaque['libelle_capacite'] . '</td>';
                    echo '<td>' . $attaque['pp_capacite'] . '</td>';
                    echo '<td>' . $attaque['precision_capacite'] . '</td>';
                    echo '<td>' . ($attaque['puissance_capacite'] ?? 'N/A') . '</td>';
                    echo '<td>' . $attaque['type'] . '</td>';
                    echo '</tr>';
                }

    echo'</table>
        </div>';
    echo '<div class="atk">Version shiny</div>';
    echo '<div class ="shiny"><img src="./images/pokemon_sugimori_shiny/'.$pokemon_info['id_pokemon'].'.png"></div>';

    echo '<div class="atk">Evolution</div>
    <div class = "nom_evo"';
    if (!empty($evolution)) {
        foreach ($evolution as $evo) {
            echo '<div class="evo">
                    <div class="pokemon_base">
                        <img src="' . $image['chemin'] . '"/>
                    </div>
                    <div class="fleche_et_level">
                        <span class="fleche">
                            <i class="icon-fleche">→︎</i>
                        </span>
                        <small>(Niveau ' . $evo['niveau'] . ')</small>
                    </div>
                    <div class="pokemon_evo">
                        <img src="' . $evo['chemin'] . '" />
                    </div>
                </div>';
        }
    } else {
        echo '<div class="evo">' . $pokemon_info['nom'] . ' n\'a pas d\'évolution connue.</div>';
    }
    '</div>';
    
}

function displayPokemonDresseur($image, $pokemon_info, $type,$attaques,$evolution,$nbVues,$nbAttrape){
    echo '<div class="nom">' . $pokemon_info['nom'] . '</div>
    <div class="pokemon">
        <img class="image_pok" src="' . $image['chemin'] . '"/>
        <div class="description-container">
            <div class="description">Description</div>
            <div class="desc">' . $pokemon_info['description'] . '</div>
        </div>
    </div>
    <div class="typ">Types</div>
    <div class="types">';
    foreach ($type as $typeItem) {
        $chemin = './images/types/Miniature_Type_'.$typeItem.'_EV.png';
        
        echo '<img src="' . $chemin . '"/>';
    }
    echo'</div>
        <div class="atk">Attaques</div>
        <div class="attaques">
            
            <table>
                <tr>
                    <th>numero</th>
                    <th>Nom</th>
                    <th>PP</th>
                    <th>Précision (%)</th>
                    <th>Puissance</th>
                    <th>Type</th>
                </tr>';
                
                foreach ($attaques as $attaque) {
                    echo '<tr>';
                    echo '<td>' . $attaque['id_capacite'] . '</td>';
                    echo '<td>' . $attaque['libelle_capacite'] . '</td>';
                    echo '<td>' . $attaque['pp_capacite'] . '</td>';
                    echo '<td>' . $attaque['precision_capacite'] . '</td>';
                    echo '<td>' . ($attaque['puissance_capacite'] ?? 'N/A') . '</td>';
                    echo '<td>' . $attaque['type'] . '</td>';
                    echo '</tr>';
                }

    echo'</table>
        </div>';
    echo '<div class="atk">Version shiny</div>';
    echo '<div class ="shiny"><img src="./images/pokemon_sugimori_shiny/'.$pokemon_info['id_pokemon'].'.png"></div>';

    echo '<div class="atk">Evolution</div>
    <div class = "nom_evo"';
    if (!empty($evolution)) {
        foreach ($evolution as $evo) {
            echo '<div class="evo">
                    <div class="pokemon_base">
                        <img src="' . $image['chemin'] . '"/>
                    </div>
                    <div class="fleche_et_level">
                        <span class="fleche">
                            <i class="icon-fleche">→︎</i>
                        </span>
                        <small>(Niveau ' . $evo['niveau'] . ')</small>
                    </div>
                    <div class="pokemon_evo">
                        <img src="' . $evo['chemin'] . '" />
                    </div>
                </div>';
        }
    } else {
        echo '<div class="evo">' . $pokemon_info['nom'] . ' n\'a pas d\'évolution connue.</div>';
    }
    '</div>';
    echo'<div class="atk">Vos intéractions avec ce pokemon</div>';
    if(!empty($nbVues)){
        echo '<div class="va">Vous avez vue ce pokemon : <strong>'. $nbVues .'</strong> fois.</div>';
        if($nbAttrape==0){
            echo '<div class="va">Vous n\'avez pas attrapé ce pokemon.</div></br>';
        }else{
            echo '<div class="va">Vous avez attrapé ce pokemon : <strong> '. $nbAttrape .' </strong> fois.</div></br>';
            
        }
    }else{
        echo '<div class="evo">Vous n\'avez pas encore vu ce pokemon.</div></br>';
    }

}


?>
