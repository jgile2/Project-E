package projecte.container;

import projecte.container.slot.SlotBag;
import projecte.items.InventoryPouch;
import projecte.items.ItemPouch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class ContainerPouch extends Container
{
    InventoryPouch itemInv;
    
    
    public ContainerPouch(InventoryPouch itemInv, EntityPlayer player)
    {
        this.itemInv = itemInv;
        ItemStack item = player.getCurrentEquippedItem();
        
        int i = (ItemPouch.ROWS - 4) * 18;
        int j;
        int k;

        for (j = 0; j < ItemPouch.ROWS; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(itemInv, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for (j = 0; j < 3; ++j)
        {
            for (k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new SlotBag(item,player.inventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }

        for (j = 0; j < 9; ++j)
        {
            this.addSlotToContainer(new SlotBag(item,player.inventory, j, 8 + j * 18, 161 + i));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }
    
    @Override
    public void onContainerClosed(EntityPlayer player)
    {
        itemInv.save(player.inventory);
        super.onContainerClosed(player);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < ItemPouch.INV_SIZE)
            {
                if (!this.mergeItemStack(itemstack1, ItemPouch.INV_SIZE, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, ItemPouch.INV_SIZE, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
    
    @Override
    protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4) {
    	return false;
    }
}
