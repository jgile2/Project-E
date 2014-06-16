package projecte.api.emc;

import net.minecraft.item.ItemStack;

public class RecipeEmcValue implements IEmcValue {
    
    private ItemStack[] input;
    private ItemStack   output;
    
    public RecipeEmcValue(ItemStack[] input, ItemStack output) {
    
        this.input = input;
        this.output = output;
    }
    
    public ItemStack[] getInput() {
    
        return input;
    }
    
    public ItemStack getOutput() {
    
        return output;
    }
    
    @Override
    public EmcValue getValue() {
    
        double value = 0;
        
        for (ItemStack i : input){
            value += EmcRegistry.getValuePreCalc(i, true).getValue();
        	System.out.println("Emc value is"+ value+" for item: "+i.getDisplayName());
        	
        }
        return new EmcValue(EmcValueSource.RECIPE, value);
    }
    
}
