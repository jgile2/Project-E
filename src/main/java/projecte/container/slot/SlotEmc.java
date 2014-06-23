package projecte.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import projecte.api.emc.EmcRegistry;
import projecte.api.emc.EmcStackType;
import projecte.api.emc.StackEmcValue;

public class SlotEmc extends SlotCustom {

	public SlotEmc(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack is) {

		if(!super.isItemValid(is))
			return false;
		
		double v = EmcRegistry.inst().getValue(is);
		StackEmcValue stackValue = EmcRegistry.inst().getEmcValue(is);
		
		if(v == 0)
			return false;
		
		if (getRequiredType() != null && stackValue.getType() != getRequiredType())
			return false;

		return true;
	}

	public EmcStackType getRequiredType() {
		return null;
	}

}
