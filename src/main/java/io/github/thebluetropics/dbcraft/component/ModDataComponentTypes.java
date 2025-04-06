package io.github.thebluetropics.dbcraft.component;

import com.mojang.serialization.Codec;
import io.github.thebluetropics.dbcraft.DubacraftMod;
import net.minecraft.component.DataComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModDataComponentTypes {
	public static final DataComponentType<Boolean> SIGNED = register(
		"signed",
		DataComponentType.<Boolean>builder()
			.codec(Codec.BOOL)
			.packetCodec(PacketCodecs.BOOL)
			.build()
	);
	public static final DataComponentType<RecordData> RECORD_DATA = register(
		"record_data",
		DataComponentType.<RecordData>builder()
			.codec(RecordData.CODEC)
			.packetCodec(RecordData.PACKET_CODEC)
			.build()
	);

	public static <T> DataComponentType<T> register(String id, DataComponentType<T> dataComponentType) {
		return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(DubacraftMod.ID, id), dataComponentType);
	}

	public static void initialize() {
		/* ... */
	}
}
