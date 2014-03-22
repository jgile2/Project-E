package projecte.container;

import projecte.api.emc.EmcValue;
import projecte.api.emc.EmcValueType;
import projecte.api.emc.EmcValues;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEmc extends Slot{

	public SlotEmc(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack is) {
		
		EmcValue v = EmcValues.getValueForStack(is);
		if(v == null) return false;
		
		if(getRequiredType() != null && v.getType() != getRequiredType())
			return false;
		
		return true;
	}
	
	public EmcValueType getRequiredType(){
		return null;
	}

}
