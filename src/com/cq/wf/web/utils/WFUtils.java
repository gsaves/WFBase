package com.cq.wf.web.utils;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

public class WFUtils {

    private static ProcessEngine pe;

    public static ProcessEngine getProcessEngine() {

        if (pe == null) {
            pe = ProcessEngines.getDefaultProcessEngine();
        }
        return pe;
    }

    public static RuntimeService getRuntimeService() {
        return getProcessEngine().getRuntimeService();
    }

    public static RepositoryService getRepositoryService() {
        return getProcessEngine().getRepositoryService();
    }

    public static TaskService getTaskService() {
        return getProcessEngine().getTaskService();
    }

    public static ManagementService getManagementService() {
        return getProcessEngine().getManagementService();
    }

    public static IdentityService getIdentityService() {
        return getProcessEngine().getIdentityService();
    }

    public static HistoryService getHistoryService() {
        return getProcessEngine().getHistoryService();
    }

    public static FormService getFormService() {
        return getProcessEngine().getFormService();
    }
}
