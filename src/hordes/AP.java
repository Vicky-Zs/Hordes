package hordes;

public class AP extends Hordes {
  public static void drinkWater (int n) { // n est le numéro du joueur enregistré dans le tableau
    if ((p[n].containsInventory("Gourde d'eau")) && (!p[n].getDrink())) {
      // On vérifié qu'il y a une gourde d'eau et qu'il n'a pas bu cette journée
      System.out.println("Vous vous désaltérez, vous regagnez 6 PA");
      p[n].gain6ap(); // Gain de 6 pa
      p[n].drink(); // On enregistre que le joueur a bu et on retire une gourde d'eau de son inventaire
    }
    else if (p[n].getDrink()) { // S'il a bu, on le signale
      System.out.println("Vous ne pouvez pas boire, vous avez déjà bu");
    }
    else {
      System.out.println("Vous ne pouvez pas boire, vous n'avez pas d'eau sur vous");
    }
  }

  public static void eatRation (int n) { // n est le numéro du joueur enregistré dans le tableau
    if ((p[n].containsInventory("Ration")) && (!p[n].getEat())) {
      // On vérifie qu'il a une ration dans son inventaire et s'il n'a pas mangé
      System.out.println("Vous mangez de la nourriture pas très bonne, mais ça rempli votre ventre, vous regagnez 6 PA");
      p[n].gain6ap(); // Gain de 4 pa
      p[n].eat(); // On enregistre que le joueur a mangé et on retire une ration d'eau de son inventaire
    }
    else if(p[n].getEat()) { // S'il a mangé, on le signale
      System.out.println("Vous ne pouvez pas manger, vous avez déjà mangé");
    }
    else {
      System.out.println("Vous ne pouvez pas manger, vous n'avez pas de ration sur vous");
    }
  }

  public static void drinkAddict (int n) { //n est le numéro du joueur enregistré dans le tableau
    if (p[n].containsInventory("Boisson énergisante")) {
      System.out.println("Vous buvez une boisson énergisante, vous en devenez dépendant et vous regagnez 4 PA");
      p[n].gain4ap(); // Gain de 4 pa
      p[n].addict(); // On met à 0 au compteur d'addiction, et on retire une boisson énergisante
    }
    else {
      System.out.println("Vous ne pouvez pas prendre une boisson énergisante, vous n'en avez pas sur vous");
    }
  }
}
