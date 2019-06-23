package tools.redfox.bamboo.status.servlet;

import com.atlassian.bamboo.builder.BuildState;
import com.atlassian.bamboo.builder.LifeCycleState;
import com.atlassian.bamboo.deployments.environments.Environment;
import com.atlassian.bamboo.deployments.environments.service.EnvironmentService;
import com.atlassian.bamboo.deployments.results.DeploymentResult;
import com.atlassian.bamboo.deployments.results.service.DeploymentResultService;
import com.atlassian.bamboo.deployments.versions.service.DeploymentVersionService;
import com.atlassian.bamboo.plan.PlanResultKey;
import com.atlassian.bamboo.resultsummary.ResultsSummary;
import com.atlassian.bamboo.resultsummary.ResultsSummaryManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import tools.redfox.bamboo.status.Status;
import tools.redfox.bamboo.status.service.BadgeBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeployBadgeServlet extends AbstractBadgeServlet {
    private final ResultsSummaryManager resultsSummaryManager;
    private final DeploymentResultService deploymentResultService;
    private final DeploymentVersionService deploymentVersionService;
    private final EnvironmentService environmentService;

    public DeployBadgeServlet(@ComponentImport ResultsSummaryManager resultsSummaryManager,
                              @ComponentImport DeploymentResultService deploymentResultService,
                              @ComponentImport DeploymentVersionService deploymentVersionService,
                              @ComponentImport EnvironmentService environmentService) {
        super();
        this.resultsSummaryManager = resultsSummaryManager;
        this.deploymentResultService = deploymentResultService;
        this.deploymentVersionService = deploymentVersionService;
        this.environmentService = environmentService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String envIdStr = request.getPathInfo();
            long envId = Long.parseLong(envIdStr.substring(1));
            response.sendRedirect(getBadgeUrl(envId));
        } catch (NumberFormatException e) {
            response.sendRedirect(BadgeBuilder.unknownDeployment());
        }

        send(response);
    }

    protected String getBadgeUrl(long envId) {
        Environment environment = this.environmentService.getEnvironment(envId);
        if (environment == null) {
            return BadgeBuilder.unknownDeployment();
        }

        DeploymentResult deploymentResult = this.deploymentResultService.getLatestDeploymentResultForEnvironment(envId);
        if (deploymentResult == null) {
            return BadgeBuilder.unknownDeployment(environment.getName());
        }

        PlanResultKey planResultKey = this.deploymentVersionService.getRelatedPlanResultKeys(deploymentResult.getDeploymentVersion().getId()).iterator().next();
        if (planResultKey == null) {
            return BadgeBuilder.unknownDeployment(environment.getName());
        }

        ResultsSummary resultsSummary = this.resultsSummaryManager.getResultsSummary(planResultKey);
        if (resultsSummary == null) {
            return BadgeBuilder.unknownDeployment(environment.getName());
        }

        return BadgeBuilder.deployment(
                getDeploymentStatus(deploymentResult),
                environment.getName(),
                deploymentResult.getDeploymentVersionName()
        );
    }

    protected Status getDeploymentStatus(DeploymentResult result) {
        if (result.getLifeCycleState() == LifeCycleState.IN_PROGRESS) {
            return Status.IN_PROGRESS;
        }
        if (result.getLifeCycleState() == LifeCycleState.FINISHED) {
            if (result.getDeploymentState() == BuildState.SUCCESS) {
                return Status.SUCCESS;
            }
            return Status.FAILED;
        }

        return Status.UNKNOWN;
    }
}
