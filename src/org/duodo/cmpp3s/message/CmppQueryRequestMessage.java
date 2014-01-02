/**
 * 
 */
package org.duodo.cmpp3s.message;

import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.cmpp3s.packet.CmppQueryRequest;
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
public class CmppQueryRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = -7762194632879048169L;
	private String time = String.format("%tY%<tm%<td",System.currentTimeMillis());
	private short queryType = 0;
	private String queryCode = new String(
			new byte[CmppQueryRequest.QUERYCODE.getLength()],
			GlobalVars.defaultTransportCharset);
	private String reserve = new String(
			new byte[CmppQueryRequest.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);
	
	public CmppQueryRequestMessage() {
		this(CmppPacketType.CMPPQUERYREQUEST);
	}
	public CmppQueryRequestMessage(PacketType packetType) {
		setPacketType(packetType);
    	Header header = new DefaultHead();
		header.setSequenceId((GlobalVars.sequenceId.compareAndSet(
								Integer.MAX_VALUE, 0) ? GlobalVars.sequenceId
								.getAndIncrement() : GlobalVars.sequenceId
								.getAndIncrement()));
		setHeader(header);
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the queryType
	 */
	public short getQueryType() {
		return queryType;
	}
	/**
	 * @param queryType the queryType to set
	 */
	public void setQueryType(short queryType) {
		this.queryType = queryType;
	}
	/**
	 * @return the queryCode
	 */
	public String getQueryCode() {
		return queryCode;
	}
	/**
	 * @param queryCode the queryCode to set
	 */
	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}
	/**
	 * @return the reserve
	 */
	public String getReserve() {
		return reserve;
	}
	/**
	 * @param reserve the reserve to set
	 */
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
}
