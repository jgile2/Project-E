package projecte.event;

import projecte.blocks.PEBlocks;
import projecte.items.PEItems;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketFillEvent {

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void tryToFillBucket(FillBucketEvent event) {
		if(event.current.getItem() != Items.bucket)return;
		
		ItemStack fullBucket = getLiquid(event.world, event.target);
		
		if(fullBucket==null)return;
		
		event.world.setBlockToAir(event.target.blockX,event.target.blockY,event.target.blockZ);
		event.result=fullBucket;
		event.setResult(Result.ALLOW);
	}
	
	private ItemStack getLiquid(World world,MovingObjectPosition pos){
		Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
		if(block == PEBlocks.blockFluidEMC) return new ItemStack(PEItems.BucketLiquidEMC);
		
		return null;
	}
}
