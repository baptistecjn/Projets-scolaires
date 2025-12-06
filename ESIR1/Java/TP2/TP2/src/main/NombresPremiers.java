package main;

import tableau.Block;
import tableau.Tableau2x;
import types.Tableau;
import java.util.Scanner;
import java.util.Random;

public class NombresPremiers {
    public static boolean estPremier(int n, Tableau<Integer> nombresPremiers) {
        assert(n>=2);
        assert(nombresPremiers!=null);
        for(int i = 0; i<nombresPremiers.size(); i++) {
            int elt = nombresPremiers.get(i);
            if (elt * elt > n) {
                break;
            }
            if (n % elt == 0) {
                return false;
            }
        }
        return true;
    }

    public static int calculerNombresPremiers(int n, Tableau<Integer> nombresPremiers) {
        assert(n>=2);
        assert(nombresPremiers!=null);

        for(int i = 2; i<=n; i++) {
            if(nombresPremiers.full()){
                return (i-1);
            }
            if(estPremier(i, nombresPremiers)){
                nombresPremiers.push_back(i);
            }
        }
        return n;
    }

    public static Tableau<Integer> remplirHasard(int nb) {
        Block block = new Block(nb);
        Random random = new Random();
        for(int i = 0; i < nb; i++) {
            int entier = random.nextInt(9);
            block.push_back(entier);
        }
        return block;

    }

    public static int eliminerPresents(Tableau<Integer> t, Tableau<Integer> nombresPremiers) {
        Tableau<Integer> temp = new Block<>(100);
        int nbElimines = 0;

        for (int i = 0; i < t.size(); i++) {
            if (!estPresent(t.get(i), nombresPremiers, nombresPremiers.size())) {
                temp.push_back(t.get(i));
            } else {
                nbElimines++;
            }
        }


        t.clear(); // Vide le tableau original
        for (int i = 0; i < temp.size(); i++) {
            t.push_back(temp.get(i));
        }

        return nbElimines;
    }


    public static boolean estPresent(int x, Tableau<Integer> block, int length) {
        for(int i = 0; i < length; i++) {
            if(x == block.get(i)) {
                return true;
            }
        }
        return false;
    }


    public static void toString(Tableau<Integer> nombresPremiers,int nb) {
        StringBuilder string1 = new StringBuilder();
        for (int i = 0; i < nombresPremiers.size(); i++) {
            if (i > 0) {
                string1.append(", ");
            }
            string1.append(nombresPremiers.get(i));
        }
        System.out.println("Nombres premiers :" + string1.toString());
        StringBuilder string2 = new StringBuilder();
        for(int i = 0; i < remplirHasard(nb).size(); i++) {
            if (i > 0) {
                string2.append(", ");
            }
            string2.append(remplirHasard(nb).get(i));
        }
        System.out.println("Nombres au hazard :" + string2.toString());
    }
    public static void afficherTableau(String message, Tableau<Integer> tableau) {
        StringBuilder sb = new StringBuilder(message);
        for (int i = 0; i < tableau.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(tableau.get(i));
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saisissez un entier : ");
        int valeur = scanner.nextInt();
        Block block = new Block(100);
        int nb = calculerNombresPremiers(valeur, block);

        Tableau<Integer> nombresPremiers = new Block<>(100);

        calculerNombresPremiers(valeur, nombresPremiers);
        afficherTableau("Nombres premiers :", nombresPremiers);

        Tableau<Integer> tableauHasard = remplirHasard(nb);
        afficherTableau("Tableau de nombres aléatoires avant élimination :", tableauHasard);

        int nbElimines = eliminerPresents(tableauHasard, nombresPremiers);
        System.out.println("Nombre d'entiers premiers éliminés : " + nbElimines);

        afficherTableau("Tableau de nombres aléatoires après élimination :", tableauHasard);

        toString(block,nb);
    }
}
