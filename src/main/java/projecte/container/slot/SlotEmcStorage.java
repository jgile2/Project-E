package projecte.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import projecte.api.tile.IItemEmcBuffer;

public class SlotEmcStorage extends SlotCustom {

	public SlotEmcStorage(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack is) {

		if (!super.isItemValid(is))
			return false;

		if (!(is.getItem() instanceof IItemEmcBuffer))
			return false;

		return true;
	}

}
