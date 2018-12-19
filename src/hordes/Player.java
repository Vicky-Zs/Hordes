package hordes;

import java.util.ArrayList;

public class Player {
  private String pseudo; //Pseudo du joueur
  private int nb_ap; //Nombre de pa allant de 0 à 6
	private int pos_x; // Position du joueur allant de -12 à 12
	private int pos_y; //Position du joueur allant de -12 à 12
	private int hp; //Point de vie du joueur (Si tombe à 0 ou moins, le joueur est mort)
	private boolean drink; //Si vrai, il a déjà bu et ne peut plus boire
	private boolean eat; //Si vrai, le joueur a déjà mangé et ne peut plus mangé
	private int addict; // -1 le joueur n'est pas dépendant, si 0 ou plus, permet de savoir le nombre de tour sans la prise de boisson énergisante
  private boolean inCity; // Si c'est vrai, il est en ville, sinon il est en dehors
	private ArrayList<String> inventory = new ArrayList<>(); // Inventaire du joueur

	//Constructeur
	public Player(String pseudo) {
		this.pseudo = pseudo;
		this.nb_ap = 6; // On commence avec 6 pa
		this.pos_x = 0; // On commence en ville
		this.pos_y = 0; // On commence en ville
		this.hp = 100; // On commence avec 100 points de vie
		this.drink = false;
		this.eat = false;
		this.addict = -1;
		this.inCity = true;
		// Un nouveau joueur arrive dans la ville, avec les PV max, le nombre de pa Max, ect...
	}

	// Obtient le pseudo du joueur
	public String getPseudo() {
		return pseudo;
	}

	// Modifie le pseudo du joueur
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	// Obtient le nombre de pa du joueur
	public int getNb_ap() {
		return nb_ap;
	}

	// Modifie le nombre de pa du joueur
	public void setNb_ap(int nb_ap) {
		this.nb_ap = nb_ap;
	}

  // Perte de 1 pa
  public void action(){
    this.nb_ap --;
  }

	// Gain de 6 pa
	public void gain6ap(){
		if (this.nb_ap < 5) {
			this.nb_ap = this.nb_ap + 6;
		}
		else {
			this.nb_ap = 10;
		}
	}

	// Gain de 4 pa
	public void gain4ap(){
		if (this.nb_ap < 7) {
			this.nb_ap = this.nb_ap + 4;
		}
		else {
			this.nb_ap = 10;
		}
	}

	// Obtient la position du joueur
	public int getPos_x() {
		return pos_x;
	}

	// Modifie la position du joueur
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	// Obtient la position du joueur
	public int getPos_y() {
		return pos_y;
	}

	// Modifie la position du joueur
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	// Obtient le nombre de PV d'un joueur
	public int getHP () {
		return hp;
	}

	// Modifie le nombre de PV d'un joueur
	/*public void setPV (int pv) {
		this.pv = pv;
	}*/

	// Pert de x PV
	public void lostHP (int x) {
		this.hp = this.hp - x;
	}

	// Permet de savoir si un joueur a bu ou non
	public boolean getDrink () {
		return drink;
	}

	// Modifie pour dire qu'il a bu
	public void drink () {
		this.drink = true;
    this.inventory.remove("Gourde d'eau");
	}

	// Permet de savoir si un joueur a mangé ou non
	public boolean getEat () {
		return eat;
	}

	// Modifie pour dire qu'il a mangé
	public void eat () {
		this.eat = true;
    this.inventory.remove("Ration");
	}

	// Permet de savoir si un joueur est addict
	public int getAddict () {
		return addict;
	}

	// Modifie pour dire qu'il a bu une boisson énergisante
	public void addict() {
		this.addict = 0;
	}

  // Ajoute 1 à addict
  public void addAddict() {
    this.addict ++;
  }

	// Permet de savoir si un joueur est en ville ou non
	public boolean getInCity () {
		return inCity;
	}

	// Modifie si le joueur est en ville ou non
	public void setInCity (boolean inCity) {
		this.inCity = inCity;
	}

	// Changement de joueur
	public void resetDay () {
		this.drink = false;
		this.eat = false;
	}

	// Permet de voir votre inventaire
	public void getInventory() {
		if (inventory.isEmpty()) {
			System.out.println("Votre inventaire ne contient aucun objet");
		}
		else {
			System.out.println("Votre inventaire contient : ");
			for (byte i = 0; i < inventory.size(); i++) {
        System.out.print(inventory.get(i) + " ");
			}
		}
	}

	// Permet de savoir si l'iventaire est vide
	public boolean isEmpty(){
		return inventory.isEmpty();
	}
	// Permet de retourner le n_ième objet de votre inventaire
	public String getInventory(int n) {
		return inventory.get(n);
	}

  // Permet d'afficher l'inventaire pour l'ajout d'un item à la banque ou sur le sol
	public void bankInventory() {
		if (inventory.isEmpty()) {
			System.out.println("Votre inventaire ne contient aucun objet, vous ne pouvez pas déposer un item");
		}
		else {
			System.out.println("Quel objet voulez vous déposer ?");
			for (byte i = 0; i < inventory.size(); i++) {
        System.out.println((i+1) + " : " + inventory.get(i) + " ");
			}
		}
	}

	// Ajouter un item à votre inventaire
	public void addInventory(String item) {
		if (inventory.size() < 10) {
			inventory.add(inventory.size(), item);
		}
		else {
			System.out.println("Votre inventaire est plein, vous ne pouvez pas prendre un item supplémentaire");
		}
	}

  // Permet de connaître le nombre d'item dans votre inventaire
  public int sizeInventory() {
    return inventory.size();
  }

	// Vérfie si vous avez un objet dans votre inventaire
	public boolean containsInventory (String item) {
		return inventory.contains(item);
	}

  public void removeInventory (String item) {
		if (inventory.contains(item)) {
				inventory.remove(item);
		}
		else {
			System.out.println("Erreur, l'objet n'est pas dans votre inventaire");
		}
  }
}
