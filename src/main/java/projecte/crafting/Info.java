package projecte.crafting;

import projecte.blocks.PEBlocks;
import projecte.compat.nei.InfoData;

public class Info {

	public static void addInfo(){
		InfoData.add(PEBlocks.energyCondenser, "Energy Condensor", new String[]{"The energy condensor can create items with a emc value from liquid emc, to use this block first you will need a the item you want to create, a klein star and the philosophers stone, second you put your item in the left slot of the gui, the klein star goes in the centre and the philosophers stone goes in the far right slot, then all you need to do is pipe liquid emc into it"});

	}
}
