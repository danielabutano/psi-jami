package psidev.psi.mi.jami.enricher.impl;

import psidev.psi.mi.jami.bridges.fetcher.SourceFetcher;
import psidev.psi.mi.jami.enricher.CvTermEnricher;
import psidev.psi.mi.jami.enricher.listener.SourceEnricherListener;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Source;

/**
 * Minimal updater of a source
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/01/14</pre>
 */

public class MinimalSourceUpdater extends MinimalSourceEnricher{
    public MinimalSourceUpdater(SourceFetcher cvTermFetcher) {
        super(new MinimalCvTermUpdater<Source>(cvTermFetcher));
    }

    protected MinimalSourceUpdater(CvTermEnricher<Source> cvEnricher) {
        super(cvEnricher);
    }

    @Override
    protected void processPublication(Source cvTermToEnrich, Source cvTermFetched) {
        if (cvTermFetched.getPublication() != null && cvTermFetched.getPublication() != cvTermToEnrich.getPublication()){
            Publication oldPub = cvTermToEnrich.getPublication();
            cvTermToEnrich.setPublication(cvTermFetched.getPublication());
            if (getCvTermEnricherListener() instanceof SourceEnricherListener){
                ((SourceEnricherListener)getCvTermEnricherListener()).onPublicationUpdate(cvTermToEnrich, oldPub);
            }
        }
    }
}
