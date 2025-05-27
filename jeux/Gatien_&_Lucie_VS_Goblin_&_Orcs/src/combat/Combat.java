//package combat;
//import deck.Deck;
//import Inventory.Hand;
//import Inventory.Defausse;
//import carte.Carte;
//import map.Map;
//import entity.Player;
//import entity.Monster;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Combat {
//    private Player player;
//    private Deck deck_player;
//    private Map map;
//    private List<Monster> monsters;
//    private boolean isPlayerTurn; //boolean pour savoir si cest le tour du joueur ou non
//    private static final long TIME_TURN = 30_000; // 30 secondes max pour le tour du joueur
//    private boolean firstTurn = true;
//    private Hand hand;
//    private Defausse defausse;
//
//
//    public Combat(Player player, Deck deck_player, Map map) {
//        this.player = player;
//        this.deck_player = deck_player;
//        this.map = map;
//        this.monsters = map.getMonsters();
//        this.isPlayerTurn = true; //c'est le joueur qui commence
//        this.hand = new Hand();
//        this.defausse = new Defausse();
//
//    }
//
//    public void start(){
//        System.out.println("Combat commencé !");
//        while (!isCombatOver()) {
//            if (isPlayerTurn) {
//                System.out.println("Tour du joueur");
//                playerTurn();
//            } else {
//                System.out.println("Tour des ennemis");
//                monstersTurn();
//            }
//            isPlayerTurn = !isPlayerTurn;
//        }
//
//        System.out.println("Combat terminé !");
//        if (player.getisAlive()) {
//            System.out.println("Victoire !");
//        } else {
//            System.out.println("Défaite...");
//        }
//    }
//
//    private boolean isCombatOver() {
//        if(!player.getisAlive()) return true;
//
//        boolean all_monsters_ko = false;
//        for(Monster e : monsters) {
//            if(e.getisAlive()){
//                all_monsters_ko = true;
//                break;
//            }
//        }
//        return !all_monsters_ko;
//    }
//
//    public void playerTurn(){
//        //on reset les PA
//        player.resetPA();
//        //on dit au joueur cb il a de PA
//        System.out.println("Vous avez " + player.getPA() + " PA.");
//        //si cest le premier tour : on remplie la main
//        if(firstTurn) {
//            firstTurn = false;
//            hand.piocheHand(deck_player);
//        }
//        //si le deck est vide, on mélange la défausse, on remplie le deck avec et on remplie la main
//        if(deck_player.isEmpty()){
//            defausse.melangeDefausse(deck_player);
//            hand.piocheHand(deck_player);
//        }
//        //si la n'est pas pleine : on pioche UNE carte
//        if(!hand.isFull()){ //si
//            hand.piocheOneCarte(deck_player);
//        }
//        long startTime = System.currentTimeMillis();
//        boolean fin_tour = false;
//        while(!fin_tour){
//            long currentTime = System.currentTimeMillis();
//            if(currentTime - startTime > TIME_TURN) {
//                System.out.println("Temps écoule, fin du tour de " + player.getName() + " !");
//                fin_tour = true;
//            }
//
//
//
//        }
//
//
//    }
//}
