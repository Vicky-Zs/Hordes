package hordes;

public class City {
	private int defense;
	private Boolean door; //True = Open, False = Fermer



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
}
