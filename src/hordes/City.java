package hordes;

public class City {
	private int defense;
	private Boolean door; //True = Open, False = Fermer
	private int[] bank = new int [5]; // Création du tableau d'objets en banque
	// 0 = Planche, 1 = Plaque de métal, 2 = Boisson énergisante, 3 = Ration, 4 = Gourde d'eau
	private int[] build = new int [7]; // Création du tableau de pa pour le chantier
	// Tableau de chantier:
	// 0 = Mur d'enceinte
	// 1 = Fils barbelés
	// 2 = Fosses à zombies
	// 3 = Mines autour de la ville
	// 4 = Portes blindées
	// 5 = Miradors avec mitrailleuses automatisés
	// 6 = Abris anti-atomique

	public City() { // On considère que la ville commence les portes ouvertes et avec 10 de défense de base
		this.defense = 10;
		this.door = true;
		for (int i = 0; i < build.length; i++) {
			switch (i) { //On enregiste le nombre de pa nécessaire pour terminier le chantier
				case 0:
					this.build[i] = 10;
				break;
				case 1:
					this.build[i] = 20;
				break;
				case 2:
					this.build[i] = 30;
				break;
				case 3:
					this.build[i] = 30;
				break;
				case 4:
					this.build[i] = 40;
				break;
				case 5:
					this.build[i] = 50;
				break;
				case 6:
					this.build[i] = 60;
				break;
			}
			for (int j = 0; j < bank.length; j++) {
				this.bank[i] = 0; // On initialise bien à 0 toutes les cases de la bank
			}
			bank[3] = 50; // La ville commence avec 50 rations
			System.out.println ("La banque a été initialisé");
		}
	}

	// Obtenir le nombre de défense de la ville
	public int getDefense() {
		return defense;
	}

	// Modifie la désense de la ville
	public void setDefense(int defense) {
		this.defense = defense;
	}

	// Obtenir l'état de la porte
	public Boolean getDoor() {
		return door;
	}

	// Modifie l'état de la porte
	public void setDoor(Boolean door) {
		this.door = door;
	}

	// Obtenir le nombre d'un objet spécifique
	public int getBank(int i) {
		return bank[i];
	}

	// Modifie le nombre d'un objet spécifique
	public void setBank(int i, int nb) {
		this.bank[i] = nb;
	}

	// Obtenir le nombre d'un PA d'un chantier spécifique
	public int getBuild(int i) {
		return build[i];
	}

	// Modifie le nombre d'un PA d'un chantier spécifique
	public void setBuild(int i, int nb) {
		this.build[i] = nb;
	}

