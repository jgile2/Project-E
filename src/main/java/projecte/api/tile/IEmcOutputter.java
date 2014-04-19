package projecte.api.tile;

import net.minecraftforge.common.util.ForgeDirection;

public interface IEmcOutputter {

	public int getMaxOutput(ForgeDirection side);

	/**
	 * @param amount
	 *            The amount of EMC that's tried to extract out of the block
	 * @param side
	 *            The side from which the EMC comes out of
	 * @param doOutput
	 *            Whether or not this block is going to really output or just
	 *            simulate it
	 * @return The amount of EMC that could be outputted by the block
	 */
	public int output(int amount, ForgeDirection side, boolean doOutput);

}
