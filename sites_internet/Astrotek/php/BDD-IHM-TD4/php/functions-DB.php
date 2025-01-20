<?php
require_once("./includes/config-bdd.php");  

#connexion à la base de données en MySQLi (base de données SQL)
function connectionDB(){
	//connexion à la BDD via le mode procédural (connexion non persistente)
	$mysqli = mysqli_connect(SERVEUR, USER, PWD, DB_NAME);
	//gestion des erreurs manuelle (si rapport d'erreur désactivé)
	if(mysqli_error($mysqli)){
		echo 'Il y a une erreur'.mysqli_error($mysqli);
	}
	//modification du jeu de résutlats en utf8
	mysqli_set_charset($mysqli,"utf-8");
	//retour du flux de connexion
	return $mysqli;
  
}

#ferme la connexion à la base de données
function closeDB($mysqli){



}

#lecture de la base de données en fonction d'une requête SQL passée en paramètre d'entrée
#retourne un tableau associatif contenant les résultats de la requête
#à utiliser pour les requêtes de type SELECT
function readDB($mysqli, $sql_input){
	//exécution de la requête $sql_input et récupération du résultat de type mysqli_result

	//vérification de la requête : 
		//si la requête est incorrect ou le nombre de ligne retourné égal à 0,
		//on retourne un tableau vide

	//Sinon, on retourne un tableau associatif

}

#ecrit/modifie la base de données grâce à la requête SQL passée en paramètre d'entrée
#à utiliser pour les requêtes de type INSERT INTO, UPDATE, DELETE
function writeDB($mysqli, $sql_input){
	//exécution de la requête $sql_input

	//retourne le résultat de la requête

}

?>