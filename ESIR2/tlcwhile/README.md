## Compilateur While

Un compilateur pour le langage While, développé dans le cadre du cours de Théorie des Langages et Compilation.



## Architecture du Compilateur

Le compilateur suit une chaîne de traitement modulaire, orchestrée par le fichier principal Main.java :

- Analyse Lexicale & Syntaxique : Utilisation d'ANTLR pour valider la grammaire et générer le Parse Tree.

- Arbre de Syntaxe Abstraite (AST) : Simplification de l'arbre pour extraire la structure logique.

- Analyse Sémantique : Vérification des types, de la portée des variables et des signatures de fonctions.

- Code 3 Adresses (C3A) : Génération d'un code intermédiaire proche de l'assembleur.

- Optimisation : Simplification du C3A (suppression des opérations redondantes).

- Génération de code cible : Traduction vers le C++ en utilisant une bibliothèque runtime dédiée à la gestion des arbres binaires.



## Structure du Dépôt

- /src/main/while_compiler/ : Cœur du compilateur (Main, Analyseur Sémantique).

- /src/main/antlr_grammar/ : Grammaires ANTLR (.g) et fichiers générés.

- /src/main/Backend/ : Logique de traduction vers le C++.

- /src/main/c_runtime/ : Bibliothèque runtime (gestion des données while).

- /src/test/ : Exemples de fichiers WHILE et tests de gestion d'erreurs.

- /lib/ : Dépendances ANTLR.



## Installation et Utilisation
Prérequis

- Java JDK 8 ou supérieur.

- ANTLR 3.5.3 (fourni dans le dossier lib).

- Un compilateur C++ (GCC/G++).


# Compilation du projet

Pour compiler les sources Java du compilateur :

javac -cp "lib/antlr-3.5.3-complete-no-st3.jar" -d bin src/main/while_compiler/*.java


# Utilisation (Compilation d'un fichier .while)

Pour lancer la compilation d'un fichier source :

java -cp "bin:lib/antlr-3.5.3-complete-no-st3.jar" while_compiler.Main <chemin_fichier>.while



## Contributeurs
AUFFRET Gatien
BERGERET Aubin
COJEAN Baptiste
RENOIR Jules
SERANDOUR Matéo
