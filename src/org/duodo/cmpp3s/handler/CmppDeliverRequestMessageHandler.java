/**
 * 
 */
package org.duodo.cmpp3s.handler;

import org.duodo.cmpp3s.message.CmppDeliverRequestMessage;
import org.duodo.cmpp3s.message.CmppDeliverResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.session.Session;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppDeliverRequestMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageHandler() {
		this(CmppPacketType.CMPPDELIVERREQUEST);
	}

	public CmppDeliverRequestMessageHandler(PacketType packetType) {
		this.packetType = packetType;
	}
	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Message message = (Message) e.getMessage();
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        }		
        
        CmppDeliverRequestMessage requestMessage = (CmppDeliverRequestMessage) message;
		CmppDeliverResponseMessage responseMessage = new CmppDeliverResponseMessage();
		
		responseMessage.setRequest(requestMessage);
		responseMessage.setMsgId(requestMessage.getMsgId());
		responseMessage.setResult(0L);
		
		ctx.getChannel().write(responseMessage);
		
		
		((Session) ctx.getChannel().getAttachment()).receive(requestMessage.setResponse(responseMessage));
		
		super.messageReceived(ctx, e);
	}

}
