package org.duodo.cmpp3s.factory.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;
import org.duodo.cmpp3s.config.CmppUpstreamSessionConfig;
import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.config.UpstreamSessionConfigMapFactory;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppUpstreamSessionConfigMapFactory<T extends Map<String, Map<String, E>>, E extends SessionConfig> extends
        UpstreamSessionConfigMapFactory<T, E> {
    @SuppressWarnings("unchecked")
	public CmppUpstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            T sessionConfigMap,
            List<String> configList) {
            this(configurationBuilder, sessionConfigMap, "upstream", "cmppsession", (Class<E>) CmppUpstreamSessionConfig.class, configList);
    }
    /**
     * @param configurationBuilder
     * @param sessionConfigMap
     * @param sessionType
     * @param classz
     */
    public CmppUpstreamSessionConfigMapFactory(
            CombinedConfiguration configurationBuilder,
            T sessionConfigMap, 
            String sessionType,
            String configName,
            Class<E> classz,
            List<String> configList) {
        super(configurationBuilder, sessionConfigMap, sessionType, configName, classz, configList);
    }


}
