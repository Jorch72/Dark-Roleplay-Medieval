package net.dark_roleplay.medieval.client.model_baking;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class DelayedLoader implements ICustomModelLoader{

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
