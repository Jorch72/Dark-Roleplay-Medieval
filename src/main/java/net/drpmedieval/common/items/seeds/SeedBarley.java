package net.drpmedieval.common.items.seeds;

import net.drpcore.api.items.AdvancedCropSeed;
import net.drpmedieval.common.blocks.DRPMedievalBlocks;
import net.drpmedieval.common.util.DRPMedievalCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SeedBarley extends AdvancedCropSeed {

	public SeedBarley() {
		super(DRPMedievalBlocks.BARLEY, Blocks.FARMLAND);
		this.setRegistryName("SeedBarley");
		this.setUnlocalizedName("SeedBarley");
		this.setCreativeTab(DRPMedievalCreativeTabs.drpmedievalMiscTab);
	}
}
