package projecte.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import projecte.ModInfo;
import projecte.ProjectE;
import projecte.api.emc.EmcValue;

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
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x,
			int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!p.canPlayerEdit(x, y, z, side, is)) {
			return false;
		} else {
			if (p.isSneaking()) {
				List entities = w.getEntitiesWithinAABB(
						EntityItem.class,
						AxisAlignedBB.getBoundingBox(x + hitX - 0.25, y + hitY
								- 0.25, z + hitZ - 0.25, x + hitX + 0.25, y
								+ hitY + 0.25, z + hitZ + 0.25));
				EntityItem closest = null;
				double dist = Double.MAX_VALUE;
				for (Object o : entities) {
					EntityItem ei = (EntityItem) o;
					double d = ei.getDistance(x + hitX, y + hitY, z + hitZ);

					if (d < dist) {
						dist = d;
						closest = ei;
					}
				}
				
				if (closest == null)
					return false;

				EmcValue itemValue = EmcValues.getValueForStack(closest
						.getEntityItem());

				if (itemValue == null)
					return false;
				
				List<ItemStack> sameEmc = new ArrayList<ItemStack>();
				
				for(EmcValue v : EmcValues.getEmcValues(itemValue.getValue()))
					for(ItemStack item : v.getItems())
						sameEmc.add(item);
				if(sameEmc.size() <= 1) return false;
				
				int currentIndex = -1;
				for(ItemStack i : sameEmc){
					if(i.isItemEqual(closest.getEntityItem())){
						currentIndex = sameEmc.indexOf(i);
						break;
					}
				}
				
				if(currentIndex < 0) return false;
				
				ItemStack result = null;
				
				if(currentIndex + 1 >= sameEmc.size()){
					result = sameEmc.get(0).copy();
				}else{
					result = sameEmc.get(currentIndex + 1).copy();
				}
				
				result.stackSize = closest.getEntityItem().stackSize;
				
				closest.setEntityItemStack(result);
			}else{
				ItemStack closest = new ItemStack(w.getBlock(x, y, z));
				
				EmcValue blockValues = EmcValues.getValueForStack(closest);

				if (blockValues == null)
					return false;
				
				List<ItemStack> sameEmc = new ArrayList<ItemStack>();
				
				for(EmcValue v : EmcValues.getEmcValues(blockValues.getValue()))
					for(ItemStack item : v.getItems())
						if(item.getItem() instanceof ItemBlock)
							sameEmc.add(item);
				if(sameEmc.size() <= 1) return false;
				
				int currentIndex = -1;
				for(ItemStack i : sameEmc){
					if(i.isItemEqual(closest)){
						currentIndex = sameEmc.indexOf(i);
						break;
					}
				}
				
				if(currentIndex < 0) return false;
				
				ItemStack result = null;
				
				if(currentIndex + 1 >= sameEmc.size()){
					result = sameEmc.get(0).copy();
				}else{
					result = sameEmc.get(currentIndex + 1).copy();
				}
				
				System.out.println(result);
				
				ItemBlock b = (ItemBlock) result.getItem();
				w.setBlock(x, y, z, Blocks.air);
				b.placeBlockAt(result, p, w, x, y, z, side, hitX, hitY, hitZ, result.getItemDamage());
			}
		}

		return false;
	}

}
