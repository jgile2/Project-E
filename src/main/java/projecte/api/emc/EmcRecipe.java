package projecte.api.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class EmcRecipe {

	ItemStack[] input = null;
	ItemStack output = null;

	public EmcRecipe(ItemStack... input) {
		this.input = input;

		double value = 0;

		boolean allFuel = true;
		boolean allMatter = true;

		for (ItemStack i : input) {
			if (i == null)
				continue;

			EmcData val = EmcRegistry.getValue(i);

			if (val == null)
				return;

			if (val.getType() == EmcValueType.FUEL)
				allMatter = false;
			if (val.getType() == EmcValueType.MATTER)
				allFuel = false;

			value += val.getValue();
		}

		if ((!allFuel && !allMatter) || (allFuel && allMatter)) {
			return;
		}

		EmcData[] results = EmcRegistry.getItemsWithValue(value);
		results = EmcRegistry.filter(results, allFuel ? EmcValueType.FUEL : (allMatter ? EmcValueType.MATTER : null));
		if (results.length > 0) {
			output = results[0].getItem().copy();
		}
	}

	public EmcRecipe(ItemStack output, ItemStack... input) {
		this.input = input;
		this.output = output;
	}

	public ItemStack[] getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}

	public boolean isValid() {

		if (output == null)
			return false;

		boolean allNull = true;
		for (ItemStack is : input)
			if (is != null)
				allNull = false;
		if (allNull)
			return false;

		return true;
	}

	@Override
	public boolean equals(Object o) {

		if (!(o instanceof EmcRecipe))
			return false;

		EmcRecipe r = (EmcRecipe) o;

		List<ItemStack> a = new ArrayList<ItemStack>();
		List<ItemStack> b = new ArrayList<ItemStack>();
		for (ItemStack is : input)
			if (is != null)
				a.add(is);
		for (ItemStack is : r.input)
			if (is != null)
				b.add(is);

		if (a.size() == 0 || b.size() == 0)
			return false;

		while (a.size() > 0 && b.size() > 0) {
			ItemStack is = a.get(0);
			ItemStack is2 = null;
			for (ItemStack i : b) {
				if (is.isItemEqual(i)) {
					is2 = i;
					break;
				}
			}
			a.remove(is);
			if (is2 != null)
				b.remove(is2);
		}

		if (a.size() > 0 || b.size() > 0)
			return false;

		return true;
	}

}
