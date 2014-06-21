package projecte.api.tile;

import net.minecraftforge.common.util.ForgeDirection;

public class EmcContainerPipe extends EmcContainerTile {
	public EmcContainerPipe() {
		setMaxEmcOutput(20);
		setMaxEmcInput(20);
		setMaxEmcStored(1000);
		// TODO Auto-generated constructor stub
	}
	
	public void addFluid(){
		int currentStored = this.getEmcStored();
		
	}

}
