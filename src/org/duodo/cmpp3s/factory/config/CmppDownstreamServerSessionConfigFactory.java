/**
 * 
 */
package org.duodo.cmpp3s.factory.config;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.config.DownstreamServerSessionConfigFactory;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class CmppDownstreamServerSessionConfigFactory<T> extends
		DownstreamServerSessionConfigFactory<T> {

	/**
	 * 
	 */
	public CmppDownstreamServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}
