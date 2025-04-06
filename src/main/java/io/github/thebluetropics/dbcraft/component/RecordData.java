package io.github.thebluetropics.dbcraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Uuids;

import java.util.UUID;

public final class RecordData {
	public static final Codec<RecordData> CODEC = RecordCodecBuilder.<RecordData>create(
		instance -> instance.group(
			Uuids.CODEC.fieldOf("issuer_uuid").forGetter(o -> o.issuer_uuid),
			Codec.STRING.fieldOf("issuer_name").forGetter(o -> o.issuer_name),
			Codec.LONG.fieldOf("timestamp").forGetter(o -> o.timestamp),
			Codec.STRING.fieldOf("text").forGetter(o -> o.text)
		).apply(instance, RecordData::new)
	);
	public static final PacketCodec<ByteBuf, RecordData> PACKET_CODEC = PacketCodec.tuple(
		Uuids.PACKET_CODEC, data -> data.issuer_uuid,
		PacketCodecs.STRING, data -> data.issuer_name,
		PacketCodecs.VAR_LONG, data -> data.timestamp,
		PacketCodecs.STRING, data -> data.text,
		RecordData::new
	);

	public final UUID issuer_uuid;
	public final String issuer_name;
	public final long timestamp;
	public final String text;

	public RecordData(UUID issuer_uuid, String issuer_name, long timestamp, String text) {
		this.issuer_name = issuer_name;
		this.issuer_uuid = issuer_uuid;
		this.timestamp = timestamp;
		this.text = text;
	}
}
