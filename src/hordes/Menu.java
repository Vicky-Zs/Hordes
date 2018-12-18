package hordes;

class Menu extends Hordes {
  // Menu principal
  public static void mainMenu (int i) {
    int in;
    int exit = 0;
    do {
      System.out.println("\n \nQue voulez vous faire ?");
      if (p[i].getInCity()) {
        System.out.println("1 = Accéder à votre inventaire\n"
        + "2 = Accéder à la banque\n"
        + "3 = Prendre de l'eau\n"
        + "4 = Accéder aux chantiers\n"
        + "5 = Accéder à la porte");
        if (city.getDoor()) {
          System.out.println("6 = Sortir de la ville\n"
          + "7 = Consulter le talkie\n"
          + "8 = Consulter le journal\n"
          + "0 = Passer son tour");
        }
        else {
          System.out.println("6 = Consulter le talkie\n"
          + "7 = Consulter le journal\n"
          + "0 = Passer son tour");
        }
        in = scan.nextInt();
        if ((city.getDoor() == false) && (in>5)) {
          // On change la valeur pour que la valeur corresponde au menu
          in ++;
        }
        switch (in) {
          case 0:
          exit = -1;
          break;
          case 1:
          menuInventory(i);
          in = 0;
          break;
          case 2:
          menuBank(i);
          in = 0;
          break;
          case 3:
          Inside.takeWater(i);
          System.out.println("Vous avez pris de l'eau");
          in = 0;
          break;
          case 4:
          menuBuild(i);
          in = 0;
          break;
          case 5:
          menuDoor(i);
          in = 0;
          break;
          case 6:
          if (city.getDoor()) {
            if (p[i].getNb_ap() == 0) {
              System.out.println("Vous êtes fatigué, voulez-vous vraiment sorir "
              + "de la ville ?\n1 = Oui\n0 = Non");
              do {
                in = scan.nextInt();
                switch (in) {
                  case 0:
                  break;
                  case 1:
                  p[i].setInCity(false);
                  System.out.println("Vous êtes sorti de la ville");
                  in = 0;
                  break;
                  default:
                  System.out.println("La réponse n'est pas acceptée, "
                  + "veuillez de nouveau entrer votre réponse");
                }
              }while (in != 0);
            }
            else {
              p[i].setInCity(false);
              System.out.println("Vous êtes sorti de la ville");
            }
            in = 0;
          }
          else {
            System.out.println("La porte est fermée, vous ne pouvez pas sortir");
          }
          break;
          case 7:
          menuTalkie();
          in = 0;
          break;
          case 8:
          consultNewspaper();
          in = 0;
          break;
          default:
          System.out.println("La réponse n'est pas acceptée, "
          + "veuillez de nouveau entrer votre réponse");
        }
      }
      else {
        System.out.println("Vous êtes en [" + p[i].getPos_x() + ";" +p[i].getPos_y() + "]\n"
        + "Il y a " + map[p[i].getPos_x() + 12][p[i].getPos_y() + 12].getZ() + " zombie(s)\n"
        + "Vous avez " + p[i].getNb_ap() + " pa");
        if ((p[i].getPos_x() == 0) && p[i].getPos_y() == 0) {
          System.out.println("1 = Accéder à votre inventaire");
          if (city.getDoor()) {
            System.out.println("2 = Entrer en ville");
            if (p[i].getNb_ap() > 0){
              System.out.println("3 = Se déplacer");
            }
          }
          else {
            if (p[i].getNb_ap() > 0){
              System.out.println("2 = Se déplacer");
            }
          }
          System.out.println("0 = Passer son tour");
          do {
            in = scan.nextInt();
            if ((city.getDoor() == false) && (p[i].getNb_ap() > 0)) {
              in ++;
            }
            switch (in) {
              case 0:
              exit = -1;
              break;
              case 1:
              menuInventory(i);
              in = 0;
              break;
              case 2:
              if (city.getDoor()) {
                p[i].setInCity(true);
              }
              else {
                System.out.println("La porte est fermée, vous ne pouvez pas entrer");
              }
              in = 0;
              break;
              case 3:
              menuMove(i);
              in = 0;
              break;
            }
          }while (in != 0);
        }
        else if ((map[p[i].getPos_x() + 12][p[i].getPos_y() + 12].getZ() == 0) && (p[i].getNb_ap() > 0)){
          System.out.println("1 = Accéder à votre inventaire\n"
          + "2 = Fouiller \n"
          + "3 = Se déplacer \n"
          + "4 = Prendre un item \n"
          + "5 = Déposer un item \n"
          + "6 = Mettre à jour le trackie \n"
          + "7 = Consulter le talkie\n"
          + "0 = Passer son tour");
          do {
            in = scan.nextInt();
            switch (in) {
              case 0:
              exit = -1;
              break;
              case 1:
              menuInventory(i);
              in = 0;
              break;
              case 2:
              Outside.search(i);
              in = 0;
              break;
              case 3:
              menuMove(i);
              in = 0;
              break;
              case 4:
              Outside.takeItem(i);
              in = 0;
              break;
              case 5:
              Outside.dropItem(i);
              in = 0;
              break;
              case 6:
              map[p[i].getPos_x() + 12][p[i].getPos_y() + 12].talkie();
              in = 0;
              break;
              case 7:
              menuTalkie();
              in = 0;
              break;
              default:
              System.out.println("La réponse n'est pas acceptée, "
              + "veuillez de nouveau entrer votre réponse");
            }
          }while (in !=0);
        }
        else if (p[i].getNb_ap() == 0) {
          System.out.println("Vous êtes fatigué\n"
          + "1 = Accéder à votre inventaire\n"
          + "2 = Consulter le talkie\n"
          + "3 = Prendre un item \n"
          + "4 = Déposer un item \n"
          + "0 = Passer son tour");
          do {
            in = scan.nextInt();
            switch(in) {
              case 0:
              exit = -1;
              break;
              case 1:
              menuInventory(i);
              in = 0;
              break;
              case 2:
              menuTalkie();
              in = 0;
              break;
              case 3:
              Outside.takeItem(i);
              break;
              case 4:
              Outside.dropItem(i);
              break;
              default:
              System.out.println("La réponse n'est pas acceptée, "
              + "veuillez de nouveau entrer votre réponse");
            }
          }while (in != 0);
        }
        else {
          System.out.println("1 = Accéder à votre inventaire\n"
          + "2 = Tuer un zombie \n"
          + "0 = Passer son tour \n");
          do {
            in = scan.nextInt();
            switch (in) {
              case 0:
              exit = -1;
              break;
              case 1:
              menuInventory(i);
              in = 0;
              break;
              case 2:
              exit = Outside.killZombie(i);
              in = 0;
              break;
              default:
              System.out.println("La réponse n'est pas acceptée, "
              + "veuillez de nouveau entrer votre réponse");
            }
          } while (in != 0);
        }
      }
    }while(exit != -1);
  }

