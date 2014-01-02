/**
 * 
 */
package org.duodo.cmpp3s.message;

import org.apache.commons.codec.binary.Hex;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.util.MsgId;

/**
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 *
 */
public class CmppSubmitResponseMessage extends DefaultMessage {
	private static final long serialVersionUID = -6806940736604019528L;
	private MsgId msgId = new MsgId();
	private long result = 0;
	
	public CmppSubmitResponseMessage() {
		this(CmppPacketType.CMPPSUBMITRESPONSE);
	}
	
	public CmppSubmitResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}
	
	/**
	 * @return the msgId
	 */
	public MsgId getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(MsgId msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the result
	 */
	public long getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(long result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("CmppSubmitResponseMessage [msgId=%s, result=%s, getPacketType()=%s, getTimestamp()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						msgId, result, getPacketType(), getTimestamp(),
						getChannelIds(), getChildChannelIds(), getHeader(),
						Hex.encodeHexString(getBodyBuffer()));
	}
	
	
}
