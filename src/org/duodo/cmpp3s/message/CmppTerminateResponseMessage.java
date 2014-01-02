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
public class CmppTerminateResponseMessage extends DefaultMessage {
	private static final long serialVersionUID = -2657187574508760595L;

	public CmppTerminateResponseMessage() {
		this(CmppPacketType.CMPPTERMINATERESPONSE);
	}
	public CmppTerminateResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("CmppTerminateResponseMessage [toString()=%s]",
				super.toString());
	}
	
}
