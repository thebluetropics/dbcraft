package io.github.thebluetropics.dbcraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.Optional;

public final class RecordData {
	public static final Codec<RecordData> CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
			PersistentPlayerReference.CODEC.fieldOf("issuer").forGetter(data -> data.issuer),
			PersistentPlayerReference.CODEC.optionalFieldOf("recipient").forGetter(data -> data.recipient),
			Codec.LONG.fieldOf("timestamp").forGetter(o -> o.timestamp),
			Codec.STRING.fieldOf("text").forGetter(o -> o.text)
		).apply(instance, RecordData::new)
	);
	public static final PacketCodec<ByteBuf, RecordData> PACKET_CODEC = PacketCodec.tuple(
		PersistentPlayerReference.PACKET_CODEC, data -> data.issuer,
		PersistentPlayerReference.PACKET_CODEC.collect(PacketCodecs::optional), data -> data.recipient,
		PacketCodecs.VAR_LONG, data -> data.timestamp,
		PacketCodecs.STRING, data -> data.text,
		RecordData::new
	);

	public final PersistentPlayerReference issuer;
	public final Optional<PersistentPlayerReference> recipient;
	public final long timestamp;
	public final String text;

	public RecordData(PersistentPlayerReference issuer, Optional<PersistentPlayerReference> recipient, long timestamp, String text) {
		this.issuer = issuer;
		this.recipient = recipient;
		this.timestamp = timestamp;
		this.text = text;
	}
}
