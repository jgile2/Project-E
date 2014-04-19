package projecte.api.emc;

import net.minecraft.item.ItemStack;

public class EmcData {

	private ItemStack is;
	private EmcValueType type;
	private double value;

	public EmcData(ItemStack is, EmcValueType type, double value) {
		this.is = is;
		this.type = type;
		this.value = value;
	}

	public ItemStack getItem() {
		return is;
	}

	public EmcValueType getType() {
		return type;
	}

	public double getValue() {
		return value;
	}
	
	public double getValue(int stackSize){
		return value * stackSize;
	}

}
