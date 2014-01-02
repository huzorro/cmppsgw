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
public enum CmppQueryRequest implements PacketStructure {
    TIME(CmppDataType.OCTERSTRING, true, 8),
    QUERYTYPE(CmppDataType.UNSIGNEDINT, true, 1),
    QUERYCODE(CmppDataType.OCTERSTRING, true, 10),
    RESERVE(CmppDataType.OCTERSTRING, true, 8);
	
    private DataType dataType;
    private boolean isFixFiledLength; 
    private int length;
    
    private CmppQueryRequest(DataType dataType, boolean isFixFiledLength, int length) {
        this.dataType = dataType;
        this.isFixFiledLength = isFixFiledLength;
        this.length = length;
    }
    @Override
    public DataType getDataType() {
        return dataType;
    }

    @Override
    public boolean isFixFiledLength() {
        return isFixFiledLength;
    }

    @Override
    public boolean isFixPacketLength() {
        return true;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getBodyLength() {
        int bodyLength = 0;
        for(CmppQueryRequest request : CmppQueryRequest.values()) {
            bodyLength += request.getLength();
        }
        return bodyLength;
    }    
}
