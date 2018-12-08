package hordes;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class Hordes {
	private static Player[] p = new Player [20]; // Création du tableau de joueur
	private static int nb_p = 0; // Numero du joueur (pour la création de joueur)
	private static Map[][] m = new Map [25][25]; // Création du tableau des cases
	private static ArrayList<String> temp_mort = new ArrayList<>(); // Liste des morts qui ont lieu durant la journée en cours
	private static ArrayList<String> mort = new ArrayList<>(); // Liste des morts qui ont eu lieu les 24 dernières heures
	private static ArrayList<String> old_mort = new ArrayList<>(); // Liste des morts qui ont eu lieu les 24 dernières heures
	private static Scanner scan = new Scanner (System.in); // Permet d'avoir des entrées
	private static Random r= new Random(); // Permet d'avoir des nombres aléatoires
	private static City city = new City(); // Variable ville (unique)
	private static int nb_j = 1; // Nombre de jour depuis le début (commence à 1)
	private static int nb_z = 0; // Nombre de zombies qui attaquent la nuit
	private static ArrayList<Integer> fiftyfifty = new ArrayList<>(); // Permet de choisir la moité lors de l'attaque en ville

	/* ----------------------------------------------------------------------- */
	/* --------------------------  INITIALISATION  --------------------------- */
	/* ----------------------------------------------------------------------- */

	// Initialisation de la Map
	public static void iniMap() {
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				m[i][j] = new Map(i-12, j-12);
				// On ne peut pas mettre de nombre négatif dans un tableau et on souhaite que la ville soit en [0;0]
				// Nous décallons donc de -12
			}
		}
		System.out.println ("La map a été initialisé");
	}


	public static void showMap() {
		for (byte i = 0; i < 25; i++) {
			for (byte j = 0; j < 25; j++) {
				System.out.print(i + " " + j + " -- ");
        m[i][j].showItem();
			}
		}
	}

	// Repartition des objets de maniere aleatoire
	public static void iniItems () {
		for (int i = 0; i < 100; i++) {
			// Initialisation des variables aléatoires sur les différentes cases du jeu
			int rand1 = r.nextInt(24);
			int rand2 = r.nextInt(24);
			System.out.println(i+"-rand1:"+rand1+"-rand2:"+rand2);
			if ((rand1 != 12)&&(rand2 != 12)) {
				// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
				m[rand1][rand2].addHide_item("Boisson Energisante");
			}
			else {
				i--;
			}
		}
		for (int i = 0; i < 500; i++) {
			// Initialisation des variables aléatoires sur les différentes cases du jeu
			int rand1 = r.nextInt(24);
			int rand2 = r.nextInt(24);
			System.out.println(i+"-rand1:"+rand1+"-rand2:"+rand2);
			if ((rand1 != 12)&&(rand2 != 12)) {
				// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
        m[rand1][rand2].addHide_item("Plaque de métal");
			}
			else {
				i--;
			}
		}
		for (int i = 0; i < 1000; i++) {
			// Initialisation des variables aléatoires sur les différentes cases du jeu
			int rand1 = r.nextInt(24);
			int rand2 = r.nextInt(24);
			System.out.println(i+"-rand1:"+rand1+"-rand2:"+rand2);
			if ((rand1 != 12)&&(rand2 != 12)) {
				// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
        m[rand1][rand2].addHide_item("Planche");
			}
			else {
				i--;
			}
		}
	}

	// Ajoute un joueur
	public static void addPlayer () {
		System.out.println("rentrer le nombre de joueurs");
		// On demande de rentrer le nombre de joueurs avec le scanner
		int nb_p = scan.nextInt();
		while (nb_p < 2 || nb_p > 20){
			System.out.println("Le nombre entré n'est pas valide");
			nb_p = scan.nextInt();
			// Si on rentre moins de 2 joueurs on redéfinie le nombre de joueurs
		}
    System.out.println("Le nombre entré est valide");
		// Si le nombre de joueurs est compirs entre 2 et 20 joueurs on rentre leurs prénoms
			for (int i = 0; i < nb_p; i++){
			// Pour le nombre de joueurs qu'on a initialisé
			System.out.println("rentrer le prénom du joueur "+ i);
			String firstname = scan.next();
			p[i] = new Player(firstname);
		}
	}

	// Calcul zombies sur une case
		public static void zMap () {
			int k = 0;
			int rand = 0;
			for (int i = 0; i < 25; i++) { //Parcours case par case
				for (int j = 0; j < 25; j++) {
					rand = r.nextInt(9); //Calcul d'un nombre random entre 0 et 9
						// Si rand = 0, 1 ou 2 OU si case de la ville alors il n'y a pas de zombies.
						if ((rand < 3) || (i == 12 && j == 12)) {
							m[i][j].setZ(0); //0 zombie sur cette case
						}
						else {
							rand = r.nextInt(7)+1; //Calcul d'un nombre entre 1 et 7
							m[i][j].setZ(rand); //Il y aura entre 1 et 7 zombie(s) sur cette case
						}
						k ++; //Vérification
						System.out.println("Sur la case [" + m[i][j].getCoor_x() + ";" + m[i][j].getCoor_y() + "], il y a " + m[i][j].getZ() + " zombies"); //Pour voir le nombre de zombies sur une case
				}
			}
			if (k == 625) {
				System.out.println("Les zombies ont bougé");
			}
		}





		/* ----------------------------------------------------------------------- */
		/* -------------------------------  BANK  -------------------------------- */
		/* ----------------------------------------------------------------------- */

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
		// Rappel : 0 = Planche, 1 = Plaque de métal, 2 = Boisson énergisante, 3 = Ration, 4 = Gourde d'eau
		public static void addBank (int n) { // n est le numéro du joueur enregistré dans le tableau
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
		public static void removeBank (int n) { // n est le numéro du joueur enregistré dans le tableau
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

		/* ----------------------------------------------------------------------- */
		/* --------------------------  ACTION EN VILLE  -------------------------- */
		/* ----------------------------------------------------------------------- */

		public static void takeWater(int n) { // n est le numéro du joueur enregistré dans le tableau
			p[n].addInventory("Gourde d'eau");
		}

		// Algo pour sortir de la ville
		public static void exitTown (int n) {// n est le numéro du joueur enregistré dans le tableau
			if (city.getDoor()) { // On vérifie que la prote est bien ouverte
					p[n].setInCity(false);
			}
			else {
				System.out.println("La porte est fermée, vous ne pouvez pas sortir de la ville");
			}
		}

		// Algo pour entrer dans la ville
		public static void enterTown (int n) {// n est le numéro du joueur enregistré dans le tableau
			if (city.getDoor()) { // On vérifie que la prote est bien ouverte
					p[n].setInCity(true);
			}
			else {
				System.out.println("La porte est fermée, vous ne pouvez pas entrer de la ville");
			}
		}
		// TODO: Chantier
		// Affiche la liste des chantiers
		public void displayBuild () {
			for (int i = 0; i < 7; i ++) {
				switch (i) {
					case 0: // Mur d'enceinte
					if (city.getBuild(i) == 0) {
						System.out.println ("Le mur d'enceinte est déjà terminé");
					}
					else {
						System.out.print ("Le mur d'enceinte nécessite 20 planches et 5 plaques de métal ");
						if ((city.getBank(0) < 20) && (city.getBank(1) < 5)) {
							System.out.println (", il manque " + (20 - city.getBank(0)) + " planche(s) et il manque " + (5 - city.getBank(1)) + " plaque(s) de métal");
						}
						else if (city.getBank(0) < 20) {
							System.out.println (", il manque " + (20 -city.getBank(0)) + " planche(s)");
						}
						else if (city.getBank(1) < 5) {
							System.out.println (", il manque " + (5 - city.getBank(1)) + " plaque(s) de métal");
						}
						else {
							System.out.println ("et vous devez investir " + city.getBuild(i) + " pa(s) pour finir ce chantier");
						}
					}
					break;
					case 1: // Fils barbelés
					if (city.getBuild(i) == 0) {
						System.out.println ("Les fils barbelés sont déjà terminés");
					}
					else {
						System.out.print ("Les fils barbelés nécessitent 20 planches et 30 plaques de métal ");
						if ((city.getBank(0) < 20) && (city.getBank(1) < 30)) {
							System.out.println (", il manque " + (20 - city.getBank(0)) + " planche(s) et il manque " + (30 - city.getBank(1)) + " plaque(s) de métal");
						}
						else if (city.getBank(0) < 20) {
							System.out.println (", il manque " + (20 - city.getBank(0)) + " planche(s)");
						}
						else if (city.getBank(1) < 30) {
							System.out.println (", il manque " + (30 - city.getBank(1)) + " plaque(s) de métal");
						}
						else {
							System.out.println ("et vous devez investir " + city.getBuild(i) + " pa(s) pour finir ce chantier");
						}
					}
					break;
					case 2: // Fosses à zombies
					if (city.getBuild(i) == 0) {
						System.out.println ("La fosse à zombies est déjà terminée");
					}
					else {
						System.out.print ("La fosse à zombies nécessite 50 planches et 25 plaques de métal ");
						if ((city.getBank(0) < 50) && (city.getBank(1) < 25)) {
							System.out.println (", il manque " + (50 -city.getBank(0)) + " planche(s) et il manque " + (25 - city.getBank(1)) + " plaque(s) de métal");
						}
						else if (city.getBank(0) < 50) {
							System.out.println (", il manque " + (50 - city.getBank(0)) + " planche(s)");
						}
						else if (city.getBank(1) < 25) {
							System.out.println (", il manque " + (25 - city.getBank(1)) + " plaque(s) de métal");
						}
						else {
							System.out.println ("et vous devez investir " + city.getBuild(i) + " pa(s) pour finir ce chantier");
						}
					}
					break;
					case 3: // Mines autour de la ville
					if (city.getBuild(i) == 0) {
						System.out.println ("Les mines autour de la ville sont déjà placées");
					}
					else {
						System.out.print ("Les mines nécessitent 10 planches et 50 plaques de métal ");
						if ((city.getBank(0) < 10) && (city.getBank(1) < 50)) {
							System.out.println (", il manque " + (10 - city.getBank(0)) + " planche(s) et il manque " + (50 - city.getBank(1)) + " plaque(s) de métal");
						}
						else if (city.getBank(0) < 10) {
							System.out.println (", il manque " + (10 - city.getBank(0)) + " planche(s)");
						}
						else if (city.getBank(1) < 50) {
							System.out.println (", il manque " + (50 - city.getBank(1)) + " plaque(s) de métal");
						}
						else {
							System.out.println ("et vous devez investir " + city.getBuild(i) + " pa(s) pour finir ce chantier");
						}
					}
					break;
					case 4: // Portes blindées
					if (city.getBuild(i) == 0) {
						System.out.println ("Les portes blindées sont déjà terminées");
					}
					else {
						System.out.print ("Les portes blindées nécessitent 50 planches et 50 plaques de métal ");
						if ((city.getBank(0) < 50) && (city.getBank(1) < 50)) {
							System.out.println (", il manque " + (50 - city.getBank(0)) + " planche(s) et il manque " + (50 - city.getBank(1)) + " plaque(s) de métal");
						}
						else if (city.getBank(0) < 50) {
							System.out.println (", il manque " + (50 - city.getBank(0)) + " planche(s)");
						}
						else if (city.getBank(1) < 50) {
							System.out.println (", il manque " + (50 - city.getBank(1)) + " plaque(s) de métal");
						}
						else {
							System.out.println ("et vous devez investir " + city.getBuild(i) + " pa(s) pour finir ce chantier");
						}
					}
					break;
					case 5: // Miradors avec mitrailleuses automatisés
					if (city.getBuild(i) == 0) {
						System.out.println ("Les miradors avec mitrailleuses automatisés sont déjà terminées");
					}
					else {
						System.out.print ("Les miradors avec mitrailleuses automatisés nécessitent 50 planches et 50 plaques de métal ");
						if ((city.getBank(0) < 75) && (city.getBank(1) < 75)) {
							System.out.println (", il manque " + (75 - city.getBank(0)) + " planche(s) et il manque " + (75 - city.getBank(1)) + " plaque(s) de métal");
						}
						else if (city.getBank(0) < 75) {
							System.out.println (", il manque " + (75 - city.getBank(0)) + " planche(s)");
						}
						else if (city.getBank(1) < 75) {
							System.out.println (", il manque " + (75 - city.getBank(1)) + " plaque(s) de métal");
						}
						else {
							System.out.println ("et vous devez investir " + city.getBuild(i) + " pa(s) pour finir ce chantier");
						}
					}
					break;
					case 6: // Abris anti-atomique
					if (city.getBuild(i) == 0) {
						System.out.println ("Les miradors avec mitrailleuses automatisés sont déjà terminées");
					}
					else {
						System.out.print ("Les miradors avec mitrailleuses automatisés nécessitent 50 planches et 50 plaques de métal ");
						if ((city.getBank(0) < 100) && (city.getBank(1) < 200)) {
							System.out.println (", il manque " + (100 - city.getBank(0)) + " planche(s) et il manque " + (200 - city.getBank(1)) + " plaque(s) de métal");
						}
						else if (city.getBank(0) < 100) {
							System.out.println (", il manque " + (100 - city.getBank(0)) + " planche(s)");
						}
						else if (city.getBank(1) < 100) {
							System.out.println (", il manque " + (75 - city.getBank(1)) + " plaque(s) de métal");
						}
						else {
							System.out.println ("et vous devez investir " + city.getBuild(i) + " pa(s) pour finir ce chantier");
						}
					}
					break;
					}
			}
		}

		/* ----------------------------------------------------------------------- */
		/* --------------------------------  MAP  -------------------------------- */
		/* ----------------------------------------------------------------------- */

		//Permet au joueur de se déplacer vers le nord
		public static void goNorth (int n) { // n est le numéro du joueur enregistré dans le tableau
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
			}
			else {
				p[n].setPos_y(p[n].getPos_y() - 1);
			}
		}

		//Permet au joueur de se déplacer vers le sud
		public static void goSouth (int n) { // n est le numéro du joueur enregistré dans le tableau
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
			}
			else {
				p[n].setPos_y(p[n].getPos_y() + 1);
			}
		}

		//Permet au joueur de se déplacer vers l'est
		public static void goEast (int n) { // n est le numéro du joueur enregistré dans le tableau
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
			}
			else {
				p[n].setPos_x(p[n].getPos_x() - 1);
			}
		}

		//Permet au joueur de se déplacer vers l'ouest
		public static void goWest (int n) { // n est le numéro du joueur enregistré dans le tableau
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
			}
			p[n].setPos_x(p[n].getPos_x() + 1);
		}

		//Permet de fouiller la zone
		public static void search (int n) { // n est le numéro du joueur enregistré dans le tableau
			String temp;
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus fouiller");
			}
			else if ((p[n].getPos_x() == 0) && (p[n].getPos_y() == 0)) {
				System.out.println("Vous êtes en ville, vous ne pouvez pas fouiller");
			}
			else if (m[p[n].getPos_x() + 12][p[n].getPos_x() + 12].getSearch()) { // Conversion de la coordonée réel en coordonée du tableau m
				System.out.println("Cette zone a déjà été intégralement fouillé, il serait inutil de la fouiller de nouveau");
			}
			else {
				if (m[p[n].getPos_x() + 12][p[n].getPos_x() + 12].isEmpty()) { // Permet de savoir si une case contient encore ou non des objets cachés
					System.out.println("Cette zone ne contient plus d'objet, elle a été intégralement fouillé");
				}
				else {
					temp = m[p[n].getPos_x() + 12][p[n].getPos_x() + 12].removeHide_item();
					System.out.print("Vous avez trouvé " + temp);
					if (p[n].sizeInventory()<10) {
						p[n].addInventory(temp);
						System.out.println(", il a été ajouté à votre inventaire");
					}
					else {
						m[p[n].getPos_x() + 12][p[n].getPos_x() + 12].addItem(temp);
						System.out.println(", votre inventaire est plein, vous avez posé " + temp + " au sol");
					}
				}
			}
		}

		//TODO: Ramasser un item

		// Ramasser un item au sol
		public static void takeItem (int n) { // n est le numéro du joueur enregistré dans le tableau

		}

		// Déposer un item sur le sol
		public static void dropItem (int n) { // n est le numéro du joueur enregistré dans le tableau
			if ((p[n].getPos_x() != 0) && (p[n].getPos_y() != 0)) {
				p[n].bankInventory(); // On affiche l'inventaire
				int temp = scan.nextInt() - 1; // On demande à l'utilisateur l'objet qu'il veut mettre à la bank, puis nous l'ajoutons à la banque
				//On oublie pas qu'on commence à afficher à 1 et que la liste commence à 0 d'où le -1
				 m[p[n].getPos_x() + 12][p[n].getPos_x() + 12].addItem((p[n].getInventory(temp))); // On ajout à la case
				p[n].removeInventory(p[n].getInventory(temp)); // Suppression des items de l'inventaire
			}
			else {
				System.out.println("Vous ne pouvez pas déposer un item ici, vous êtes en ville");
			}
		}

		/* ----------------------------------------------------------------------- */
		/* ---------------------------  REGAIN DE PA  ---------------------------- */
		/* ----------------------------------------------------------------------- */
		public static void drinkWater (int n) { // n est le numéro du joueur enregistré dans le tableau
			if ((p[n].containsInventory("Gourde d'eau")) && (p[n].getDrink() == false)) {
				System.out.println("Vous vous désaltérez, vous regagnez 6 PA");
				if (p[n].getNb_pa() < 5) {
					p[n].setNb_pa(p[n].getNb_pa() + 6);
				}
				else {
					p[n].setNb_pa(10);
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
				if (p[n].getNb_pa() < 5) {
					p[n].setNb_pa(p[n].getNb_pa() + 6);
				}
				else {
					p[n].setNb_pa(10);
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
				if (p[n].getNb_pa() < 7) {
					p[n].setNb_pa(p[n].getNb_pa() + 4);
				}
				else {
					p[n].setNb_pa(10);
				}
				p[n].addict();
			}
		}


		/* ----------------------------------------------------------------------- */
		/* ------------------------  CHANGEMENT JOUR TOUR  ----------------------- */
		/* ----------------------------------------------------------------------- */
		public static void changingTurn (){ //Algo de changement de tour (toutes les 2 heures)
			for (int i =0; i<nb_p; i++) { //On regarde chaque joueur
				if (p[i].getPV() != 0) {
					if (p[i].getNb_pa() < 7) { //Gain des pas
						p[i].setNb_pa(p[i].getNb_pa() + 4);
					}
					else {
						p[i].setNb_pa(10);
					}
					if (p[i].getAddict() != -1) { // On vérifie sa dépendance aux boissons énergisante
						p[i].addAddict();
						if (p[i].getAddict() > 2) {
							p[i].setPV(p[i].getPV() - 5);
						}
					}
					if (p[i].getPV() < 1) { // On vérifie s'il ne meurt pas à ce tour
						temp_mort.add(p[i].getPseudo());
						p[i].setPV(0); // Le joueur est mort, il a donc 0 PV
					}
				}
			}
		}

		public static void changingDay(){ //Algo de changement de jour (uniquement à 00h)
			int temp = 0;
			fiftyfifty.clear();
			if (mort.isEmpty() == false) { // On vérifie que la liste n'est pas vide (pour éviter un plantage)
				for (int i = 0; i < mort.size(); i++) {
					old_mort.add(mort.get(i)); // On archive les noms des morts
					mort.remove(i); // Et on les retire de la liste des morts les dernières 24h
				}
			}
			if (temp_mort.isEmpty() == false) { // On vérifie que la liste n'est pas vide (pour éviter un plantage)
				for (int i = 0; i < temp_mort.size(); i++) {
					mort.add(temp_mort.get(i)); // On met les morts de la journée dans la liste des morts les 24 dernières heures
					temp_mort.remove(i); // Et on les retire de la liste des morts de la journée
				}
			}
			for (int i = 0; i < nb_p; i++){ //On vérifie si chaque joueur est en ville
				if (p[i].getInCity()) {
					p[i].resetDay(); // Enlève le statut "a bu" et "a mangé"
				}
				else {
					mort.add(p[i].getPseudo()); //Si ce n'est pas le cas, on l'ajoute à la liste des morts
					p[i].setPV(0); // Le joueur est mort, il a donc 0 PV
				}
			}
			// Attaque des zombies sur la ville
			nb_z = r.nextInt(10) + 10*nb_j; // On choisit un nombre aléatoire entre 0 et 10 que l'on ajoute à 10* le nombre du jour
			nb_j ++; // On ajout un au nombre de jour
			if (city.getDefense() <= nb_z) {
				System.out.println("Les zombies ont réussi à passer");
				for (int i = 0; i < nb_p; i++) {
					if (p[i].getPV() != 0){
						fiftyfifty.add(i); // On ajoute le numéro de chaque joueur qui est encore en vie
					}
				}
				for (int i = 0; i < (fiftyfifty.size()/2 + fiftyfifty.size() % 2); i++) { // Nombre de joueur divisé par 2 + le reste de la division euclidienne
					temp = fiftyfifty.get(r.nextInt(fiftyfifty.size())); // On prend un nombre aléatoire dans la liste fiftyfifty
					System.out.print(p[temp].getPseudo() + " "); // On annonce que le joueur est mort
					mort.add(p[temp].getPseudo()); // On l'ajoute à la liste
					p[temp].setPV(0); // Le joueur est mort, il a donc 0 PV
				}
				System.out.println("sont morts durant l'attaque de cette nuit");
			}
		}



		/* ----------------------------------------------------------------------- */
		/* ------------------------------  JOURNAL  ------------------------------ */
		/* ----------------------------------------------------------------------- */
		// Affichage du journal reprenant les morts de la veille
		// Hameau obscur est le nom de la ville -> J'ai pris le nom d'une de mes villes quand j'y jouais ;)
		public static void consultNewspaper() {
			System.out.println ("Hameau Obscur - Jour " + nb_j);
			if (nb_j == 1) {
				System.out.println("Bienvenue au Hameau Obscur !!\nNous avons espéré que les zombies nous oublie, mais ce n'est pas le cas.\nNous allons donc devoir nous organiser et tenir, le Hameau Obscur ne doit pas tomber !");
			}
			else if (mort.isEmpty()) {
				System.out.println("Il n'y a pas eu de mort, malgré l'attaque des " + nb_z + " zombies sur le Hameau Obscur");
			}
			else {
				System.out.print("Voici les morts de la veille : ");
				for (int i = 0; i < mort.size(); i++) {
					System.out.println(mort.get(i) + " ");
				}
			}
		}
}
