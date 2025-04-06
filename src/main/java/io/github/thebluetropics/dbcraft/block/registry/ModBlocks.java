package io.github.thebluetropics.dbcraft.block.registry;

import io.github.thebluetropics.dbcraft.DubacraftMod;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
	public static final Block DARK_SEAMLESS_GLASS = register(
		"dark_seamless_glass",
		new TransparentBlock(
			AbstractBlock.Settings.create()
				.mapColor(MapColor.DEEPSLATE_GRAY)
				.instrument(Instrument.HAT)
				.strength(0.3F)
				.sounds(BlockSoundGroup.GLASS)
				.nonOpaque()
				.allowsSpawning(Blocks::never)
				.solidBlock(Blocks::never)
				.suffocates(Blocks::never)
				.blockVision(Blocks::never)
		)
	);
	public static final Block SEAMLESS_GLASS = register(
		"seamless_glass",
		new TransparentBlock(
			AbstractBlock.Settings.create()
				.mapColor(MapColor.CLEAR)
				.instrument(Instrument.HAT)
				.strength(0.3F)
				.sounds(BlockSoundGroup.GLASS)
				.nonOpaque()
				.allowsSpawning(Blocks::never)
				.solidBlock(Blocks::never)
				.suffocates(Blocks::never)
				.blockVision(Blocks::never)
		)
	);

	public static <T extends Block> T register(String id, T block) {
		return Registry.register(Registries.BLOCK, Identifier.of(DubacraftMod.ID, id), block);
	}

	public static void initialize() {
		/* ... */
	}
}
