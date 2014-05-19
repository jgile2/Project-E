package projecte.tile;

import net.minecraft.block.Block;
import projecte.blocks.PEBlocks;

public class TileCollectorCore extends TileEnergyCollector{
	public TileCollectorCore() {
		super(16, 100, 60000, 16);
	}	
	
	public void isFormed(){
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		Block type1 = this.worldObj.getBlock(x, y+1, z);
		Block type2 = this.worldObj.getBlock(x, y-1, z);
		Block type3 = this.worldObj.getBlock(x, y, z-1);
		Block type4 = this.worldObj.getBlock(x, y, z+1);
		
		
		if(type1==type2&&type2==type3&&type3==type4&&type4==PEBlocks.CollectorMK1){
			//System.out.println("is formed");
		}
	}
	
	@Override
	public void updateEntity() {
		// TODO Auto-generated method stub
		super.updateEntity();
		isFormed();
	}

}
