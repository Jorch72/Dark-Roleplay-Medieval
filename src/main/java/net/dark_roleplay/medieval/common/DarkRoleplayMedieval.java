package net.dark_roleplay.medieval.common;

import com.google.common.collect.ImmutableMap;

import net.dark_roleplay.library.sides.IProxy;
import net.dark_roleplay.medieval.client.objects.blocks.color_handlers.DryClayGrassColor;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.TESR_ClockCore;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.TESR_Flowers;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.TESR_Roof;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.TESR_Shelf;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.TESR_ShopSign;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderAnvil;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderCauldron;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderChain;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderFirepit;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderGrindstone;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderHangingCauldron;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderHook;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderKeyHanging;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderMortar;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderRopeAnchor;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderShipsWheel;
import net.dark_roleplay.medieval.client.objects.blocks.tesrs.old.SpecialRenderTarget;
import net.dark_roleplay.medieval.client.objects.model_loaders.DelayedBaker_HangingBridge;
import net.dark_roleplay.medieval.client.objects.model_loaders.DelayedBaker_RopeFence;
import net.dark_roleplay.medieval.client.objects.model_loaders.DelayedBaker_Timbering;
import net.dark_roleplay.medieval.client.objects.model_loaders.MultiLayerModelLoader;
import net.dark_roleplay.medieval.common.handler.MedievalGuis;
import net.dark_roleplay.medieval.common.handler.MedievalBlocks;
import net.dark_roleplay.medieval.common.handler.MedievalItems;
import net.dark_roleplay.medieval.common.handler.MedievalMappings;
import net.dark_roleplay.medieval.common.handler.MedievalPackets;
import net.dark_roleplay.medieval.common.handler.MedievalVillagers;
import net.dark_roleplay.medieval.common.handler.MedievalWorldGen;
import net.dark_roleplay.medieval.common.objects.blocks.tile_entities.TE_ClockCore;
import net.dark_roleplay.medieval.common.objects.blocks.tile_entities.TE_FlowerContainer;
import net.dark_roleplay.medieval.common.objects.blocks.tile_entities.TE_Roof;
import net.dark_roleplay.medieval.mess.common.objects.blocks.storage.barrels.TESR_FluidBarrel;
import net.dark_roleplay.medieval.mess.common.objects.blocks.storage.barrels.TE_FluidBarrel;
import net.dark_roleplay.medieval.mess.common.objects.blocks.storage.shelf.TE_Shelf;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TE_DungeonChest;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityAnvil;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityCauldron;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityChain;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityFirepit;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityGrindstone;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityHangingCauldron;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityHook;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityKeyHanging;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityMortar;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityRopeAnchor;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityShipsWheel;
import net.dark_roleplay.medieval.mess.common.objects.blocks.tileentities.TileEntityTarget;
import net.dark_roleplay.medieval.mess.common.objects.blocks.util.shop_sign.TE_ShopSign;
import net.dark_roleplay.medieval.testing.Keybinds;
import net.dark_roleplay.medieval.testing.blockstate_loading.CustomBlockstateLoader;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = References.MODID, version = References.VERSION, name = References.NAME, acceptedMinecraftVersions = References.ACCEPTEDVERSIONS, dependencies = References.DEPENDECIES, updateJSON = References.UPDATECHECK)
public class DarkRoleplayMedieval {

	@Mod.Instance(References.MODID)
	public static DarkRoleplayMedieval INSTANCE;
	
	@SidedProxy
	public static IBaseProxy proxy;
	
