package io.github.thebluetropics.dbcraft;

import io.github.thebluetropics.dbcraft.block.registry.ModBlocks;
import io.github.thebluetropics.dbcraft.component.ModDataComponentTypes;
import io.github.thebluetropics.dbcraft.item.group.registry.ModItemGroups;
import io.github.thebluetropics.dbcraft.item.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DubacraftMod implements ModInitializer {
	public static final String ID = "dbcraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(DubacraftMod.class);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModItems.initialize();
		ModItemGroups.initialize();
		ModDataComponentTypes.initialize();
	}
}
