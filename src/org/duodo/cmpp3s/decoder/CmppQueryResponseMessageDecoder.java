/**
 * 
 */
package org.duodo.cmpp3s.decoder;

import org.duodo.cmpp3s.message.CmppQueryResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.cmpp3s.packet.CmppQueryResponse;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppQueryResponseMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	
	public CmppQueryResponseMessageDecoder() {
		this(CmppPacketType.CMPPQUERYRESPONSE);
	}

	public CmppQueryResponseMessageDecoder(PacketType packetType) {
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
        if(packetType.getCommandId() != commandId) return msg;
        
        CmppQueryResponseMessage responseMessage = new CmppQueryResponseMessage();
       
        responseMessage.setBodyBuffer(message.getBodyBuffer());
        responseMessage.setHeader(message.getHeader());
        ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message.getBodyBuffer());		
        
		responseMessage.setTime(bodyBuffer.readBytes(
				CmppQueryResponse.TIME.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		responseMessage.setQueryType(bodyBuffer.readUnsignedByte());
		responseMessage.setQueryCode(bodyBuffer.readBytes(
				CmppQueryResponse.QUERYCODE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		responseMessage.setMtTLMsg(bodyBuffer.readUnsignedInt());
		responseMessage.setMtTLUsr(bodyBuffer.readUnsignedInt());
		responseMessage.setMtScs(bodyBuffer.readUnsignedInt());
		responseMessage.setMtWT(bodyBuffer.readUnsignedInt());
		responseMessage.setMtFL(bodyBuffer.readUnsignedInt());
		responseMessage.setMoScs(bodyBuffer.readUnsignedInt());
		responseMessage.setMoWT(bodyBuffer.readUnsignedInt());
		responseMessage.setMoFL(bodyBuffer.readUnsignedInt());
		
		return responseMessage;
	}

}
