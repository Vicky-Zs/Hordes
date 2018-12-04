package hordes;
import java.util.Scanner;
import java.util.ArrayList;

public class Hordes {
	private static Player[] p = new Player [20]; // Création du tableau de joueur
	private static int nb_p = 0; // Numero du joueur (pour la création de joueur)
	private static Map[][] m = new Map [25][25]; // Création du tableau des cases
	private static int[] bank = new int [5]; // Création du tableau d'objets en banque
	private static ArrayList<String> mort = new ArrayList<>(); // Liste des morts qui ont eu lieu les 24 dernières heures
	private static ArrayList<String> old_mort = new ArrayList<>(); // Liste des morts qui ont eu lieu les 24 dernières heures
	private static Scanner scan = new Scanner (System.in);
	private static City city = new City();
	// 0 = Planche, 1 = Plaque de métal, 2 = Boisson énergisante, 3 = Ration, 4 = Gourde d'eau

	/* ----------------------------------------------------------------------- */
	/* --------------------------  INITIALISATION  --------------------------- */
	/* ----------------------------------------------------------------------- */

	// Initialisation de la Map
	public static void iniMap() {
		for (byte i = 0; i < 25; i++) {
			for (byte j = 0; j < 25; j++) {
				m[i][j] = new Map(i-12, j-12);
				// On ne peut pas mettre de nombre négatif dans un tableau et on souhaite que la ville soit en [0;0]
				// Nous décallons donc de -12
			}
		}
		System.out.println ("La map a été initialisé");
	}

	// Initialisation de la Banque
	public static void iniBank(){
		for (byte i = 0; i < bank.length; i++) {
			bank[i] = 0; // On initialise bien à 0 toutes les cases de la bank
		}
		bank[3] = 50; // La ville commence avec 50 rations
		System.out.println ("La banque a été initialisé");
	}

	// Répartition des objets de manière aléatoire
	public static void iniItems () {
		int rand1 = 0; // Une variable aléatoire
		int rand2 = 0; //Une autre variable aléatoire
		for (int i = 0; i < 1000; i++) {
			if (i < 100) {
				do {
					rand1 = (int) Math.random()*25; // Nombre aléatoire entre 0 et 24
					rand2 = (int) Math.random()*25; // Nombre aléatoire entre 0 et 24
				}while ((rand1 != 12)&&(rand2 != 12));
				// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
				m[rand1][rand2].addHide_item("Boisson Energisante");
			}
			if (i < 500) {
				do {
					rand1 = (int) Math.random()*25; // Nombre aléatoire entre 0 et 24
					rand2 = (int) Math.random()*25; // Nombre aléatoire entre 0 et 24
				}while ((rand1 != 12)&&(rand2 != 12));
				// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
				m[rand1][rand2].addHide_item("Plaque de métal");
			}
			do {
				rand1 = (int) Math.random()*25; // Nombre aléatoire entre 0 et 24
				rand2 = (int) Math.random()*25; // Nombre aléatoire entre 0 et 24
			}while ((rand1 != 12)&&(rand2 != 12));
			// On vérifie que l'objet n'arrive pas dans la ville, si c'est le cas, on recommence la boucle do while
			m[rand1][rand2].addHide_item("Planche");
		}
		System.out.println ("Les objets ont été dispersés dans la carte");
	}

	// Ajoute un joueur
	public static void add_player (String pseudo) {
		if (nb_p < 20) {
				p[nb_p] = new Player(pseudo);
				nb_p ++;
		}
		else {
			System.out.println("Le nombre maximal de joueur est atteint");
		}
	}

