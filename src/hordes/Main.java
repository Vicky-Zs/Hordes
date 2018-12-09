package hordes;
import java.util.Scanner;

public class Main {
  private static int nb_turn = 1; // Le numéro du tour, le tour 1 correspond de 0h à 2h, le tour de 2 de 2h à 4h, ..., le tour 12 correspon de 22h à 00h
  // A minuit, c'est l'attaque des zombies.
  private static Scanner scan = new Scanner (System.in); // Permet d'avoir des entrées

  public static void main(String[] args) {
   System.out.println("Bonjour, et bienvenue sur le jeu Hordes");
    Hordes.iniMap();
    //Hordes.iniItems();
    //Hordes.showMap();
  }
}
