package projecte.api.emc;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OredictEmcValue implements IEmcValue {

	private String name;
	protected EmcValue value;
	protected EmcValueType type;

	public OredictEmcValue(String oredictName, double value, EmcValueType type) {
		this.name = oredictName;
		this.value = new EmcValue(EmcValueSource.OREDICT, value);
		this.type = type;
	}

	public EmcValue getValue() {
		return value;
	}

	public ItemStack[] getItems() {
		return OreDictionary.getOres(name).toArray(new ItemStack[] {});
	}

	public String getName() {
		return name;
	}

	public EmcValueType getType() {
		return type;
	}

}