	// Calcul zombies sur une case
		public static void Z_map () {
			int k = 0;
			int rand = 0;
			for (byte i = 0; i < 25; i++) { //Parcours case par case
				for (byte j = 0; j < 25; j++) {
					rand = (int) Math.random()*10; //Calcul d'un nombre random entre 0 et 9
						// Si rand = 0, 1 ou 2 OU si case de la ville alors il n'y a pas de zombies.
						if ((rand < 3) || (i == 12 && j == 12)) {
							m[i][j].setZ(0); //0 zombie sur cette case
						}
						else {
							rand = (int) Math.random()*7+1; //Calcul d'un nombre entre 1 et 7
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
		public static void consult_bank() {
				for (byte i = 0; i<bank.length; i++) {
				// 0 = Planche, 1 = Plaque de métal, 2 = Boisson énergisante, 3 = Ration, 4 = Gourde d'eau
					switch (i){
						case 0: //Pour les planches
							if (bank[i] == 0) {
								System.out.println("Il n'y a aucune planche");
							}
							else if (bank[i] == 1) {
								System.out.println("Il y a une planche");
							}
							else {
								System.out.println("Il y a " + bank[i] + " planches");
							}
						break;
						case 1: //Pour les plaques de métal
							if (bank[i] == 0) {
								System.out.println("Il n'y a aucune plaque de métal");
							}
							else if (bank[i] == 1) {
								System.out.println("Il y a une plaque de métal");
							}
							else {
								System.out.println("Il y a " + bank[i] + " plaques de métal");
							}
						break;
						case 2: //Pour les boissons énergisantes
							if (bank[i] == 0) {
								System.out.println("Il n'y a aucune boisson énergisante");
							}
							else if (bank[i] == 1) {
								System.out.println("Il y a une boisson énergisante");
							}
							else {
								System.out.println("Il y a " + bank[i] + " boissons énergisantes");
							}
						break;
						case 3: //Pour les rations
							if (bank[i] == 0) {
								System.out.println("Il n'y a aucune ration");
							}
							else if (bank[i] == 1) {
								System.out.println("Il y a une ration");
							}
							else {
								System.out.println("Il y a " + bank[i] + " rations");
							}
						break;
						case 4: //Pour les gourdes d'eau
							if (bank[i] == 0) {
								System.out.println("Il n'y a aucune gourde d'eau");
							}
							else if (bank[i] == 1) {
								System.out.println("Il y a une gourde d'eau");
							}
							else {
								System.out.println("Il y a " + bank[i] + " gourdes d'eau");
							}
						break;
						default:
							System.out.println("Erreur");
					}
				}
		}

		// Ajoute un item à la banque
		// Rappel : 0 = Planche, 1 = Plaque de métal, 2 = Boisson énergisante, 3 = Ration, 4 = Gourde d'eau
		public static void add_bank (int n) { // n est le numéro du joueur enregistré dans le tableau
			if ((p[n].getPos_x() == 0) && (p[n].getPos_y() == 0)) {
				p[n].bankInventory(); // On affiche l'inventaire
				int temp = scan.nextInt(); // On demande à l'utilisateur l'objet qu'il veut mettre à la bank, puis nous l'ajoutons à la banque
				if (p[n].getInventory(temp) == "Planche") {
					bank[0] = +1;
				}
				else if (p[n].getInventory(temp) == "Plaque de métal") {
					bank[1] = +1;
				}
				else if (p[n].getInventory(temp) == "Boisson énergisante") {
					bank[2] = +1;
				}
				else if (p[n].getInventory(temp) == "Ration") {
					bank[3] = +1;
				}
				else if (p[n].getInventory(temp) == "Gourde d'eau") {
					bank[4] = +1;
				}
				else {
					System.out.println ("Erreur dans l'ajout dans la banque");
				}
				p[n].removeInventory(p[n].getInventory(temp)); // Suppression des items de l'inventaire
			}
			else {
				System.out.println("Vous ne pouvez pas ajouter un item à la banque, vous n'êtes aps en ville");
			}
		}

		// Retrait d'un objet de la banque
		public static void remove_bank (int n) { // n est le numéro du joueur enregistré dans le tableau
			System.out.println("Quel objet voulez-vous prendre ? \n0 = Planche \n1 = Plaque de métal \n2 = Boisson énergisante \n3 = Ration \n4 = Gourde d'eau");
			int temp = scan.nextInt();
			switch(temp){ //On vérifie si l'objet est en bank, puis on l'ajoute à l'inventaire et on réduit de 1 le nombre contenu dans la banque
				case 0:
					if (bank[temp] == 0) {
						System.out.println("Il n'y a plus de planche dans la banque, vous ne pouvez pas en prendre");
					}
					else {
						System.out.println("Une planche a été ajouté à votre inventaire");
						p[n].addInventory("Planche");
						bank[temp] = -1;
					}
				break;
				case 1:
					if (bank[temp] == 0) {
						System.out.println("Il n'y a plus de plaque de métal dans la banque, vous ne pouvez pas en prendre");
					}
					else {
						System.out.println("Une plaque de métal a été ajouté à votre inventaire");
						p[n].addInventory("Plaque de métal");
						bank[temp] = -1;
					}
				break;
				case 2:
					if (bank[temp] == 0) {
						System.out.println("Il n'y a plus de boisson énergisante dans la banque, vous ne pouvez pas en prendre");
					}
					else {
						System.out.println("Une boisson énergisante a été ajouté à votre inventaire");
						p[n].addInventory("Boisson énergisante");
						bank[temp] = -1;
					}
				break;
				case 3:
					if (bank[temp] == 0) {
						System.out.println("Il n'y a plus de ration dans la banque, vous ne pouvez pas en prendre");
					}
					else {
						System.out.println("Une ration a été ajouté à votre inventaire");
						p[n].addInventory("Ration");
						bank[temp] = -1;
					}
				break;
				case 4:
					if (bank[temp] == 0) {
						System.out.println("Il n'y a plus de gourde d'eau dans la banque, vous ne pouvez pas en prendre");
					}
					else {
						System.out.println("Une gourde d'eau a été ajouté à votre inventaire");
						p[n].addInventory("Gourde d'eau");
						bank[temp] = -1;
					}
				break;
				default:
					System.out.println("Erreur");
			}
		}

		/* ----------------------------------------------------------------------- */
		/* --------------------------  ACTION EN VILLE  -------------------------- */
		/* ----------------------------------------------------------------------- */

		public static void take_water(int n) { // n est le numéro du joueur enregistré dans le tableau
			p[n].addInventory("Gourde d'eau");
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
		public static void fouiller(int n) { // n est le numéro du joueur enregistré dans le tableau
			if (p[n].getNb_pa() == 0) {
				System.out.println("Vous êtes fatigué, vous ne pouvez plus fouiller");
			}
			else if ((p[n].getPos_x() == 0) && (p[n].getPos_y() == 0)) {
				System.out.println("Vous êtes en ville, vous ne pouvez pas fouiller");
			}
			else {

			}
		}

		/* ----------------------------------------------------------------------- */
		/* ---------------------------  REGAIN DE PA  ---------------------------- */
		/* ----------------------------------------------------------------------- */

		public static void drink_water (int n) { // n est le numéro du joueur enregistré dans le tableau
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

		public static void eat_ration (int n) { // n est le numéro du joueur enregistré dans le tableau
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

		public static void drink_addict (int n) { //n est le numéro du joueur enregistré dans le tableau
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
		/* --------------------------  ETAT PHYSIQUE  ---------------------------- */
		/* ----------------------------------------------------------------------- */
		public static void changing_turn (){ //Algo de changement de tour (toutes les 2 heures)
			for (int i =0; i<nb_p; i++) { //On regarde chaque joueur
				if ((mort.contains(p[i].getPseudo()) == false) || (old_mort.contains(p[i].getPseudo()) == false)) {
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
						mort.add(p[i].getPseudo());
					}
				}
			}
		}

		public static void changing_day(){ //Algo de changement de jour (uniquement à 00h)
			for (int i = 0; i< nb_p; i++){ //On vérifie si chaque joueur est en ville
				if ((p[i].getPos_x() != 0) && (p[i].getPos_y() != 0)) {
					mort.add(p[i].getPseudo()); //Si ce n'est pas le cas, on l'ajoute à la liste des morts
				}
			}

			if (mort.isEmpty()) {
				System.out.println("Il n'y a pas eu de mort");
			}
			else {
				System.out.print("Voici les morts de la veille : ");
				for (int i = 0; i < mort.size(); i++) {
					System.out.println(mort.get(i) + " ");
					old_mort.add(mort.get(i));
					mort.remove(i);
				}
			}
		}
}
