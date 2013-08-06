package psidev.psi.mi.jami.enricher.impl.organism.listener;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.StatisticsWriter;
import psidev.psi.mi.jami.model.*;

import java.io.*;

/**
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 18/07/13
 */
public class OrganismEnricherStatisticsWriter
        extends StatisticsWriter<Organism>
        implements OrganismEnricherListener {


    private static final String OBJECT = "Organism";

    public OrganismEnricherStatisticsWriter(String fileName) throws IOException {
        super(fileName, OBJECT);
    }

    public OrganismEnricherStatisticsWriter(String successFileName, String failureFileName) throws IOException {
        super(successFileName, failureFileName, OBJECT);
    }

    public OrganismEnricherStatisticsWriter(File successFile, File failureFile) throws IOException {
        super(successFile, failureFile, OBJECT);
    }


    public void onEnrichmentComplete(Organism organism, EnrichmentStatus status, String message){
        onObjectEnriched(organism , status , message);
    }

    public void onCommonNameUpdate(Organism organism, String oldCommonName) {
        checkObject(organism);
        updateCount++;
    }

    public void onScientificNameUpdate(Organism organism, String oldScientificName) {
        checkObject(organism);
        updateCount++;
    }

    public void onTaxidUpdate(Organism organism, String oldTaxid) {
        checkObject(organism);
        updateCount++;
    }

    public void onAddedAlias(Organism organism, Alias added) {
        checkObject(organism);
        additionCount++;
    }

    public void onRemovedAlias(Organism organism, Alias removed) {
        checkObject(organism);
        removedCount++;
    }
}
