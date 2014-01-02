/**
 * 
 */
package org.duodo.cmpp3s.handler;

import org.duodo.cmpp3s.packet.CmppHead;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultHead;
import org.duodo.netty3ext.message.Header;
import org.duodo.netty3ext.message.Message;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppCommonsHeaderHandler extends OneToOneEncoder {

	/**
	 * 
	 */
	public CmppCommonsHeaderHandler() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if(!(msg instanceof Message)) return msg;
        Message message = (Message) msg;
        
        Header header = new DefaultHead();
        header.setCommandId(message.getPacketType().getCommandId());
        header.setHeadLength(CmppHead.COMMANDID.getHeadLength());
		header.setBodyLength(message.getPacketType().getPacketStructures().length > 0 ? message
				.getPacketType().getPacketStructures()[0].getBodyLength() : 0);
        header.setPacketLength(header.getHeadLength() + header.getBodyLength());
        
		header.setSequenceId(message.getRequest() != null ? message
				.getRequest().getHeader().getSequenceId()
				: (message.getHeader() != null ? message.getHeader().getSequenceId()
						: (GlobalVars.sequenceId.compareAndSet(
								Integer.MAX_VALUE, 0) ? GlobalVars.sequenceId
								.getAndIncrement() : GlobalVars.sequenceId
								.getAndIncrement())));
		
        message.setHeader(header);
        return message;
	}

}
