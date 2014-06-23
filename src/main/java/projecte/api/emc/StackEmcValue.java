package projecte.api.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class StackEmcValue {
    
    private List<IEmcValue> values = new ArrayList<IEmcValue>();
    private double          value  = -1;
    private ItemStack       is;
    private EmcStackType    type   = EmcStackType.MATTER;
    
    public StackEmcValue(ItemStack is) {
    
        this.is = is;
    }
    
    public double getValue() {
    
        return value;
    }
    
    public StackEmcValue calculateAndDispose() {
    
        if (values == null) return this;
        
        double val = 0;
        
        for (IEmcValue v : values)
            if (v.shouldInvalidateOthers()) {
                value = v.getValue();
                dispose();
                return this;
            }
        
        int vals = 0;
        
        for (IEmcValue v : new ArrayList<IEmcValue>(values)) {
            int v2 = v.getValue();
            if (v2 >= 0) {
                val += v2;
                vals++;
            }
        }
        
        if (vals == 0) return this;
        
        value = val / vals;
        dispose();
        return this;
    }
    
    private void dispose() {
    
        if (values != null) {
            values.clear();
            values = null;
        }
    }
    
    public EmcStackType getType() {
    
        return type;
    }
    
    public List<IEmcValue> getValues() {
    
        return values;
    }
    
    public ItemStack getItem() {
    
        return is;
    }
    
    public void addValue(IEmcValue value) {
    
        if (!values.contains(value)) values.add(value);
    }
    
}
