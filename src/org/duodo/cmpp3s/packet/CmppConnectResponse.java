/**
 * 
 */
package org.duodo.cmpp3s.packet;

import org.duodo.netty3ext.packet.DataType;
import org.duodo.netty3ext.packet.PacketStructure;


/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public enum CmppConnectResponse implements PacketStructure {
    STATUS(CmppDataType.UNSIGNEDINT, true, 4),
    AUTHENTICATORISMG(CmppDataType.OCTERSTRING, true, 16),
    VERSION(CmppDataType.UNSIGNEDINT, true, 1);
    private DataType dataType;
    private boolean isFixFiledLength; 
    private int length;
    private CmppConnectResponse(DataType dataType, boolean isFixFiledLength, int length) {
        this.dataType = dataType;
        this.isFixFiledLength = isFixFiledLength;
        this.length = length;
    }
    public DataType getDataType() {
        return dataType;
    }
    public boolean isFixFiledLength() {
        return isFixFiledLength;
    }
    public boolean isFixPacketLength() {
    	return true;
    }
    public int getLength() {
        return length;
    }
    public int getBodyLength() {
        int bodyLength = 0;
        for(CmppConnectResponse response : CmppConnectResponse.values()) {
            bodyLength += response.getLength();
        }
        return bodyLength;
    }
}
