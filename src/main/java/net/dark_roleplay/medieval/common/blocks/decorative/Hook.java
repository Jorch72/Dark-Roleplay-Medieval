package net.dark_roleplay.medieval.common.blocks.decorative;

import net.dark_roleplay.medieval.common.blocks.tileentitys.TileEntityHook;
import net.dark_roleplay.medieval.common.handler.DRPMedievalBlocks;
import net.dark_roleplay.medieval.common.handler.DRPMedievalCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Hook extends BlockContainer {

	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public Hook(String registryName) {
		super(Material.IRON);
		this.setRegistryName(registryName);
		this.setUnlocalizedName(registryName);
		this.setCreativeTab(DRPMedievalCreativeTabs.DECORATION);
		this.setHardness(4F);
		this.setHarvestLevel("pickaxe", 1);
		this.setSoundType(SoundType.METAL);
	}

	// -------------------------------------------------- Block Data --------------------------------------------------

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.3125F, 0.375F, 0.3125F, 0.6875F, 1F, 0.6875F);
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) {

		switch (meta) {
			case 0:
				return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.NORTH);
			case 1:
				return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.EAST);
			case 2:
				return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.SOUTH);
			case 3:
				return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.WEST);
			default:
				return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.NORTH);
		}
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {

		EnumFacing facing = state.getValue(Hook.FACING);
		if(facing.equals(EnumFacing.NORTH)) return 0;
		if(facing.equals(EnumFacing.EAST)) return 1;
		if(facing.equals(EnumFacing.SOUTH)) return 2;
		if(facing.equals(EnumFacing.WEST)) return 3;
		return 0;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, new IProperty[] {Hook.FACING});
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
			
	// -------------------------------------------------- Block Placement --------------------------------------------------
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){

		if(!this.canBlockStay(worldIn, pos, EnumFacing.UP)){
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}
				super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
	}

	protected boolean canBlockStay(World worldIn, BlockPos pos, EnumFacing facing) {

		return worldIn.getBlockState(pos.offset(EnumFacing.UP)).getBlock().equals(DRPMedievalBlocks.CHAIN);
	}
	
	// -------------------------------------------------- Block Events --------------------------------------------------
	
	@Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){

		if(!worldIn.getBlockState(pos.offset(EnumFacing.UP)).getBlock().equals(DRPMedievalBlocks.CHAIN)) return Blocks.AIR.getDefaultState();
		EntityPlayer entity = (EntityPlayer) placer;
		if(entity != null){
			int dir = MathHelper.floor((entity.rotationYaw * 4.0F) / 360.0F + 0.5D) & 3;
			switch (dir) {
				case 0:
					return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.NORTH);
				case 1:
					return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.EAST);
				case 2:
					return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.SOUTH);
				case 3:
					return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.WEST);
				default:
					return this.getDefaultState().withProperty(Hook.FACING, EnumFacing.NORTH);
			}
		}
		return Blocks.AIR.getDefaultState();
	}
	
	// -------------------------------------------------- Old Rendering System --------------------------------------------------
	// TODO Old Rendering System
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {

		return new TileEntityHook();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}
