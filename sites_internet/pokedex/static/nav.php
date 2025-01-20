<?php
function nav_template() {
    echo '<nav>
        <ul> 
        <li><a href="index.php">Pokedex</a></li>';
        if (isset($_SESSION['connecte']) && $_SESSION['connecte'] === true) {
            echo '<li><a href="logout.php">Deconnexion</a></li>';
            echo '<li><a href="formulaire.php">modifier</a></li>';

        } else {
            echo '<li><a href="connection.php">Connexion</a></li>';
        }
    echo '</ul>
    </nav>';
}



?>