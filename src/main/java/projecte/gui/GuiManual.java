package projecte.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import projecte.ModInfo;
import projecte.blocks.PEBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiManual extends GuiScreen {
	public static final ResourceLocation gui = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/manual.png");
    public static ResourceLocation Gui_Manual = new ResourceLocation("minecraft", "textures/gui/book.png");
    
    private static ResourceLocation bookRight = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/bookright.png");
    private static ResourceLocation bookLeft = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/bookleft.png");
    
    int bookImageWidth = 206;
    int bookImageHeight = 200;
    private static final int BOOK_BTN_NEXT = 0;
    private static final int BOOK_BTN_PREV = 1;

    public static List<ManualPage> pages = new ArrayList<ManualPage>();

    private GuiButton next;
    private GuiButton prev;

    private int pageIndex = 0;
    private int bookTotalPages = 12;

    public int bookXStart;

    public GuiManual() {

    }

    @Override
    public void initGui() {
        super.initGui();

        bookXStart = (width - 256) / 2;

        buttonList.add(prev = new GuiButtonPageChange(BOOK_BTN_PREV, bookXStart + 2, 200, true));
        buttonList.add(next = new GuiButtonPageChange(BOOK_BTN_NEXT, bookXStart + 285, 183, false));

        updateButtons();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id)
        {
            case BOOK_BTN_NEXT:
                pageIndex++;
                break;
            case BOOK_BTN_PREV:
                --pageIndex;
                break;
        }
        updateButtons();
    }

    private void updateButtons()
    {
        this.next.visible = (this.pageIndex < this.bookTotalPages - 1);
        this.prev.visible = this.pageIndex > 0;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartials) {
        drawBackground();
        drawForeground();

        super.drawScreen(mouseX, mouseY, renderPartials);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char c, int key) {
        if (key == Keyboard.KEY_ESCAPE) {
         //ItemFlowstoneManual.currentIcon = ItemFlowstoneManual.icon[0];
            mc.displayGuiScreen(null);
        }
    }

    protected void drawBackground() {
        int bookXStart = (width - 256) / 2;
       // mc.renderEngine.bindTexture(gui);
        //drawTexturedModalRect(bookXStart, 10, 0, 0, 512, 200);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(bookRight);
        int localWidth = (this.width / 2);
        byte localHeight = 8;
        this.drawTexturedModalRect(localWidth, localHeight, 0, 0, this.bookImageWidth, this.bookImageHeight);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(bookLeft);
        localWidth = localWidth - this.bookImageWidth;
        this.drawTexturedModalRect(localWidth, localHeight, 256 - this.bookImageWidth, 0, this.bookImageWidth, this.bookImageHeight);

  
    }

    public void drawForeground() {
        switch (pageIndex) {
            case 0: drawStartScreen(); break;
            case 1: drawScreenTwo(); break;
            case 2: drawScreenThree(); break;
            case 3: drawScreenFour(); break;
            case 4: drawScreenFive(); break;
            case 5: drawScreenSix(); break;
            case 6: drawScreenSeven(); break;
            case 7: drawScreenEight(); break;
            case 8: drawScreenNine(); break;
            case 9: drawScreenTen(); break;
            case 10: drawScreenEleven(); break;
            case 11: drawScreenTwelve(); break;
        }
    }

    protected void drawStartScreen() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;
        int leftPage = ((width - 256) / 2)-50;
        fontRendererObj.drawString("Home Page", leftPage, 25, 0);

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        
        fontRendererObj.drawString("Welcome to Project E", leftPage, 45, 0);
        fontRendererObj.drawString("This Model is all about energy and matter.", leftPage, 55, 0);
        fontRendererObj.drawString("Will you are playing with this mod you will", leftPage, 65, 0);
        fontRendererObj.drawString("discovering weird new things in the way of", leftPage, 75, 0);
        fontRendererObj.drawString("energy. If you want to learn to use this", leftPage, 85, 0);
        fontRendererObj.drawString("interesting and strange new mod keep reading", leftPage, 95, 0);
        fontRendererObj.drawString("or if your lazy you can look at a video", leftPage, 105, 0);
        fontRendererObj.drawString("online, it's all you to you with this mod", leftPage, 115, 0);
        fontRendererObj.drawString("so have fun and start playing", leftPage, 125, 0);
        
        fontRendererObj.setUnicodeFlag(false);
        fontRendererObj.drawString("And Dont Die!", leftPage, 145, 0);
        fontRendererObj.setUnicodeFlag(true);

		fontRendererObj.drawString("This mod works best with NEI,", bookXStart + 120, 57, 0);
	//	fontRendererObj.drawString("Flowstone Mixtures (a.k.a ", bookXStart + 120, 67, 0);
	//	fontRendererObj.drawString("Lucky Drinks), Steam Tech,", bookXStart + 120, 77, 0);
		//fontRendererObj.drawString("IC2, Applied Energistics 2", bookXStart + 120, 87, 0);
	//	fontRendererObj.drawString("and Buildcraft", bookXStart + 120, 97, 0);
	//	fontRendererObj.drawString("Written by the Flowstone Team", bookXStart + 120, 157, 0);
	//	fontRendererObj.drawString("For your benefits.", bookXStart + 120, 167, 0);
	//	fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }
   /*
	* Example of using a localized string:
	* fontRenderObj.drawString(TextHelper.localize("info.flowstoneenergy.manual.page.one"));
	* That will display the localization on line 152 of en_US.lang
	* Now you just need to get drawSplitString to work :D
	*/

	private void drawScreenTwo() {
        int currentPage = pageIndex + 1;
		int bookXStart = ((width - 256) / 2) + 8;
       // Item item = ItemRegistry.flowwrench;
        fontRendererObj.drawString("Machine Box", bookXStart, 57, 0);

        ManualPage.renderItem(itemRender, bookXStart + 60, 57, new ItemStack(PEBlocks.energyCondenser).getItem());

		boolean unicode = fontRendererObj.getUnicodeFlag();
//        fontRendererObj.setUnicodeFlag(true);
//		fontRendererObj.drawString("The machine box, is the ", bookXStart, 77, 0);
//		fontRendererObj.drawString("basis of all machines and is ", bookXStart, 87, 0);
//		fontRendererObj.drawString("required to make all the ", bookXStart, 97, 0);
//		fontRendererObj.drawString("machines. The machine box ", bookXStart, 107, 0);
//		fontRendererObj.drawString("doesn't do much... it's just", bookXStart, 117, 0);
//        fontRendererObj.drawString("there... ", bookXStart, 127, 0);
//        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
//		fontRendererObj.setUnicodeFlag(unicode);
	}

    private void drawScreenThree() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;
		//fontRendererObj.drawString("Machine Workbench", bookXStart, 57, 0);

		ManualPage.renderItem(itemRender, bookXStart + 95, 57, new ItemStack(PEBlocks.energyCollectorMK1).getItem());

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
//		fontRendererObj.drawString("The Machine Workbench, or MWB", bookXStart, 77, 0);
//		fontRendererObj.drawString("for short, is the first machine", bookXStart, 87, 0);
//		fontRendererObj.drawString("you will need to get started in", bookXStart, 97, 0);
//		fontRendererObj.drawString("Flowstone Energy. You use this", bookXStart, 107, 0);
//		fontRendererObj.drawString("machine to craft the other", bookXStart, 117, 0);
//		fontRendererObj.drawString("machines by adding two gears", bookXStart, 127, 0);
//		fontRendererObj.drawString("a machine box. The MWB has", bookXStart, 137, 0);
//        fontRendererObj.drawString("three inputs, the left is", bookXStart, 147, 0);
//		fontRendererObj.drawString("for a gear, the middle is for", bookXStart, 157, 0);
//		fontRendererObj.drawString("the machine box, and the right", bookXStart, 167, 0);
//		fontRendererObj.drawString("is for the other gear, it also", bookXStart + 120, 57, 0);
//		fontRendererObj.drawString("has one output slot, but the MWB", bookXStart + 120, 67, 0);
//		fontRendererObj.drawString("is not only for machines, it is", bookXStart + 120, 77, 0);
//		fontRendererObj.drawString("also how you create the", bookXStart + 120, 87, 0);
//        fontRendererObj.drawString("Pneumatic Flowwrench.", bookXStart + 120, 97, 0);
//		fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
//        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenFour() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;
//        fontRendererObj.drawString("Energized Ore Tumbler", bookXStart, 57, 0);
//
//        boolean unicode = fontRendererObj.getUnicodeFlag();
//        fontRendererObj.setUnicodeFlag(true);
//        fontRendererObj.drawString("The Energized Ore Tumbler is", bookXStart, 77, 0);
//        fontRendererObj.drawString("a machine that is used to,", bookXStart, 87, 0);
//        fontRendererObj.drawString("double ores it takes the base", bookXStart, 97, 0);
//        fontRendererObj.drawString("ores and smashes it up and", bookXStart, 107, 0);
//        fontRendererObj.drawString("turns it into dusts which can", bookXStart, 117, 0);
//        fontRendererObj.drawString("be smelted into ingots thereby", bookXStart, 127, 0);
//        fontRendererObj.drawString("doubling the ore processing", bookXStart, 137, 0);
//        fontRendererObj.drawString("system. Simply place a piece", bookXStart, 147, 0);
//        fontRendererObj.drawString("of ore into the input slot and", bookXStart, 157, 0);
//        fontRendererObj.drawString("wait for the processing to", bookXStart, 167, 0);
//        fontRendererObj.drawString("finish then put the dusts into", bookXStart + 120, 57, 0);
//        fontRendererObj.drawString("some form of furnace.", bookXStart + 120, 67, 0);
//        fontRendererObj.drawString("NOTE:", bookXStart + 120, 87, 0);
//        fontRendererObj.drawString("If you have Tinker's Construct", bookXStart + 120, 97, 0);
//        fontRendererObj.drawString("or Metallurgy mods installed", bookXStart + 120, 107, 0);
//        fontRendererObj.drawString("their ores will work in this", bookXStart + 120, 117, 0);
//        fontRendererObj.drawString("machine, producing the", bookXStart + 120, 127, 0);
//        fontRendererObj.drawString("corresponding dusts.", bookXStart + 120, 137, 0);
//        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
//        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenFive() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenSix() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenSeven() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenEight() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenNine() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenTen() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenEleven() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    private void drawScreenTwelve() {
        int currentPage = pageIndex + 1;
        int bookXStart = ((width - 256) / 2) + 8;

        boolean unicode = fontRendererObj.getUnicodeFlag();
        fontRendererObj.setUnicodeFlag(true);
        fontRendererObj.drawString("Page " + currentPage + "/" + bookTotalPages, bookXStart + 160, 177, 0);
        fontRendererObj.setUnicodeFlag(unicode);
    }

    protected void RenderItem(RenderItem itemRenderer, int x, int y, Item food)
    {
        itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(food), x, y, false);
    }

    @SideOnly(Side.CLIENT)
    public
    static class GuiButtonPageChange extends GuiButton {
        private final boolean previous;
        private static final String __OBFID = "CL_00000745";

        public GuiButtonPageChange(int ID, int X, int Y, boolean previous) {
            super(ID, X, Y, 20, 10, "");
            this.previous = previous;
        }

        /**
         * Draws this button to the screen.
         */
        @Override
        public void drawButton(Minecraft mc, int mouseX, int mouseY) {
            if (visible) {
                boolean mouseOver = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
                GL11.glColor4f(1, 1, 1, 1);
                mc.renderEngine.bindTexture(Gui_Manual);
                int u = 0;
                int v = 192;

                if (mouseOver) {
                    u += 23;
                }

                if (previous) {
                    v += 13;
                }

                drawTexturedModalRect(xPosition, yPosition, u, v, 23, 13);
            }
        }

    }
}