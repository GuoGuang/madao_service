package com.madao.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class FlowableExampleController {

	RuntimeService runtimeService;
	TaskService taskService;
	RepositoryService repositoryService;
	ProcessEngine processEngine;

	@GetMapping("/getPic")
	public void showPic(HttpServletResponse response, String processId) throws Exception {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
		if (pi == null) {
			throw new RuntimeException("ProcessInstance不存在");
		}
		List<Execution> executions = runtimeService
				.createExecutionQuery()
				.processInstanceId(processId)
				.list();

		List<String> activityIds = new ArrayList<>();
		List<String> flows = new ArrayList<>();
		for (Execution exe : executions) {
			List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
			activityIds.addAll(ids);
		}

		/**
		 * 生成流程图
		 */
		BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
		ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
		ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
		InputStream fileInputStream = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, false);
		ServletOutputStream outputStream = response.getOutputStream();
		byte[] buff = new byte[1024];
		int i;
		while ((i = fileInputStream.read(buff)) != -1) {
			outputStream.write(buff, 0, i);
		}
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename=1.png");
	}

	String staffId = "user1";

	/**
	 * 1、开启一个流程
	 */
	@GetMapping("/start")
	void askForLeave() {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave");
		runtimeService.setVariable(processInstance.getId(), "name", "javaboy");
		runtimeService.setVariable(processInstance.getId(), "reason", "休息一下");
		runtimeService.setVariable(processInstance.getId(), "days", 10);
		log.info("创建请假流程 processId：{}", processInstance.getId());
	}

	/**
	 * 2、员工查找到自己的任务，然后提交给人事审批
	 */
	@GetMapping("/init")
	void init() {
		List<Task> alist = taskService.createTaskQuery().taskAssignee(staffId).orderByTaskId().desc().list();
		for (org.flowable.task.api.Task task : alist) {
			log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
			Map<String, Object> map = new HashMap<>();
			//提交给组长的时候，需要指定组长的 id
//			map.put("role", "1123598816738675203");
			taskService.complete(task.getId(), map);
		}
	}

	/**
	 * 3、人事审批
	 */
	@GetMapping("/three")
	void three() {
		List<Task> list = taskService.createTaskQuery().taskAssignee("user2").orderByTaskId().desc().list();
		for (Task task : list) {
			log.info("人事 {} 在审批 {} 任务", task.getAssignee(), task.getId());
			Map<String, Object> map = new HashMap<>();
			//审批的时候，如果是拒绝，就不需要指定经理的 id
			map.put("days", "5");
			taskService.complete(task.getId(), map);
		}
	}

	/**
	 * 4、经理审批-拒绝
	 */
	@GetMapping("/four")
	void four() {
		List<Task> list = taskService.createTaskQuery().taskAssignee("manage1").orderByTaskId().desc().list();
		for (Task task : list) {
			log.info("经理 {} 在审批 {} 任务", task.getAssignee(), task.getId());
			Map<String, Object> map = new HashMap<>();
			map.put("days", "拒绝");
			taskService.complete(task.getId(), map);
		}
	}

	/**
	 * 5、老板审批
	 * j结束后在act_hi_actinst表中有endevent
	 */
	@GetMapping("/five")
	void boss1() {
		List<Task> list = taskService.createTaskQuery().taskAssignee("boss1").orderByTaskId().desc().list();
		for (Task task : list) {
			log.info("l老板 {} 在审批 {} 任务", task.getAssignee(), task.getId());
			Map<String, Object> map = new HashMap<>();
			map.put("days", "3");
			taskService.complete(task.getId(), map);
		}
	}

}
