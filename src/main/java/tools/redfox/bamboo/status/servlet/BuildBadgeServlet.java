package tools.redfox.bamboo.status.servlet;

import com.atlassian.bamboo.chains.ChainResultsSummary;
import com.atlassian.bamboo.plan.Plan;
import com.atlassian.bamboo.plan.PlanKeys;
import com.atlassian.bamboo.plan.PlanManager;
import com.atlassian.bamboo.resultsummary.ResultsSummaryManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.redfox.bamboo.status.Status;
import tools.redfox.bamboo.status.service.BadgeBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BuildBadgeServlet extends AbstractBadgeServlet {
    private static final Logger logger = LoggerFactory.getLogger(BuildBadgeServlet.class);
    private PlanManager planManager;
    private ResultsSummaryManager resultsSummaryManager;

    public BuildBadgeServlet(@ComponentImport PlanManager planManager,
                             @ComponentImport ResultsSummaryManager resultsSummaryManager) {
        super();
        this.planManager = planManager;
        this.resultsSummaryManager = resultsSummaryManager;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String buildKey = request.getPathInfo().substring(1);
        Plan plan = null;
        ChainResultsSummary resultsSummary = null;

        try {
            plan = this.planManager.getPlanByKey(PlanKeys.getPlanKey(buildKey));
        } catch (IllegalArgumentException e) {
        }

        if (plan == null) {
            response.sendRedirect(getBadgeUrl(Status.UNKNOWN, "Build"));
        } else if (plan != null && ((resultsSummary = getLastBuildResult(plan)) != null)) {
            response.sendRedirect(getBadgeUrl(this.getResultsStatus(resultsSummary), "Build no." + resultsSummary.getBuildNumber()));
        } else {
            response.sendRedirect(getBadgeUrl(Status.UNKNOWN, "Build"));
        }

        send(response);
    }

    private String getBadgeUrl(Status status, String label) {
        return BadgeBuilder.url(status, label, status.toString());
    }

    private ChainResultsSummary getLastBuildResult(Plan plan) {
        return this.resultsSummaryManager.getLastResultsSummary(plan.getKey(), ChainResultsSummary.class);
    }

    private Status getResultsStatus(ChainResultsSummary resultsSummary) {
        if (resultsSummary.isActive()) {
            return Status.IN_PROGRESS;
        }
        if (resultsSummary.isFailed()) {
            return Status.FAILED;
        }
        if (resultsSummary.isSuccessful()) {
            return Status.SUCCESS;
        }

        return Status.UNKNOWN;
    }
}
