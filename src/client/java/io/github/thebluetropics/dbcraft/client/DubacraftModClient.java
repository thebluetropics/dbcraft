package io.github.thebluetropics.dbcraft.client;

import io.github.thebluetropics.dbcraft.DubacraftMod;
import io.github.thebluetropics.dbcraft.block.registry.ModBlocks;
import io.github.thebluetropics.dbcraft.component.ModDataComponentTypes;
import io.github.thebluetropics.dbcraft.item.registry.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class DubacraftModClient implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(DubacraftModClient.class);

	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DARK_SEAMLESS_GLASS, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEAMLESS_GLASS, RenderLayer.getTranslucent());

		ModelPredicateProviderRegistry.register(
			ModItems.RECORD,
			Identifier.of(DubacraftMod.ID, "signed"),
			(stack, world, entity, seed) -> Objects.equals(stack.get(ModDataComponentTypes.SIGNED), true) ? 1.0f : 0.0f
		);
	}
}
