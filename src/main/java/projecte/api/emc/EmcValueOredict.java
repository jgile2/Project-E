package projecte.api.emc;

public class EmcValueOredict implements IEmcValue {
    
    private String oredictId;
    private int    value;
    
    public EmcValueOredict(String oredictId, int value) {
    
        this.oredictId = oredictId;
        this.value = value;
    }
    
    @Override
    public int getValue() {
    
        return value;
    }
    
    public String getOredictId() {
    
        return oredictId;
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
