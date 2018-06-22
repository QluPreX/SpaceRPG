package domain;

public abstract class Item {
	/***
	 * the item class: It get's it's DataInformation from a saved XML file (Not implemented yet)
	 * @author karel
	 * @version 1.0
	 */
	protected String name;
	/**
	 * the main constructor of the Class Item
	 * 
	 */
	public Item(String name) {
		setName(name);
	}
	/**
	 * The getter from the class Item
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * The setter from the class item
	 * @param name
	 */
	private void setName(String name) {
		this.name = name;
	}
}