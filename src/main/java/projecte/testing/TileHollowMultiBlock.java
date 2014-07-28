package projecte.testing;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import projecte.fluid.PEFluids;
import projecte.packet.PacketCollector;
import projecte.packet.PacketManager;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileHollowMultiBlock extends TileMultiBlock implements ISidedInventory,IFluidHandler {
	private ItemStack[] items = new ItemStack[11];
	private int maxEMCPerSecond = 16;
	private long tick = 0;
	private double tempStored = 1;
    public TileHollowMultiBlock() {
    	setMaxEmcOutput(100);
		setMaxEmcStored(60000);
		//maxEMCPerSecond=16;
    }

    @Override
    public void doMultiBlockStuff() {
    	//System.out.println("X: "+getMasterX()+", Y: "+getMasterY()+", Z: "+getMasterZ());
    	//System.out.println(tank.getFluidAmount());
    	
    	if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {

			double a = maxEMCPerSecond;

			a /= 20D;
        	//System.out.println(getSunStrength());

			a *= getSunStrength();

			tempStored += a;

			if (tempStored >= 1) {
	        	//System.out.println(getEmcStored());

				setEmcStored(getEmcStored() + 1);
				tempStored -= 1;
				//System.out.println(FMLCommonHandler.instance().getEffectiveSide());
				PacketCollector packet = new PacketCollector(getEmcStored(),this.worldObj,this.getMasterX(),this.getMasterY(),this.getMasterZ());
				
				PacketManager.sendToAll(packet);
				
			}

			tick = tick + 1;
    	}

		
    }

    @Override
    public void masterWriteToNBT(NBTTagCompound nbt) {
        //System.out.println(FMLCommonHandler.instance().getEffectiveSide());

    	saveEmcToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				this.items[i].writeToNBT(tag);
				nbttaglist.appendTag(tag);
			}
		}

		nbt.setTag("Items", nbttaglist);
    }

    @Override
    public void masterReadFromNBT(NBTTagCompound nbt) {
    	readEmcFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.items = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbtSlot = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
			int var1 = nbtSlot.getByte("Slot") & 255;

			if (var1 >= 0 && var1 < this.items.length) {
				this.items[var1] = ItemStack.loadItemStackFromNBT(nbtSlot);
			}
		}
    }

    @Override
    public boolean checkMultiBlockForm() {
        int i = 0;
        // Scan a 3x3x3 area, starting with the bottom left corner
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    // Make sure tile isn't null, is an instance of the same Tile, and isn't already a part of a multiblock (if ours is already part of one)
                    if (tile != null && (tile instanceof TileHollowMultiBlock)) {
                        if (this.isMaster()) {
                            if (((TileHollowMultiBlock)tile).hasMaster())
                                i++;
                        } else if (!((TileHollowMultiBlock)tile).hasMaster())
                            i++;
                    }
                }
        // check if there are 26 blocks present ((3*3*3) - 1) and check that center block is empty
        return i > 25 && worldObj.isAirBlock(xCoord, yCoord + 1, zCoord);
    }

    @Override
    public void setupStructure() {
        for (int x = xCoord - 1; x < xCoord + 2; x++)
            for (int y = yCoord; y < yCoord + 3; y++)
                for (int z = zCoord - 1; z < zCoord + 2; z++) {
                    TileEntity tile = worldObj.getTileEntity(x, y, z);
                    // Check if block is bottom center block
                    boolean master = (x == xCoord && y == yCoord && z == zCoord);
                    if (tile != null && (tile instanceof TileHollowMultiBlock)) {
                        ((TileHollowMultiBlock) tile).setMasterCoords(xCoord, yCoord, zCoord);
                        ((TileHollowMultiBlock) tile).setHasMaster(true);
                        ((TileHollowMultiBlock) tile).setIsMaster(master);
                    }
                }
    }
    @Override
	public int getSizeInventory() {
		return items.length;

	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.items[i];

	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.items[i] != null) {
			ItemStack itemstack;

			if (this.items[i].stackSize <= j) {
				itemstack = this.items[i];
				this.items[i] = null;
				return itemstack;
			} else {
				itemstack = this.items[i].splitStack(j);

				if (this.items[i].stackSize == 0) {
					this.items[i] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}

	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.items[i] != null) {
			ItemStack itemstack = this.items[i];
			this.items[i] = null;
			return itemstack;
		} else {
		}

		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	public FluidTank tank = new FluidTank(1000);

	protected int maxEmcInput = 0;
	protected int maxEmcOutput = 0;

	public void setMaxEmcInput(int max) {
		maxEmcInput = max;
	}

	public void setMaxEmcOutput(int max) {
		maxEmcOutput = max;
	}

	public void setMaxEmcStored(int max) {
		tank.setCapacity(max);
		setEmcStored(getEmcStored());
	}

	public int getMaxEmcInput() {
		return maxEmcInput;
	}

	public int getMaxEmcOutput() {
		return maxEmcOutput;
	}

	public int getMaxEmcStored() {
		return tank.getCapacity();
	}

	public int getEmcStored() {
		return tank.getFluidAmount();
	}

	public void setEmcStored(int amt) {
		tank.setFluid(new FluidStack(PEFluids.liquidEMC, Math.min(amt, getMaxEmcStored())));
		

	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource.getFluid() != PEFluids.liquidEMC)
			return 0;

		FluidStack res = resource.copy();
		res.amount = Math.min(res.amount, maxEmcInput);

		return tank.fill(res, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		World world = this.worldObj;
		TileHollowMultiBlock masterTank = (TileHollowMultiBlock) world.getTileEntity(getMasterX(), getMasterY(), getMasterZ());
		if (resource.getFluid() != PEFluids.liquidEMC)
			return null;
		
		return masterTank.tank.drain(Math.min(resource.amount, maxEmcOutput), doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		World world = this.worldObj;
		TileHollowMultiBlock masterTank = (TileHollowMultiBlock) world.getTileEntity(getMasterX(), getMasterY(), getMasterZ());
		return masterTank.tank.drain(Math.min(maxDrain, maxEmcOutput), doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false; 
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return fluid == PEFluids.liquidEMC;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		World world = this.worldObj;
		TileHollowMultiBlock masterTank = (TileHollowMultiBlock) world.getTileEntity(getMasterX(), getMasterY(), getMasterZ());
	
		return new FluidTankInfo[] { masterTank.tank.getInfo() };
	}

	public void saveEmcToNBT(NBTTagCompound tag) {
		tag.setInteger("stored_emc", tank.getFluidAmount());
	}

	public void readEmcFromNBT(NBTTagCompound tag) {
		setEmcStored(tag.getInteger("stored_emc"));
	}

	public boolean checkSun() {
		World w = this.worldObj;

		if (w.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.glowstone)
			return true;

		for (int y = yCoord + 1; y < w.getHeight(); y++) {
			Block b = w.getBlock(xCoord, y, zCoord);
			if (b.getLightOpacity(w, xCoord, y, zCoord) > 0)// 0 - 255
				return false;
		}
		return true;
	}

	private double getSunBrightness(World w, float par1) {
		float f1 = w.getCelestialAngle(par1);
		float f2 = 1.0F - (MathHelper.cos(f1 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);

		if (f2 < 0.0F) {
			f2 = 0.0F;
		}

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		f2 = 1.0F - f2;
		f2 = (float) ((double) f2 * (1.0D - (double) (w.getRainStrength(par1) * 5.0F) / 16.0D));
		f2 = (float) ((double) f2 * (1.0D - (double) (w.getWeightedThunderStrength(par1) * 5.0F) / 16.0D));
		return f2 * 0.8F + 0.2F;
	}

	public double getSunStrength() {
//		if (!checkSun())
//			return 0;

		World w = this.worldObj;

//		if (w.getBlock(xCoord, yCoord +2 , zCoord) == Blocks.glowstone)
//			return 1;
		//System.out.println(getSunBrightness(w, 0F));

		return getSunBrightness(w, 0F);
	}

	/* Functionality */



	@Override
	public void updateEntity() {
		super.updateEntity();
	}

}
