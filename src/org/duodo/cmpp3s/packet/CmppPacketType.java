/**
 * 
 */
package org.duodo.cmpp3s.packet;

import org.duodo.netty3ext.packet.PacketStructure;
import org.duodo.netty3ext.packet.PacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public enum CmppPacketType implements PacketType {
    CMPPCONNECTREQUEST(0x00000001L, CmppConnectRequest.class),
    CMPPCONNECTRESPONSE(0x80000001L, CmppConnectResponse.class),
    CMPPTERMINATEREQUEST(0x00000002L, CmppTerminateRequest.class),
    CMPPTERMINATERESPONSE(0x80000002L, CmppTerminateResponse.class),    
    CMPPSUBMITREQUEST(0x00000004L, CmppSubmitRequest.class), 
    CMPPSUBMITRESPONSE(0x80000004L, CmppSubmitResponse.class),
    CMPPDELIVERREQUEST(0x00000005L, CmppDeliverRequest.class),
    CMPPDELIVERRESPONSE(0x80000005L, CmppDeliverResponse.class),    
    CMPPQUERYREQUEST(0x00000006L, CmppQueryRequest.class),
    CMPPQUERYRESPONSE(0x80000006L, CmppQueryResponse.class),
    CMPPCANCELREQUEST(0x00000007L, CmppCancelRequest.class),
    CMPPCANCELRESPONSE(0x80000007L, CmppCancelResponse.class),
    CMPPACTIVETESTREQUEST(0x00000008L, CmppActiveTestRequest.class),
    CMPPACTIVETESTRESPONSE(0x80000008L, CmppActiveTestResponse.class);
    
    private long commandId;
    private Class<? extends PacketStructure> packetStructure;
    
    private CmppPacketType(long commandId, Class<? extends PacketStructure> packetStructure) {
        this.commandId = commandId;
        this.packetStructure = packetStructure;
    }
    public long getCommandId() {
        return commandId;
    }
    public PacketStructure[] getPacketStructures() {
    	return packetStructure.getEnumConstants();
    }

    public long getAllCommandId() {
        long defaultId = 0x0;
        long allCommandId = 0x0;
        for(CmppPacketType packetType : CmppPacketType.values()) {
            allCommandId |= packetType.commandId;
        }
        return allCommandId ^ defaultId;
    }
}
