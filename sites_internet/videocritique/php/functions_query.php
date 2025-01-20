<?php
function getUser($mysqli, $login, $password) {
    $sql_input = 'SELECT user.login, user.nomUser, dresseur.mdp  FROM user WHERE user.login =  ' . $login . ' AND user.mdp = ' . $password . '';
    $user = readDB($mysqli,$sql_input);
    return $user;
}

function getSupport($mysqli, $nomJeu) {
    $sql_input = 'SELECT supports.libelleSup FROM jeu INNER JOIN estSup ON jeu.nomJeu = estSup.nomJeu INNER JOIN supprots ON estSup.idSup = supports.idSup WHERE jeu.nomjeu = '. $nomJeu . '';
    $support = readDB($mysqli,$sql_input);
    return $support;
}

function getCategorie($mysqli, $nomJeu) {
    $sql_input = 'SELECT categories.libelleCat FROM jeu INNER JOIN estCat ON jeu.nomJeu = estCat.nomJeu INNER JOIN categories ON estCat.idCat = categories.idCat WHERE jeu.nomjeu = '. $nomJeu . '';
    $categorie = readDB($mysqli,$sql_input);
    return $categorie;
}

function getPrix($mysqli, $nomJeu) {
    $sql_input = 'SELECT prix FROM jeu WHERE jeu.nomjeu = '. $nomJeu . '';
    $prix = readDB($mysqli,$sql_input);
    return $prix;
}

function getDatejeu($mysqli, $nomJeu) {
    $sql_input = 'SELECT datejeu FROM jeu WHERE jeu.nomjeu = '. $nomJeu . '';
    $datejeu = readDB($mysqli,$sql_input);
    return $datejeu;
}

function getSynopsis($mysqli, $nomJeu) {
    $sql_input = 'SELECT synopsis FROM jeu WHERE jeu.nomjeu = '. $nomJeu . '';
    $synopsis = readDB($mysqli,$sql_input);
    return $synopsis;
}

/*function getUsernamPP($mysqli, $login) {
    $sql_input = 'SELECT user.pp, user.nomUser FROM user WHERE user.login = '. $login . '';
    $usernamePP = readDB($mysqli,$sql_input);
    return $usernamePP;
}*/

function getNoteavis($mysqli, $idAvis) {
    $sql_input = 'SELECT noteAvis FROM avis WHERE idAvis = '. $idAvis . '';
    $noteavis = readDB($mysqli,$sql_input);
    return $noteavis;
}

function getNotearticle($mysqli, $titreArticle) {
    $sql_input = 'SELECT noteArticle FROM article WHERE titreArticle = '. $titreArticle . '';
    $notearticle = readDB($mysqli,$sql_input);
    return $notearticle;
}

function getContenuavis($mysqli, $idAvis) {
    $sql_input = 'SELECT texte FROM avis WHERE idAvis = '. $idAvis . '';
    $contenuavis = readDB($mysqli,$sql_input);
    return $contenuavis;
}

function getContenuarticle($mysqli, $titreArticle) {
    $sql_input = 'SELECT contenu FROM article WHERE titreArticle = '. $titreArticle . '';
    $contenuarticle = readDB($mysqli,$sql_input);
    return $contenuarticle;
}
?>