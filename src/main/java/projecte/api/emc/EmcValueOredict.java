package projecte.api.emc;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class EmcValueOredict extends EmcValue {

	protected String oredictName;
	
	protected EmcValueOredict(String oredictName, double value, EmcValueType type) {
		super(null, value, type);
		
		this.oredictName = oredictName;
	}
	
	@Override
	public ItemStack[] getItems() {
		return OreDictionary.getOres(oredictName).toArray(new ItemStack[]{});
	}
	
	@Override
	public double getValue() {
		return value;
	}
	
	@Override
	public double getValueForStackSize(int size) {
		return value * size;
	}

}
