package hordes;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class Hordes {
	private static Player[] p = new Player [20]; // Création du tableau de joueur
	private static int nb_p = 0; // Numero du joueur (pour la création de joueur)
	private static Map[][] m = new Map [25][25]; // Création du tableau des cases
	private static ArrayList<String> alive = new ArrayList<>(); // Liste des joueurs qui sont vivants
	private static ArrayList<String> temp_mort = new ArrayList<>(); // Liste des morts qui ont lieu durant la journée en cours
	private static ArrayList<String> mort = new ArrayList<>(); // Liste des morts qui ont eu lieu les 24 dernières heures
	private static ArrayList<String> old_mort = new ArrayList<>(); // Liste des morts qui ont eu lieu les 24 dernières heures
	private static Scanner scan = new Scanner (System.in); // Permet d'avoir des entrées
	private static Random r = new Random(); // Permet d'avoir des nombres aléatoires
	private static City city = new City(); // Variable ville (unique)
	private static int nb_day = 1; // Nombre de jour depuis le début (commence à 1)
	private static int nb_z = 0; // Nombre de zombies qui attaquent la nuit
	private static ArrayList<Integer> fiftyfifty = new ArrayList<>(); // Permet de choisir la moité lors de l'attaque en ville
	private static int nb_turn = 1; // Le numéro du tour, le tour 1 correspond de 0h à 2h, le tour de 2 de 2h à 4h, ..., le tour 12 correspon de 22h à 00h
        // A minuit, c'est l'attaque des zombies.

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

	// Repartition des objets de maniere aleatoire
	public static void iniItems () {
		for (int i = 0; i < 100; i++) {
			// Initialisation des variables aléatoires sur les différentes cases du jeu
			int rand1 = r.nextInt(25); // On choisit un nombre aléatoire entre 0 et 24
			int rand2 = r.nextInt(25); // On choisit un nombre aléatoire entre 0 et 24
			 // System.out.println(i+" - rand1 : "+rand1+" - rand2 : "+rand2); // Ligne pour vérifier les randoms
			if ((rand1 != 12)||(rand2 != 12)) {
				// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
				m[rand1][rand2].addHide_item("Boisson Energisante");
			}
			else {
				i--;
				// System.out.println("Sur la ville"); // Ligne pour vérifier ce qu'il se passe sur la ville
			}
		}
		for (int i = 0; i < 500; i++) {
			// Initialisation des variables aléatoires sur les différentes cases du jeu
			int rand1 = r.nextInt(25); // On choisit un nombre aléatoire entre 0 et 24
			int rand2 = r.nextInt(25); // On choisit un nombre aléatoire entre 0 et 24
			// System.out.println(i+" - rand1 : "+rand1+" - rand2 : "+rand2); // Ligne pour vérifier les randoms
			if ((rand1 != 12)||(rand2 != 12)) {
				// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
        m[rand1][rand2].addHide_item("Plaque de métal");
			}
			else {
				i--;
				// System.out.println("Sur la ville"); // Ligne pour vérifier ce qu'il se passe sur la ville
			}
		}
		for (int i = 0; i < 1000; i++) {
			// Initialisation des variables aléatoires sur les différentes cases du jeu
			int rand1 = r.nextInt(25); // On choisit un nombre aléatoire entre 0 et 24
			int rand2 = r.nextInt(25); // On choisit un nombre aléatoire entre 0 et 24
			// System.out.println(i+" -rand1 : "+rand1+" - rand2 : "+rand2); // Ligne pour vérifier les randoms
			if ((rand1 != 12)||(rand2 != 12)) {
				// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
        m[rand1][rand2].addHide_item("Planche");
			}
			else {
				i--;
				// System.out.println("Sur la ville"); // Ligne pour vérifier ce qu'il se passe sur la ville
			}
		}
	}

	//Affiche ce qu'il y a sur la map
	public static void showMap() {
		for (byte i = 0; i < 25; i++) {
			for (byte j = 0; j < 25; j++) {
				System.out.print(i + " " + j + " -- ");
				m[i][j].showHideItem();
			}
		}
	}

	// Ajoute un joueur
	public static void addPlayer () {
		String firstname;
		System.out.println("Entrer le nombre de joueurs : ");
		// On demande de rentrer le nombre de joueurs avec le scanner
		nb_p = scan.nextInt();
		while ((nb_p < 2) || (nb_p > 20)){
			System.out.println("Le nombre entré n'est pas valide");
			nb_p = scan.nextInt();
			// Si on rentre moins de 2 joueurs on redéfinie le nombre de joueurs
		}
    System.out.println("Le nombre entré est valide");
		// Si le nombre de joueurs est compirs entre 2 et 20 joueurs on rentre leurs prénoms
			for (int i = 0; i < nb_p; i++){
			// Pour le nombre de joueurs qu'on a initialisé
			System.out.println("Entrer le pseudo du joueur "+ (i+1));
			firstname = scan.next();
			while (alive.contains(firstname)){
				System.out.println("Veuillez choisir un autre pseudo, " + firstname + " est déjà pris");
				firstname = scan.next();
			}
			p[i] = new Player(firstname);
			alive.add(firstname);
		}
	}

	// Calcul zombies sur une case
		public static void zMap () {
			int k = 0;
			int rand = 0;
			for (int i = 0; i < 25; i++) { // Parcours case par case
				for (int j = 0; j < 25; j++) {
					rand = r.nextInt(10); // Calcul d'un nombre random entre 0 et 9
						// Si rand = 0, 1 ou 2 OU si case de la ville alors il n'y a pas de zombies.
						if ((rand < 3) || (i == 12 && j == 12)) {
							m[i][j].setZ(0); //0 zombie sur cette case
						}
						else {
							rand = r.nextInt(7)+1; //Calcul d'un nombre entre 1 et 7
							m[i][j].setZ(rand); //Il y aura entre 1 et 7 zombie(s) sur cette case
						}
						k ++; //Vérification
						// System.out.println("Sur la case [" + m[i][j].getCoor_x() + ";" + m[i][j].getCoor_y() + "], il y a " + m[i][j].getZ() + " zombies"); // Pour voir le nombre de zombies sur une case
				}
			}
			System.out.println("Les zombies ont bougé");
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

		// Affiche la liste des chantiers
		public static void displayBuild () {
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
							System.out.println (", il manque " + (50 - city.getBank(0)) + " planche(s) et il manque " + (25 - city.getBank(1)) + " plaque(s) de métal");
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

		public static void participateBuild (int n) { // n est le numéro du joueur enregistré dans le tableau
			System.out.println ("Sur quel chantier vous voulez travailler ?\n1 : Mur d'enceinte\n2 : Fils barbelés\n3 : Fosses à zombies\n4 : Mines autour de la ville\n5 : Portes blindées\n6 : Miradors avec mitrailleuses automatisés\n7 : Abris anti-atomique");
			int i = scan.nextInt() - 1;
			switch (i) {
				case 0:
					if ((city.getBank(0) < 20) && (city.getBank(1) < 5)){
						System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
					}
					else {
						System.out.println("Combien de pa voulez-vous mettre ?");
						i = scan.nextInt();
						while (i > p[n].getNb_pa()) {
							System.out.println("Vous n'avez pas assez de pa");
							i = scan.nextInt();
						}
						if (i > city.getBuild(0)) {
							i = city.getBuild(0);
						}
						System.out.println("Vous avez dépensé " + i + "pa dans le Mur d'enceinte");
						city.setBuild(0, (city.getBuild(0) - i));
						p[n].setNb_pa(p[n].getNb_pa() - i);
						if (city.getBuild(0) == 0) {
							System.out.println ("Le chantier est terminé");
							city.setDefense(city.getDefense() + 20);
						}
					}
				break;
				case 1:
					if ((city.getBank(0) < 20) && (city.getBank(1) < 30)){
						System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
					}
					else {
						System.out.println("Combien de pa voulez-vous mettre ?");
						i = scan.nextInt();
						while (i > p[n].getNb_pa()) {
							System.out.println("Vous n'avez pas assez de pa");
							i = scan.nextInt();
						}
						if (i > city.getBuild(1)) {
							i = city.getBuild(1);
						}
						System.out.println("Vous avez dépensé " + i + "pa dans les Fils barbelés");
						city.setBuild(1, (city.getBuild(1) - i));
						p[n].setNb_pa(p[n].getNb_pa() - i);
						if (city.getBuild(1) == 0) {
							System.out.println ("Le chantier est terminé");
							city.setDefense(city.getDefense() + 30);
						}
					}
				break;
				case 2:
					if ((city.getBank(0) < 50) && (city.getBank(1) < 25)){
						System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
					}
					else {
						System.out.println("Combien de pa voulez-vous mettre ?");
						i = scan.nextInt();
						while (i > p[n].getNb_pa()) {
							System.out.println("Vous n'avez pas assez de pa");
							i = scan.nextInt();
						}
						if (i > city.getBuild(2)) {
							i = city.getBuild(2);
						}
						System.out.println("Vous avez dépensé " + i + "pa dans les Fosses à zombies");
						city.setBuild(2, (city.getBuild(2) - i));
						p[n].setNb_pa(p[n].getNb_pa() - i);
						if (city.getBuild(2) == 0) {
							System.out.println ("Le chantier est terminé");
							city.setDefense(city.getDefense() + 50);
						}
					}
				break;
				case 3:
					if ((city.getBank(0) < 10) && (city.getBank(1) < 50)){
						System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
					}
					else {
						System.out.println("Combien de pa voulez-vous mettre ?");
						i = scan.nextInt();
						while (i > p[n].getNb_pa()) {
							System.out.println("Vous n'avez pas assez de pa");
							i = scan.nextInt();
						}
						if (i > city.getBuild(3)) {
							i = city.getBuild(3);
						}
						System.out.println("Vous avez dépensé " + i + "pa dans les Mines autour de la ville");
						city.setBuild(3, (city.getBuild(3) - i));
						p[n].setNb_pa(p[n].getNb_pa() - i);
						if (city.getBuild(3) == 0) {
							System.out.println ("Le chantier est terminé");
							city.setDefense(city.getDefense() + 50);
						}
					}
				break;
				case 4:
					if ((city.getBank(0) < 50) && (city.getBank(1) < 50)){
						System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
					}
					else {
						System.out.println("Combien de pa voulez-vous mettre ?");
						i = scan.nextInt();
						while (i > p[n].getNb_pa()) {
							System.out.println("Vous n'avez pas assez de pa");
							i = scan.nextInt();
						}
						if (i > city.getBuild(4)) {
							i = city.getBuild(4);
						}
						System.out.println("Vous avez dépensé " + i + "pa dans les Portes blindées");
						city.setBuild(4, (city.getBuild(4) - i));
						p[n].setNb_pa(p[n].getNb_pa() - i);
						if (city.getBuild(4) == 0) {
							System.out.println ("Le chantier est terminé");
							city.setDefense(city.getDefense() + 100);
						}
					}
				break;
				case 5:
					if ((city.getBank(0) < 75) && (city.getBank(1) < 75)){
						System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
					}
					else {
						System.out.println("Combien de pa voulez-vous mettre ?");
						i = scan.nextInt();
						while (i > p[n].getNb_pa()) {
							System.out.println("Vous n'avez pas assez de pa");
							i = scan.nextInt();
						}
						if (i > city.getBuild(5)) {
							i = city.getBuild(5);
						}
						System.out.println("Vous avez dépensé " + i + "pa dans les Miradors avec mitrailleuses automatisés");
						city.setBuild(5, (city.getBuild(5) - i));
						p[n].setNb_pa(p[n].getNb_pa() - i);
						if (city.getBuild(5) == 0) {
							System.out.println ("Le chantier est terminé");
							city.setDefense(city.getDefense() + 200);
						}
					}
				break;
				case 6:
					if ((city.getBank(0) < 100) && (city.getBank(1) < 200)){
						System.out.println("Vous ne pouvez pas participer à ce chantier, il manque des objets");
					}
					else {
						System.out.println("Combien de pa voulez-vous mettre ?");
						i = scan.nextInt();
						while (i > p[n].getNb_pa()) {
							System.out.println("Vous n'avez pas assez de pa");
							i = scan.nextInt();
						}
						if (i > city.getBuild(6)) {
							i = city.getBuild(6);
						}
						System.out.println("Vous avez dépensé " + i + "pa dans les Abris anti-atomique");
						city.setBuild(6, (city.getBuild(6) - i));
						p[n].setNb_pa(p[n].getNb_pa() - i);
						if (city.getBuild(6) == 0) {
							System.out.println ("Le chantier est terminé");
							city.setDefense(city.getDefense() + 500);
						}
					}
				break;
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
			else if (m[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getZ()>0) {
				System.out.println("Il y a des zombies, vous devez tous les tuer avant de bouger");
			}
			else if (p[n].getPos_y() + 12 == 0) {
				System.out.println("Vous êtes arrivé à la limite nord de la carte");
			}
			else {
				p[n].setPos_y(p[n].getPos_y() - 1);
				p[n].setNb_pa(p[n].getNb_pa() - 1);
			}
		}

		//Permet au joueur de se déplacer vers le sud
		public static void goSouth (int n) { // n est le numéro du joueur enregistré dans le tableau
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
			}
			else if (p[n].getPos_y() + 12 == 24) {
				System.out.println("Vous êtes arrivé à la limite sud de la carte");
			}
			else if (m[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getZ()>0) {
				System.out.println("Il y a des zombies, vous devez tous les tuer avant de bouger");
			}
			else {
				p[n].setPos_y(p[n].getPos_y() + 1);
				p[n].setNb_pa(p[n].getNb_pa() - 1);
			}
		}

		//Permet au joueur de se déplacer vers l'est
		public static void goEast (int n) { // n est le numéro du joueur enregistré dans le tableau
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
			}
			else if (p[n].getPos_x() + 12 == 24) {
				System.out.println("Vous êtes arrivé à la limite est de la carte");
			}
			else if (m[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getZ()>0) {
				System.out.println("Il y a des zombies, vous devez tous les tuer avant de bouger");
			}
			else {
				p[n].setPos_x(p[n].getPos_x() - 1);
				p[n].setNb_pa(p[n].getNb_pa() - 1);
			}
		}

		//Permet au joueur de se déplacer vers l'ouest
		public static void goWest (int n) { // n est le numéro du joueur enregistré dans le tableau
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus vous déplacer");
			}
			else if (p[n].getPos_y() + 12 == 0) {
				System.out.println("Vous êtes arrivé à la limite ouest de la carte");
			}
			else if (m[p[n].getPos_x() + 12][p[n].getPos_y() + 12].getZ()>0) {
				System.out.println("Il y a des zombies, vous devez tous les tuer avant de bouger");
			}
			else {
			p[n].setPos_x(p[n].getPos_x() + 1);
			p[n].setNb_pa(p[n].getNb_pa() - 1);
			}
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
				}
				if (p[i].getPV() < 1) { // On vérifie s'il ne meurt pas à ce tour
					temp_mort.add(p[i].getPseudo());
					alive.remove(p[i].getPseudo());
					p[i].setPV(0); // Le joueur est mort, il a donc 0 PV
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
			nb_z = r.nextInt(11) + (10 * nb_day); // On choisit un nombre aléatoire entre 0 et 10 que l'on ajoute à 10* le nombre du jour
			nb_day ++; // On ajout un au nombre de jour
			if (city.getDefense() <= nb_z) {
				System.out.println("Les zombies ont réussi à passer");
				for (int i = 0; i < nb_p; i++) {
					if (p[i].getPV() != 0){
						fiftyfifty.add(i); // On ajoute le numéro de chaque joueur qui est encore en vie
					}
				}
				for (int i = 0; i < (fiftyfifty.size()/2 + fiftyfifty.size() % 2); i++) { // Nombre de joueur divisé par 2 + le reste de la division euclidienne
					temp = fiftyfifty.get(r.nextInt(fiftyfifty.size()+1)); // On prend un nombre aléatoire dans la liste fiftyfifty
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
			System.out.println ("Hameau Obscur - Jour " + nb_day);
			if (nb_day == 1) {
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
			System.out.println("\n \n");
		}

		/* ----------------------------------------------------------------------- */
		/* --------------------------------  MENU  ------------------------------- */
		/* ----------------------------------------------------------------------- */
		public static void game() {
			String ok;
			int in = 0;
			while (alive.isEmpty() == false) {
				for (nb_turn = 1; nb_turn < 13 ; nb_turn ++) {
					System.out.println("\n \n \nJour " + nb_day + " - Tour " + nb_turn);
					for (int i = 0; i < nb_p; i ++) {
						if (alive.contains(p[i].getPseudo())) {
							System.out.println("\n \nC'est au tour de " + p[i].getPseudo() + "\nTapez ok");
					    ok = scan.next();
					    System.out.println("Vous avez " + p[i].getNb_pa() + " pa");
							do {
								System.out.println("Voulez-vous lire le journal ?\n0 = Non\n1 = Oui");
						    in = scan.nextInt();
						    if (in == 1) {
						      consultNewspaper();
									in = 0;
						    }
						    else if (in == 1923) {
						      p[i].setPV(0);
						      System.out.println(p[i].getPseudo() + " va mourir au prochain tour (Commande admin)");
									in = 0;
						    }
								else if (i != 0) {
									System.out.println("La réponse n'est pas acceptez, veuillez de nouveau entrer votre réponse");
								}
							}while (in != 0);
							do {
								System.out.println("\n \nQue voulez vous faire ?");
								if (p[i].getInCity()) {
									System.out.println("1 = Accéder à votre inventaire\n"
        					+ "2 = Accéder à la baque\n"
        					+ "3 = Prendre de l'eau\n"
									+ "4 = Participer aux chantiers\n"
									+ "5 = Accéder à la porte\n"
        					+ "6 = Sortir de la ville\n"
        					+ "0 = Passer son tour");
        					in = scan.nextInt();
									switch (in) {
										case 0:
										in = -1;
										break;
										// Niveau 1
										case 1:
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
														// Niveau 2
														case 1:
														p[i].getInventory();
														in = 0;
														break;
														// Niveau 2
														case 2:
														drinkWater(i);
														in = 0;
														break;
														// Niveau 2
														case 3:
														eatRation(i);
														in = 0;
														break;
														// Niveau 2
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
														// Niveau 2
														case 1:
														p[i].getInventory();
														in = 0;
														break;
														// Niveau 2
														case 2:
														drinkWater(i);
														in = 0;
														break;
														// Niveau 2
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
														// Niveau 2
														case 1:
														p[i].getInventory();
														in = 0;
														break;
														// Niveau 2
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
										break;
										// Niveau 1
										case 2:
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
														// Niveau 2
														case 1:
														consultBank();
														in = 0;
														break;
														// Niveau 2
														case 2:
														removeBank(i);
														in = 0;
														break;
														// Niveau 2
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
														// Niveau 2
														case 1:
														consultBank();
														in = 0;
														break;
														// Niveau 2
														case 2:
														addBank(i);
														in = 0;
														break;
														// Niveau 2
														case 3:
														removeBank(i);
														in = 0;
														break;
														// Niveau 2
														default:
														System.out.println("La réponse n'est pas acceptée, "
														+ "veuillez de nouveau entrer votre réponse");
													}
												}while (in !=0);
											}
										break;
										// Niveau 1
										case 3:
										takeWater(i);
										break;
										// Niveau 1
										case 4:
										displayBuild();
										if (p[i].getNb_pa()>0) {
											participateBuild(i);
										}
										else {
											System.out.println("Vous ne pouvez pas participer "
											+ "aux chantiers, vous êtes fatigué");
										}
										break;
										// Niveau 1
										case 5:
										if (city.getDoor()) {
											System.out.print("La porte est ouverte");
											if (p[i].getNb_pa() > 0) {
												System.out.print(", souhaitez-vous la fermer ? "
												+ "\n0 = Non \n1 = Oui");
												do {
													in = scan.nextInt();
													switch (in) {
														case 0:
														break;
														// Niveau 2
														case 1:
														p[i].setNb_pa(p[i].getNb_pa() - 1);
														city.setDoor(false);
														in = 0;
														break;
														// Niveau 2
														default:
														System.out.println("La réponse n'est pas acceptée, "
														+ "veuillez de nouveau entrer votre réponse");
													}
												}while (in != 0);
											}
										}
										else {
											System.out.print("La porte est fermée");
											if (p[i].getNb_pa() > 0) {
												System.out.print(", souhaitez-vous l'ouvrir ? "
												+ "\n0 = Non \n1 = Oui");
												do {
													in = scan.nextInt();
													switch (in) {
														case 0:
														break;
														// Niveau 2
														case 1:
														p[i].setNb_pa(p[i].getNb_pa() - 1);
														city.setDoor(true);
														in = 0;
														break;
														// Niveau 2
														default:
														System.out.println("La réponse n'est pas acceptée, "
														+ "veuillez de nouveau entrer votre réponse");
													}
												}while (in != 0);
											}
										}
										break;
										case 6:
										p[i].setInCity(false);
										System.out.println("Vous êtes sorti de la ville");
										break;
										default:
										System.out.println("La réponse n'est pas acceptée, "
										+ "veuillez de nouveau entrer votre réponse");
									}
								}
								else {

									in = -1;
									System.out.println("Le citoyen n'est pas en ville");
								}
							}while(in != -1);
						}
					}
					changingTurn();
					if (nb_turn == 12) {
						changingDay();
					}
				}
				nb_day ++;
			}
		}
	}
