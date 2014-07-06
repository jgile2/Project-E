package projecte.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projecte.container.ContainerCondenser;
import projecte.container.ContainerConverter;
import projecte.container.ContainerCustomiser;
import projecte.container.ContainerEnergyCollectorMK1;
import projecte.container.ContainerEnergyCollectorMK2;
import projecte.container.ContainerEnergyCollectorMK3;
import projecte.container.ContainerPouch;
import projecte.container.ContainerRelayMK1;
import projecte.container.ContainerRelayMK2;
import projecte.container.ContainerRelayMK3;
import projecte.items.InventoryManual;
import projecte.items.InventoryPouch;
import projecte.tile.TileCollectorCore;
import projecte.tile.TileCondenser;
import projecte.tile.TileConverter;
import projecte.tile.TileCustomiser;
import projecte.tile.TileEnergyCollectorMK1;
import projecte.tile.TileEnergyCollectorMK2;
import projecte.tile.TileEnergyCollectorMK3;
import projecte.tile.TileRelayMK1;
import projecte.tile.TileRelayMK2;
import projecte.tile.TileRelayMK3;
import projecte.util.GuiIds;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {
    public EntityPlayer player;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        this.player = player;
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
        if (entity instanceof TileCustomiser) {
            return new ContainerCustomiser(player, (TileCustomiser) entity);
        }
        InventoryPouch invpouch = new InventoryPouch();
        invpouch.load(player.inventory);

        if (ID == GuiIds.Pouch) {
            return new ContainerPouch(invpouch, player);
        }

        InventoryManual invmanual = new InventoryManual();

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
        if (entity instanceof TileCustomiser) {
            
            return new GuiCustomiser(player, (TileCustomiser) entity);
        }
        InventoryPouch inv = new InventoryPouch();
        inv.load(player.inventory);
        if (ID == GuiIds.Pouch) {
            return new GuiPouch(inv, player);
        }
        InventoryManual invmanual = new InventoryManual();
        if (ID == GuiIds.Manual) {
            return new GuiManual();
        }
        return null;
    }

}
