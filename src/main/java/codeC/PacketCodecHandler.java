package codeC;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import protocal.Packet;
import protocal.PacketCodeC;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf , Packet> {

    public static final PacketCodecHandler INSTANCE=new PacketCodecHandler();

    private PacketCodecHandler(){

    }
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
        ByteBuf byteBuf=channelHandlerContext.alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(byteBuf,packet);
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
