package io.github.thebluetropics.dbcraft.client;

import io.github.thebluetropics.dbcraft.block.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DubacraftModClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(DubacraftModClient.class);

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DARK_SEAMLESS_GLASS, RenderLayer.getTranslucent());
	}
}
