/**
 * 
 */
package org.duodo.cmpp3s.encoder;

import org.duodo.cmpp3s.message.CmppDeliverRequestMessage;
import org.duodo.cmpp3s.packet.CmppDeliverRequest;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.cmpp3s.packet.CmppReportRequest;
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
public class CmppDeliverRequestMessageEncoder extends OneToOneEncoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageEncoder() {
		this(CmppPacketType.CMPPDELIVERREQUEST);
	}
	
	public CmppDeliverRequestMessageEncoder(PacketType packetType) {
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
        
        CmppDeliverRequestMessage requestMessage = (CmppDeliverRequestMessage) message;
        
        ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();
        
        bodyBuffer.writeBytes(DefaultMsgIdUtil.msgId2Bytes(requestMessage.getMsgId()));
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getDestId()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppDeliverRequest.DESTID.getLength(), 0));
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getServiceid().getBytes(GlobalVars.defaultTransportCharset),
				CmppDeliverRequest.SERVICEID.getLength(), 0));
		bodyBuffer.writeByte(requestMessage.getTppid());
		bodyBuffer.writeByte(requestMessage.getTpudhi());
		bodyBuffer.writeByte(requestMessage.getMsgfmt());
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getSrcterminalId()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppDeliverRequest.SRCTERMINALID.getLength(), 0));
		bodyBuffer.writeByte(requestMessage.getSrcterminalType());
		bodyBuffer.writeByte(requestMessage.getRegisteredDelivery());
		bodyBuffer.writeByte(requestMessage.getMsgLength());
		
		if(!requestMessage.isReport()) {
			bodyBuffer.writeBytes(requestMessage.getMsgContentBytes());
		} else {
			bodyBuffer.writeBytes(DefaultMsgIdUtil.msgId2Bytes(requestMessage
					.getReportRequestMessage().getMsgId()));
			bodyBuffer.writeBytes(Bytes.ensureCapacity(
					requestMessage.getReportRequestMessage().getStat()
							.getBytes(GlobalVars.defaultTransportCharset),
					CmppReportRequest.STAT.getLength(), 0));
			bodyBuffer.writeBytes(Bytes.ensureCapacity(
					requestMessage.getReportRequestMessage().getSubmitTime()
							.getBytes(GlobalVars.defaultTransportCharset),
					CmppReportRequest.SUBMITTIME.getLength(), 0));
			bodyBuffer.writeBytes(Bytes.ensureCapacity(
					requestMessage.getReportRequestMessage().getDoneTime()
							.getBytes(GlobalVars.defaultTransportCharset),
					CmppReportRequest.DONETIME.getLength(), 0));
			bodyBuffer
					.writeBytes(Bytes
							.ensureCapacity(
									requestMessage
											.getReportRequestMessage()
											.getDestterminalId()
											.getBytes(
													GlobalVars.defaultTransportCharset),
									CmppReportRequest.DESTTERMINALID
											.getLength(), 0));
//			bodyBuffer.writeByte((int) requestMessage.getReportRequestMessage().getSmscSequence());
			bodyBuffer.writeInt((int) requestMessage.getReportRequestMessage().getSmscSequence());
		}
		
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getLinkid()
				.getBytes(GlobalVars.defaultTransportCharset),
				CmppDeliverRequest.LINKID.getLength(), 0));
		
        message.setBodyBuffer(bodyBuffer.copy().array());
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());     
		return messageBuffer;
	}

}
