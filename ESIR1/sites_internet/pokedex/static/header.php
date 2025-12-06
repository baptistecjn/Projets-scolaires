<?php
    function header_template(){ 
        echo '
    <header>
        <a href="index.php">
            <img src="images/logo.webp" alt="Logo du site" class="logo"/>
        </a>
    </header>
    ';
    }
    function head_template(){
        echo '
    <head>
        <title>TD2</title>
        <meta name="author" content="Baptiste COJEAN">
        <meta name="keywords" content="ESIR, CUPGE2, 2024, BDD, IHM">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="./styles/css.css"> 
        <!-- Google fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">

    </head>
    ';
    }