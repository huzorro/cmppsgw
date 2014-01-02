package org.duodo.cmpp3s.global;

import java.util.List;
import java.util.Map;

import org.duodo.cmpp3s.factory.config.CmppDownstreamSessionConfigMapFactory;
import org.duodo.cmpp3s.factory.config.CmppDuplexstreamSessionConfigMapFactory;
import org.duodo.cmpp3s.factory.config.CmppUpstreamSessionConfigMapFactory;
import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.global.DefaultGlobalVarsInitialize;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.global.GlobalVarsInitialize;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppGlobalVarsInitialize extends
        DefaultGlobalVarsInitialize {
	
    public CmppGlobalVarsInitialize() {
    	super("cmppsession");
	}
    
	public CmppGlobalVarsInitialize(String configName) {
		super(configName);
	}

	@Override
	public GlobalVarsInitialize upstreamSessionConfigInitialize(
			List<String> configList) throws Exception {
		new CmppUpstreamSessionConfigMapFactory<Map<String, Map<String,SessionConfig>>, SessionConfig>(GlobalVars.config, GlobalVars.upstreamSessionConfigMap, configList).create();
		return this; 
	}

	@Override
	public GlobalVarsInitialize downstreamSessionConfigInitialize(
			List<String> configList) throws Exception {
		new CmppDownstreamSessionConfigMapFactory<Map<String, Map<String,SessionConfig>>, SessionConfig>(GlobalVars.config, GlobalVars.downstreamSessionConfigMap, configList).create();
		return this; 
	}

	@Override
	public GlobalVarsInitialize duplexstreamSessionConfigInitialize(
			List<String> configList) throws Exception {
		new CmppDuplexstreamSessionConfigMapFactory<Map<String, Map<String,SessionConfig>>, SessionConfig>(GlobalVars.config, GlobalVars.duplexSessionConfigMap, configList).create();
		return this; 
	}

}
