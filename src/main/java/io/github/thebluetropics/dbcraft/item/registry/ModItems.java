package io.github.thebluetropics.dbcraft.item.registry;

import io.github.thebluetropics.dbcraft.DubacraftMod;
import io.github.thebluetropics.dbcraft.block.registry.ModBlocks;
import io.github.thebluetropics.dbcraft.item.EmptyRecordItem;
import io.github.thebluetropics.dbcraft.item.RecordItem;
import io.github.thebluetropics.dbcraft.item.WrenchItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
	public static final Item FORTRESS_BRICKS = register(
		"fortress_bricks",
		new BlockItem(
			ModBlocks.FORTRESS_BRICKS,
			new Item.Settings()
		)
	);
	public static final Item SMALL_FORTRESS_BRICKS = register(
		"small_fortress_bricks",
		new BlockItem(
			ModBlocks.SMALL_FORTRESS_BRICKS,
			new Item.Settings()
		)
	);
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
	public static final Item WRENCH = register(
		"wrench",
		new WrenchItem(
			new Item.Settings()
				.maxDamage(256)
				.maxCount(1)
				.attributeModifiers(
					AttributeModifiersComponent.builder()
						.add(
							EntityAttributes.GENERIC_ATTACK_DAMAGE,
							new EntityAttributeModifier(
								Item.ATTACK_DAMAGE_MODIFIER_ID,
								"Tool modifier",
								1.0f,
								EntityAttributeModifier.Operation.ADD_VALUE
							),
							AttributeModifierSlot.MAINHAND
						)
						.add(
							EntityAttributes.GENERIC_ATTACK_SPEED,
							new EntityAttributeModifier(
								Item.ATTACK_SPEED_MODIFIER_ID,
								"Tool modifier",
								-2.8f,
								EntityAttributeModifier.Operation.ADD_VALUE
							),
							AttributeModifierSlot.MAINHAND
						)
						.build()
				)
		)
	);
	public static final Item EMPTY_RECORD = register(
		"empty_record",
		new EmptyRecordItem(
			new Item.Settings()
				.maxCount(1)
		)
	);
	public static final Item RECORD = register(
		"record",
		new RecordItem(
			new Item.Settings()
				.maxCount(1)
				.rarity(Rarity.UNCOMMON)
		)
	);

	public static <T extends Item> T register(String id, T item) {
		return Registry.register(Registries.ITEM, Identifier.of(DubacraftMod.ID, id), item);
	}

	public static void initialize() {
		/* ... */
	}
}
