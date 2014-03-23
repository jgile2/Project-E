package projecte.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import projecte.api.emc.EmcValue;
import projecte.api.emc.EmcValueType;
import projecte.api.emc.EmcValues;

public class SlotEmc extends SlotCustom {

	public SlotEmc(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack is) {

		if(!super.isItemValid(is))
			return false;
		
		EmcValue v = EmcValues.getValueForStack(is);
		if (v == null)
			return false;

		if (getRequiredType() != null && v.getType() != getRequiredType())
			return false;

		return true;
	}

	public EmcValueType getRequiredType() {
		return null;
	}

}