	// Affiche la liste des chantiers
	public void printBuild () {// n est le numéro du joueur enregistré dans le tableau
		for (int i = 0; i < 7; i ++) {
			switch (i) {
				case 0: // Mur d'enceinte
				if (build[i] == 0) {
					System.out.println ("Le mur d'enceinte est déjà terminé");
				}
				else {
					System.out.print ("Le mur d'enceinte nécessite 20 planches et 5 plaques de métal ");
					if ((bank[0] < 20) && (bank[1] < 5)) {
						System.out.println (", il manque " + (20 - bank[0]) + " planche(s) et il manque " + (5 - bank[1]) + " plaque(s) de métal");
					}
					else if (bank[0] < 20) {
						System.out.println (", il manque " + (20 - bank[0]) + " planche(s)");
					}
					else if (bank[1] < 5) {
						System.out.println (", il manque " + (5 - bank[1]) + " plaque(s) de métal");
					}
					else {
						System.out.println ("et vous devez investir " + build[i] + " pa(s) pour finir ce chantier");
					}
				}
				break;
				case 1: // Fils barbelés
				if (build[i] == 0) {
					System.out.println ("Les fils barbelés sont déjà terminés");
				}
				else {
					System.out.print ("Les fils barbelés nécessitent 20 planches et 30 plaques de métal ");
					if ((bank[0] < 20) && (bank[1] < 30)) {
						System.out.println (", il manque " + (20 - bank[0]) + " planche(s) et il manque " + (30 - bank[1]) + " plaque(s) de métal");
					}
					else if (bank[0] < 20) {
						System.out.println (", il manque " + (20 - bank[0]) + " planche(s)");
					}
					else if (bank[1] < 30) {
						System.out.println (", il manque " + (30 - bank[1]) + " plaque(s) de métal");
					}
					else {
						System.out.println ("et vous devez investir " + build[i] + " pa(s) pour finir ce chantier");
					}
				}
				break;
				case 2: // Fosses à zombies
				if (build[i] == 0) {
					System.out.println ("La fosse à zombies est déjà terminée");
				}
				else {
					System.out.print ("La fosse à zombies nécessite 50 planches et 25 plaques de métal ");
					if ((bank[0] < 50) && (bank[1] < 25)) {
						System.out.println (", il manque " + (50 - bank[0]) + " planche(s) et il manque " + (25 - bank[1]) + " plaque(s) de métal");
					}
					else if (bank[0] < 50) {
						System.out.println (", il manque " + (50 - bank[0]) + " planche(s)");
					}
					else if (bank[1] < 25) {
						System.out.println (", il manque " + (25 - bank[1]) + " plaque(s) de métal");
					}
					else {
						System.out.println ("et vous devez investir " + build[i] + " pa(s) pour finir ce chantier");
					}
				}
				break;
				case 3: // Mines autour de la ville
				if (build[i] == 0) {
					System.out.println ("Les mines autour de la ville sont déjà placées");
				}
				else {
					System.out.print ("Les mines nécessitent 10 planches et 50 plaques de métal ");
					if ((bank[0] < 10) && (bank[1] < 50)) {
						System.out.println (", il manque " + (10 - bank[0]) + " planche(s) et il manque " + (50 - bank[1]) + " plaque(s) de métal");
					}
					else if (bank[0] < 10) {
						System.out.println (", il manque " + (10 - bank[0]) + " planche(s)");
					}
					else if (bank[1] < 50) {
						System.out.println (", il manque " + (50 - bank[1]) + " plaque(s) de métal");
					}
					else {
						System.out.println ("et vous devez investir " + build[i] + " pa(s) pour finir ce chantier");
					}
				}
				break;
				case 4: // Portes blindées
				if (build[i] == 0) {
					System.out.println ("Les portes blindées sont déjà terminées");
				}
				else {
					System.out.print ("Les portes blindées nécessitent 50 planches et 50 plaques de métal ");
					if ((bank[0] < 50) && (bank[1] < 50)) {
						System.out.println (", il manque " + (50 - bank[0]) + " planche(s) et il manque " + (50 - bank[1]) + " plaque(s) de métal");
					}
					else if (bank[0] < 50) {
						System.out.println (", il manque " + (50 - bank[0]) + " planche(s)");
					}
					else if (bank[1] < 50) {
						System.out.println (", il manque " + (50 - bank[1]) + " plaque(s) de métal");
					}
					else {
						System.out.println ("et vous devez investir " + build[i] + " pa(s) pour finir ce chantier");
					}
				}
				break;
				case 5: // Miradors avec mitrailleuses automatisés
				if (build[i] == 0) {
					System.out.println ("Les miradors avec mitrailleuses automatisés sont déjà terminées");
				}
				else {
					System.out.print ("Les miradors avec mitrailleuses automatisés nécessitent 50 planches et 50 plaques de métal ");
					if ((bank[0] < 75) && (bank[1] < 75)) {
						System.out.println (", il manque " + (75 - bank[0]) + " planche(s) et il manque " + (75 - bank[1]) + " plaque(s) de métal");
					}
					else if (bank[0] < 75) {
						System.out.println (", il manque " + (75 - bank[0]) + " planche(s)");
					}
					else if (bank[1] < 75) {
						System.out.println (", il manque " + (75 - bank[1]) + " plaque(s) de métal");
					}
					else {
						System.out.println ("et vous devez investir " + build[i] + " pa(s) pour finir ce chantier");
					}
				}
				break;
				case 6: // Abris anti-atomique
				if (build[i] == 0) {
					System.out.println ("Les miradors avec mitrailleuses automatisés sont déjà terminées");
				}
				else {
					System.out.print ("Les miradors avec mitrailleuses automatisés nécessitent 50 planches et 50 plaques de métal ");
					if ((bank[0] < 100) && (bank[1] < 200)) {
						System.out.println (", il manque " + (100 - bank[0]) + " planche(s) et il manque " + (200 - bank[1]) + " plaque(s) de métal");
					}
					else if (bank[0] < 100) {
						System.out.println (", il manque " + (100 - bank[0]) + " planche(s)");
					}
					else if (bank[1] < 100) {
						System.out.println (", il manque " + (75 - bank[1]) + " plaque(s) de métal");
					}
					else {
						System.out.println ("et vous devez investir " + build[i] + " pa(s) pour finir ce chantier");
					}
				}
				break;
				}
		}
	}
}
