package projecte.api.emc;

import net.minecraft.item.ItemStack;

public class EmcValueCrafting implements IEmcValue {
    
    private RecipeWrapper recipe;
    
    public EmcValueCrafting(RecipeWrapper recipe) {
    
        this.recipe = recipe;
    }
    
    @Override
    public int getValue() {
    
        int val = 0;
        
        for (ItemStack i : recipe.getInput()) {
            double v = EmcRegistry.inst().getValue(i);
            if (v > 0) val += v;
            else return -1;
        }
        
        val /= recipe.getOutput().stackSize;
        
        if (val == 0) return -1;
        
        return val;
    }
    
    @Override
    public boolean shouldInvalidateOthers() {
    
        return false;
    }
    
    @Override
    public void dispose() {
    
        recipe.dispose();
    }
    
}
