package org.duodo.cmpp3s.factory.pipeline;

import java.util.concurrent.TimeUnit;

import org.duodo.cmpp3s.decoder.CmppConnectRequestMessageDecoder;
import org.duodo.cmpp3s.decoder.CmppHeaderDecoder;
import org.duodo.cmpp3s.decoder.CmppSubmitRequestMessageDecoder;
import org.duodo.cmpp3s.decoder.CmppTerminateRequestMessageDecoder;
import org.duodo.cmpp3s.encoder.CmppConnectResponseMessageEncoder;
import org.duodo.cmpp3s.encoder.CmppHeaderEncoder;
import org.duodo.cmpp3s.encoder.CmppSubmitResponseMessageEncoder;
import org.duodo.cmpp3s.encoder.CmppTerminateResponseMessageEncoder;
import org.duodo.cmpp3s.factory.session.CmppServerSessionFactory;
import org.duodo.cmpp3s.handler.CmppActiveTestRequestMessageHandler;
import org.duodo.cmpp3s.handler.CmppCommonsHeaderHandler;
import org.duodo.cmpp3s.handler.CmppCommonsMessageHandler;
import org.duodo.cmpp3s.handler.CmppConnectRequestMessageHandler;
import org.duodo.cmpp3s.handler.CmppServerIdleStateHandler;
import org.duodo.cmpp3s.handler.CmppSubmitRequestMessageHandler;
import org.duodo.cmpp3s.handler.CmppTerminateRequestMessageHandler;
import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.DefaultServerSessionFactory;
import org.duodo.netty3ext.factory.session.config.DefaultServerSessionConfigFactory;
import org.duodo.netty3ext.session.Session;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

public class CmppDownstreamServerChannelPipelineFactory implements
		ChannelPipelineFactory {

	private DefaultServerSessionFactory<Session> sessionFactory;
	private DefaultServerSessionConfigFactory<SessionConfig> configFactory;
	private SessionConfig config;
	private Timer timer;
	/**
	 * 
	 */
	public CmppDownstreamServerChannelPipelineFactory(
			CmppServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory,
			SessionConfig config) {
		this(sessionFactory, configFactory, config, new HashedWheelTimer());
	}
	

	/**
	 * @param sessionFactory
	 * @param configFactory
	 * @param config
	 * @param timer
	 */
	public CmppDownstreamServerChannelPipelineFactory(
			DefaultServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory,
			SessionConfig config, Timer timer) {
		this.sessionFactory = sessionFactory;
		this.configFactory = configFactory;
		this.config = config;
		this.timer = timer;
	}


	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline();
        
		pipeline.addLast("IdleStateHandler", new IdleStateHandler(timer, 0, 0,
				config.getIdleTime(), TimeUnit.SECONDS));
		pipeline.addLast("CmppServerIdleStateHandler", new CmppServerIdleStateHandler());
		
        pipeline.addLast("CmppHeaderDecoder", new CmppHeaderDecoder());

        
        pipeline.addLast("CmppSubmitRequestMessageDecoder", new CmppSubmitRequestMessageDecoder());
        pipeline.addLast("CmppSubmitResponseMessageEncoder", new CmppSubmitResponseMessageEncoder());
        
        pipeline.addLast("CmppConnectRequestMessageDecoder", new CmppConnectRequestMessageDecoder());
        pipeline.addLast("CmppConnectResponseMessageEncoder", new CmppConnectResponseMessageEncoder());

        
        pipeline.addLast("CmppTerminateRequestMessageDecoder", new CmppTerminateRequestMessageDecoder());
        pipeline.addLast("CmppTerminateResponseMessageEncoder", new CmppTerminateResponseMessageEncoder());

        pipeline.addLast("CmppConnectRequestMessageHandler", new CmppConnectRequestMessageHandler(sessionFactory, configFactory));

        pipeline.addLast("CmppHeaderEncoder", new CmppHeaderEncoder());          
        pipeline.addLast("CmppCommonsHeaderHandler", new CmppCommonsHeaderHandler());
        pipeline.addLast("CmppCommonsMessageHandler", new CmppCommonsMessageHandler());
        
        pipeline.addLast("CmppSubmitRequestMessageHandler", new CmppSubmitRequestMessageHandler());
        pipeline.addLast("CmppActiveTestRequestMessageHandler", new CmppActiveTestRequestMessageHandler());
        pipeline.addLast("CmppTerminateRequestMessageHandler", new CmppTerminateRequestMessageHandler());
        
        return pipeline;
	}

}
