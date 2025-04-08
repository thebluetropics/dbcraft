package io.github.thebluetropics.dbcraft.item;

import io.github.thebluetropics.dbcraft.DubacraftMod;
import io.github.thebluetropics.dbcraft.component.ModDataComponentTypes;
import io.github.thebluetropics.dbcraft.component.PersistentPlayerReference;
import io.github.thebluetropics.dbcraft.component.RecordData;
import net.minecraft.client.item.TooltipType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static io.github.thebluetropics.dbcraft.component.ModDataComponentTypes.RECORD_DATA;
import static io.github.thebluetropics.dbcraft.component.ModDataComponentTypes.SIGNED;

public class RecordItem extends Item {
	public RecordItem(Settings settings) {
		super(settings);
	}

	@Override
	public Text getName(ItemStack stack) {
		return Objects.equals(stack.get(SIGNED), true) ? Text.translatable("item." + DubacraftMod.ID + ".record.signed") : super.getName(stack);
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		var data = stack.get(ModDataComponentTypes.RECORD_DATA);

		if (data != null) {
			LocalDate date = Instant.ofEpochSecond(data.timestamp)
				.atZone(ZoneId.systemDefault())
				.toLocalDate();

			MutableText first_line = Text.literal("from").formatted(Formatting.DARK_GRAY);
			first_line.append(Text.literal(" "));
			first_line.append(Text.literal(data.issuer.name).formatted(Formatting.GRAY));

			PersistentPlayerReference recipient = data.recipient.orElse(null);

			if (recipient != null) {
				first_line.append(Text.literal(" for ").formatted(Formatting.DARK_GRAY));
				first_line.append(Text.literal(recipient.name).formatted(Formatting.GRAY));
			}

			MutableText second_line = Text.literal("at ").formatted(Formatting.DARK_GRAY);
			second_line.append(Text.literal(date.format(DateTimeFormatter.ISO_LOCAL_DATE)).formatted(Formatting.GRAY));

			tooltip.add(first_line);
			tooltip.add(second_line);
			tooltip.add(Text.empty());
			tooltip.add(Text.literal(data.text).formatted(Formatting.DARK_GRAY));
		}
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		var stack = player.getStackInHand(hand);
		ItemStack other_stack = player.getStackInHand(hand.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND);

		var record_data = stack.get(RECORD_DATA);

		if (record_data != null) {
			if (record_data.issuer.uuid.equals(player.getUuid())) {
				// sign the record
				if (other_stack.isOf(Items.DIAMOND) && !Objects.equals(stack.get(SIGNED), true)) {
					stack.set(SIGNED, true);
					other_stack.decrementUnlessCreative(1, player);

					return TypedActionResult.success(stack);
				}

				// assign a recipient to the record
				if (other_stack.isOf(Items.WRITABLE_BOOK) && !Objects.equals(stack.get(SIGNED), true) && Objects.equals(record_data.recipient, Optional.empty())) {
					String book_content = EmptyRecordItem.get_book_content(other_stack);

					if (book_content != null) {
						if (!world.isClient) {
							PlayerEntity recipient = world.getServer().getPlayerManager().getPlayer(book_content);

							if (recipient != null) {
								var new_record_data = new RecordData(
									record_data.issuer,
									Optional.of(new PersistentPlayerReference(recipient.getUuid(), recipient.getName().getLiteralString())),
									record_data.timestamp,
									record_data.text
								);

								stack.set(RECORD_DATA, new_record_data);

								return TypedActionResult.success(stack);
							}
						}
					}
				}
			}
		}

		return super.use(world, player, hand);
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return Objects.equals(stack.get(SIGNED), true);
	}
}
