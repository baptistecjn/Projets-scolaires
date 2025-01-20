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
session_start();;

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
        </main>
    <?footer_template();?>
    </body>
</html>
<?closeDB($mysqli);?>