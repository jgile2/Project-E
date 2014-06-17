package projecte.items;

import projecte.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class InventoryManual implements IInventory{
    public ItemStack[] stacks;

    public InventoryManual()
    {
        stacks = new ItemStack[ItemPouch.INV_SIZE];
    }

    @Override
    public int getSizeInventory()
    {
        return 5;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return stacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int num)
    {
        ItemStack wasThere = stacks[slot];

        if (wasThere == null)
            return null;

        if (wasThere.stackSize <= num)
        {
            stacks[slot] = null;
            return wasThere;
        }
        else
        {
            return wasThere.splitStack(num);
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        stacks[slot] = stack;
    }

    @Override
    public String getInventoryName()
    {
        return StatCollector.translateToLocal(ModInfo.MOD_ID + ".pouch.name");
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void markDirty()
    {
        // uh...  send packet? TODO:
        //save();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return true;
    }
    
    public void load(InventoryPlayer inv)
    {
       // stacks = ItemPouch.readFromStack(inv.getCurrentItem());
    }
    
    public void save(InventoryPlayer inv)
    {
        //ItemPouch.writeToStack(inv.getCurrentItem(), stacks);
    }

    @Override
    public void openInventory()
    {
        //stacks = ItemPouch.readFromStack(original);
        // load();
    }

    @Override
    public void closeInventory()
    {
        //ItemPouch.writeToStack(original, stacks);
        //save();
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2)
    {
        return true;
    }
}
