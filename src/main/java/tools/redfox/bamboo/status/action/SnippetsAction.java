package tools.redfox.bamboo.status.action;

import com.atlassian.bamboo.configuration.AdministrationConfigurationAccessor;
import com.atlassian.bamboo.deployments.environments.Environment;
import com.atlassian.bamboo.deployments.projects.DeploymentProject;
import com.atlassian.bamboo.deployments.projects.service.DeploymentProjectService;
import com.atlassian.bamboo.plan.PlanKeys;
import com.atlassian.bamboo.plan.PlanManager;
import com.atlassian.bamboo.security.BambooPermissionManager;
import com.atlassian.bamboo.ww2.actions.PlanActionSupport;
import com.atlassian.bamboo.ww2.aware.permissions.PlanAdminSecurityAware;
import com.atlassian.bamboo.ww2.aware.permissions.PlanReadSecurityAware;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import java.util.ArrayList;
import java.util.List;

public class SnippetsAction extends PlanActionSupport implements PlanReadSecurityAware, PlanAdminSecurityAware {
    public SnippetsAction(@ComponentImport BambooPermissionManager bambooPermissionManager,
                          @ComponentImport DeploymentProjectService deploymentProjectService,
                          @ComponentImport AdministrationConfigurationAccessor administrationConfigurationAccessor,
                          @ComponentImport PlanManager planManager) {
        setBambooPermissionManager(bambooPermissionManager);
        setDeploymentProjectService(deploymentProjectService);
        setAdministrationConfigurationAccessor(administrationConfigurationAccessor);
        setPlanManager(planManager);
    }

    public List<Environment> getEnvironments() {
        ArrayList<Environment> environments = new ArrayList<Environment>();
        for (DeploymentProject deploymentProject : this.deploymentProjectService.getDeploymentProjectsRelatedToPlan(PlanKeys.getPlanKey((String) this.getPlan().getPlanKey().getKey()))) {
            for (Environment environment : deploymentProject.getEnvironments()) {
                environments.add(environment);
            }
        }
        return environments;
    }
}

