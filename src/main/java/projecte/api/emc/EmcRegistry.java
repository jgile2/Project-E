package projecte.api.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import projecte.items.PEItems;

public class EmcRegistry {
    
    private static EmcRegistry inst;
    
    public static EmcRegistry inst() {
    
        if (inst == null) inst = new EmcRegistry();
        
        return inst;
    }
    
    public static EmcRegistry instance() {
    
        return inst();
    }
    
    private EmcRegistry() {
    
    }
    
    private List<StackEmcValue> values = new ArrayList<StackEmcValue>();
    
    public StackEmcValue getEmcValue(ItemStack item) {
    
        if (item == null) return null;
        
        for (StackEmcValue v : values)
            if (v.getItem().isItemEqual(item)) return v;
        
        StackEmcValue v = new StackEmcValue(item);
        values.add(v);
        return v;
    }
    
    public double getValue(ItemStack item) {
    
        StackEmcValue val = getEmcValue(item);
        // val.calculateAndDispose();
        return val.getValue();
    }
    
    public void addHardcodedValue(ItemStack item, int value) {
    
        EmcValueHardcode val = new EmcValueHardcode(value);
        getEmcValue(item).addValue(val);
    }
    
    @SuppressWarnings("rawtypes")
    public void addHardcodedValue(Item item, int value) {
    
        EmcValueHardcode val = new EmcValueHardcode(value);
        List l = new ArrayList();
        item.getSubItems(item, null, l);
        for (Object o : l) {
            ItemStack is = (ItemStack) o;
            if (is == null) continue;
            getEmcValue(is).addValue(val);
        }
    }
    
    @SuppressWarnings("rawtypes")
    public void addHardcodedValue(Block block, int value) {
    
        Item item = Item.getItemFromBlock(block);
        EmcValueHardcode val = new EmcValueHardcode(value);
        List l = new ArrayList();
        item.getSubItems(item, null, l);
        for (Object o : l) {
            ItemStack is = (ItemStack) o;
            if (is == null) continue;
            getEmcValue(is).addValue(val);
        }
    }
    
    public void addOredictValue(String id, int value) {
    
        EmcValueOredict val = new EmcValueOredict(id, value);
        for (ItemStack is : OreDictionary.getOres(id))
            getEmcValue(is).addValue(val);
    }
    
    public void loadValuesFromRecipes() {
    
        for (Object o : CraftingManager.getInstance().getRecipeList()) {
            IRecipe r = (IRecipe) o;
            if (r == null) continue;
            
            List<RecipeWrapper> recipes = RecipeWrapper.wrap(r);
            
            if (recipes == null || recipes.isEmpty()) continue;
            
//            StackEmcValue v = getEmcValue(recipes.get(0).getOutput());
//            for (RecipeWrapper recipe : recipes) {
//                EmcValueCrafting val = new EmcValueCrafting(recipe);
//                v.addValue(val);
//            }
        }
    }
    
    public void calculateValues() {
    
        for (int i = 0; i < 16; i++) {
            int id = 0;
            while (id < values.size()) {
                values.get(id).calculateAndDispose();
                id++;
            }
        }
    }
    
    public void postInit() {
    
        //loadValuesFromRecipes();
        calculateValues();
    }
    
    public void registerDefault() {
    
        addOredictValue("logWood", 32);
        addOredictValue("woodLog", 32);
        
        addOredictValue("saplingTree", 32);
        addOredictValue("treeSapling", 32);
        
        addOredictValue("leavesTree", 1);
        addOredictValue("treeLeaves", 1);
        
        addOredictValue("cobblestone", 1);
        addOredictValue("blockCobble", 1);
        
        addHardcodedValue(new ItemStack(Blocks.deadbush), 1);
        addHardcodedValue(new ItemStack(Blocks.tallgrass), 1);
        addHardcodedValue(new ItemStack(Blocks.grass), 1);
        addHardcodedValue(new ItemStack(Blocks.glass_pane), 1);
        addHardcodedValue(new ItemStack(Blocks.ice), 1);
        addHardcodedValue(new ItemStack(Blocks.leaves), 1);
        addHardcodedValue(new ItemStack(Blocks.leaves2), 1);
        addHardcodedValue(new ItemStack(Blocks.netherrack), 1);
        addHardcodedValue(new ItemStack(Blocks.sand), 1);
        addHardcodedValue(new ItemStack(Blocks.snow), 1);
        addHardcodedValue(new ItemStack(Blocks.stone), 1);
        addHardcodedValue(new ItemStack(Blocks.end_stone), 1);
        
        addHardcodedValue(new ItemStack(Blocks.gravel), 4);
        addHardcodedValue(new ItemStack(Items.flint), 4);
        
        addHardcodedValue(new ItemStack(Blocks.cactus), 8);
        addHardcodedValue(new ItemStack(Blocks.vine), 8);
        
        addHardcodedValue(new ItemStack(Blocks.web), 12);
        addHardcodedValue(new ItemStack(Items.string), 12);
        
        addHardcodedValue(new ItemStack(Blocks.yellow_flower), 16);
        addHardcodedValue(new ItemStack(Blocks.red_flower), 16);
        addHardcodedValue(new ItemStack(Blocks.waterlily), 16);
        
        addHardcodedValue(new ItemStack(Items.wheat), 24);
        addHardcodedValue(new ItemStack(Items.nether_wart), 24);
        addHardcodedValue(new ItemStack(Items.rotten_flesh), 24);
        addHardcodedValue(new ItemStack(Items.slime_ball), 24);
        
        addHardcodedValue(new ItemStack(Blocks.reeds), 32);
        addHardcodedValue(new ItemStack(Items.egg), 32);
        
        addHardcodedValue(new ItemStack(Items.feather), 48);
        addHardcodedValue(new ItemStack(Blocks.soul_sand), 48);
        
        addHardcodedValue(new ItemStack(Blocks.obsidian), 64);
        addHardcodedValue(new ItemStack(Items.redstone), 64);
        
        addHardcodedValue(new ItemStack(Items.porkchop), 64);
        addHardcodedValue(new ItemStack(Items.beef), 64);
        addHardcodedValue(new ItemStack(Items.fish), 64);
        addHardcodedValue(new ItemStack(Items.chicken), 64);
        addHardcodedValue(new ItemStack(Items.leather), 64);
        addHardcodedValue(new ItemStack(Items.clay_ball), 64);
        
        addHardcodedValue(new ItemStack(Items.glowstone_dust), 384);
        
        addHardcodedValue(new ItemStack(PEItems.alchemicalCoal), 512);
        
        addHardcodedValue(new ItemStack(Items.ender_pearl), 1024);
        addHardcodedValue(new ItemStack(Items.blaze_rod), 1536);
        addHardcodedValue(new ItemStack(Items.bone), 96);
        
        addOredictValue("blockStone", 1);
        
        // Items
        
        addOredictValue("ingotIron", 256);
        addOredictValue("ingotGold", 2048);
        addOredictValue("gemDiamond", 8192);
        addOredictValue("gemEmerald", 8192);
        addOredictValue("ingotCopper", 85);
        addOredictValue("ingotTin", 128);
        addOredictValue("ingotBronze", 96);
        addOredictValue("ingotSilver", 512);
        addOredictValue("ingotSilver", 2048);
        addOredictValue("ingotLead", 2048);
        
        addHardcodedValue(Blocks.wool, 48);
        
        addHardcodedValue(new ItemStack(Items.coal), 128);
    }
}
