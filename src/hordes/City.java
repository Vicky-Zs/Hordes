package hordes;

public class City {
	private int defense;
	private Boolean door; //True = Open, False = Fermer
	private int[] bank = new int [5]; // Création du tableau d'objets en banque
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
		this.build[0] = 10; // Nb de pa nécessaire pour finir ce chantier
		this.build[1] = 20; // Nb de pa nécessaire pour finir ce chantier
		this.build[2] = 30; // Nb de pa nécessaire pour finir ce chantier
		this.build[3] = 30; // Nb de pa nécessaire pour finir ce chantier
		this.build[4] = 40; // Nb de pa nécessaire pour finir ce chantier
		this.build[5] = 50; // Nb de pa nécessaire pour finir ce chantier
		this.build[6] = 60; // Nb de pa nécessaire pour finir ce chantier
		this.bank[0] = 0; // Nb de Planche en banque
		this.bank[1] = 0; // Nb de Plaque de métal en banque
		this.bank[2] = 0; // Nb de Boisson énergisante en banque
		this.bank[3] = 50; // La ville commence avec 50 rations
		this.bank[4] = 0; // Nb de d'objets en banque
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
