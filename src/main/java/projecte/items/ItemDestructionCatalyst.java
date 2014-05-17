package projecte.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import projecte.ModInfo;
import projecte.ProjectE;

public class ItemDestructionCatalyst extends Item {
	public ItemDestructionCatalyst() {

		super();
		this.setUnlocalizedName(ModInfo.MOD_ID + ".destructionCatalyst");
		this.setCreativeTab(ProjectE.tab);
		this.setMaxDamage(5);
		
		maxStackSize = 1;

	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(ModInfo.MOD_ID + ":destructionCatalyst");
	}

	@Override
	public void addInformation(ItemStack ist, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("Mines Out A 3x3x3");
		par3List.add("[WIP]");
	}

	@Override
	public boolean onItemUse(ItemStack ist, EntityPlayer player, World world, int x, int y, int z, int side, float xOff, float yOff, float zOff) {
		int charge = Math.abs(ist.getItemDamage()-this.getMaxDamage());
		if (hasFuel(player)) {
			doExplosion(world, x, y, z, side, player,charge);
		}
		return true;
	}

	public void doExplosion(World world, int cx, int cy, int cz, int side, EntityPlayer player,int charge) {
		System.out.println(charge);
		int depth = charge;
		int width = charge;
		//int depth = player.getCurrentEquippedItem().getItemDamage();
		//int width = player.getCurrentEquippedItem().getItemDamage();

		
		boolean destroyedSomething = false;
		boolean playOnce = true;
		if (!world.isRemote) {
			ForgeDirection dir = ForgeDirection.getOrientation(side);
			int x = cx + dir.offsetX;
			int y = cy + dir.offsetY;
			int z = cz + dir.offsetZ;
			for (int xD = (dir.offsetX > 0 ? -dir.offsetX * depth : -width); xD <= (dir.offsetX < 0 ? -dir.offsetX * depth : width); xD++) {
				for (int yD = (dir.offsetY > 0 ? -dir.offsetY * depth : -width); yD <= (dir.offsetY < 0 ? -dir.offsetY * depth : width); yD++) {
					for (int zD = (dir.offsetZ > 0 ? -dir.offsetZ * depth : -width); zD <= (dir.offsetZ < 0 ? -dir.offsetZ * depth : width); zD++) {
						if (isBreakable(world.getBlock(x + xD, y + yD, z + zD))) {
							
							Block block = world.getBlock(x + xD, y + yD, z + zD);
							List<ItemStack> drops = block.getDrops(world, x + xD, y + yD, z + zD, world.getBlockMetadata(x + xD, y + yD, z + zD), 0);

							for (ItemStack item : drops) {
								
								if(item != null){
									world.setBlockToAir(x + xD, y + yD, z + zD);
									
									EntityItem entity = new EntityItem(world, cx, cy, cz, item);
									world.spawnEntityInWorld(entity);
								}

							}
							destroyedSomething = true;

						}

					}
				}
			}
			if (destroyedSomething) {
				consumeFuel(player);
			}
		}
	}

	public boolean isBreakable(Block block) {

		return ":" != null;
	}

	public boolean consumeFuel(EntityPlayer player) {
		int FuelCost = 1;
		IInventory inventory = player.inventory;
		for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
			if (inventory.getStackInSlot(slot) == null) {
				continue;
			}
			if (inventory.getStackInSlot(slot).getItem() == Items.glowstone_dust) {
				while (FuelCost > 0 && player.inventory.getStackInSlot(slot) != null) {
					player.inventory.decrStackSize(slot, 1);
					FuelCost--;
				}
				if (FuelCost == 0)
					return true;
			}
		}
		return false;
	}

	public boolean hasFuel(EntityPlayer player) {
		int FuelCount = 0;
		IInventory inventory = player.inventory;
		for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
			if (inventory.getStackInSlot(slot) == null) {
				continue;
			}
			if (inventory.getStackInSlot(slot).getItem() == Items.glowstone_dust) {
				FuelCount += inventory.getStackInSlot(slot).stackSize;
				if (FuelCount >= 1)
					return true;
			}
		}
		return false;
	}
}
