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
public enum CmppCancelRequest implements PacketStructure {
	MSGID(CmppDataType.UNSIGNEDINT, true, 8);
    private DataType dataType;
    private boolean isFixFiledLength; 
    private int length;
    
    private CmppCancelRequest(DataType dataType, boolean isFixFiledLength, int length) {
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
        for(CmppCancelRequest request : CmppCancelRequest.values()) {
            bodyLength += request.getLength();
        }
        return bodyLength;
    } 
}
