package psidev.psi.mi.jami.enricher.impl.organism.listener;

import psidev.psi.mi.jami.enricher.listener.EnricherListener;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.listener.OrganismChangeListener;
import psidev.psi.mi.jami.model.Organism;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 13/06/13
 */
public interface OrganismEnricherListener
        extends OrganismChangeListener  , EnricherListener<Organism> {

    public void onEnrichmentComplete(Organism organism , EnrichmentStatus status , String message);
}
