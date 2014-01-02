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
public enum CmppQueryResponse implements PacketStructure {
	TIME(CmppDataType.OCTERSTRING, true, 8),    	
	QUERYTYPE(CmppDataType.UNSIGNEDINT, true, 1),
	QUERYCODE(CmppDataType.OCTERSTRING, true, 10),
	MTTLMSG(CmppDataType.UNSIGNEDINT, true, 4), 
	MTTLUSR(CmppDataType.UNSIGNEDINT, true, 4), 
	MTSCS(CmppDataType.UNSIGNEDINT, true, 4),   
	MTWT(CmppDataType.UNSIGNEDINT, true, 4),    
	MTFL(CmppDataType.UNSIGNEDINT, true, 4),    
	MOSCS(CmppDataType.UNSIGNEDINT, true, 4),   
	MOWT(CmppDataType.UNSIGNEDINT, true, 4),    
	MOFL(CmppDataType.UNSIGNEDINT, true, 4);    
	
    private DataType dataType;
    private boolean isFixFiledLength; 
    private int length;
    
    private CmppQueryResponse(DataType dataType, boolean isFixFiledLength, int length) {
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
        for(CmppQueryResponse response : CmppQueryResponse.values()) {
            bodyLength += response.getLength();
        }
        return bodyLength;
    }    
}
