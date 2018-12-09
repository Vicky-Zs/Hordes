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
				this.bank[j] = 0; // On initialise bien à 0 toutes les cases de la bank
			}
		}
		bank[3] = 50; // La ville commence avec 50 rations
		System.out.println ("La banque a été initialisé");
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
	public int getBank(int i) { // i =  le numéro où l'objet est stocké
		return bank[i];
	}

	// Modifie le nombre d'un objet spécifique
	public void setBank(int i, int nb) { // i =  le numéro où l'objet est stocké et nb = le nouveau nombre d'objets en bank
		this.bank[i] = nb;
	}

	// Obtenir le nombre d'un PA d'un chantier spécifique
	public int getBuild(int i) { // i =  le numéro du chantier
		return build[i];
	}

	// Modifie le nombre d'un PA d'un chantier spécifique
	public void setBuild(int i, int nb) { // i =  le numéro du chantier et nb = le nouveau nombre de pa
		this.build[i] = nb;
	}
}
