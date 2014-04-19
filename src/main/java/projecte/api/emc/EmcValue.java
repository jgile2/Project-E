package projecte.api.emc;

import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import projecte.ProjectE;
import projecte.util.ModUtils;

public class EmcValue {

	protected EmcValueSource source;
	protected double value = 0;

	public EmcValue(EmcValueSource source, double value) {
		this.source = source;
		this.value = Math.max(value, 0);

		if (source == null)
			ProjectE.log.log(Level.SEVERE, ModUtils.getActiveMod().getName() + " is registering an emc value with no source!");
	}

	public EmcValueSource getSource() {
		return source;
	}

	public double getValue(ItemStack item) {
		return value;
	}
	
}
