/**
 * 
 */
package org.duodo.cmpp3s.message;

import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultHead;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.message.Header;
import org.duodo.netty3ext.packet.PacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 *
 */
public class CmppActiveTestRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = 4496674961657465872L;
	
	public CmppActiveTestRequestMessage() {
		this(CmppPacketType.CMPPACTIVETESTREQUEST);
	}
	
	public CmppActiveTestRequestMessage(PacketType packetType) {
		setPacketType(packetType);
    	Header header = new DefaultHead();
		header.setSequenceId((GlobalVars.sequenceId.compareAndSet(
								Integer.MAX_VALUE, 0) ? GlobalVars.sequenceId
								.getAndIncrement() : GlobalVars.sequenceId
								.getAndIncrement()));
		setHeader(header);
	}
	
}
