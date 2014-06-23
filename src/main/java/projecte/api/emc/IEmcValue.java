package projecte.api.emc;

public interface IEmcValue {
    
    public int getValue();
    
    public void dispose();
    
    public boolean shouldInvalidateOthers();
    
}
