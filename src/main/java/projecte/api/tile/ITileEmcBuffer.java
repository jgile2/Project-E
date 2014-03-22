package projecte.api.tile;

public interface ITileEmcBuffer {

	public int getStoredEmc();
	public int getMaxStoredEmc();
	
	public void drain(int amt);
	public void add(int amt);
	
}
