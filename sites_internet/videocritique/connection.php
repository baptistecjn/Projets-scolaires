<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL); 
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
require_once("./static/header.php");
require_once("./static/nav.php");
require_once("./static/footer.php");
require_once("./php/functions-DB.php");
require_once("./php/functions_query.php");
require_once("./php/functions_structure.php");

$mysqli = connectionDB();
session_start();

?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <?head_template();?>
</head>
<body>
    <header>
        <?header_template();?>
    </header>
    <?nav_template();?>
    <main>
        <div class="form-container">
            <form class="form" action="php/login.php" method="POST">
                <p class="form-title">Se connecter à son compte</p>
                <div class="input-container">
                    <input type="text" placeholder="Login" name="login" id="login" required>
                </div>
                <div class="input-container">
                    <input type="password" placeholder="Password" name="password" id="password" required>
                </div>
                <button type="submit" class="submit">
                    Se connecter
                </button>
            </form>
            <form class="form" action="php/login.php" method="POST">
                <p class="form-title">S'inscrire</p>
                <div class="input-container">
                    <input type="text" placeholder="Login" name="login" id="login" required>
                </div>
                <div class="input-container">
                    <input type="password" placeholder="Password" name="password" id="password" required>
                </div>
                <div class="input-container">
                    <input type="email" placeholder="Email" name="email" id="email" required>
                </div>
                <div class="input-container">
                    <input type="text" placeholder="Nom" name="nom" id="nom" required>
                </div>
                <div class="input-container">
                    <input type="text" placeholder="Prénom" name="prenom" id="prenom" required>
                </div>
                <div class="input-container">
                    <input type="date" name="date_naissance" id="date_naissance" required>
                </div>
                <div class="input-container">
                    <input type="file"  id="profile_pic" name="profile_pic" accept=".jpg, .jpeg, .png" required>
                </div>
                <button type="submit" class="submit">
                    S'inscrire
                </button>
            </form>
        </div>
    </main>
    <?footer_template();?>
    </body>
</html>

<?closeDB($mysqli);?>