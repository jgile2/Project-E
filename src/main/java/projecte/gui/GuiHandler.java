package projecte.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projecte.container.*;
import projecte.tile.*;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);

		if (entity instanceof TileEnergyCollectorMK1) {
			return new ContainerEnergyCollectorMK1(player.inventory, (TileEnergyCollectorMK1) entity);
		}

		if (entity instanceof TileEnergyCollectorMK2) {
			return new ContainerEnergyCollectorMK2(player.inventory, (TileEnergyCollectorMK2) entity);
		}

		if (entity instanceof TileEnergyCollectorMK3) {
			return new ContainerEnergyCollectorMK3(player.inventory, (TileEnergyCollectorMK3) entity);
		}
		if (entity instanceof TileCondenser) {
			return new ContainerCondensor(player.inventory, (TileCondenser) entity);
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
		if (ID==100) {
			return new ContainerAlChest(player.inventory);
		}

		return null;
	}

	@Override
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
		if (entity instanceof TileCondenser) {
			return new GuiCondensor(player.inventory, (TileCondenser) entity);
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
		if (ID==100) {
			return new GuiAlchemyBag(player.inventory);
		}
		return null;
	}

}
