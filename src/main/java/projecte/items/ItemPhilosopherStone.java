package projecte.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;

public class ItemPhilosopherStone extends Item {

	public ItemPhilosopherStone() {

		this.setUnlocalizedName(ModInfo.MOD_ID + ".philosophersStone");
		this.setCreativeTab(ProjectE.tab);
		this.setMaxStackSize(1);
	}

	public void registerIcons(IIconRegister reg) {
		itemIcon = reg.registerIcon(ModInfo.MOD_ID + ":philosophersStone");
	}

	@SuppressWarnings("rawtypes")
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!p.canPlayerEdit(x, y, z, side, is)) {
			return false;
		} else {
		}

		return false;
	}

}