  public static void menuInventory (int i) {
    int in;
    do {
      if (p[i].containsInventory("Gourde d'eau") && (p[i].containsInventory("Ration"))) {
        System.out.println("Inventaire :\n"
        +"1 = Consulter votre inventaire\n"
        +"2 = Boire\n"
        +"3 = Manger\n"
        +"0 = Revenir au menu principal");
        do {
          in = scan.nextInt();
          switch(in){
            case 0:
            break;
            case 1:
            p[i].getInventory();
            in = 0;
            break;
            case 2:
            AP.drinkWater(i);
            in = 0;
            break;
            case 3:
            AP.eatRation(i);
            in = 0;
            break;
            default:
            System.out.println("La réponse n'est pas acceptée, "
            + "veuillez de nouveau entrer votre réponse");
          }
        }while(in != 0);
      }
      else if(p[i].containsInventory("Gourde d'eau")) {
        System.out.println("Inventaire :\n"
        +"1 = Consulter votre inventaire\n"
        +"2 = Boire\n"
        +"0 = Revenir au menu principal");
        do {
          in = scan.nextInt();
          switch (in) {
            case 0:
            break;
            case 1:
            p[i].getInventory();
            in = 0;
            break;
            case 2:
            AP.drinkWater(i);
            in = 0;
            break;
            default:
            System.out.println("La réponse n'est pas acceptée, "
            + "veuillez de nouveau entrer votre réponse");
          }
        }while(in != 0);
      }
      else if(p[i].containsInventory("Ration")){
        System.out.println("Inventaire :\n"
        +"1 = Consulter votre inventaire\n"
        +"2 = Manger\n"
        +"0 = Revenir au menu principal");
        do {
          in = scan.nextInt();
          switch (in) {
            case 0:
            break;
            case 1:
            p[i].getInventory();
            in = 0;
            break;
            case 2:
            AP.eatRation(i);
            in = 0;
            break;
            default:
            System.out.println("La réponse n'est pas acceptée, "
            + "veuillez de nouveau entrer votre réponse");
          }
        }while(in !=0);
      }
      else {
        p[i].getInventory();
        in = 0;
      }
    }while(in != 0);
  }

  public static void menuBank (int i) {
    int in;
    if (p[i].isEmpty()) {
      System.out.println ("Banque : \n"
      +"1 = Consulter la banque\n"
      +"2 = Prendre un objet à la banque\n"
      +"0 = Revenir au menu principal");
      do {
        in = scan.nextInt();
        switch (in) {
          case 0:
          break;
          case 1:
          Bank.consultBank();
          in = 0;
          break;
          case 2:
          Bank.removeBank(i);
          in = 0;
          break;
          default:
          System.out.println("La réponse n'est pas acceptée, "
          + "veuillez de nouveau entrer votre réponse");
        }
      }while (in !=0);
    }
    else {
      System.out.println ("Banque : \n"
      +"1 = Consulter la banque\n"
      +"2 = Déposer un objet à la banque\n"
      +"3 = Prendre un objet à la banque\n"
      +"0 = Revenir au menu principal");
      do {
        in = scan.nextInt();
        switch (in) {
          case 0:
          break;
          case 1:
          Bank.consultBank();
          in = 0;
          break;
          case 2:
          Bank.addBank(i);
          in = 0;
          break;
          case 3:
          Bank.removeBank(i);
          in = 0;
          break;
          default:
          System.out.println("La réponse n'est pas acceptée, "
          + "veuillez de nouveau entrer votre réponse");
        }
      }while (in !=0);
    }
  }

