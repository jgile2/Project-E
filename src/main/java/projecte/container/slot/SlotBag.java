package projecte.container.slot;

import projecte.items.PEItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotBag extends Slot{
	int currentSlot=0;
	public SlotBag(int slot,IInventory par1iInventory, int id, int x, int y) {
		super(par1iInventory, id, x, y);
		currentSlot = slot; 
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		//if(player.inventory.getStackInSlot(currentSlot).getItem()==PEItems.AlchemyBag)return false;
		return true;
	}
	
	
}
