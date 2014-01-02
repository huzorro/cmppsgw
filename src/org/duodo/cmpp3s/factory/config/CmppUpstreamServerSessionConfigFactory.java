/**
 * 
 */
package org.duodo.cmpp3s.factory.config;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.config.UpstreamServerSessionConfigFactory;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class CmppUpstreamServerSessionConfigFactory<T> extends
		UpstreamServerSessionConfigFactory<T> {

	/**
	 * 
	 */
	public CmppUpstreamServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}
