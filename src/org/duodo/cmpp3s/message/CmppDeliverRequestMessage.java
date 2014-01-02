/**
 * 
 */
package org.duodo.cmpp3s.message;

import org.apache.commons.codec.binary.Hex;
import org.duodo.cmpp3s.packet.CmppDeliverRequest;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.cmpp3s.packet.CmppReportRequest;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultHead;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.message.Header;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.util.MsgId;

/**
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 *
 */
public class CmppDeliverRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = 1906062473217979916L;
	private MsgId msgId = new MsgId();
	private String destId = new String(
			new byte[CmppDeliverRequest.DESTID.getLength()],
			GlobalVars.defaultTransportCharset);
	private String serviceid = new String(
			new byte[CmppDeliverRequest.SERVICEID.getLength()],
			GlobalVars.defaultTransportCharset);
	private short tppid = 0;
	private short tpudhi = 0;
	private short msgfmt = 15;
	private String srcterminalId = new String(
			new byte[CmppDeliverRequest.SRCTERMINALID.getLength()],
			GlobalVars.defaultTransportCharset);
	private short srcterminalType = 0;
	private short registeredDelivery = 0;
	private short msgLength = 140;
	private String msgContent = new String(new byte[msgLength],
			GlobalVars.defaultTransportCharset);
    private CmppReportRequestMessage reportRequestMessage = new CmppReportRequestMessage();
	private String linkid = new String(
			new byte[CmppDeliverRequest.LINKID.getLength()],
			GlobalVars.defaultTransportCharset);
	
	
	private byte[] msgContentBytes = new byte[msgLength];	
	private boolean isReport = false; 
	
	public CmppDeliverRequestMessage() {
		this(CmppPacketType.CMPPDELIVERREQUEST);
    	Header header = new DefaultHead();
		header.setSequenceId((GlobalVars.sequenceId.compareAndSet(
								Integer.MAX_VALUE, 0) ? GlobalVars.sequenceId
								.getAndIncrement() : GlobalVars.sequenceId
								.getAndIncrement()));
		setHeader(header);
	}
	
	public CmppDeliverRequestMessage(PacketType packetType) {
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
     * @return the destId
     */
    public String getDestId() {
        return destId;
    }
    /**
     * @param destId the destId to set
     */
    public void setDestId(String destId) {
        this.destId = destId;
    }
    /**
     * @return the serviceid
     */
    public String getServiceid() {
        return serviceid;
    }
    /**
     * @param serviceid the serviceid to set
     */
    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }
    /**
     * @return the tppid
     */
    public short getTppid() {
        return tppid;
    }
    /**
     * @param tppid the tppid to set
     */
    public void setTppid(short tppid) {
        this.tppid = tppid;
    }
    /**
     * @return the tpudhi
     */
    public short getTpudhi() {
        return tpudhi;
    }
    /**
     * @param tpudhi the tpudhi to set
     */
    public void setTpudhi(short tpudhi) {
        this.tpudhi = tpudhi;
    }
    /**
     * @return the msgfmt
     */
    public short getMsgfmt() {
        return msgfmt;
    }
    /**
     * @param msgfmt the msgfmt to set
     */
    public void setMsgfmt(short msgfmt) {
        this.msgfmt = msgfmt;
    }
    /**
     * @return the srcterminalId
     */
    public String getSrcterminalId() {
        return srcterminalId;
    }
    /**
     * @param srcterminalId the srcterminalId to set
     */
    public void setSrcterminalId(String srcterminalId) {
        this.srcterminalId = srcterminalId;
    }
    /**
     * @return the srcterminalType
     */
    public short getSrcterminalType() {
        return srcterminalType;
    }
    /**
     * @param srcterminalType the srcterminalType to set
     */
    public void setSrcterminalType(short srcterminalType) {
        this.srcterminalType = srcterminalType;
    }
    /**
     * @return the registeredDelivery
     */
    public short getRegisteredDelivery() {
        return registeredDelivery;
    }
    /**
     * @param registeredDelivery the registeredDelivery to set
     */
    public void setRegisteredDelivery(short registeredDelivery) {
        this.registeredDelivery = registeredDelivery;
        if(this.registeredDelivery != 0) {
        	setReport(true);
        	setReportRequestMessage(this.reportRequestMessage);
        }
    }
    /**
     * @return the msgLength
     */
    public short getMsgLength() {
        return msgLength;
    }
    /**
     * @param msgLength the msgLength to set
     */
    public void setMsgLength(short msgLength) {
        this.msgLength = msgLength;
    }
    /**
     * @return the msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }
    /**
     * @param msgContent the msgContent to set
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
        setMsgContentBytes(this.msgContent.getBytes(GlobalVars.defaultTransportCharset));
    }
    /**
     * @return the reportRequestMessage
     */
    public CmppReportRequestMessage getReportRequestMessage() {
        return reportRequestMessage;
    }
    /**
     * @param reportRequestMessage the reportRequestMessage to set
     */
    public void setReportRequestMessage(
            CmppReportRequestMessage reportRequestMessage) {
        this.reportRequestMessage = reportRequestMessage;
        setMsgLength((short) CmppReportRequest.values()[0].getBodyLength()); 
    }
    /**
     * @return the linkid
     */
    public String getLinkid() {
        return linkid;
    }
    /**
     * @param linkid the linkid to set
     */
    public void setLinkid(String linkid) {
        this.linkid = linkid;
    }
	/**
	 * @return the msgContentBytes
	 */
	public byte[] getMsgContentBytes() {
		return msgContentBytes;
	}
	/**
	 * @param msgContentBytes the msgContentBytes to set
	 */
	public void setMsgContentBytes(byte[] msgContentBytes) {
		this.msgContentBytes = msgContentBytes;
		setMsgLength((short) this.msgContentBytes.length);
	}
	/**
	 * @return the isReport
	 */
	public boolean isReport() {
		return isReport;
	}
	/**
	 * @param isReport the isReport to set
	 */
	public void setReport(boolean isReport) {
		this.isReport = isReport;
	}

	@Override
	public String toString() {
		return String
				.format("CmppDeliverRequestMessage [msgId=%s, destId=%s, serviceid=%s, tppid=%s, tpudhi=%s, msgfmt=%s, srcterminalId=%s, srcterminalType=%s, registeredDelivery=%s, msgLength=%s, msgContent=%s, reportRequestMessage=%s, linkid=%s, msgContentBytes=%s, isReport=%s, getPacketType()=%s, getTimestamp()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getLifeTime()=%s, isTerminationLife()=%s, getResponse()=%s, getRequests()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						msgId, destId, serviceid, tppid, tpudhi, msgfmt,
						srcterminalId, srcterminalType, registeredDelivery,
						msgLength, msgContent, reportRequestMessage, linkid,
						Hex.encodeHexString(msgContentBytes), isReport,
						getPacketType(), getTimestamp(), getChannelIds(),
						getChildChannelIds(), getLifeTime(),
						isTerminationLife(), getResponse(), getRequests(),
						getHeader(), Hex.encodeHexString(getBodyBuffer()));
	}

		
}
