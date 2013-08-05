package psidev.psi.mi.jami.enricher.impl.experiment.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.model.Experiment;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 01/08/13
 */
public class ExperimentEnricherLogger
        implements ExperimentEnricherListener{


    protected static final Logger log = LoggerFactory.getLogger(ExperimentEnricherLogger.class.getName());

    public void onExperimentEnriched(Experiment experiment, EnrichmentStatus status, String message) {
        log.info(experiment.toString()+" enrichment complete " +
                "with status ["+status+"], message: "+message);
    }
}