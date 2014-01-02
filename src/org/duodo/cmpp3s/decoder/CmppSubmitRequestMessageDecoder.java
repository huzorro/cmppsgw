/**
 * 
 */
package org.duodo.cmpp3s.decoder;

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
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppSubmitRequestMessageDecoder extends OneToOneDecoder {
	private PacketType packetType;
	/**
	 * 
	 */
	public CmppSubmitRequestMessageDecoder() {
		this(CmppPacketType.CMPPSUBMITREQUEST);
	}
	public CmppSubmitRequestMessageDecoder(PacketType packetType) {
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
        
        CmppSubmitRequestMessage requestMessage = new CmppSubmitRequestMessage();
        requestMessage.setBodyBuffer(message.getBodyBuffer());
        requestMessage.setHeader(message.getHeader());
        
        ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message.getBodyBuffer());
                       
		requestMessage.setMsgid(DefaultMsgIdUtil.bytes2MsgId(bodyBuffer
				.readBytes(CmppSubmitRequest.MSGID.getLength())
				.array()));
        requestMessage.setPktotal(bodyBuffer.readUnsignedByte());
        requestMessage.setPktotal(bodyBuffer.readUnsignedByte());
        requestMessage.setRegisteredDelivery(bodyBuffer.readUnsignedByte());
        requestMessage.setMsglevel(bodyBuffer.readUnsignedByte());
		requestMessage.setServiceId(bodyBuffer.readBytes(
				CmppSubmitRequest.SERVICEID.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		requestMessage.setFeeUserType(bodyBuffer.readUnsignedByte());
		
		requestMessage.setFeeterminalId(bodyBuffer
						.readBytes(CmppSubmitRequest.FEETERMINALID.getLength())
						.toString(GlobalVars.defaultTransportCharset).trim());		
		
		requestMessage.setFeeterminaltype(bodyBuffer.readUnsignedByte());
		requestMessage.setTppId(bodyBuffer.readUnsignedByte());
		requestMessage.setTpudhi(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgFmt(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgsrc(bodyBuffer
				.readBytes(CmppSubmitRequest.MSGSRC.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setFeeType(bodyBuffer.readBytes(
				CmppSubmitRequest.FEETYPE.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setFeeCode(bodyBuffer.readBytes(
				CmppSubmitRequest.FEECODE.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setValIdTime(bodyBuffer.readBytes(
				CmppSubmitRequest.VALIDTIME.getLength()).toString(
				GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setAtTime(bodyBuffer
				.readBytes(CmppSubmitRequest.ATTIME.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setSrcId(bodyBuffer
				.readBytes(CmppSubmitRequest.SRCID.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setDestUsrtl(bodyBuffer.readUnsignedByte());
		
		requestMessage.setDestterminalId(bodyBuffer
				.readBytes(CmppSubmitRequest.DESTTERMINALID.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setDestterminaltype(bodyBuffer.readUnsignedByte());
		requestMessage.setMsgLength(bodyBuffer.readUnsignedByte());
		
		requestMessage.setMsgContent(bodyBuffer
				.readBytes(requestMessage.getMsgLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		requestMessage.setLinkID(bodyBuffer
				.readBytes(CmppSubmitRequest.LINKID.getLength())
				.toString(GlobalVars.defaultTransportCharset).trim());
		
		return requestMessage;
	}

}
