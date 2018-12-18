package hordes;

class Outside extends Hordes {
  //Permet au joueur de se déplacer vers le nord
  public static void goNorth (int n) { // n est le numéro du joueur enregistré dans le tableau
    if (p[n].getNb_ap() == 0) {
      System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
    }
    else if (map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getZ()>0) {
      System.out.println("Il y a des zombies, vous devez tous les tuer avant de bouger");
    }
    else if (p[n].getPos_y() + 12 == 0) {
      System.out.println("Vous êtes arrivé à la limite nord de la carte");
    }
    else {
      p[n].setPos_y(p[n].getPos_y() - 1);
      p[n].action();
    }
  }

  //Permet au joueur de se déplacer vers le sud
  public static void goSouth (int n) { // n est le numéro du joueur enregistré dans le tableau
    if (p[n].getNb_ap() == 0) {
      System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
    }
    else if (p[n].getPos_y() + 12 == 24) {
      System.out.println("Vous êtes arrivé à la limite sud de la carte");
    }
    else if (map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getZ()>0) {
      System.out.println("Il y a des zombies, vous devez tous les tuer avant de bouger");
    }
    else {
      p[n].setPos_y(p[n].getPos_y() + 1);
      p[n].action();
    }
  }

  //Permet au joueur de se déplacer vers l'est
  public static void goEast (int n) { // n est le numéro du joueur enregistré dans le tableau
    if (p[n].getNb_ap() == 0) {
      System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
    }
    else if (p[n].getPos_x() + 12 == 24) {
      System.out.println("Vous êtes arrivé à la limite est de la carte");
    }
    else if (map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getZ()>0) {
      System.out.println("Il y a des zombies, vous devez tous les tuer avant de bouger");
    }
    else {
      p[n].setPos_x(p[n].getPos_x() + 1);
      p[n].action();
    }
  }

  //Permet au joueur de se déplacer vers l'ouest
  public static void goWest (int n) { // n est le numéro du joueur enregistré dans le tableau
    if (p[n].getNb_ap() == 0) {
      System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
    }
    else if (p[n].getPos_y() + 12 == 0) {
      System.out.println("Vous êtes arrivé à la limite ouest de la carte");
    }
    else if (map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getZ()>0) {
      System.out.println("Il y a des zombies, vous devez tous les tuer avant de bouger");
    }
    else {
    p[n].setPos_x(p[n].getPos_x() - 1);
    p[n].action();
    }
  }

  //Permet de fouiller la zone
  public static void search (int n) { // n est le numéro du joueur enregistré dans le tableau
    String temp;
    if (p[n].getNb_ap() == 0) {
      System.out.println("Vous êtes fatigué, vous ne pouvez plus fouiller");
    }
    else if ((p[n].getPos_x() == 0) && (p[n].getPos_y() == 0)) {
      System.out.println("Vous êtes en ville, vous ne pouvez pas fouiller");
    }
    else if (map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getSearch()) { // Conversion de la coordonée réel en coordonée du tableau m
      System.out.println("Cette zone est épuisée, il est inutile de la fouiller de nouveau");
    }
    else {
      if (map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].isEmpty()) { // Permet de savoir si une case contient encore ou non des objets cachés
        System.out.println("Cette zone ne contient plus d'objet, elle est considérée comme épuisée");
        map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].setSearch();
        p[n].action();
      }
      else {
        temp = map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].removeHide_item();
        System.out.print("Vous avez trouvé " + temp);
        p[n].action();
        if (p[n].sizeInventory()<10) {
          p[n].addInventory(temp);
          System.out.println(", il a été ajouté à votre inventaire");
        }
        else {
          map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].addItem(temp);
          System.out.println(", votre inventaire est plein, vous avez posé " + temp + " au sol");
        }
      }
    }
  }

  //TODO: Ramasser un item

  // Ramasser un item au sol
  public static void takeItem (int n) { // n est le numéro du joueur enregistré dans le tableau
    int in;
    String temp;
    if ((p[n].getPos_x() == 0) && (p[n].getPos_y() == 0)) {
      System.out.println("Vous êtes en ville ou au porte, il n'y a aucun objet"
      +" à rammasser ici");
    }
    else {
      if (p[n].sizeInventory() < 11) {
        map[p[n].getPos_x()+12][p[n].getPos_y()+12].bankItem();
        do {
          in = scan.nextInt();
          if ((in < map[p[n].getPos_x()+12][p[n].getPos_y()+12].sizeItem() + 1) && (in < 0)) {
            temp = map[p[n].getPos_x()+12][p[n].getPos_y()+12].removeItem(in - 1);
            p[n].addInventory(temp);
          }
          else if (in != 0) {
            System.out.println("La valeur que vous avez entré n'es");
          }

        } while (in != 0);
      }
      else {
        System.out.println("Votre inventaire est plein, vous ne pouvez pas "
        +"prendre d'objets");
      }
    }
  }

  // Déposer un item sur le sol
  public static void dropItem (int n) { // n est le numéro du joueur enregistré dans le tableau
    int in;
    if ((p[n].getPos_x() != 0) || (p[n].getPos_y() != 0)) {
      p[n].bankInventory(); // On affiche l'inventaire
      do {
        in = scan.nextInt() - 1; // On demande à l'utilisateur l'objet qu'il veut mettre à la bank, puis nous l'ajoutons à la banque
        //On oublie pas qu'on commence à afficher à 1 et que la liste commence à 0 d'où le -1
      }while ((in > (p[n].sizeInventory() + 1)) || (in < 0));
      System.out.println("Vous avez déposé " + p[n].getInventory(in) + " au sol");
      map[p[n].getPos_x() + 12][p[n].getPos_y() + 12].addItem((p[n].getInventory(in)));
      // On ajout à la case
      p[n].removeInventory(p[n].getInventory(in));
      // Suppression des items de l'inventaire
    }
    else {
      System.out.println("Vous ne pouvez pas déposer un item ici, vous êtes en ville");
    }
  }

  // Tuer un zombie
  public static int killZombie(int i){
    int rand, end = 0;
    if (map[p[i].getPos_x() + 12][p[i].getPos_y() + 12].getZ() == 0){
      System.out.println("Il n'y a pas de zombies à tuer ici");
    }
    else {
      p[i].action();
      map[p[i].getPos_x() + 12][p[i].getPos_y() + 12].killZ();
      rand = r.nextInt(10); //On choisit un nombre random entre 0 et 9
      if (rand == 0){
        System.out.println("Durant l'attaque, vous avez été blessé. "
        + "Vous perdez 10 points de vie");
        p[i].lostPv(10);
        if (p[i].getPV() < 1){
          end = -1;
        }
      }
    }
    return end;
  }
}
