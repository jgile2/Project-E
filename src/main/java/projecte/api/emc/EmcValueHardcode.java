package projecte.api.emc;

public class EmcValueHardcode implements IEmcValue {
    
    private int value = 0;
    
    public EmcValueHardcode(int value) {
    
        this.value = value;
    }
    
    @Override
    public int getValue() {
    
        return value;
    }
    
    @Override
    public boolean shouldInvalidateOthers() {
    
        return true;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }
    
}