  public static void menuDoor (int i) {
    int in;
    if (city.getDoor()) {
      System.out.print("La porte est ouverte");
      if (p[i].getNb_ap() > 0) {
        System.out.println(", souhaitez-vous la fermer ? "
        + "\n0 = Non \n1 = Oui");
        do {
          in = scan.nextInt();
          switch (in) {
            case 0:
            break;
            case 1:
            p[i].action();
            city.setDoor(false);
            System.out.println("Vous avez fermé la porte");
            in = 0;
            break;
            default:
            System.out.println("La réponse n'est pas acceptée, "
            + "veuillez de nouveau entrer votre réponse");
          }
        }while (in != 0);
      }
    }
    else {
      System.out.print("La porte est fermée,");
      if (p[i].getNb_ap() > 0) {
        System.out.print(" souhaitez-vous l'ouvrir ? "
        + "\n0 = Non \n1 = Oui");
        do {
          in = scan.nextInt();
          switch (in) {
            case 0:
            break;
            case 1:
            p[i].action();
            city.setDoor(true);
            System.out.println("Vous avez ouvert la porte");
            in = 0;
            break;
            default:
            System.out.println("La réponse n'est pas acceptée, "
            + "veuillez de nouveau entrer votre réponse");
          }
        }while (in != 0);
      }
    }
  }

  public static void menuMove (int i) {
    int in;
    System.out.println("Où voulez-vous allez ?\n"
    + "1 = Vers le Nord\n"
    + "2 = Vers l'Est\n"
    + "3 = Vers le Sud\n"
    + "4 = Vers l'Ouest\n"
    + "0 = Revenir au menu principal");
    do {
      in = scan.nextInt();
      switch (in) {
        case 0:
        break;
        case 1:
        Outside.goNorth(i);
        in = 0;
        break;
        case 2:
        Outside.goEast(i);
        in = 0;
        break;
        case 3:
        Outside.goSouth(i);
        in = 0;
        break;
        case 4:
        Outside.goWest(i);
        in = 0;
        break;
        default:
        System.out.println("La réponse n'est pas acceptée, "
        + "veuillez de nouveau entrer votre réponse");
      }
    }while (in != 0);
  }

  public static void menuBuild(int i) {
    int in;
    if (p[i].getNb_ap() > 0) {
      System.out.println("1 = Consulter l'avancement du chantier\n"
      +"2 = Participer au chantier \n"
      +"0 = Revenir au menu principal");
      do {
        in = scan.nextInt();
        switch (in) {
          case 0:
          break;
          case 1:
          Inside.displayBuild();
          in = 0;
          break;
          case 2:
          Inside.participateBuild(i);
          in = 0;
          break;
          default:
          System.out.println("La réponse n'est pas acceptée, "
          + "veuillez de nouveau entrer votre réponse");
        }
      } while (in != 0);
    }
    else {
      System.out.println("Vous ne pouvez pas participer "
      + "aux chantiers, vous êtes fatigué.\n"
      + "Souhaitez-vous consulter l'avancement des chantiers ?"
      + "1 = Oui \n 0 = Non");
      do {
        in = scan.nextInt();
        switch (in) {
          case 0:
          break;
          case 1:
          Inside.displayBuild();
          in = 0;
          break;
          default:
          System.out.println("La réponse n'est pas acceptée, "
          + "veuillez de nouveau entrer votre réponse");
        }
      } while (in != 0);
    }
  }

  public static void menuTalkie() {
    int in, i, j;
    System.out.println("1 = Consulter toute la carte\n"
    + "2 = Consulter seulement une case\n"
    + "0 = Revenir au menu principal");
    do {
      in = scan.nextInt();
      switch (in) {
        case 0:
        break;
        case 1:
        Talkie.consult();
        in = 0;
        break;
        case 2:
        System.out.println("Choisissez la coordonée x (entre -12 et +12)");
        do {
          i = scan.nextInt() + 12;
          if ((i < 0) || (i > 24)) {
            System.out.println("La réponse n'est pas acceptée, "
            + "veuillez de nouveau entrer votre réponse");
          }
        } while ((i < 0) || (i > 24));
        System.out.println("Choisissez la coordonée y (entre -12 et +12)");
        do {
          j = scan.nextInt() + 12;
          if ((i < 0) || (i > 24)) {
            System.out.println("La réponse n'est pas acceptée, "
            + "veuillez de nouveau entrer votre réponse");
          }
        } while ((j < 0) || (j > 24));
        Talkie.consult(i, j);
        in = 0;
        break;
        default:
        System.out.println("La réponse n'est pas acceptée, "
        + "veuillez de nouveau entrer votre réponse");
      }
    } while (in != 0);
  }
}
