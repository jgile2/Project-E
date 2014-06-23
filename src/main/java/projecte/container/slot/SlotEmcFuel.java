package projecte.container.slot;

import net.minecraft.inventory.IInventory;
import projecte.api.emc.EmcStackType;

public class SlotEmcFuel extends SlotEmc {

	public SlotEmcFuel(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public EmcStackType getRequiredType() {
		return EmcStackType.FUEL;
	}

}
