package projecte.api.tile;

public interface IEmcBuffer {

	/**
	 * @return Max amount of EMC that can be stored
	 */
	public int getMaxStored();

	/**
	 * @return Amount of EMC currently stored in the block
	 */
	public int getStored();
	
	/*
	 * Sets the amount of EMC inside the buffer
	 */
	public void setStored(int amount);

}
