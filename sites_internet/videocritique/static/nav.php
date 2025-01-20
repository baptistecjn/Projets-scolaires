<?php
function nav_template(){
    echo '
    <nav>
        <ul>
            <li><a href="index.php">Accueil</a></li>';
            if (empty($_SESSION)){
                echo '<li><a href="connection.php">Connexion</a></li>';
            }
            else{
                echo '<li><a href="http://localhost/php/logout.php">Deconnexion</a></li>
                <li><a href="http://localhost/profil.php">Profil</a></li>';
            }
        echo '</ul>
    </nav>';
}
?>