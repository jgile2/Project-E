package projecte.util;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OredictUtil {

	private static boolean registered = false;

	public static void registerVanillaOredict() {

		if (!registered) {
			registered = true;

			/* Blocks */

			register(Blocks.log, "logWood");
			register(Blocks.log2, "logWood");
			register(Blocks.log, "woodLog");
			register(Blocks.log2, "woodLog");

			register(Blocks.planks, "plankWood");
			register(Blocks.planks, "woodPlank");

			register(Blocks.crafting_table, "craftingtable");
			register(Blocks.crafting_table, "craftingTable");

			register(Blocks.wooden_slab, "slabWood");
			register(Blocks.wooden_slab, "woodSlab");

			register(Blocks.oak_stairs, "stairWood");
			register(Blocks.spruce_stairs, "stairWood");
			register(Blocks.birch_stairs, "stairWood");
			register(Blocks.jungle_stairs, "stairWood");
			register(Blocks.acacia_stairs, "stairWood");
			register(Blocks.dark_oak_stairs, "stairWood");
			register(Blocks.oak_stairs, "woodStair");
			register(Blocks.spruce_stairs, "woodStair");
			register(Blocks.birch_stairs, "woodStair");
			register(Blocks.jungle_stairs, "woodStair");
			register(Blocks.acacia_stairs, "woodStair");
			register(Blocks.dark_oak_stairs, "woodStair");

			register(Blocks.sapling, "saplingTree");
			register(Blocks.sapling, "treeSapling");

			register(Blocks.leaves, "leavesTree");
			register(Blocks.leaves2, "leavesTree");
			register(Blocks.leaves, "treeLeaves");
			register(Blocks.leaves2, "treeLeaves");

			register(Blocks.cobblestone, "blockCobble");

			register(Blocks.stone, "blockStone");

			/* Items */

			register(Items.iron_ingot, "ingotIron");
			register(Items.gold_ingot, "ingotGold");

			register(Items.stick, "stickWood");
			register(Items.stick, "woodStick");

			register(Items.record_11, "record");
			register(Items.record_13, "record");
			register(Items.record_blocks, "record");
			register(Items.record_cat, "record");
			register(Items.record_chirp, "record");
			register(Items.record_far, "record");
			register(Items.record_mall, "record");
			register(Items.record_mellohi, "record");
			register(Items.record_stal, "record");
			register(Items.record_strad, "record");
			register(Items.record_wait, "record");
			register(Items.record_ward, "record");

			register(Items.dye, "dye");
		}
	}

	@SuppressWarnings("rawtypes")
	private static void register(Object o, String name) {
		if (o instanceof Block) {
			register(Item.getItemFromBlock((Block) o), name);
		} else if (o instanceof Item) {
			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
				List l = new ArrayList();
				((Item) o).getSubItems((Item) o, null, l);
				for (Object obj : l) {
					ItemStack is = (ItemStack) obj;
					register(is, name);
				}
			}else{
				OreDictionary.registerOre(name, (Item) o);
			}
		} else if (o instanceof ItemStack) {
			OreDictionary.registerOre(name, (ItemStack) o);
		}
	}

}
