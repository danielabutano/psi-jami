package psidev.psi.mi.jami.enricher.listener.feature;

import psidev.psi.mi.jami.enricher.listener.EnrichmentStatus;
import psidev.psi.mi.jami.enricher.listener.StatisticsWriter;
import psidev.psi.mi.jami.model.*;

import java.io.*;

/**
 * A statistics logger which records changes made by the enricher.
 * Each addition, removal or update is counted and, upon the completion of the enrichment of the object,
 * is logged in either a file of successes or failures depending on the enrichmentStatus.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 18/07/13
 */
public class FeatureEnricherStatisticsWriter
        extends StatisticsWriter<Feature>
        implements FeatureEnricherListener {


    private static final String jamiObject = "Feature";
    /**
     * Uses the known name of the JamiObject type as the seed to generate names for the success an failure log files.
     * @throws IOException      Thrown if a problem is encountered with file location.
     */
    public FeatureEnricherStatisticsWriter() throws IOException {
        super(jamiObject);
    }

    /**
     * Creates the files from the provided seed file name with 'success' and 'failure' appended.
     * @param fileName          The seed to base the names of the files on.
     * @throws IOException      Thrown if a problem is encountered with file location.
     */
    public FeatureEnricherStatisticsWriter(String fileName) throws IOException {
        super(fileName, jamiObject);
    }

    /**
     * Uses the provided names to create the files for successful and failed enrichment logging.
     * @param successFileName   The exact name for the file to log successful enrichments in
     * @param failureFileName   The exact name for the file to log failed enrichments in
     * @throws IOException      Thrown if a problem is encountered with file location.
     */
    public FeatureEnricherStatisticsWriter(String successFileName, String failureFileName) throws IOException {
        super(successFileName, failureFileName, jamiObject);
    }

    /**
     * Uses the exact files provided to log successful and failed enrichments.
     * @param successFile       The file to log successful enrichments in
     * @param failureFile       The file to log failed enrichments in.
     * @throws IOException      Thrown if a problem is encountered with file location.
     */
    public FeatureEnricherStatisticsWriter(File successFile, File failureFile) throws IOException {
        super(successFile, failureFile, jamiObject);
    }


    // ================================================================

    public void onEnrichmentComplete(Feature feature, EnrichmentStatus status, String message) {
        onObjectEnriched(feature, status, message);
    }

    public void onUpdatedRange(Feature feature, Range updated, String message) {
        checkObject(feature);
        updateCount++;
    }

    public void onInvalidRange(Feature feature, Range invalid, String message) {
        // No changes have been made
    }

    public void onShortNameUpdate(Feature feature, String oldShortName) {
        checkObject(feature);
        updateCount++;
    }

    public void onFullNameUpdate(Feature feature, String oldFullName) {
        checkObject(feature);
        updateCount++;
    }

    public void onInterproUpdate(Feature feature, String oldInterpro) {
        checkObject(feature);
        updateCount++;
    }

    public void onTypeAdded(Feature feature, CvTerm oldType) {
        checkObject(feature);
        additionCount++;
    }

    public void onAddedIdentifier(Feature feature, Xref added) {
        checkObject(feature);
        additionCount++;
    }

    public void onRemovedIdentifier(Feature feature, Xref removed) {
        checkObject(feature);
        removedCount++;
    }

    public void onAddedXref(Feature feature, Xref added) {
        checkObject(feature);
        additionCount++;
    }

    public void onRemovedXref(Feature feature, Xref removed) {
        checkObject(feature);
        removedCount++;
    }

    public void onAddedAnnotation(Feature feature, Annotation added) {
        checkObject(feature);
        additionCount++;
    }

    public void onRemovedAnnotation(Feature feature, Annotation removed) {
        checkObject(feature);
        removedCount++;
    }

    public void onAddedRange(Feature feature, Range added) {
        checkObject(feature);
        additionCount++;
    }

    public void onRemovedRange(Feature feature, Range removed) {
        checkObject(feature);
        removedCount++;
    }


}