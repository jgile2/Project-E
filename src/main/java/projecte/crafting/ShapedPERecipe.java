package projecte.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import projecte.ProjectE;
import projecte.util.ItemUtil;

public class ShapedPERecipe implements IRecipe {
	private static final int MAX_CRAFT_GRID_WIDTH = 3;
	private static final int MAX_CRAFT_GRID_HEIGHT = 3;
	private ItemStack output = null;

	private Object[] input = null;

	private int width = 0;

	private int height = 0;
	private boolean hidden;

	public ShapedPERecipe(ItemStack result, boolean hidden, Object[] recipe) {
		this.hidden = hidden;
		this.output = result.copy();

		String shape = "";
		int idx = 0;

		if ((recipe[idx] instanceof String[])) {
			String[] parts = (String[]) (String[]) recipe[(idx++)];

			for (String s : parts) {
				this.width = s.length();
				shape = shape + s;
			}

			this.height = parts.length;
		} else {
			while ((recipe[idx] instanceof String)) {
				String s = (String) recipe[(idx++)];
				shape = shape + s;
				this.width = s.length();
				this.height += 1;
			}
		}

		if (this.width * this.height != shape.length()) {
			String ret = "Invalid shaped aromic recipe: ";
			for (Object tmp : recipe) {
				ret = ret + tmp + ", ";
			}
			ret = ret + this.output;
			throw new RuntimeException(ret);
		}

		HashMap itemMap = new HashMap();

		for (; idx < recipe.length; idx += 2) {
			Character chr = (Character) recipe[idx];
			Object in = recipe[(idx + 1)];

			if ((in instanceof ItemStack)) {
				itemMap.put(chr, ((ItemStack) in).copy());

				if (OreDictionary.getOreIDs((ItemStack) in).length > 0) {
					//LogHelperPre.debugLog("Using " + in.toString() + " as a recipe ingredient. You could also have used a OreDict name " + " (in recipe: " + result.toString() + ")");
					ProjectE.log.log(Level.INFO,"Using " + in.toString() + " as a recipe ingredient. You could also have used a OreDict name " + " (in recipe: " + result.toString() + ")");
				}

			} else if ((in instanceof String)) {
				itemMap.put(chr, OreDictionary.getOres((String) in));
			} else if ((in instanceof Class)) {
				itemMap.put(chr, ItemUtil.getItemsFromClass((Class) in));
			} else if ((in instanceof RecipePart)) {
				itemMap.put(chr, in);
			} else if ((in instanceof Item)) {
				ItemStack item = new ItemStack((Item) in, 1, 32767);
				itemMap.put(chr, item);
				if (OreDictionary.getOreIDs(item).length > 0) {
					ProjectE.log.log(Level.INFO,"Using " + in.toString() + " as a recipe ingredient. You could also have used a OreDict name " + " (in recipe: " + result.toString() + ")");
				}

			} else if ((in instanceof Block)) {
				ItemStack item = new ItemStack((Block) in, 1, 32767);
				itemMap.put(chr, item);
				if (OreDictionary.getOreIDs(item).length > 0) {
					ProjectE.log.log(Level.INFO,"Using " + in.toString() + " as a recipe ingredient. You could also have used a OreDict name " + " (in recipe: " + result.toString() + ")");
					
				}

			} else {
				String ret = "Invalid shaped aromic recipe: ";
				for (Object tmp : recipe) {
					ret = ret + tmp + ", ";
				}
				ret = ret + this.output;
				throw new RuntimeException(ret);
			}
		}

		this.input = new Object[this.width * this.height];
		int x = 0;
		for (char chr : shape.toCharArray())
			this.input[(x++)] = itemMap.get(Character.valueOf(chr));
	}

	public ItemStack getCraftingResult(InventoryCrafting var1) {
		return this.output.copy();
	}

	public int getRecipeSize() {
		return this.input.length;
	}

	public ItemStack getRecipeOutput() {
		return this.output;
	}

	public boolean matches(InventoryCrafting inv, World world) {
		for (int x = 0; x <= 3 - this.width; x++) {
			for (int y = 0; y <= 3 - this.height; y++) {
				if ((checkMatch(inv, x, y, false)) || (checkMatch(inv, x, y, true))) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				int subX = x - startX;
				int subY = y - startY;
				Object target = null;

				if ((subX >= 0) && (subY >= 0) && (subX < this.width) && (subY < this.height)) {
					if (mirror)
						target = this.input[(this.width - subX - 1 + subY * this.width)];
					else {
						target = this.input[(subX + subY * this.width)];
					}
				}

				ItemStack slot = inv.getStackInRowAndColumn(x, y);

				if ((target instanceof ItemStack)) {
					if (!checkItemEquals((ItemStack) target, slot))
						return false;
				} else if ((target instanceof ArrayList)) {
					boolean matched = false;

					for (ItemStack item : (ArrayList<ItemStack>) target) {
						matched = (matched) || (checkItemEquals(item, slot));
					}

					if (!matched)
						return false;
				} else if ((target instanceof RecipePart)) {
					if (!((RecipePart) target).doesItemMatch(slot))
						return false;
				} else if ((target == null) && (slot != null)) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean checkItemEquals(ItemStack target, ItemStack input) {
		if (((input == null) && (target != null)) || ((input != null) && (target == null))) {
			return false;
		}
		//return true;
		//return ItemUtil.areItemsSameMatching(target, input);
		if(input.getItem() ==target.getItem()){
			return true;
		}
		//System.out.println("item does not match");
		return false;
	}

	public Object[] getInput() {
		return this.input;
	}

	public boolean isHidden() {
		return this.hidden;
	}

	public int getRecipeWidth() {
		return this.width;
	}

	public int getRecipeHeight() {
		return this.height;
	}
}