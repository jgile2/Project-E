package projecte.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import projecte.items.PEItems;

public class SlotBag extends Slot {
	ItemStack item;
	

	public SlotBag(ItemStack item, IInventory par1iInventory, int id, int x, int y) {
		super(par1iInventory, id, x, y);
		this.item = item;
		
		
	}
	
	
//	@Override
//	public boolean isItemValid(ItemStack itemstack) {
//		return itemstack.getItem() != PEItems.Pouch;
//
//	}
//	
	
	@Override
	public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
		if(this.getStack().getItem()==PEItems.Pouch){
			return false;
		}
		return true;
	}


}
