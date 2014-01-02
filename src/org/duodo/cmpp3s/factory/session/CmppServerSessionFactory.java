/**
 * 
 */
package org.duodo.cmpp3s.factory.session;

import java.util.concurrent.ScheduledExecutorService;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.DefaultServerSessionFactory;
import org.duodo.netty3ext.future.QFuture;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.pool.session.SessionPool;
import org.duodo.netty3ext.queue.BdbQueueMap;
import org.jboss.netty.channel.Channel;
/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class CmppServerSessionFactory<T> extends DefaultServerSessionFactory<T> {
	/**
	 * 
	 * @param config
	 * @param requestQueue
	 * @param responseQueue
	 * @param deliverQueue
	 * @param messageQueue
	 * @param scheduleExecutor
	 * @param sessionPool
	 */
	public CmppServerSessionFactory(
			BdbQueueMap<Long, QFuture<Message>> receiveQueue,
			BdbQueueMap<Long, QFuture<Message>> responseQueue,
			BdbQueueMap<Long, QFuture<Message>> deliverQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool) {
		super(receiveQueue, responseQueue, deliverQueue, 
				scheduleExecutor, sessionPool);
	}

	/**
	 * 
	 * @param config
	 * @param requestQueue
	 * @param responseQueue
	 * @param deliverQueue
	 * @param messageQueue
	 * @param scheduleExecutor
	 * @param sessionPool
	 * @param channel
	 */
	public CmppServerSessionFactory(
			SessionConfig config,
			BdbQueueMap<Long, QFuture<Message>> receiveQueue,
			BdbQueueMap<Long, QFuture<Message>> responseQueue,
			BdbQueueMap<Long, QFuture<Message>> deliverQueue,
			ScheduledExecutorService scheduleExecutor, SessionPool sessionPool,
			Channel channel) {
		super(config, receiveQueue, responseQueue, deliverQueue, 
				scheduleExecutor, sessionPool, channel);
	}

}
