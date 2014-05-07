package projecte.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import projecte.items.PEItems;

public class SlotPhilosopherStone extends SlotCustom {

	public SlotPhilosopherStone(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if (itemstack.getItem() == PEItems.philosophersStone) {
			return true;
		}
		return false;
	}
}
