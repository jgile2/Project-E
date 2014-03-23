package projecte.container.slot;

import net.minecraft.inventory.IInventory;
import projecte.api.emc.EmcValueType;

public class SlotEmcMatter extends SlotEmc {

	public SlotEmcMatter(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public EmcValueType getRequiredType() {
		return EmcValueType.MATTER;
	}

}
