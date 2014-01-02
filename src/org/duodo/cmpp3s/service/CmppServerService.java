/**
 * 
 */
package org.duodo.cmpp3s.service;

import java.util.List;

import org.duodo.cmpp3s.global.CmppGlobalVarsInitialize;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.global.GlobalVarsInitialize;
import org.duodo.netty3ext.plugin.DefaultReceivedMsgPluginManagerService;
import org.duodo.netty3ext.plugin.DefaultSubmitMsgPluginManagerService;
import org.duodo.netty3ext.processor.MessageBindToChannelSubmitProcessor;
import org.duodo.netty3ext.service.MessageBindToChannelSubmitService;
import org.duodo.netty3ext.service.Service;
import org.duodo.netty3ext.service.manager.ServerServices;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppServerService implements ServerServices {
	private String configName;
	private static final GlobalVarsInitialize globalVarsInitialize = new CmppGlobalVarsInitialize();

	public CmppServerService() {
		this("cmppsession");
	}

	/**
	 * @param configName
	 */
	public CmppServerService(String configName) {
		this.configName = configName;
	}

	@Override
	public ServerServices upstreamGlobalVarsInit() throws Exception {
		return upstreamGlobalVarsInit(null);
	}

	@Override
	public ServerServices upstreamGlobalVarsInit(List<String> configList)
			throws Exception {
		globalVarsInitialize.upstreamSessionConfigInitialize(configList)
				.upstreamThreadPoolInitialize(configList)
				.upstreamSessionPoolInitialize(configList)
				.upstreamMessageQueueInitialize(configList)
				.upstreamServerBootstrapInitialize(configList)
				.upstreamMessagePluginManagerInitialize(configList);
		return this;
	}

	@Override
	public ServerServices downstreamGlobalVarsInit() throws Exception {
		return downstreamGlobalVarsInit(null);
	}

	@Override
	public ServerServices downstreamGlobalVarsInit(List<String> configList)
			throws Exception {
		globalVarsInitialize.downstreamSessionConfigInitialize(configList)
				.downstreamThreadPoolInitialize(configList)
				.downstreamSessionPoolInitialize(configList)
				.downstreamMessageQueueInitialize(configList)
				.downstreamServerBootstrapInitialize(configList)
				.downstreamMessagePluginManagerInitialize(configList);
		return this;
	}

	@Override
	public ServerServices duplexstreamGlobalVarsInit() throws Exception {
		return duplexstreamGlobalVarsInit(null);
	}

	@Override
	public ServerServices duplexstreamGlobalVarsInit(List<String> configList)
			throws Exception {
		globalVarsInitialize.duplexstreamSessionConfigInitialize(configList)
				.duplexstreamThreadPoolInitialize(configList)
				.duplexstreamSessionPoolInitialize(configList)
				.duplexstreamMessageQueueInitialize(configList)
				.duplexstreamServerBootstrapInitialize(configList)
				.duplexstreamMessagePluginManagerInitialize(configList);
		return this;
	}

	@Override
	public Service upstreamServiceInit() {
		return upstreamServiceInit(null);
	}

	@Override
	public Service upstreamServiceInit(List<String> configList) {
		return new CmppUpstreamServerService(
				GlobalVars.upstreamSessionConfigMap.get(configName), 
				GlobalVars.serverBootstrapMap, 
				GlobalVars.receiveMsgQueueMap,
				GlobalVars.responseMsgQueueMap, 
				GlobalVars.deliverMsgQueueMap, 
				GlobalVars.scheduleExecutorMap, 
				GlobalVars.sessionPoolMap,
				GlobalVars.upstreamServicesRunningList,
				configList);
	}

	@Override
	public Service downstreamServiceInit() {
		return downstreamServiceInit(null);
	}

	@Override
	public Service downstreamServiceInit(List<String> configList) {
		return new CmppDownstreamServerService(
				GlobalVars.downstreamSessionConfigMap.get(configName), 
				GlobalVars.serverBootstrapMap, 
				GlobalVars.receiveMsgQueueMap, 
				GlobalVars.responseMsgQueueMap, 
				GlobalVars.deliverMsgQueueMap, 
				GlobalVars.scheduleExecutorMap, 
				GlobalVars.sessionPoolMap, 
				GlobalVars.downstreamServicesRunningList,
				configList);
	}

	@Override
	public Service duplexstreamServiceInit() {
		return duplexstreamServiceInit(null);
	}

	@Override
	public Service duplexstreamServiceInit(List<String> configList) {
		return new CmppDuplexstreamServerService(
				GlobalVars.duplexSessionConfigMap.get(configName), 
				GlobalVars.serverBootstrapMap, 
				GlobalVars.receiveMsgQueueMap, 
				GlobalVars.responseMsgQueueMap, 
				GlobalVars.deliverMsgQueueMap, 
				GlobalVars.scheduleExecutorMap, 
				GlobalVars.sessionPoolMap, 
				GlobalVars.duplexstreamServicesRunningList, 
				configList);
	}


	@Override
	public Service duplexstreamResponseMsgPluginManagerServiceInit() {
		return duplexstreamResponseMsgPluginManagerServiceInit(null);
	}

	@Override
	public Service duplexstreamResponseMsgPluginManagerServiceInit(
			List<String> configList) {
		return new DefaultReceivedMsgPluginManagerService(
				GlobalVars.duplexSessionConfigMap.get(configName), 
				GlobalVars.responseMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.pluginManagerUtilMap,
				configList);
	}

	@Override
	public Service upstreamResponseMsgPluginManagerServiceInit() {
		return upstreamResponseMsgPluginManagerServiceInit(null);
	}

	@Override
	public Service upstreamResponseMsgPluginManagerServiceInit(
			List<String> configList) {
		return new DefaultReceivedMsgPluginManagerService(
				GlobalVars.upstreamSessionConfigMap.get(configName), 
				GlobalVars.responseMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.pluginManagerUtilMap,
				configList);
	}

	@Override
	public Service downstreamReceiveMsgPluginManagerServiceInit() {
		return downstreamReceiveMsgPluginManagerServiceInit(null);
	}

	@Override
	public Service downstreamReceiveMsgPluginManagerServiceInit(
			List<String> configList) {
		return new DefaultReceivedMsgPluginManagerService(
				GlobalVars.downstreamSessionConfigMap.get(configName), 
				GlobalVars.receiveMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.pluginManagerUtilMap,
				configList);
	}

	@Override
	public Service duplexstreamReceiveMsgPluginManagerServiceInit() {
		return duplexstreamReceiveMsgPluginManagerServiceInit(null);
	}

	@Override
	public Service duplexstreamReceiveMsgPluginManagerServiceInit(
			List<String> configList) {
		return new DefaultReceivedMsgPluginManagerService(
				GlobalVars.duplexSessionConfigMap.get(configName), 
				GlobalVars.receiveMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.pluginManagerUtilMap,
				configList);
	}


	@Override
	public Service upstreamDeliverMsgPluginManagerServiceInit() {
		return upstreamDeliverMsgPluginManagerServiceInit(null);
	}

	@Override
	public Service upstreamDeliverMsgPluginManagerServiceInit(
			List<String> configList) {
		return new DefaultSubmitMsgPluginManagerService(
				GlobalVars.upstreamSessionConfigMap.get(configName), 
				GlobalVars.deliverMsgQueueMap,
				GlobalVars.executorServiceMap, 
				GlobalVars.pluginManagerUtilMap, 
				configList);
	}

	@Override
	public Service duplexstreamDeliverMsgPluginManagerServiceInit() {
		return duplexstreamDeliverMsgPluginManagerServiceInit(null);
	}

	@Override
	public Service duplexstreamDeliverMsgPluginManagerServiceInit(
			List<String> configList) {
		return new DefaultSubmitMsgPluginManagerService(
				GlobalVars.duplexSessionConfigMap.get(configName), 
				GlobalVars.deliverMsgQueueMap,
				GlobalVars.executorServiceMap, 
				GlobalVars.pluginManagerUtilMap, 
				configList);
	}

	@Override
	public Service upstreamDeliverServiceInit() {
		return upstreamDeliverServiceInit(null);
	}

	@Override
	public Service upstreamDeliverServiceInit(List<String> configList) {
		return new MessageBindToChannelSubmitService<MessageBindToChannelSubmitProcessor>(
				GlobalVars.sessionPoolMap, 
				GlobalVars.deliverMsgQueueMap, 
				GlobalVars.reserveMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.upstreamSessionConfigMap.get(configName), 
				false, 
				MessageBindToChannelSubmitProcessor.class, 
				configList);
	}

	@Override
	public Service upstreamReserveDeliverServiceInit() {
		return upstreamReserveDeliverServiceInit(null);
	}

	@Override
	public Service upstreamReserveDeliverServiceInit(List<String> configList) {
		return new MessageBindToChannelSubmitService<MessageBindToChannelSubmitProcessor>(
				GlobalVars.sessionPoolMap, 
				GlobalVars.reserveMsgQueueMap, 
				GlobalVars.reserveMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.upstreamSessionConfigMap.get(configName), 
				false, 
				MessageBindToChannelSubmitProcessor.class, 
				configList);
	}

	@Override
	public Service duplexstreamDeliverServiceInit() {
		return duplexstreamDeliverServiceInit(null);
	}

	@Override
	public Service duplexstreamDeliverServiceInit(List<String> configList) {
		return new MessageBindToChannelSubmitService<MessageBindToChannelSubmitProcessor>(
				GlobalVars.sessionPoolMap, 
				GlobalVars.deliverMsgQueueMap, 
				GlobalVars.reserveMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.duplexSessionConfigMap.get(configName), 
				false, 
				MessageBindToChannelSubmitProcessor.class, 
				configList);	
	}

	@Override
	public Service duplexstreamReserveDeliverServiceInit() {
		return duplexstreamReserveDeliverServiceInit(null);
	}

	@Override
	public Service duplexstreamReserveDeliverServiceInit(List<String> configList) {
		return new MessageBindToChannelSubmitService<MessageBindToChannelSubmitProcessor>(
				GlobalVars.sessionPoolMap, 
				GlobalVars.reserveMsgQueueMap, 
				GlobalVars.reserveMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.duplexSessionConfigMap.get(configName), 
				false, 
				MessageBindToChannelSubmitProcessor.class, 
				configList);
	}
	@Override
	public void process() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
