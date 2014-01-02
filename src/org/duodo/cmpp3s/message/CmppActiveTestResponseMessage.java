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
public class CmppActiveTestResponseMessage extends DefaultMessage{
	private static final long serialVersionUID = 4300214238350805590L;
	
	public CmppActiveTestResponseMessage() {
		this(CmppPacketType.CMPPACTIVETESTRESPONSE);
	}
	
	public CmppActiveTestResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}
}
