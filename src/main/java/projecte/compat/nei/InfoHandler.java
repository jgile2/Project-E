package projecte.compat.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//import net.minecraft.inventory.Container;

public class InfoHandler implements IUsageHandler, ICraftingHandler {
    
    @SideOnly(Side.CLIENT)
    public FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    public static int   color        = -12566464;
    int                 currentRecipe;
    ItemStack           displayItem  = null;
    
    public InfoHandler() {
    
        currentRecipe = -1;
    }
    
    public InfoHandler(int currentRecipe, ItemStack item) {
    
        if (currentRecipe < InfoData.data.size()) {
            this.currentRecipe = currentRecipe;
        }
        
        displayItem = item;
    }
    
    @Override
    public String getRecipeName() {
    
        return "Project E: Info";
    }
    
    @Override
    public int numRecipes() {
    
        return currentRecipe < 0 ? 0 : InfoData.data.get(currentRecipe).info.length;
    }
    
    @Override
    public void drawBackground(int recipe) {
    
    }
    
    public int getWidth() {
    
        return 166;
    }
    
    @Override
    public PositionedStack getResultStack(int recipe) {
    
        return new PositionedStack(displayItem != null ? displayItem : InfoData.data.get(currentRecipe).item, getWidth() / 2 - 9, 0, false);
    }
    
    @Override
    public void drawForeground(int recipe) {
    
        List text = fontRenderer.listFormattedStringToWidth(InfoData.data.get(currentRecipe).info[recipe], getWidth() - 8);
        // List<String> text = new ArrayList<String>();
        // text.add("test");
        for (int i = 0; i < text.size(); i++) {
            String t = (String) text.get(i);
            GuiDraw.drawString(t, getWidth() / 2 - GuiDraw.getStringWidth(t) / 2, 18 + i * 8, color, false);
        }
    }
    
    @Override
    public List<PositionedStack> getIngredientStacks(int recipe) {
    
        return new ArrayList();
    }
    
    @Override
    public List<PositionedStack> getOtherStacks(int recipetype) {
    
        return new ArrayList();
    }
    
    @Override
    public void onUpdate() {
    
    }
    
    @Override
    public boolean hasOverlay(GuiContainer gui, Container container, int recipe) {
    
        return false;
    }
    
    @Override
    public IRecipeOverlayRenderer getOverlayRenderer(GuiContainer gui, int recipe) {
    
        return null;
    }
    
    @Override
    public IOverlayHandler getOverlayHandler(GuiContainer gui, int recipe) {
    
        return null;
    }
    
    @Override
    public int recipiesPerPage() {
    
        return 1;
    }
    
    @Override
    public List<String> handleTooltip(GuiRecipe gui, List<String> currenttip, int recipe) {
    
        return currenttip;
    }
    
    @Override
    public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe) {
    
        return currenttip;
    }
    
    @Override
    public boolean keyTyped(GuiRecipe gui, char keyChar, int keyCode, int recipe) {
    
        return false;
    }
    
    @Override
    public boolean mouseClicked(GuiRecipe gui, int button, int recipe) {
    
        return false;
    }
    
    @Override
    public IUsageHandler getUsageHandler(String inputId, Object[] ingredients) {
    
        if (!inputId.equals("item")) { return this; }
        
        for (Object ingredient : ingredients) {
            if ((ingredient instanceof ItemStack)) {
                for (int j = 0; j < InfoData.data.size(); j++)
                    if (InfoData.data.get(j).matches((ItemStack) ingredient)) return new InfoHandler(j, (ItemStack) ingredient);
            }
        }
        return this;
    }
    
    @Override
    public ICraftingHandler getRecipeHandler(String outputId, Object[] results) {
    
        if (outputId != "item") { return this; }
        
        for (Object result : results) {
            if ((result instanceof ItemStack)) {
                for (int j = 0; j < InfoData.data.size(); j++)
                    if (InfoData.data.get(j).matches((ItemStack) result)) return new InfoHandler(j, (ItemStack) result);
            }
        }
        return this;
    }
}
