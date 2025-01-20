<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL); 
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
require_once(__DIR__."/../includes/config-bdd.php");
require_once("./functions-DB.php");
require_once("./functions_query.php");
require_once("./functions_structure.php");

$mysqli = connectionDB();

$login = $_POST['login'];
$password = $_POST['password'];
$user = getUser($mysqli, $login, $password);

if (!empty($user)) {
  session_start();
  $_SESSION["id"] = $user[0]['login'];
  $_SESSION["login"] = $user[0]['mdp'];
  $_SESSION["connected"] = true;
  closeDB($mysqli);
  header("Location: http://localhost/index.php");
} 
else {
  closeDB($mysqli);
  header("Location: http://localhost/connection.php");
}


?>