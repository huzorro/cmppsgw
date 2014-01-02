package org.duodo.cmpp3s.message;

import org.apache.commons.codec.binary.Hex;
import org.duodo.cmpp3s.packet.CmppConnectRequest;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultHead;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.message.Header;
import org.duodo.netty3ext.packet.PacketType;

/**
 * 
 *
 * @author huzorro(huzorro@gmail.com)
 * @param T extends ChannelBuffer
 */
public class CmppConnectRequestMessage extends DefaultMessage {
    private static final long serialVersionUID = -4852540410843278872L;
	private String sourceAddr = new String(
			new byte[CmppConnectRequest.SOURCEADDR.getLength()],
			GlobalVars.defaultTransportCharset);
	private byte[] authenticatorSource = new byte[CmppConnectRequest.AUTHENTICATORSOURCE
			.getLength()];
    private short version = 0x30;
    private long timestamp = 0L;
    
    public CmppConnectRequestMessage() {
    	this(CmppPacketType.CMPPCONNECTREQUEST);
    }
    public CmppConnectRequestMessage(PacketType packetType) {    	
    	setPacketType(packetType);
    	Header header = new DefaultHead();
		header.setSequenceId((GlobalVars.sequenceId.compareAndSet(
								Integer.MAX_VALUE, 0) ? GlobalVars.sequenceId
								.getAndIncrement() : GlobalVars.sequenceId
								.getAndIncrement()));
		setHeader(header);    	
    }
    /**
     * @return the sourceAddr
     */
    public String getSourceAddr() {
        return sourceAddr;
    }
    /**
     * @param sourceAddr the sourceAddr to set
     */
    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }
    /**
     * @return the authenticatorSource
     */
    public byte[] getAuthenticatorSource() {
        return authenticatorSource;
    }
    /**
     * @param authenticatorSource the authenticatorSource to set
     */
    public void setAuthenticatorSource(byte[] authenticatorSource) {
        this.authenticatorSource = authenticatorSource;
    }
    /**
     * @return the version
     */
    public short getVersion() {
        return version;
    }
    /**
     * @param version the version to set
     */
    public void setVersion(short version) {
        this.version = version;
    }
    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }
    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("CmppConnectRequestMessage [sourceAddr=%s, authenticatorSource=%s, version=%s, timestamp=%s, getPacketType()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getResponse()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						sourceAddr, Hex.encodeHexString(authenticatorSource),
						version, timestamp, getPacketType(), getChannelIds(),
						getChildChannelIds(), getResponse(), getHeader(),
						Hex.encodeHexString(getBodyBuffer()));
	}
    
}
