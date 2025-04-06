package io.github.thebluetropics.dbcraft.item.registry;

import io.github.thebluetropics.dbcraft.DubacraftMod;
import io.github.thebluetropics.dbcraft.block.registry.ModBlocks;
import io.github.thebluetropics.dbcraft.item.WrenchItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
	public static final Item DARK_SEAMLESS_GLASS = register(
		"dark_seamless_glass",
		new BlockItem(
			ModBlocks.DARK_SEAMLESS_GLASS,
			new Item.Settings()
		)
	);
	public static final Item SEAMLESS_GLASS = register(
		"seamless_glass",
		new BlockItem(
			ModBlocks.SEAMLESS_GLASS,
			new Item.Settings()
		)
	);

	public static <T extends Item> T register(String id, T item) {
		return Registry.register(Registries.ITEM, Identifier.of(DubacraftMod.ID, id), item);
	}

	public static void initialize() {
		/* ... */
	}
}
