package hordes;

class Inside extends Hordes {
  // Algo pour prendre de l'eau au puit
  public static void takeWater(int n) {
    // n est le numéro du joueur enregistré dans le tableau
    p[n].addInventory("Gourde d'eau");
  }

  // Algo pour sortir de la ville
  public static void exitTown (int n) {
    // n est le numéro du joueur enregistré dans le tableau
    if (!city.getDoor()) { // On vérifie que la prote est bien ouverte
        p[n].setInCity(false);
    }
    else {
      System.out.println("La porte est fermée, vous ne pouvez pas sortir de la ville");
    }
  }

  // Algo pour entrer dans la ville
  public static void enterTown (int n) {
    // n est le numéro du joueur enregistré dans le tableau
    if (!city.getDoor()) { // On vérifie que la prote est bien ouverte
        p[n].setInCity(true);
    }
    else {
      System.out.println("La porte est fermée, vous ne pouvez pas entrer de la ville");
    }
  }

  // Affiche la liste des chantiers
  public static void displayBuild () {
    for (int i = 0; i < 7; i ++) {
      switch (i) {
        case 0: // Mur d'enceinte
        if (city.getBuild(i) == 0) {
          System.out.println ("Le mur d'enceinte est déjà terminé\n \n");
        }
        else {
          System.out.println ("Mur d'enceinte :\n" +
          "20 planches et 5 plaques de métal ");
          if ((city.getBank(0) < 20) && (city.getBank(1) < 5)) {
            System.out.println (", il manque " + (20 - city.getBank(0))
            + " planche(s) et " + (5 - city.getBank(1))
            + " plaque(s) de métal \n \n");
          }
          else if (city.getBank(0) < 20) {
            System.out.println (", il manque " + (20 -city.getBank(0))
            + " planche(s) \n \n");
          }
          else if (city.getBank(1) < 5) {
            System.out.println (", il manque " + (5 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else {
            System.out.println ("et vous devez investir " + city.getBuild(i)
            + " ap(s) pour finir ce chantier\n \n");
          }
        }
        break;
        case 1: // Fils barbelés
        if (city.getBuild(i) == 0) {
          System.out.println ("Les fils barbelés sont déjà terminés\n \n");
        }
        else {
          System.out.println ("Fils barbelés : \n"
          +"20 planches et 30 plaques de métal ");
          if ((city.getBank(0) < 20) && (city.getBank(1) < 30)) {
            System.out.println (", il manque " + (20 - city.getBank(0))
            + " planche(s) et il manque " + (30 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else if (city.getBank(0) < 20) {
            System.out.println (", il manque " + (20 - city.getBank(0))
            + " planche(s)\n \n");
          }
          else if (city.getBank(1) < 30) {
            System.out.println (", il manque " + (30 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else {
            System.out.println ("et vous devez investir " + city.getBuild(i)
            + " ap(s) pour finir ce chantier\n \n");
          }
        }
        break;
        case 2: // Fosses à zombies
        if (city.getBuild(i) == 0) {
          System.out.println ("La fosse à zombies est déjà terminée\n \n");
        }
        else {
          System.out.println ("Fosse à zombies : \n "+
          "50 planches et 25 plaques de métal ");
          if ((city.getBank(0) < 50) && (city.getBank(1) < 25)) {
            System.out.println (", il manque " + (50 - city.getBank(0))
            + " planche(s) et il manque " + (25 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else if (city.getBank(0) < 50) {
            System.out.println (", il manque " + (50 - city.getBank(0))
            + " planche(s)\n \n");
          }
          else if (city.getBank(1) < 25) {
            System.out.println (", il manque " + (25 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else {
            System.out.println ("et vous devez investir " + city.getBuild(i)
            + " ap(s) pour finir ce chantier\n \n");
          }
        }
        break;
        case 3: // Mines autour de la ville
        if (city.getBuild(i) == 0) {
          System.out.println ("Les mines autour de la ville sont déjà placées\n \n");
        }
        else {
          System.out.print ("Mines \n10 planches et 50 plaques de métal ");
          if ((city.getBank(0) < 10) && (city.getBank(1) < 50)) {
            System.out.println (", il manque " + (10 - city.getBank(0))
            + " planche(s) et il manque "
            + (50 - city.getBank(1)) + " plaque(s) de métal \n \n");
          }
          else if (city.getBank(0) < 10) {
            System.out.println (", il manque " + (10 - city.getBank(0))
            + " planche(s)\n \n");
          }
          else if (city.getBank(1) < 50) {
            System.out.println (", il manque " + (50 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else {
            System.out.println ("et vous devez investir " + city.getBuild(i)
            + " ap(s) pour finir ce chantier\n \n");
          }
        }
        break;
        case 4: // Portes blindées
        if (city.getBuild(i) == 0) {
          System.out.println ("Les portes blindées sont déjà terminées\n \n");
        }
        else {
          System.out.println ("Les portes blindées :\n"
          +"50 planches et 50 plaques de métal ");
          if ((city.getBank(0) < 50) && (city.getBank(1) < 50)) {
            System.out.println (", il manque " + (50 - city.getBank(0))
            + " planche(s) et il manque " + (50 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else if (city.getBank(0) < 50) {
            System.out.println (", il manque " + (50 - city.getBank(0))
            + " planche(s)\n \n");
          }
          else if (city.getBank(1) < 50) {
            System.out.println (", il manque " + (50 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else {
            System.out.println ("et vous devez investir " + city.getBuild(i)
            + " ap(s) pour finir ce chantier\n \n");
          }
        }
        break;
        case 5: // Miradors avec mitrailleuses automatisés
        if (city.getBuild(i) == 0) {
          System.out.println ("Les miradors avec mitrailleuses "
          + "automatisés sont déjà terminées\n \n");
        }
        else {
          System.out.println ("Miradors avec mitrailleuses automatisés: \n"
          + "nécessitent 50 planches et 50 plaques de métal ");
          if ((city.getBank(0) < 75) && (city.getBank(1) < 75)) {
            System.out.println (", il manque " + (75 - city.getBank(0))
            + " planche(s) et il manque " + (75 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else if (city.getBank(0) < 75) {
            System.out.println (", il manque " + (75 - city.getBank(0))
            + " planche(s)\n \n");
          }
          else if (city.getBank(1) < 75) {
            System.out.println (", il manque " + (75 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else {
            System.out.println ("et vous devez investir " + city.getBuild(i)
            + " ap(s) pour finir ce chantier\n \n");
          }
        }
        break;
        case 6: // Abris anti-atomique
        if (city.getBuild(i) == 0) {
          System.out.println ("Les abris anti-atomique sont déjà construits"
          + " \n \n");
        }
        else {
          System.out.println ("Miradors avec mitrailleuses automatisés : \n"
          + "50 planches et 50 plaques de métal ");
          if ((city.getBank(0) < 100) && (city.getBank(1) < 200)) {
            System.out.println (", il manque " + (100 - city.getBank(0))
            + " planche(s) et il manque " + (200 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else if (city.getBank(0) < 100) {
            System.out.println (", il manque " + (100 - city.getBank(0))
            + " planche(s)\n \n");
          }
          else if (city.getBank(1) < 100) {
            System.out.println (", il manque " + (75 - city.getBank(1))
            + " plaque(s) de métal\n \n");
          }
          else {
            System.out.println ("et vous devez investir " + city.getBuild(i)
            + " ap(s) pour finir ce chantier\n \n");
          }
        }
        break;
        }
    }
  }

  // Permet de participer au chantier
  public static void participateBuild (int n) { // n est le numéro du joueur enregistré dans le tableau
    int i = 0;
    int j = 1;
    int in;
    System.out.println ("Sur quel chantier vous voulez travailler ?");
    if ((city.getBank(0) > 19) && (city.getBank(1) > 4)) {
      System.out.println ("1 : Mur d'enceinte");
    }
    if ((city.getBank(0) > 19) && city.getBank(1) > 29) {
      System.out.println ("2 : Fils barbelés");
    }
    if ((city.getBank(0) > 49) && city.getBank(1) > 24) {
      System.out.println ("3 : Fosses à zombies");
    }
    if ((city.getBank(0) > 9) && city.getBank(1) > 49) {
      System.out.println ("4 : Mines autour de la ville");
    }
    if ((city.getBank(0) > 49) && city.getBank(1) > 49) {
      System.out.println ("5 : Portes blindées");
    }
    if ((city.getBank(0) > 74) && city.getBank(1) > 74) {
      System.out.println ("6 : Miradors avec mitrailleuses automatisés");
    }
    if ((city.getBank(0) > 74) && city.getBank(1) > 74) {
      System.out.println ("7 : Abris anti-atomique");
    }
    System.out.println("0 = Revenir au Menu principal\n"
    +"Les chantiers non-affichés sont des chantiers sur lesquels "
    + "vous ne pouvez pas participer par manque de ressource");

    do {
      in = scan.nextInt();
      switch (in) {
        case 0:
        break;
        case 1:
        if ((city.getBank(0) < 20) && (city.getBank(1) < 5)){
          System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
        }
        else {
          System.out.println("Combien de pa voulez-vous mettre ?");
          i = scan.nextInt();
          while (i > p[n].getNb_ap()) {
            System.out.println("Vous n'avez pas assez de pa");
            i = scan.nextInt();
          }
          if (i > city.getBuild(0)) {
            i = city.getBuild(0);
          }
          System.out.println("Vous avez dépensé " + i + "pa dans le Mur d'enceinte");
          city.setBuild(0, (city.getBuild(0) - i));
          p[n].setNb_ap(p[n].getNb_ap() - i);
          if (city.getBuild(0) == 0) {
            System.out.println ("Le chantier est terminé");
            city.setDefense(city.getDefense() + 20);
          }
        }
        in = 0;
        break;
        case 2:
        if ((city.getBank(0) < 20) && (city.getBank(1) < 30)){
          System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
        }
        else {
          System.out.println("Combien de pa voulez-vous mettre ?");
          i = scan.nextInt();
          while (i > p[n].getNb_ap()) {
            System.out.println("Vous n'avez pas assez de pa");
            i = scan.nextInt();
          }
          if (i > city.getBuild(1)) {
            i = city.getBuild(1);
          }
          System.out.println("Vous avez dépensé " + i + "pa dans les Fils barbelés");
          city.setBuild(1, (city.getBuild(1) - i));
          p[n].setNb_ap(p[n].getNb_ap() - i);
          if (city.getBuild(1) == 0) {
            System.out.println ("Le chantier est terminé");
            city.setDefense(city.getDefense() + 30);
          }
        }
        in = 0;
        break;
        case 3:
        if ((city.getBank(0) < 50) && (city.getBank(1) < 25)){
          System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
        }
        else {
          System.out.println("Combien de pa voulez-vous mettre ?");
          i = scan.nextInt();
          while (i > p[n].getNb_ap()) {
            System.out.println("Vous n'avez pas assez de pa");
            i = scan.nextInt();
          }
          if (i > city.getBuild(2)) {
            i = city.getBuild(2);
          }
          System.out.println("Vous avez dépensé " + i + "pa dans les Fosses à zombies");
          city.setBuild(2, (city.getBuild(2) - i));
          p[n].setNb_ap(p[n].getNb_ap() - i);
          if (city.getBuild(2) == 0) {
            System.out.println ("Le chantier est terminé");
            city.setDefense(city.getDefense() + 50);
          }
        }
        in = 0;
        break;
        case 4:
        if ((city.getBank(0) < 10) && (city.getBank(1) < 50)){
          System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
        }
        else {
          System.out.println("Combien de pa voulez-vous mettre ?");
          i = scan.nextInt();
          while (i > p[n].getNb_ap()) {
            System.out.println("Vous n'avez pas assez de pa");
            i = scan.nextInt();
          }
          if (i > city.getBuild(3)) {
            i = city.getBuild(3);
          }
          System.out.println("Vous avez dépensé " + i + "pa dans les Mines autour de la ville");
          city.setBuild(3, (city.getBuild(3) - i));
          p[n].setNb_ap(p[n].getNb_ap() - i);
          if (city.getBuild(3) == 0) {
            System.out.println ("Le chantier est terminé");
            city.setDefense(city.getDefense() + 50);
          }
        }
        in = 0;
        break;
        case 5:
        if ((city.getBank(0) < 50) && (city.getBank(1) < 50)){
          System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
        }
        else {
          System.out.println("Combien de pa voulez-vous mettre ?");
          i = scan.nextInt();
          while (i > p[n].getNb_ap()) {
            System.out.println("Vous n'avez pas assez de pa");
            i = scan.nextInt();
          }
          if (i > city.getBuild(4)) {
            i = city.getBuild(4);
          }
          System.out.println("Vous avez dépensé " + i + "pa dans les Portes blindées");
          city.setBuild(4, (city.getBuild(4) - i));
          p[n].setNb_ap(p[n].getNb_ap() - i);
          if (city.getBuild(4) == 0) {
            System.out.println ("Le chantier est terminé");
            city.setDefense(city.getDefense() + 100);
          }
        }
        in = 0;
        break;
        case 6:
        if ((city.getBank(0) < 75) && (city.getBank(1) < 75)){
          System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
        }
        else {
          System.out.println("Combien de pa voulez-vous mettre ?");
          i = scan.nextInt();
          while (i > p[n].getNb_ap()) {
            System.out.println("Vous n'avez pas assez de pa");
            i = scan.nextInt();
          }
          if (i > city.getBuild(5)) {
            i = city.getBuild(5);
          }
          System.out.println("Vous avez dépensé " + i + "pa dans les Miradors avec mitrailleuses automatisés");
          city.setBuild(5, (city.getBuild(5) - i));
          p[n].setNb_ap(p[n].getNb_ap() - i);
          if (city.getBuild(5) == 0) {
            System.out.println ("Le chantier est terminé");
            city.setDefense(city.getDefense() + 200);
          }
        }
        in = 0;
        break;
        case 7:
        if ((city.getBank(0) < 100) && (city.getBank(1) < 200)){
          System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
        }
        else {
          System.out.println("Combien de pa voulez-vous mettre ?");
          i = scan.nextInt();
          while (i > p[n].getNb_ap()) {
            System.out.println("Vous n'avez pas assez de pa");
            i = scan.nextInt();
          }
          if (i > city.getBuild(6)) {
            i = city.getBuild(6);
          }
          System.out.println("Vous avez dépensé " + i + "pa dans les Abris anti-atomique");
          city.setBuild(6, (city.getBuild(6) - i));
          p[n].setNb_ap(p[n].getNb_ap() - i);
          if (city.getBuild(6) == 0) {
            System.out.println ("Le chantier est terminé");
            city.setDefense(city.getDefense() + 500);
          }
        }
        in = 0;
        break;
        default:
        System.out.println("La réponse n'est pas acceptée, "
        + "veuillez de nouveau entrer votre réponse");
      }
    } while (in !=0);
  }
}
