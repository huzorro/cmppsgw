package org.duodo.cmpp3s.session;

import java.util.concurrent.ScheduledExecutorService;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.future.QFuture;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.queue.BdbQueueMap;
import org.duodo.netty3ext.session.DefaultSession;
import org.jboss.netty.channel.Channel;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppSession extends DefaultSession {
	
	/**
	 * 
	 * @param channel
	 * @param receiveQueue
	 * @param responseQueue
	 * @param deliverQueue
	 * @param scheduleExecutor
	 * @param config
	 */
    public CmppSession(
            Channel channel,
            BdbQueueMap<Long, QFuture<Message>> deliverQueue,
            BdbQueueMap<Long, QFuture<Message>> responseQueue,
            BdbQueueMap<Long, QFuture<Message>> receiveQueue,
            ScheduledExecutorService scheduleExecutor, SessionConfig config) {
    	super(channel, deliverQueue, responseQueue, receiveQueue, scheduleExecutor, config);
    }

    
}
