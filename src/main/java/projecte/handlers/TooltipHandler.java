package projecte.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.input.Mouse;
import projecte.ModInfo;
import projecte.api.emc.EmcData;
import projecte.api.emc.EmcRegistry;
import projecte.api.tile.IEmcContainerItem;
import projecte.util.Color;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class TooltipHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void renderTick(TickEvent.RenderTickEvent event){
        Minecraft mc = Minecraft.getMinecraft();

        try {
            GuiScreen gui = Minecraft.getMinecraft().currentScreen;
            if (gui.isShiftKeyDown() || gui.isCtrlKeyDown() && ((gui instanceof GuiContainer))) {
                ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
                int sizeX = res.getScaledWidth();
                int sizeY = res.getScaledHeight();
                int mouseX = Mouse.getX() * sizeX / mc.displayWidth;
                int mouseY = sizeY - Mouse.getY() * sizeY / mc.displayHeight - 1;

                for (Object obj : ((GuiContainer)gui).inventorySlots.inventorySlots) {
                    Slot slot = (Slot) obj;
                    if (slot.getHasStack()) {
                        int xs = ((Integer) ReflectionHelper.getPrivateValue(GuiContainer.class, ((GuiContainer)gui), new String[]{"xSize", "f", "field_146999_f"})).intValue();
                        int ys = ((Integer) ReflectionHelper.getPrivateValue(GuiContainer.class, ((GuiContainer)gui), new String[]{"ySize", "g", "field_147000_g"})).intValue();
                        int guiLeft = (gui.width - xs) / 2;
                        int guiTop = (gui.height - ys) / 2;

                        if (isMouseOverSlot(slot.xDisplayPosition, slot.yDisplayPosition, mouseX, mouseY, guiLeft, guiTop)) {
                            List<String> tip = new ArrayList<String>();
                            tip.add(slot.getStack().getDisplayName());
                            getToolTip(tip, slot.getStack());
                            Method drawHoveringText = GuiScreen.class.getDeclaredMethod("drawHoveringText", new Class[]{List.class, int.class, int.class, FontRenderer.class});
                            drawHoveringText.setAccessible(true);
                            drawHoveringText.invoke(gui, tip, mouseX, mouseY, Minecraft.getMinecraft().fontRenderer);
                            break;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean isMouseOverSlot(int x, int y, int mouseX, int mouseY, int left, int right){
        int left1 = left;
        int left2 = right;
        mouseX -= left1;
        mouseY -= left2;
        return (mouseX >= x- 1) && (mouseX < x + 16 + 1) && (mouseY >= y - 1) && (mouseY < y + 16 + 1);
    }

    public static List<String> getToolTip(List<String> tip, ItemStack stack){
        EmcData val = EmcRegistry.getValue(stack);

        if (val == null) {
            tip.add(Color.RED + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.novalue"));
        } else {
            tip.add(Color.AQUA + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.itemValue")
                    + ": " + Color.GREEN + ((int) val.getValue()));
            tip.add(Color.AQUA + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.stackValue")
                    + ": "
                    + Color.GREEN
                    + ((int) val.getValue(stack.stackSize)));
            tip.add("");
            tip.add(Color.WHITE + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.type") + ": "
                    + val.getType());
        }

        if (stack.getItem() instanceof IEmcContainerItem) {
            IEmcContainerItem b = (IEmcContainerItem) stack.getItem();

            tip.add(Color.GOLD + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.stored")
                    + ": " + Color.GREEN + b.getStoredEmc(stack));
            tip.add(Color.GOLD + StatCollector.translateToLocal(ModInfo.MOD_ID + ".tooltip.maxStored")
                    + ": "
                    + Color.GREEN
                    + b.getMaxStoredEmc(stack));
        }
        int id = OreDictionary.getOreID(stack);
        String n = OreDictionary.getOreName(id);

        if (id != -1) {
            tip.add(Color.LIGHT_PURPLE + "[DEBUG] Oredict name: " + n);
        }
        return tip;
    }
}
