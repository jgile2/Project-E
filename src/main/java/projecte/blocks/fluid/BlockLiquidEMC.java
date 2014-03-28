package projecte.blocks.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;
import projecte.ModInfo;
import projecte.ProjectE;
import projecte.fluid.PEFluids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLiquidEMC extends BlockFluidClassic {
	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowingIcon;

	public BlockLiquidEMC() {
		super(PEFluids.liquidEMC, Material.water);
		setBlockName(ModInfo.MOD_ID + ".liquidEmc");
		setCreativeTab(ProjectE.tab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {

		stillIcon = reg.registerIcon(ModInfo.MOD_ID + ":liquidEMC_still");
		flowingIcon = reg.registerIcon(ModInfo.MOD_ID + ":liquidEMC_flow");
		PEFluids.liquidEMC.setIcons(stillIcon, flowingIcon);
		this.blockIcon = stillIcon;
	}
	


}
