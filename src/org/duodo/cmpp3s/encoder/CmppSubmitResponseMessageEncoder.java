/**
 * 
 */
package org.duodo.cmpp3s.encoder;

import org.duodo.cmpp3s.message.CmppSubmitResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.util.DefaultMsgIdUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppSubmitResponseMessageEncoder extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitResponseMessageEncoder() {
		this(CmppPacketType.CMPPSUBMITRESPONSE);
	}
	
	public CmppSubmitResponseMessageEncoder(PacketType packetType) {
		this.packetType = packetType;
	}
	

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
	    if(!(msg instanceof Message)) return msg;
		Message message = (Message) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()) return msg;
        
        CmppSubmitResponseMessage responseMessage = (CmppSubmitResponseMessage)message;
        
        ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();		
        
        bodyBuffer.writeBytes(DefaultMsgIdUtil.msgId2Bytes(responseMessage.getMsgId()));
        bodyBuffer.writeInt((int) responseMessage.getResult());
        message.setBodyBuffer(bodyBuffer.copy().array());
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());
        return messageBuffer;
	}

}
