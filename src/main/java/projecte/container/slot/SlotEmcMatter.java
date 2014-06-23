package projecte.container.slot;

import net.minecraft.inventory.IInventory;
import projecte.api.emc.EmcStackType;

public class SlotEmcMatter extends SlotEmc {

	public SlotEmcMatter(IInventory inv, int id, int x, int y) {
		super(inv, id, x, y);
	}

	@Override
	public EmcStackType getRequiredType() {
		return EmcStackType.MATTER;
	}

}
