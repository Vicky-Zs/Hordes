package hordes;

class Bank extends Hordes {
  //Consultation de la bank
  public static void consultBank() {
      for (int i = 0; i<5; i++) {
      // 0 = Planche, 1 = Plaque de métal, 2 = Boisson énergisante, 3 = Ration, 4 = Gourde d'eau
        switch (i){
          case 0: //Pour les planches
            if (city.getBank(i) == 0) {
              System.out.println("Il n'y a aucune planche");
            }
            else if (city.getBank(i) == 1) {
              System.out.println("Il y a une planche");
            }
            else {
              System.out.println("Il y a " + city.getBank(i) + " planches");
            }
          break;
          case 1: //Pour les plaques de métal
            if (city.getBank(i) == 0) {
              System.out.println("Il n'y a aucune plaque de métal");
            }
            else if (city.getBank(i) == 1) {
              System.out.println("Il y a une plaque de métal");
            }
            else {
              System.out.println("Il y a " + city.getBank(i) + " plaques de métal");
            }
          break;
          case 2: //Pour les boissons énergisantes
            if (city.getBank(i) == 0) {
              System.out.println("Il n'y a aucune boisson énergisante");
            }
            else if (city.getBank(i) == 1) {
              System.out.println("Il y a une boisson énergisante");
            }
            else {
              System.out.println("Il y a " + city.getBank(i) + " boissons énergisantes");
            }
          break;
          case 3: //Pour les rations
            if (city.getBank(i) == 0) {
              System.out.println("Il n'y a aucune ration");
            }
            else if (city.getBank(i) == 1) {
              System.out.println("Il y a une ration");
            }
            else {
              System.out.println("Il y a " + city.getBank(i) + " rations");
            }
          break;
          case 4: //Pour les gourdes d'eau
            if (city.getBank(i) == 0) {
              System.out.println("Il n'y a aucune gourde d'eau");
            }
            else if (city.getBank(i) == 1) {
              System.out.println("Il y a une gourde d'eau");
            }
            else {
              System.out.println("Il y a " + city.getBank(i) + " gourdes d'eau");
            }
          break;
          default:
            System.out.println("Erreur");
        }
      }
  }

  // Ajoute un item à la banque
  // Rappel : 0 = Planche, 1 = Plaque de métal, 2 = Boisson énergisante,
  // 3 = Ration, 4 = Gourde d'eau
  public static void addBank (int n) {
    // n est le numéro du joueur enregistré dans le tableau
    if ((p[n].getPos_x() == 0) && (p[n].getPos_y() == 0)) {
      p[n].bankInventory(); // On affiche l'inventaire
      int temp = scan.nextInt() - 1; // On demande à l'utilisateur l'objet qu'il veut mettre à la bank, puis nous l'ajoutons à la banque.
      //On oublie pas qu'on commence à afficher à 1 et que la liste commence à 0 d'où le -1
      if (p[n].getInventory(temp) == "Planche") {
        city.setBank(0, city.getBank(0) + 1);
      }
      else if (p[n].getInventory(temp) == "Plaque de métal") {
        city.setBank(1, city.getBank(1) + 1);
      }
      else if (p[n].getInventory(temp) == "Boisson énergisante") {
        city.setBank(2, city.getBank(2) + 1);
      }
      else if (p[n].getInventory(temp) == "Ration") {
        city.setBank(3, city.getBank(3) + 1);
      }
      else if (p[n].getInventory(temp) == "Gourde d'eau") {
        city.setBank(4, city.getBank(4) + 1);
      }
      else {
        System.out.println ("Erreur dans l'ajout dans la banque");
      }
      p[n].removeInventory(p[n].getInventory(temp)); // Suppression des items de l'inventaire
    }
    else {
      System.out.println("Vous ne pouvez pas ajouter un item à la banque, vous n'êtes pas en ville");
    }
  }

  // Retrait d'un objet de la banque
  public static void removeBank (int n) {
    // n est le numéro du joueur enregistré dans le tableau
    System.out.println("Quel objet voulez-vous prendre ? \n1 = Planche \n2 = Plaque de métal \n3 = Boisson énergisante \n4 = Ration \n5 = Gourde d'eau");
    int temp = scan.nextInt() - 1;
    switch(temp){ //On vérifie si l'objet est en bank, puis on l'ajoute à l'inventaire et on réduit de 1 le nombre contenu dans la banque
      case 0:
        if (city.getBank(temp) == 0) {
          System.out.println("Il n'y a plus de planche dans la banque, vous ne pouvez pas en prendre");
        }
        else {
          System.out.println("Une planche a été ajouté à votre inventaire");
          p[n].addInventory("Planche");
          city.setBank(temp, city.getBank(temp) - 1);
        }
      break;
      case 1:
        if (city.getBank(temp) == 0) {
          System.out.println("Il n'y a plus de plaque de métal dans la banque, vous ne pouvez pas en prendre");
        }
        else {
          System.out.println("Une plaque de métal a été ajouté à votre inventaire");
          p[n].addInventory("Plaque de métal");
          city.setBank(temp, city.getBank(temp) - 1);
        }
      break;
      case 2:
        if (city.getBank(temp) == 0) {
          System.out.println("Il n'y a plus de boisson énergisante dans la banque, vous ne pouvez pas en prendre");
        }
        else {
          System.out.println("Une boisson énergisante a été ajouté à votre inventaire");
          p[n].addInventory("Boisson énergisante");
          city.setBank(temp, city.getBank(temp) - 1);
        }
      break;
      case 3:
        if (city.getBank(temp) == 0) {
          System.out.println("Il n'y a plus de ration dans la banque, vous ne pouvez pas en prendre");
        }
        else {
          System.out.println("Une ration a été ajouté à votre inventaire");
          p[n].addInventory("Ration");
          city.setBank(temp, city.getBank(temp) - 1);
        }
      break;
      case 4:
        if (city.getBank(temp) == 0) {
          System.out.println("Il n'y a plus de gourde d'eau dans la banque, vous ne pouvez pas en prendre");
        }
        else {
          System.out.println("Une gourde d'eau a été ajouté à votre inventaire");
          p[n].addInventory("Gourde d'eau");
          city.setBank(temp, city.getBank(temp) - 1);
        }
      break;
      default:
        System.out.println("Erreur");
    }
  }
}
