package hordes;

public class Main {

  public static void main(String[] args) {
   System.out.println("Bonjour, et bienvenue sur le jeu Hordes");
    Hordes.iniMap();
    Hordes.iniItems();
    Hordes.showMap(0,1);
    Hordes.zMap();
    Hordes.addPlayer();
    Hordes.game();
  }
}
