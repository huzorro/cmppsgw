package org.duodo.cmpp3s.handler;

import java.net.InetSocketAddress;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.duodo.cmpp3s.message.CmppConnectRequestMessage;
import org.duodo.cmpp3s.message.CmppConnectResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.DefaultServerSessionFactory;
import org.duodo.netty3ext.factory.session.config.DefaultServerSessionConfigFactory;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.session.Session;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectRequestMessageHandler extends
        SimpleChannelUpstreamHandler {
	private PacketType packetType;
	private DefaultServerSessionFactory<Session> sessionFactory;
	private DefaultServerSessionConfigFactory<SessionConfig> sessionConfigFactory;
    /**
     * 
     */
    public CmppConnectRequestMessageHandler(
    		DefaultServerSessionFactory<Session> sessionFactory,
    		DefaultServerSessionConfigFactory<SessionConfig> sessionConfigFactory) {
    	this(CmppPacketType.CMPPCONNECTREQUEST, sessionFactory, sessionConfigFactory);
    }
    public CmppConnectRequestMessageHandler(
    		PacketType packetType, 
    		DefaultServerSessionFactory<Session> sessionFactory,
    		DefaultServerSessionConfigFactory<SessionConfig> sessionConfigFactory
    		) {
    	this.packetType = packetType;
    	this.sessionFactory = sessionFactory;
    	this.sessionConfigFactory = sessionConfigFactory;
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
        CmppConnectRequestMessage connectRequestMessage = (CmppConnectRequestMessage) message;
		CmppConnectResponseMessage connectResponseMessage = new CmppConnectResponseMessage();
		
        connectResponseMessage.setRequest(connectRequestMessage);
		
		SessionConfig config = sessionConfigFactory
				.setHost(
						((InetSocketAddress) ctx.getChannel()
								.getRemoteAddress()).getAddress()
								.getHostAddress())
				.setUser(connectRequestMessage.getSourceAddr())
				.setVersion(connectRequestMessage.getVersion()).create();
		if(config == null) {
			ctx.getChannel().close();
			return;
		}
		byte[] userBytes = config.getUser().getBytes(
				GlobalVars.defaultTransportCharset);
		byte[] passwdBytes = config.getPasswd().getBytes(
				GlobalVars.defaultTransportCharset);

		byte[] timestampBytes = String.format("%010d", connectRequestMessage.getTimestamp()) 
				.getBytes(GlobalVars.defaultTransportCharset);
		byte[] authBytes = DigestUtils.md5(Bytes.concat(userBytes, new byte[9],
				passwdBytes, timestampBytes));
		
		
        sessionFactory.setChannel(ctx.getChannel());
        sessionFactory.setConfig(config);
        
        Session session = sessionFactory.create();
        if(session != null) {
        	ctx.getChannel().setAttachment(session);        
        } else {
        	ctx.getChannel().close();
        	return;
        } 
        
		if (!Arrays.equals(authBytes,
				connectRequestMessage.getAuthenticatorSource())) {
			ctx.getChannel().write(connectResponseMessage);
			ctx.getChannel().close();
			return;
		}
		connectResponseMessage.setStatus(0L);

		connectResponseMessage.setAuthenticatorISMG(
				DigestUtils.md5(
						Bytes.concat(
								Ints.toByteArray((int) connectResponseMessage.getStatus()), 
								connectRequestMessage.getAuthenticatorSource(), 
								config.getPasswd().getBytes(GlobalVars.defaultTransportCharset)))				
				);
		
		ctx.getChannel().write(connectResponseMessage);
    	session.getLoginFuture().setSuccess();

    }
    

}
