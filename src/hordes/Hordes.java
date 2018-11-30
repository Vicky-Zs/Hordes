package hordes;

public class Hordes {
	private static Player[] p = new Player [20]; // Création du tableau de joueur
	private static int nb_p = 0; // Numero du joueur (pour la création de joueur)
	private static Map[][] m = new Map [25][25]; // Création du tableau des cases
	private static int[] bank = new int [5]; // Création du tableau d'objets en banque
	// 0 = Planche, 1 = Plaque de métal, 2 = Boisson énergisante, 3 = Ration, 4 = Gourde d'eau

	/* ----------------------------------------------------------------------- */
	/* --------------------------  INITIALISATION  --------------------------- */
	/* ----------------------------------------------------------------------- */

	// Initialisation de la Map
	public static void iniMap() {
		for (byte i = 0; i < 25; i++) {
			for (byte j = 0; j < 25; j++) {
				m[i][j] = new Map(i-12, j-12);
			}
		}
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
					}
				}
		}

		public static void add_bank() {}

		/* ----------------------------------------------------------------------- */
		/* --------------------------  ACTION EN VILLE  -------------------------- */
		/* ----------------------------------------------------------------------- */

		public static void take_water(int n){ // n est le numéro du joueur enregistré dans le tableau
			p[n].addInventory("Gourde d'eau");
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
			else {
				System.out.println("Vous ne pouvez pas boire, vous n'avez pas d'eau sur vous ou vous avez déjà bu");
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
}
