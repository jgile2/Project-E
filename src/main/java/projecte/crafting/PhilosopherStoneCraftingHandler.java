package projecte.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import projecte.api.emc.EmcData;
import projecte.api.emc.EmcRegistry;
import projecte.api.emc.EmcValueType;
import projecte.items.ItemPhilosopherStone;

public class PhilosopherStoneCraftingHandler extends ShapelessRecipes {

	public static final PhilosopherStoneCraftingHandler inst = new PhilosopherStoneCraftingHandler();
	
	public PhilosopherStoneCraftingHandler() {
		super(null, null);
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {

		ItemStack[] items = new ItemStack[inv.getSizeInventory()];
		int itemsSize = 0;
		boolean hasStone = false;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack is = inv.getStackInSlot(i);
			if (is == null)
				continue;

			if (is.getItem() instanceof ItemPhilosopherStone) {
				hasStone = true;
				continue;
			}

			items[itemsSize] = is;
			itemsSize++;
		}

		if (!hasStone)
			return null;
		
		ItemStack[] fixedItemArray = new ItemStack[itemsSize];
		for(int i = 0; i < itemsSize; i++){
			fixedItemArray[i] = items[i];
		}

		return getResult(fixedItemArray);
	}
	
	public static ItemStack getResult(ItemStack[] items){
		if (items.length > 1) {

			double value = 0;
			
			boolean allFuel = true;
			boolean allMatter = true;

			for (ItemStack i : items) {
				if (i == null)
					continue;

				EmcData val = EmcRegistry.getValue(i);
				
				if (val == null)
					return null;

				if(val.getType() == EmcValueType.FUEL)
					allMatter = false;
				if(val.getType() == EmcValueType.MATTER)
					allFuel = false;

				value += val.getValue();
			}
			
			if((!allFuel && !allMatter) || (allFuel && allMatter)){
				return null;
			}

			EmcData[] results = EmcRegistry.getItemsWithValue(value);
			results = EmcRegistry.filter(results, allFuel ? EmcValueType.FUEL : (allMatter ? EmcValueType.MATTER : null));
			if (results.length > 0) {
				return results[0].getItem().copy();
			}
		}else{

			double value = 0;
			
			boolean allFuel = true;
			boolean allMatter = true;

			for (ItemStack i : items) {
				if (i == null)
					continue;

				EmcData val = EmcRegistry.getValue(i);
				
				if (val == null)
					return null;

				if(val.getType() == EmcValueType.FUEL)
					allMatter = false;
				if(val.getType() == EmcValueType.MATTER)
					allFuel = false;

				value += val.getValue();
			}
			
			if((!allFuel && !allMatter) || (allFuel && allMatter)){
				return null;
			}

			EmcData[] results = EmcRegistry.getDividersForValue(value);
			results = EmcRegistry.filter(results, allFuel ? EmcValueType.FUEL : (allMatter ? EmcValueType.MATTER : null));
			if (results.length > 0) {
				ItemStack is = results[0].getItem().copy();
				is.stackSize = (int)((double)(value / EmcRegistry.getValue(is).getValue()));
				
				return is;
			}
			
		}
		return null;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World w) {

		return getCraftingResult(inv) != null;
	}

	@Override
	public ItemStack getRecipeOutput() {

		return null;
	}

	@Override
	public int getRecipeSize() {
		return 9;
	}

}
