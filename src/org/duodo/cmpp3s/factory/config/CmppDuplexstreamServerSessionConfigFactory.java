/**
 * 
 */
package org.duodo.cmpp3s.factory.config;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.config.DuplexstreamServerSessionConfigFactory;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 * @param <T>
 */
public class CmppDuplexstreamServerSessionConfigFactory<T> extends
		DuplexstreamServerSessionConfigFactory<T> {

	/**
	 * 
	 */
	public CmppDuplexstreamServerSessionConfigFactory(SessionConfig defaultSessionConfig) {
		super(defaultSessionConfig);
	}

}
