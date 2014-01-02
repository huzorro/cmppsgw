/**
 * 
 */
package org.duodo.cmpp3s.encoder;

import org.duodo.cmpp3s.message.CmppSubmitRequestMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.cmpp3s.packet.CmppSubmitRequest;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.util.DefaultMsgIdUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Bytes;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppSubmitRequestMessageEncoder extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitRequestMessageEncoder() {
		this(CmppPacketType.CMPPSUBMITREQUEST);
	}
	
	public CmppSubmitRequestMessageEncoder(PacketType packetType) {
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
        CmppSubmitRequestMessage requestMessage = (CmppSubmitRequestMessage) message;
        
        ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();

        bodyBuffer.writeBytes(DefaultMsgIdUtil.msgId2Bytes(requestMessage.getMsgid()));
        bodyBuffer.writeByte(requestMessage.getPknumber());
        bodyBuffer.writeByte(requestMessage.getPktotal());
        bodyBuffer.writeByte(requestMessage.getRegisteredDelivery());
        bodyBuffer.writeByte(requestMessage.getMsglevel());
        
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getServiceId().getBytes(GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.SERVICEID.getLength(), 0));
		
        bodyBuffer.writeByte(requestMessage.getFeeUserType());
        
  		bodyBuffer.writeBytes(Bytes.ensureCapacity(
						requestMessage.getFeeterminalId().getBytes(
								GlobalVars.defaultTransportCharset),
						CmppSubmitRequest.FEETERMINALID
								.getLength(), 0));
        bodyBuffer.writeByte(requestMessage.getFeeterminaltype());
        bodyBuffer.writeByte(requestMessage.getTppId());
        bodyBuffer.writeByte(requestMessage.getTpudhi());
        bodyBuffer.writeByte(requestMessage.getMsgFmt());
        
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getMsgsrc()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.MSGSRC.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getFeeType()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.FEETYPE.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getFeeCode()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.FEECODE.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getValIdTime().getBytes(GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.VALIDTIME.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getAtTime()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.ATTIME.getLength(), 0));
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getSrcId()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.SRCID.getLength(), 0));
		
        bodyBuffer.writeByte(requestMessage.getDestUsrtl());
        
		bodyBuffer.writeBytes(Bytes.ensureCapacity(
				requestMessage.getDestterminalId().getBytes(
						GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.DESTTERMINALID.getLength(), 0));
		
        bodyBuffer.writeByte(requestMessage.getDestterminaltype());
        bodyBuffer.writeByte(requestMessage.getMsgLength());
        
        bodyBuffer.writeBytes(requestMessage.getMsgContentBytes());
        
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getLinkID()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppSubmitRequest.LINKID.getLength(), 0));
        
        message.setBodyBuffer(bodyBuffer.copy().array());
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());
        return messageBuffer;        
	}

}
