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
public enum CmppSubmitResponse implements PacketStructure {
    MSGID(CmppDataType.UNSIGNEDINT, true, 8),
    RESULT(CmppDataType.UNSIGNEDINT, true, 4);
    private DataType dataType;
    private boolean isFixFiledLength; 
    private int length;
    
    private CmppSubmitResponse(DataType dataType, boolean isFixFiledLength, int length) {
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
        for(CmppSubmitResponse response : CmppSubmitResponse.values()) {
            bodyLength += response.getLength();
        }
        return bodyLength;
    }
}
