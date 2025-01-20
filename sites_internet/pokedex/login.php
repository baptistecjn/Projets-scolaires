<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

require_once("./php/functions_querry.php");
require_once("php/functions-DB.php");

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $login = $_POST['login'];
    $password = $_POST['password'];
    $mysqli = connectionDB();
    if (empty(dresseurExist($mysqli,$login,$password))){
        header("Location:index.php");
    }else{
        session_start();

        $_SESSION['id'] = dresseurExist($mysqli, $login, $password)[0]['id_dresseur'];
        $_SESSION['nom']=$login;
        $_SESSION['mdp']=$password;
        $_SESSION['connecte']=true;

        mysqli_close($mysqli);
        
        header("location:index.php?id={$_SESSION['id']}");       
    }



}


?>
