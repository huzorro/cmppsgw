/**
 * 
 */
package org.duodo.cmpp3s.handler;

import org.duodo.cmpp3s.message.CmppSubmitRequestMessage;
import org.duodo.cmpp3s.message.CmppSubmitResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.session.Session;
import org.duodo.netty3ext.util.MsgId;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppSubmitRequestMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitRequestMessageHandler() {
		this(CmppPacketType.CMPPSUBMITREQUEST);
	}

	public CmppSubmitRequestMessageHandler(PacketType packetType) {
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
        CmppSubmitRequestMessage requestMessage = (CmppSubmitRequestMessage) message;
		CmppSubmitResponseMessage responseMessage = new CmppSubmitResponseMessage();
		
		responseMessage.setRequest(requestMessage);
		responseMessage.setMsgId(new MsgId(((Session) ctx.getChannel()
				.getAttachment()).getConfig().getGateId()));
		responseMessage.setResult(0L);
		
		ctx.getChannel().write(responseMessage);
		
		((Session) ctx.getChannel().getAttachment()).receive(requestMessage.setResponse(responseMessage));
		
		super.messageReceived(ctx, e);
	}
	
}
