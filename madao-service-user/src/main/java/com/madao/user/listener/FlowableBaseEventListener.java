package com.madao.user.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.api.delegate.event.FlowableEventType;
import org.flowable.engine.*;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.delegate.event.impl.FlowableEntityWithVariablesEventImpl;
import org.flowable.engine.delegate.event.impl.FlowableProcessCancelledEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Flowable全局事件监听器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-06-12 14:37
 */
@Component
@Slf4j
@AllArgsConstructor
public class FlowableBaseEventListener implements FlowableEventListener {
	private final RuntimeService runtimeService;
	private final RepositoryService repositoryService;
	private final ProcessEngine processEngine;
	private final TaskService taskService;
	private final ManagementService managementService;
	private final HistoryService historyService;

	@Override
	public void onEvent(FlowableEvent event) {
		log.info("流程全局事件处理：{}", event.getType());
		FlowableEventType type = event.getType();
		if (type == FlowableEngineEventType.TASK_CREATED) {
			// 用户任务创建完成
			if (event instanceof FlowableEntityEventImpl eventImpl) {
				TaskEntityImpl taskEntity = (TaskEntityImpl) eventImpl.getEntity();
			}
		} else if (type == FlowableEngineEventType.TASK_COMPLETED) {
			// 用户审批任务
			if (event instanceof FlowableEntityWithVariablesEventImpl eventImpl) {
				TaskEntityImpl taskEntity = (TaskEntityImpl) eventImpl.getEntity();
			}
		} else if (type == FlowableEngineEventType.PROCESS_COMPLETED) {
			// 流程完成
			if (event instanceof FlowableEntityEventImpl eventImpl) {
				ExecutionEntityImpl taskEntity = (ExecutionEntityImpl) eventImpl.getEntity();
			}
		} else if (type == FlowableEngineEventType.PROCESS_CANCELLED) {
			// 流程删除
			if (event instanceof FlowableProcessCancelledEventImpl eventImpl) {
			}
		}
		BpmnModel bpmnModel = repositoryService.getBpmnModel("processDefinitionId");
		Map<String, String> dataObjectMap = bpmnModel.getMainProcess().getDataObjects().stream()
				.collect(Collectors.toMap(BaseElement::getId, v -> v.getValue() == null ? "" : v.getValue().toString()));
		String businessNotifyUri = dataObjectMap.get("business_notify_uri");
		log.info("基于业务查询模型，事件通知地址: {}", "/api/notify");
		if (StringUtils.isNotEmpty(businessNotifyUri)) {

		}
	}

	/**
	 * onEvent(..)方法抛出异常时的行为
	 * false为不处理
	 */
	@Override
	public boolean isFailOnException() {
		return false;
	}

	@Override
	public boolean isFireOnTransactionLifecycleEvent() {
		return false;
	}

	@Override
	public String getOnTransaction() {
		return null;
	}
}
