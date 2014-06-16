package projecte.api.emc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import projecte.ProjectE;
import projecte.util.ModUtils;

public class StackEmcValue implements IEmcValue {

	private ItemStack item;
	private EmcValueType type;
	private List<EmcValue> values = new ArrayList<EmcValue>();

	public StackEmcValue(ItemStack is, EmcValueType type) {
		this.item = is;
		this.type = type;
		if (is == null)
			ProjectE.log.log(Level.SEVERE, ModUtils.getActiveMod().getName() + " is attempting to create an EMC value for a null item! (This will not be allowed in the registry)");
		if (type == null)
			ProjectE.log.log(Level.SEVERE, ModUtils.getActiveMod().getName() + " is attempting to create an EMC value for an item with no type! (This will not be allowed in the registry)");
	}

	protected StackEmcValue(ItemStack is) {
		this.item = is;
		if (is == null)
			ProjectE.log.log(Level.SEVERE, ModUtils.getActiveMod().getName() + " is attempting to create an EMC value for a null item! (This will not be allowed in the registry)");
	}

	public final boolean isValid() {
		return item != null && type != null;
	}

	public ItemStack getItem() {
		return item;
	}

	public EmcValueType getType() {
		return type;
	}

	public List<EmcValue> getValues() {
		return values;
	}

	public void addValue(EmcValue value) {
		if (!values.contains(value)) {
			values.add(value);
		} else {
			ProjectE.log.log(Level.SEVERE, ModUtils.getActiveMod().getName() + " is attempting to add a null EMC value to the item: " + item);
		}
	}

	public double getAverage() {
		double d = 0;
		for (EmcValue v : values) {
			double val = v.getValue(item);
			if (val < 0)
				continue;
			d += val;
		}
		d /= values.size();
		return d;
	}

	protected void setType(EmcValueType type) {
		this.type = type;
	}

    @Override
    public EmcValue getValue() {
        
        return new EmcValue(EmcValueSource.SPECIFIC, getAverage());
    }

}
