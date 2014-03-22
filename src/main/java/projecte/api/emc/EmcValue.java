package projecte.api.emc;

import net.minecraft.item.ItemStack;

public class EmcValue {

	protected ItemStack item;
	protected double value;
	protected EmcValueType type;

	protected EmcValue(ItemStack item, double value, EmcValueType type) {

		this.item = item;
		this.value = value;
		this.type = type;
	}

	public ItemStack[] getItems() {
		return new ItemStack[] { item.copy().splitStack(1) };
	}

	public double getValue() {
		return value / (double) (item.stackSize);
	}

	public double getValueForStackSize(int size) {
		return size * getValue();
	}

	public EmcValueType getType() {
		return type;
	}

}
