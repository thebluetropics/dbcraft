package io.github.thebluetropics.dbcraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Uuids;

import java.util.UUID;

public class PersistentPlayerReference {
	public static final Codec<PersistentPlayerReference> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			Uuids.CODEC.fieldOf("uuid").forGetter(ref -> ref.uuid),
			Codec.STRING.fieldOf("name").forGetter(ref -> ref.name)
		).apply(instance, PersistentPlayerReference::new)
	);
	public static final PacketCodec<ByteBuf, PersistentPlayerReference> PACKET_CODEC = PacketCodec.tuple(
		Uuids.PACKET_CODEC, ref -> ref.uuid,
		PacketCodecs.STRING, ref -> ref.name,
		PersistentPlayerReference::new
	);

	public UUID uuid;
	public String name;

	public PersistentPlayerReference(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}
}
