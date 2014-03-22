package projecte.container;

import net.minecraft.inventory.IInventory;
import projecte.api.emc.EmcValueType;

public class SlotEmcFuel extends SlotEmc {

	public SlotEmcFuel(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public EmcValueType getRequiredType() {
		return EmcValueType.FUEL;
	}

}
