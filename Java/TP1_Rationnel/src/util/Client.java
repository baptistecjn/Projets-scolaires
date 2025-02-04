package util;

import java.util.Scanner;
import rationnel.RationnelSimple;
import types.Rationnel;
import rationnel.RationnelCouple;

public class Client {

    public static Rationnel makeRationnel(int num, int den) {
        if(num%2==0){
            return new RationnelSimple(num, den);
        }else{
            return new RationnelCouple(num,den);
        }
    }

    static void insererRationnel(Rationnel nouveau, Rationnel[] lesRationnels, int nb) {
        int i = nb - 1;
        while (i >= 0 && lesRationnels[i] != null && lesRationnels[i].compareTo(nouveau) > 0) {
            lesRationnels[i + 1] = lesRationnels[i];
            i--;
        }
        lesRationnels[i + 1] = nouveau;
    }



    static void afficher(Rationnel[] lesRationnels, int nb) {
        for (int i = 0; i < nb; i++) {
            Rationnel r = lesRationnels[i];
            System.out.println("Fraction : " + r.toString() + ", Valeur : " + r.valeur());
        }
    }

    public static Rationnel lireRationnel(Scanner input) {
        System.out.print("Numérateur: ");
        int num = input.nextInt();
        int den;
        do {
            System.out.print("Dénominateur (non nul): ");
            den = input.nextInt();
        } while (den == 0);
        
        return makeRationnel(num, den);
    }

    static Rationnel sommeRationnels(Rationnel[] lesRationnels, int nb) {
        assert(lesRationnels.length > nb && nb>=0);
        Rationnel somme = new RationnelSimple(0);
        for (int i = 0; i < nb; i++) {
            somme = somme.somme(lesRationnels[i]);
        }
        return somme;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Rationnel precedent = new RationnelSimple(0);
        Rationnel courant;
        Rationnel[] lesRationnels = new RationnelSimple[100];
        int nb = 0;
        while (true) {
            courant = lireRationnel(input);

            if (courant.getNumerateur() == 0) {
                System.out.println("Rationnel nul saisi, fin du programme.");
                break;
            }

            System.out.println("Courant : " + courant);

            insererRationnel(courant, lesRationnels, nb);
            nb++;

            Rationnel somme = courant.somme(precedent);
            System.out.println("Somme avec précédent : " + somme);

            insererRationnel(somme, lesRationnels, nb);
            nb++;

            if (courant.getNumerateur() != 0) {
                Rationnel inverse = courant.inverse();
                System.out.println("Inverse : " + inverse);

                insererRationnel(inverse, lesRationnels, nb);
                nb++;

            } else {
                System.out.println("Inverse : impossible (numérateur nul)");
            }

            System.out.println("Valeur réelle : " + courant.valeur());

            int comparaison = courant.compareTo(precedent);
            if (comparaison > 0) {
                System.out.println(courant + " > " + precedent);
            } else if (comparaison < 0) {
                System.out.println(courant + " < " + precedent);
            } else {
                System.out.println(courant + " = " + precedent);
            }

            if (courant.equals(precedent)){
                System.out.println(courant + " = " + precedent);
            } else {
                System.out.println(courant + " != " + precedent);
            }

            afficher(lesRationnels, nb);

            Rationnel sommeTableau = sommeRationnels(lesRationnels, nb);
            System.out.println(sommeTableau);

            precedent = courant;
            
        }

        input.close();
    }
}