	public static Side SIDE;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		SIDE = event.getSide();
		MedievalWorldGen.init(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(DarkRoleplayMedieval.INSTANCE, new MedievalGuis());
		proxy.preInit(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		MedievalVillagers.init(event);
		MedievalMappings.init(event);
		MedievalPackets.init();
		proxy.init(event);

		MedievalBlocks.CANDLE_HOLDER_UNLIT.init(MedievalBlocks.CANDLE_HOLDER_LIT, Item.getItemFromBlock(MedievalBlocks.BEESWAX_CANDLE));;
		MedievalBlocks.CANDLE_HOLDER_LIT.init(MedievalBlocks.CANDLE_HOLDER_UNLIT, Item.getItemFromBlock(MedievalBlocks.BEESWAX_CANDLE));
		MedievalBlocks.TORCH_HOLDER_UNLIT.init(MedievalBlocks.TORCH_HOLDER_LIT, Item.getItemFromBlock(Blocks.TORCH));
		MedievalBlocks.TORCH_HOLDER_LIT.init(MedievalBlocks.TORCH_HOLDER_UNLIT, Item.getItemFromBlock(Blocks.TORCH));
		MedievalBlocks.CANDLE_HOLDER_EMPTY.init(MedievalBlocks.CANDLE_HOLDER_UNLIT, Item.getItemFromBlock(MedievalBlocks.BEESWAX_CANDLE));
		MedievalBlocks.TORCH_HOLDER_EMPTY.init(MedievalBlocks.TORCH_HOLDER_UNLIT, Item.getItemFromBlock(Blocks.TORCH));
		
		MedievalBlocks.SALPETER_ORE.init(MedievalItems.ORE_CHUNK_SALPETER);
		MedievalBlocks.SILVER_ORE.init(MedievalItems.ORE_CHUNK_SILVER);
		MedievalBlocks.TIN_ORE.init(MedievalItems.ORE_CHUNK_TIN);
		MedievalBlocks.COPPER_ORE.init(MedievalItems.ORE_CHUNK_COPPER);
		MedievalBlocks.SULFUR_ORE.init(MedievalItems.ORE_CHUNK_SULFUR);
		
		GameRegistry.addSmelting(MedievalItems.DOUGH, new ItemStack(Items.BREAD), 0.1f);
		GameRegistry.addSmelting(MedievalItems.RAW_WOLF, new ItemStack(MedievalItems.COOKED_WOLF), 0.1f);
		GameRegistry.addSmelting(MedievalItems.RAW_CATFISH, new ItemStack(MedievalItems.COOKED_CATFISH), 0.1f);
		GameRegistry.addSmelting(MedievalItems.PUMPKIN_DOUGH, new ItemStack(MedievalItems.PUMPKIN_BREAD), 0.1f);
		GameRegistry.addSmelting(Item.getItemFromBlock(Blocks.OBSIDIAN), new ItemStack(MedievalBlocks.OBSIDIAN_GLASS), 0.1f);		
		GameRegistry.addSmelting(Item.getItemFromBlock(MedievalBlocks.UNFIRED_VASE), new ItemStack(MedievalBlocks.FIRED_VASE), 0.1f);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
	public static interface IBaseProxy extends IProxy{
		public default IAnimationStateMachine load(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters) {return null;}
	}
	
	public static class ClientProxy implements IBaseProxy{
		
		public static int TELESCOPE_LEVEL = 0;
		
		@Override
		public void preInit(FMLPreInitializationEvent event) {
			ModelLoaderRegistry.registerLoader(new DelayedBaker_HangingBridge());
			ModelLoaderRegistry.registerLoader(new DelayedBaker_RopeFence());
			ModelLoaderRegistry.registerLoader(new CustomBlockstateLoader());	
			ModelLoaderRegistry.registerLoader(new DelayedBaker_Timbering());
			ModelLoaderRegistry.registerLoader(new MultiLayerModelLoader());
			
//			RenderingRegistry.<Entity_Fox>registerEntityRenderingHandler(Entity_Fox.class, Render_Fox.FACTORY);
//			RenderingRegistry.<Wheelbarrel>registerEntityRenderingHandler(Wheelbarrel.class, WheelbarrelRenderer.FACTORY);
			ClientRegistry.bindTileEntitySpecialRenderer(TE_Roof.class, new TESR_Roof());
			ClientRegistry.bindTileEntitySpecialRenderer(TE_ClockCore.class, new TESR_ClockCore());
			ClientRegistry.bindTileEntitySpecialRenderer(TE_ShopSign.class, new TESR_ShopSign());
			ClientRegistry.bindTileEntitySpecialRenderer(TE_Shelf.class, new TESR_Shelf(Minecraft.getMinecraft().getRenderItem()));
			ClientRegistry.bindTileEntitySpecialRenderer(TE_FluidBarrel.class, new TESR_FluidBarrel());
			ClientRegistry.bindTileEntitySpecialRenderer(TE_FlowerContainer.class, new TESR_Flowers());

			ClientRegistry.bindTileEntitySpecialRenderer(TE_DungeonChest.class, new AnimationTESR<TE_DungeonChest>(){
				@Override
			    public void renderTileEntityFast(TE_DungeonChest te, double x, double y, double z, float partialTick, int breakStage, float partial, BufferBuilder renderer) {
					super.renderTileEntityFast(te, x, y, z, partialTick, breakStage, partial, renderer);
				}
				
				@Override
				public void handleEvents(TE_DungeonChest chest, float time, Iterable<Event> pastEvents){
					chest.handleEvents(time, pastEvents);
				}
			});
			
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnvil.class, new SpecialRenderAnvil());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMortar.class, new SpecialRenderMortar());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGrindstone.class, new SpecialRenderGrindstone());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHangingCauldron.class, new SpecialRenderHangingCauldron());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCauldron.class, new SpecialRenderCauldron());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityChain.class, new SpecialRenderChain());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHook.class, new SpecialRenderHook());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKeyHanging.class, new SpecialRenderKeyHanging());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShipsWheel.class, new SpecialRenderShipsWheel());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTarget.class, new SpecialRenderTarget());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRopeAnchor.class, new SpecialRenderRopeAnchor());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFirepit.class, new SpecialRenderFirepit());
			
			ClientRegistry.registerKeyBinding(Keybinds.debugging);
			
			IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
			DryClayGrassColor color = new DryClayGrassColor();
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(color, MedievalBlocks.DRY_CLAY_GRASS);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor(){
				@Override
	            public int colorMultiplier(ItemStack stack, int tintIndex){
	                IBlockState iblockstate = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
	                return color.colorMultiplier(iblockstate, (IBlockAccess)null, (BlockPos)null, tintIndex);
	            }
	        }, MedievalBlocks.DRY_CLAY_GRASS);
		}

		@Override
		public IAnimationStateMachine load(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters){
	        return ModelLoaderRegistry.loadASM(location, parameters);
	    }
	}
	
	public static class ServerProxy implements IBaseProxy{}
}
