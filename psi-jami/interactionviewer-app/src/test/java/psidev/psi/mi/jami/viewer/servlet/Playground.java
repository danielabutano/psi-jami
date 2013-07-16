package psidev.psi.mi.jami.viewer.servlet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.InteractionEvidenceSpokeExpansion;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.bridges.ols.CachedOntologyOLSFetcher;
import psidev.psi.mi.jami.commons.MIDataSourceOptionFactory;
import psidev.psi.mi.jami.commons.MIFileAnalyzer;
import psidev.psi.mi.jami.commons.MIFileType;
import psidev.psi.mi.jami.commons.PsiJami;
import psidev.psi.mi.jami.datasource.InteractionSource;
import psidev.psi.mi.jami.datasource.InteractionWriter;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.InteractionObjectCategory;
import psidev.psi.mi.jami.factory.MIDataSourceFactory;
import psidev.psi.mi.jami.json.MIJsonBinaryWriter;
import psidev.psi.mi.jami.json.MIJsonWriter;
import psidev.psi.mi.jami.model.InteractionEvidence;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Test some examples
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */

public class Playground {
    private MIFileAnalyzer fileAnalyzer;
    private ComplexExpansionMethod<InteractionEvidence, BinaryInteractionEvidence> expansionMethod;
    private OntologyTermFetcher fetcher;

    @Before
    public void init() throws BridgeFailedException {
        PsiJami.initialiseInteractionEvidenceSources();
        fileAnalyzer = new MIFileAnalyzer();
        expansionMethod = new InteractionEvidenceSpokeExpansion();
        fetcher = new CachedOntologyOLSFetcher();
    }

    @Test
    @Ignore
    public void test_play_psicquic() throws IOException {
        String urlString="http://www.ebi.ac.uk/Tools/webservices/psicquic/intact/webservices/current/search/query/param:true?format=tab27&maxResults=500";
        Writer writer = new PrintWriter(new File("example6.txt"));

        InputStream stream = null;
        InteractionSource miDataSource = null;
        InteractionWriter interactionWriter = null;
        try {
            URL url = new URL(urlString);
            URLConnection connection1 = url.openConnection();
            connection1.setReadTimeout(5000);
            connection1.setConnectTimeout(5000);
            URLConnection connection = url.openConnection();
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(30000);

            // first recognize file and create data source
            stream = connection.getInputStream();
            InputStream dataStream = connection1.getInputStream();
            MIFileType fileType = fileAnalyzer.identifyMIFileTypeFor(stream);
            MIDataSourceOptionFactory optionFactory = MIDataSourceOptionFactory.getInstance();
            MIDataSourceFactory miFactory = MIDataSourceFactory.getInstance();

            switch (fileType){
                case mitab:
                    miDataSource = miFactory.getInteractionSourceWith(optionFactory.getMitabOptions(InteractionObjectCategory.binary_evidence, true, null, dataStream));
                    interactionWriter = new MIJsonBinaryWriter(writer,null);
                    break;
                case psi25_xml:
                    miDataSource = miFactory.getInteractionSourceWith(optionFactory.getXmlOptions(InteractionObjectCategory.binary_evidence, true, dataStream));
                    interactionWriter = new MIJsonWriter(writer, null, this.expansionMethod);
                    break;
                default:
                    dataStream.close();
                    break;
            }

            // then write
            interactionWriter.start();
            interactionWriter.write(miDataSource.getInteractionsIterator());
            interactionWriter.end();
            interactionWriter.flush();
        } finally {
            // close first stream
            if (stream != null){
                try {
                    stream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // close data source
            if (miDataSource != null){
                try {
                    miDataSource.close();
                } catch (MIIOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}