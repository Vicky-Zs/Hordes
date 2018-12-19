package hordes;

public class Main {

  public static void main(String[] args) {
   System.out.println("Bonjour, et bienvenue sur le jeu Hordes");
    Initialization.iniMap();
    Initialization.iniItems();
    Initialization.addPlayer();
    Hordes.zMap();
    Hordes.game();
    Hordes.closeScan(); // Fermeture du scanner
  }
}
