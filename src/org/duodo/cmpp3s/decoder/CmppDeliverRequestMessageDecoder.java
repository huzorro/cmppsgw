/**
 * 
 */
package org.duodo.cmpp3s.decoder;

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
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppDeliverRequestMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppDeliverRequestMessageDecoder() {
		this(CmppPacketType.CMPPDELIVERREQUEST);
	}
	
	public CmppDeliverRequestMessageDecoder(PacketType packetType) {
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
        
        CmppDeliverRequestMessage requestMessage = new CmppDeliverRequestMessage();
        requestMessage.setBodyBuffer(message.getBodyBuffer());
        requestMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message.getBodyBuffer());	
        
		requestMessage.setMsgId(DefaultMsgIdUtil.bytes2MsgId(bodyBuffer
				.readBytes(CmppDeliverRequest.MSGID.getLength())
				.array()));
		requestMessage.setDestId(bodyBuffer.readBytes(
				CmppDeliverRequest.DESTID.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		requestMessage
				.setServiceid(bodyBuffer
						.readBytes(
								CmppDeliverRequest.SERVICEID
										.getLength())
						.toString(GlobalVars.defaultTransportCharset).trim());
		requestMessage.setTppid(bodyBuffer.readUnsignedByte());
		requestMessage.setTpudhi(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgfmt(bodyBuffer.readUnsignedByte());
		requestMessage.setSrcterminalId(bodyBuffer
				.readBytes(
						CmppDeliverRequest.SRCTERMINALID
								.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		requestMessage.setSrcterminalType(bodyBuffer.readUnsignedByte());
		requestMessage.setRegisteredDelivery(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgLength(bodyBuffer.readUnsignedByte());
		
		if(requestMessage.getRegisteredDelivery() == 0) {
			requestMessage.setMsgContent(bodyBuffer.readBytes(requestMessage.getMsgLength()).toString(GlobalVars.defaultTransportCharset).trim());
		} else {
			requestMessage.getReportRequestMessage().setMsgId(
					DefaultMsgIdUtil.bytes2MsgId(bodyBuffer.readBytes(
							CmppReportRequest.MSGID.getLength())
							.array()));
			requestMessage.getReportRequestMessage().setStat(
					bodyBuffer
							.readBytes(
									CmppReportRequest.STAT
											.getLength())
							.toString(GlobalVars.defaultTransportCharset)
							.trim());
			requestMessage.getReportRequestMessage().setSubmitTime(
					bodyBuffer
							.readBytes(
									CmppReportRequest.SUBMITTIME
											.getLength())
							.toString(GlobalVars.defaultTransportCharset)
							.trim());
			requestMessage.getReportRequestMessage().setDoneTime(
					bodyBuffer
							.readBytes(
									CmppReportRequest.DONETIME
											.getLength())
							.toString(GlobalVars.defaultTransportCharset)
							.trim());
			requestMessage
					.getReportRequestMessage()
					.setDestterminalId(
							bodyBuffer
									.readBytes(
											CmppReportRequest.DESTTERMINALID
													.getLength())
									.toString(
											GlobalVars.defaultTransportCharset)
									.trim());
			requestMessage.getReportRequestMessage().setSmscSequence(bodyBuffer.readUnsignedInt());
		}
		
		requestMessage.setLinkid(bodyBuffer.readBytes(
				CmppDeliverRequest.LINKID.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return requestMessage;
	}

}
