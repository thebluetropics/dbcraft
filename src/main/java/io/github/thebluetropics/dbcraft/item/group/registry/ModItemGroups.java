package io.github.thebluetropics.dbcraft.item.group.registry;

import io.github.thebluetropics.dbcraft.DubacraftMod;
import io.github.thebluetropics.dbcraft.item.registry.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
	public static final ItemGroup DEFAULT = register(
		"default",
		FabricItemGroup.builder()
			.displayName(Text.translatable("itemGroup." + DubacraftMod.ID + ".default"))
			.icon(ModItems.DARK_SEAMLESS_GLASS::getDefaultStack)
			.entries((ctx, entries) -> {
				entries.add(ModItems.FORTRESS_BRICKS);
				entries.add(ModItems.SMALL_FORTRESS_BRICKS);
				entries.add(ModItems.DARK_SEAMLESS_GLASS);
				entries.add(ModItems.SEAMLESS_GLASS);
				entries.add(ModItems.WRENCH);
				entries.add(ModItems.EMPTY_RECORD);
			})
			.build()
	);

	public static ItemGroup register(String id, ItemGroup itemGroup) {
		return Registry.register(Registries.ITEM_GROUP, Identifier.of(DubacraftMod.ID, id), itemGroup);
	}

	public static void initialize() {
		/* ... */
	}
}
