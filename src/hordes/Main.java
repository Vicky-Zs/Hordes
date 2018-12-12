package hordes;
import java.util.Scanner;

public class Main {
  private static Scanner scan = new Scanner (System.in); // Permet d'avoir des entrées

  public static void main(String[] args) {
   System.out.println("Bonjour, et bienvenue sur le jeu Hordes");
    Hordes.iniMap();
    Hordes.iniItems();
    //Hordes.showMap();
    Hordes.zMap();
    Hordes.addPlayer();
    Hordes.game();
  }
}
