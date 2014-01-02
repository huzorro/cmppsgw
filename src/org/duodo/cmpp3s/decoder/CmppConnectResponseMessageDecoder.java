package org.duodo.cmpp3s.decoder;

import org.duodo.cmpp3s.message.CmppConnectResponseMessage;
import org.duodo.cmpp3s.packet.CmppConnectResponse;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectResponseMessageDecoder extends OneToOneDecoder {
    private PacketType packetType;
    /**
     * 
     */
    public CmppConnectResponseMessageDecoder() {
        this(CmppPacketType.CMPPCONNECTRESPONSE);
    }
    public CmppConnectResponseMessageDecoder(PacketType packetType) {
        this.packetType = packetType;
    }

    /* (non-Javadoc)
     * @see org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception {
        Message message = (Message) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()) return msg;
        CmppConnectResponseMessage responseMessage = new CmppConnectResponseMessage();
        
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message.getBodyBuffer());
        
        responseMessage.setStatus(bodyBuffer.readUnsignedInt());
 		responseMessage.setAuthenticatorISMG(bodyBuffer.readBytes(
				CmppConnectResponse.AUTHENTICATORISMG.getLength())
				.array());
        responseMessage.setVersion(bodyBuffer.readUnsignedByte());
        return responseMessage;
    }

}
