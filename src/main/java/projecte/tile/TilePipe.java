package projecte.tile;

import projecte.api.tile.EmcContainerPipe;
import projecte.api.tile.EmcContainerTile;
import projecte.blocks.PEBlocks;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TilePipe extends EmcContainerPipe {
	private NBTTagCompound nbt;
	public int liquidToExtract;


	public TilePipe() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateEntity() {

		// this.setEmcStored(1000);
		canDrain();
	}

	public void canDrain() {

		for (int i = 0; i < 6; i++) {
			System.out.println("loop");

			ForgeDirection dir = ForgeDirection.getOrientation(i);
			int dx = xCoord + dir.offsetX;
			int dy = yCoord + dir.offsetY;
			int dz = zCoord + dir.offsetZ;

			World world = this.worldObj;
			Block block = world.getBlock(dx, dy, dz);
			TileEntity blockTile = world.getTileEntity(dx, dy, dz);
			System.out.println(this.getEmcStored());
			
			if (liquidToExtract <= FluidContainerRegistry.BUCKET_VOLUME) {
				liquidToExtract += FluidContainerRegistry.BUCKET_VOLUME;
			}
			
			if (blockTile instanceof IFluidHandler) {
				IFluidHandler fluidHandler = (IFluidHandler) blockTile;
				
				FluidStack extracted = fluidHandler.drain(dir.getOpposite(), liquidToExtract > 16 ? 16 : liquidToExtract, false);

				int inserted = 0;
				if (extracted != null) {
					//inserted = transport.fill(dir, extracted, true);

					fluidHandler.drain(dir.getOpposite(), inserted, true);
					System.out.println("draining");
				}
				
			}
			
			if (this.getEmcStored() >= 0) {
				if (blockTile instanceof EmcContainerPipe) {
					System.out.println("found pipe");
					// drain(dir, this.);
					FluidStack fluid = tank.getFluid();
					if (fluid != null) {
						tank.drain(Math.min(fluid.amount, maxEmcOutput), true);
					}
				}
			}
		}
	}

	

	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		saveEmcToNBT(nbt);
		super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		readEmcFromNBT(nbt);
		super.readFromNBT(nbt);
	}
}
