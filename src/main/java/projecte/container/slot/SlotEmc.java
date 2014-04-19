package projecte.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import projecte.api.emc.EmcData;
import projecte.api.emc.EmcRegistry;
import projecte.api.emc.EmcValueType;

public class SlotEmc extends SlotCustom {

	public SlotEmc(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack is) {

		if(!super.isItemValid(is))
			return false;
		
		EmcData v = EmcRegistry.getValue(is);

		if(v == null)
			return false;
		
		if (getRequiredType() != null && v.getType() != getRequiredType())
			return false;

		return true;
	}

	public EmcValueType getRequiredType() {
		return null;
	}

}
