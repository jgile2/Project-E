package projecte.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projecte.container.*;
import projecte.items.InventoryPouch;
import projecte.tile.*;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {
	public EntityPlayer player;
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		this.player =player;
		if (entity instanceof TileEnergyCollectorMK1) {
			return new ContainerEnergyCollectorMK1(player.inventory, (TileEnergyCollectorMK1) entity);
		}

		if (entity instanceof TileEnergyCollectorMK2) {
			return new ContainerEnergyCollectorMK2(player.inventory, (TileEnergyCollectorMK2) entity);
		}

		if (entity instanceof TileEnergyCollectorMK3) {
			return new ContainerEnergyCollectorMK3(player.inventory, (TileEnergyCollectorMK3) entity);
		}
		
		if (entity instanceof TileCollectorCore) {
			return new ContainerEnergyCollectorMK3(player.inventory, (TileEnergyCollectorMK3) entity);
		}

		if (entity instanceof TileCondenser) {
			return new ContainerCondenser(player.inventory, (TileCondenser) entity);
		}

		if (entity instanceof TileRelayMK1) {
			return new ContainerRelayMK1(player.inventory, (TileRelayMK1) entity);
		}
		if (entity instanceof TileRelayMK2) {
			return new ContainerRelayMK2(player.inventory, (TileRelayMK2) entity);
		}
		if (entity instanceof TileRelayMK3) {
			return new ContainerRelayMK3(player.inventory, (TileRelayMK3) entity);
		}
		
		if (entity instanceof TileConverter) {
			return new ContainerConverter(player, (TileConverter) entity);
		}
        InventoryPouch inv = new InventoryPouch();
        inv.load(player.inventory);
        
		if (ID==5) {
			return new ContainerPouch(inv,player);
		}

		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if (entity instanceof TileEnergyCollectorMK1) {
			return new GuiEnergyCollectorMK1(player.inventory, (TileEnergyCollectorMK1) entity);
		}
		if (entity instanceof TileEnergyCollectorMK2) {
			return new GuiEnergyCollectorMK2(player.inventory, (TileEnergyCollectorMK2) entity);
		}
		if (entity instanceof TileEnergyCollectorMK3) {
			return new GuiEnergyCollectorMK3(player.inventory, (TileEnergyCollectorMK3) entity);
		}
		if (entity instanceof TileCollectorCore) {
			return new GuiEnergyCollectorMK3(player.inventory, (TileEnergyCollectorMK3) entity);
		}
		if (entity instanceof TileCondenser) {
			return new GuiCondenser(player.inventory, (TileCondenser) entity);
		}
		if (entity instanceof TileRelayMK1) {
			return new GuiRelayMK1(player.inventory, (TileRelayMK1) entity);
		}
		if (entity instanceof TileRelayMK2) {
			return new GuiRelayMK2(player.inventory, (TileRelayMK2) entity);
		}
		if (entity instanceof TileRelayMK3) {
			return new GuiRelayMK3(player.inventory, (TileRelayMK3) entity);
		}
		if (entity instanceof TileConverter) {
			return new GuiConverter(player, (TileConverter) entity);
		}
        InventoryPouch inv = new InventoryPouch();
        inv.load(player.inventory);
		if (ID==5) {
			return new GuiPouch(inv,player);
		}
		return null;
	}

}
