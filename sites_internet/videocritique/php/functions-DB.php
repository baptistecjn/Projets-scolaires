<?php
include_once(__DIR__."/../includes/config-bdd.php");

function connectionDB()
{
	$mysqli = mysqli_connect(SERVEUR, USER, PWD, DB_NAME);
	if(mysqli_connect_errno()) {
		echo "Echec lors de la connexion à MySQL : " . mysqli_connect_error();
		exit();
	}
	mysqli_set_charset($mysqli, "utf8");
	
	return $mysqli;
}

function closeDB($mysqli)
{
	mysqli_close($mysqli);
}

function readDB($mysqli, $sql_input)
{
	$result = mysqli_query($mysqli, $sql_input);
	if($result == false || mysqli_num_rows($result) == 0){
		return array();
	}
	$tab_result = mysqli_fetch_all($result,MYSQLI_ASSOC);
		return $tab_result;
}

function writeDB($mysqli, $sql_input)
{
	$result = mysqli_query($mysqli, $sql_input);
	return $result;
}
?>