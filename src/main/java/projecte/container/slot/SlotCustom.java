package projecte.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCustom extends Slot {

	private boolean canPutItems = true;
	private boolean canTakeItems = true;

	public SlotCustom(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
		return canTakeItems;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return canPutItems;
	}

	public SlotCustom setCanPutItems(boolean can) {
		canPutItems = can;

		return this;
	}

	public SlotCustom setCanTakeItems(boolean can) {
		canTakeItems = can;

		return this;
	}

}
