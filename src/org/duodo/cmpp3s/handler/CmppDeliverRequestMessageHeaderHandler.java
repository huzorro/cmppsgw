/**
 * 
 */
package org.duodo.cmpp3s.handler;

import org.duodo.cmpp3s.message.CmppDeliverRequestMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppDeliverRequestMessageHeaderHandler extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageHeaderHandler() {
		this(CmppPacketType.CMPPDELIVERREQUEST);
	}

	public CmppDeliverRequestMessageHeaderHandler(PacketType packetType) {
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
        
        if(message.getPacketType().getPacketStructures()[0].isFixPacketLength()) {
        	return msg;
        }
        CmppDeliverRequestMessage requestMessage = (CmppDeliverRequestMessage) message;
        
		
		requestMessage.getHeader().setBodyLength(
				requestMessage.getHeader().getBodyLength()
						+ requestMessage.getMsgLength());
		
		requestMessage.getHeader().setPacketLength(
				requestMessage.getHeader().getPacketLength()
						+  requestMessage.getMsgLength());
		
		return requestMessage;
	}

}
