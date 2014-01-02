package org.duodo.cmpp3s.decoder;

import org.duodo.cmpp3s.packet.CmppHead;
import org.duodo.netty3ext.message.DefaultHead;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.message.Header;
import org.duodo.netty3ext.message.Message;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppHeaderDecoder extends FrameDecoder {
	private final Logger logger = LoggerFactory.getLogger(CmppHeaderDecoder.class);
    public CmppHeaderDecoder() {
        this(true);
    }

    /**
     * @param unfold
     */
    public CmppHeaderDecoder(boolean unfold) {
        super(unfold);
    }

    /* (non-Javadoc)
     * @see org.jboss.netty.handler.codec.frame.FrameDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, org.jboss.netty.buffer.ChannelBuffer)
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
            ChannelBuffer buffer) throws Exception {
        if(buffer.readableBytes() < CmppHead.COMMANDID.getHeadLength()) return null;
        buffer.markReaderIndex();
        ChannelBuffer headBuffer = 
                buffer.readBytes(CmppHead.COMMANDID.getHeadLength());
        Header header = new DefaultHead();
        header.setHeadBuffer(headBuffer.copy().array());
        
        header.setPacketLength(headBuffer.readUnsignedInt());
        header.setCommandId(headBuffer.readUnsignedInt());
        header.setSequenceId(headBuffer.readUnsignedInt());
        
        
        header.setHeadLength(CmppHead.COMMANDID.getHeadLength());
        
        
        header.setBodyLength(header.getPacketLength() - header.getHeadLength());
        
        if(buffer.readableBytes() < header.getBodyLength()) {
            buffer.resetReaderIndex();
            return null;
        }
        
        ChannelBuffer bodyBuffer = 
                buffer.readBytes((int) header.getBodyLength());
        Message message = new DefaultMessage();
        message.setBodyBuffer(bodyBuffer.copy().array());
        message.setHeader(header);
        return message;
    }

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.frame.FrameDecoder#exceptionCaught(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ExceptionEvent)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		logger.error("receive/decode/close exception {}", e, e.getCause());
		try {
			e.getChannel().close();
		} catch (Exception e1) {
			logger.error("channel close fails {}", e1);
		}
	}

}
