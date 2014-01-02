/**
 * 
 */
package org.duodo.cmpp3s.plugin;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;
import net.xeoh.plugins.base.annotations.meta.Author;
import net.xeoh.plugins.base.annotations.meta.Version;

import org.duodo.cmpp3s.message.CmppDeliverRequestMessage;
import org.duodo.netty3ext.plugin.SubmitMessageHandlerPlugin;
import org.duodo.netty3ext.plugin.SubmitMessageServicePlugin;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
@Author(name = "huzorro(huzorro@gmail.com)")
@Version(version = 10000)
@PluginImplementation
public class SimpleDeliverRequestMessage implements SubmitMessageHandlerPlugin {
	
	@InjectPlugin
	public SubmitMessageServicePlugin submitMessageServicePlugin;

	@Override
	public void submit() throws Exception {
		for(int i = 0; i < 1; i++) {
			CmppDeliverRequestMessage msg = new CmppDeliverRequestMessage();
			msg.setChannelIds("901077");
			msg.setMsgContent("哈哈Ok");
			msg.setDestId("10669501");
			msg.setSrcterminalId("13800138000");
			msg.setServiceid("abc");
			msg.setRegisteredDelivery((short)0);
			submitMessageServicePlugin.submit(msg);
		}		
	}

}
