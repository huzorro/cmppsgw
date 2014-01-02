package org.duodo.cmpp3s.handler;

import org.duodo.cmpp3s.message.CmppConnectResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.session.Session;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectResponseMessageHandler extends
        SimpleChannelUpstreamHandler {
    private final static Logger logger = LoggerFactory.getLogger(CmppConnectResponseMessageHandler.class);
    private PacketType packetType;
    /**
     * 
     */
    public CmppConnectResponseMessageHandler() {
        this(CmppPacketType.CMPPCONNECTRESPONSE);
    }
    public CmppConnectResponseMessageHandler(PacketType packetType) {
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
        CmppConnectResponseMessage connectResponseMessage = (CmppConnectResponseMessage) message;
        Session session = (Session) ctx.getChannel().getAttachment();
        
        logger.info(message.toString());
        
        if(connectResponseMessage.getStatus() == 0L) {
            session.getLoginFuture().setSuccess();
            super.messageReceived(ctx, e);
        } else {
            session.close();
            return;
        }
    }
    
}
