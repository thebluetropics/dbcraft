package io.github.thebluetropics.dbcraft.item;

import io.github.thebluetropics.dbcraft.component.ModDataComponentTypes;
import io.github.thebluetropics.dbcraft.component.RecordData;
import io.github.thebluetropics.dbcraft.item.registry.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;

import static net.minecraft.component.DataComponentTypes.WRITABLE_BOOK_CONTENT;

public class EmptyRecordItem extends Item {
	public EmptyRecordItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack other_stack = player.getStackInHand(hand.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND);

		if (other_stack.isOf(Items.WRITABLE_BOOK)) {
			var book_content = other_stack.get(WRITABLE_BOOK_CONTENT);

			if (book_content != null) {
				ArrayList<String> texts = new ArrayList<>();

				for (var page : book_content.pages()) {
					texts.add(page.get(false));
				}

				var text = String.join("", texts).replaceAll("\r?\n", "").replaceAll("\\s+", " ").trim();

				if (!text.isBlank()) {
					var modified_stack = new ItemStack(ModItems.RECORD);
					modified_stack.set(ModDataComponentTypes.SIGNED, false);
					modified_stack.set(ModDataComponentTypes.RECORD_DATA, new RecordData(
						player.getUuid(),
						player.getName().getString(),
						System.currentTimeMillis() / 1000l,
						text
					));

					return TypedActionResult.success(modified_stack);
				}
			}
		}

		return super.use(world, player, hand);
	}
}
