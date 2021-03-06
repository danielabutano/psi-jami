package psidev.psi.mi.jami.enricher.listener.impl.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.OntologyTermEnricherListener;
import psidev.psi.mi.jami.listener.impl.OntologyTermChangeLogger;
import psidev.psi.mi.jami.model.OntologyTerm;

/**
 * A logging listener. It will display a message when each event is fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 18/07/13
 */
public class OntologyTermEnricherLogger extends OntologyTermChangeLogger implements OntologyTermEnricherListener {

    private static final Logger log = LoggerFactory.getLogger(OntologyTermEnricherLogger.class.getName());

    public void onEnrichmentComplete(OntologyTerm cvTerm, EnrichmentStatus status, String message) {
        log.info(cvTerm.toString()+" enrichment complete with status ["+status+"], message: "+message);
    }

    public void onEnrichmentError(OntologyTerm object, String message, Exception e) {
        log.error(object.toString()+" enrichment error, message: "+message, e);
    }
}
