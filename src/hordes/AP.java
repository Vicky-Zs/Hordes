package hordes;

class AP extends Hordes {
  public static void drinkWater (int n) { // n est le numéro du joueur enregistré dans le tableau
    if ((p[n].containsInventory("Gourde d'eau")) && (p[n].getDrink() == false)) {
      System.out.println("Vous vous désaltérez, vous regagnez 6 PA");
      if (p[n].getNb_ap() < 5) {
        p[n].setNb_ap(p[n].getNb_ap() + 6);
      }
      else {
        p[n].setNb_ap(10);
      }
      p[n].drink();
    }
    else if (p[n].getDrink()) {
      System.out.println("Vous ne pouvez pas boire, vous avez déjà bu");
    }
    else {
      System.out.println("Vous ne pouvez pas boire, vous n'avez pas d'eau sur vous");
    }
  }

  public static void eatRation (int n) { // n est le numéro du joueur enregistré dans le tableau
    if ((p[n].containsInventory("Ration")) && (p[n].getEat() == false)) {
      System.out.println("Vous mangez de la nourriture pas très bonne, mais ça rempli votre ventre, vous regagnez 6 PA");
      if (p[n].getNb_ap() < 5) {
        p[n].setNb_ap(p[n].getNb_ap() + 6);
      }
      else {
        p[n].setNb_ap(10);
      }
      p[n].eat();
    }
    else {
      System.out.println("Vous ne pouvez pas manger, vous n'avez pas de rations sur vous ou vous avez déjà mangé");
    }
  }

  public static void drinkAddict (int n) { //n est le numéro du joueur enregistré dans le tableau
    if (p[n].containsInventory("Boisson énergisante")) {
      System.out.println("Vous buvez une boisson énergisante, vous en devenez dépendant et vous regagnez 4 PA");
      if (p[n].getNb_ap() < 7) {
        p[n].setNb_ap(p[n].getNb_ap() + 4);
      }
      else {
        p[n].setNb_ap(10);
      }
      p[n].addict();
    }
  }
}
