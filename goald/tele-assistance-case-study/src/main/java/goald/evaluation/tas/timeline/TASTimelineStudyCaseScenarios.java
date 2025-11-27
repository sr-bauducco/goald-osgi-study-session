package goald.evaluation.tas.timeline;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import goald.eval.exec.ExperimentExecutorTimelineBased;
import goald.evaluation.tas.TASRepository;
import goald.evaluation.timeline.TickProducer;
import goald.evaluation.timeline.TimelineEvaluation;
import goald.evaluation.timeline.TimelineTimer;
import goald.planning.VERespository;
import goald.repository.IRepository;


public class TASTimelineStudyCaseScenarios extends ExperimentExecutorTimelineBased {
	
	@Inject
	Logger log;
	
	@Inject
	TimelineEvaluation baseEvaluation;
	
	public void setup() {
		repo = new VERespository(getRepo());
	}

	public List<TimelineEvaluation>  caseStudy() {
		List<TimelineEvaluation> evaluations = new ArrayList<>();
		baseEvaluation.getConstants().put("resultFileName", "tsa_dataset");
		baseEvaluation.setTimer(TimelineTimer.create());
		
		/* 
		 * Study case scenarios. Each one define a different set of contexts. 
		 * For each one the deployment will be planned
		 */
		scenarioRepetitions(1, 1, 
				TickProducer.create(100l,0l,20*60*60*1000l),
				(ctxEvaluatorBuilder)-> ctxEvaluatorBuilder
					.with(
						"internet-connection")
				,
				(qualityWeightsMap) -> {
					qualityWeightsMap.put("precision", 2);
					qualityWeightsMap.put("responseTime", 1);
					qualityWeightsMap.put("storegedSize", 1);
					qualityWeightsMap.put("usability", 1);
				},
				(goalsChangeBuilding)-> goalsChangeBuilding
					.addGoal("ProvideHealthSupport")
				,
				(ctxMonitorBuilding) -> ctxMonitorBuilding
					.add((long) (2.20f*60*60*1000l), "!battery-is-low")
					.rem((long) (3.5f*60*60*1000l), "internet-connection")
					.add((long) (4.10f*60*60*1000l), "internet-connection")
					.add((long) (6.31*60*60*1000l), "!patient-is-ok")
					.add((long) (7.24*60*60*1000l), "doctor-is-present")
					.add((long) (8.78*60*60*1000l), "drug-is-available")
					.rem((long) (12.87*60*60*1000l), "doctor-is-present")
					.rem((long) (14.94*60*60*1000l), "doctor-is-present")
					.rem((long) (15.14*60*60*1000l), "internet-connection")
					.rem((long) (16.21*60*60*1000l), "!patient-is-ok")
					.rem((long) (17.30*60*60*1000l), "!battery-is-low")
					.add((long) (18.48*60*60*1000l), "!patient-is-ok")
					.rem((long) (19.64*60*60*1000l), "drug-is-available")
				, evaluations, baseEvaluation);

		return evaluations;
	}
	
	
	
	protected IRepository getRepo(){
		return TASRepository.getRepo();
	}
}
