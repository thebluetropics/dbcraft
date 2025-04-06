package io.github.thebluetropics.dbcraft.item;

import io.github.thebluetropics.dbcraft.DubacraftMod;
import io.github.thebluetropics.dbcraft.component.ModDataComponentTypes;
import net.minecraft.client.item.TooltipType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

			tooltip.add(Text.literal(data.issuer_name).formatted(Formatting.GRAY));
			tooltip.add(Text.empty());
			tooltip.add(Text.literal(data.text).formatted(Formatting.GRAY));
			tooltip.add(Text.empty());
			tooltip.add(Text.literal(date.format(DateTimeFormatter.ISO_LOCAL_DATE)).formatted(Formatting.GRAY));
		}
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		var stack = player.getStackInHand(hand);
		ItemStack other_stack = player.getStackInHand(hand.equals(Hand.MAIN_HAND) ? Hand.OFF_HAND : Hand.MAIN_HAND);

		if (other_stack.isOf(Items.DIAMOND) && Objects.equals(stack.get(SIGNED), false)) {
			stack.set(SIGNED, true);
			other_stack.decrementUnlessCreative(1, player);

			return TypedActionResult.success(stack);
		}

		return super.use(world, player, hand);
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return Objects.equals(stack.get(SIGNED), true);
	}
}
