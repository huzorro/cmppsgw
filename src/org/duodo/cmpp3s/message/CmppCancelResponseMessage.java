/**
 * 
 */
package org.duodo.cmpp3s.message;

import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.packet.PacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 *
 */
public class CmppCancelResponseMessage extends DefaultMessage {
	private static final long serialVersionUID = -1111862395776885021L;
	private long successId = 1;
	
	public CmppCancelResponseMessage() {
		this(CmppPacketType.CMPPCANCELRESPONSE);
	}
	
	public CmppCancelResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}

	/**
	 * @return the successId
	 */
	public long getSuccessId() {
		return successId;
	}

	/**
	 * @param successId the successId to set
	 */
	public void setSuccessId(long successId) {
		this.successId = successId;
	}
	
}
