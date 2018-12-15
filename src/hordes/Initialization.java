package hordes;

class Initialization extends Hordes {
  // Initialisation de la Map
	public static void iniMap() {
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				map[i][j] = new Map(i-12, j-12);
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
				map[rand1][rand2].addHide_item("Boisson Energisante");
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
        map[rand1][rand2].addHide_item("Plaque de métal");
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
        map[rand1][rand2].addHide_item("Planche");
			}
			else {
				i--;
				// System.out.println("Sur la ville"); // Ligne pour vérifier ce qu'il se passe sur la ville
			}
		}
	}

	//Affiche les objets cachés sur toutes les cases
	public static void showMap() {
		for (byte i = 0; i < 25; i++) {
			for (byte j = 0; j < 25; j++) {
				System.out.print("\n[" + (i-12) + ";" + (j-12) + "] -- ");
				map[i][j].showHideItem();
			}
		}
	}

	//Affiche les objets cachés sur une seule case
	public static void showMap(int i, int j) {
		System.out.print("\n[" + (i) + ";" + (j) + "] -- ");
		map[i+12][j+12].showHideItem();
		System.out.println(" ");
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
}
