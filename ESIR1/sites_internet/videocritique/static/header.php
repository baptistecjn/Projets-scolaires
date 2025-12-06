<?php
    function header_template(){ 
        echo '
        <a href="index.html">
            <img class="logo" src="images/logo.png" alt="Logo du site"/>
        </a>
    ';
    }
    function head_template(){
        echo '
        <title>VideoCritique</title>

        <meta name="author" content="Gatien AUFFRET & Baptiste COJEAN">
        <meta name="keywords" content="Critique de jeu BDD">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="icon" href="images/favicon.png" />
        <link rel="stylesheet" href="styles/style.css" />
    ';
    }
?>