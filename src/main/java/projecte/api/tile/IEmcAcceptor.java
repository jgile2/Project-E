package projecte.api.tile;

import net.minecraftforge.common.util.ForgeDirection;

public interface IEmcAcceptor {

	public int getMaxInput(ForgeDirection side);

	/**
	 * 
	 * @param amount
	 *            The amount of EMC that's tried to put into the block
	 * @param side
	 *            The side from which the EMC comes from
	 * @param doInput
	 *            Whether or not this block is going to really input or just
	 *            simulate it
	 * @return The amount of EMC that could be accepted by the block
	 */
	public int input(int amount, ForgeDirection side, boolean doInput);

}
