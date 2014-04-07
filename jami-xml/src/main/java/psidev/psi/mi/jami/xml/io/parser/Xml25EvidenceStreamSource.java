package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.datasource.InteractionEvidenceStream;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.xml.io.iterator.Xml25InteractionEvidenceIterator;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;

/**
 * Datasource for Psi-xml 2.5 interaction evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */

public class Xml25EvidenceStreamSource extends AbstractPsiXml25Stream<InteractionEvidence> implements InteractionEvidenceStream<InteractionEvidence>{
    public Xml25EvidenceStreamSource() {
    }

    public Xml25EvidenceStreamSource(File file) {
        super(file);
    }

    public Xml25EvidenceStreamSource(InputStream input) {
        super(input);
    }

    public Xml25EvidenceStreamSource(Reader reader) {
        super(reader);
    }

    public Xml25EvidenceStreamSource(URL url) {
        super(url);
    }

    @Override
    protected void initialiseXmlParser(Reader reader) {
        Xml25EvidenceParser parser = new Xml25EvidenceParser(reader);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    @Override
    protected void initialiseXmlParser(File file) {
        Xml25EvidenceParser parser = new Xml25EvidenceParser(file);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    @Override
    protected void initialiseXmlParser(InputStream input) {
        Xml25EvidenceParser parser = new Xml25EvidenceParser(input);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    @Override
    protected void initialiseXmlParser(URL url) {
        Xml25EvidenceParser parser = new Xml25EvidenceParser(url);
        parser.setListener(this);
        parser.setCacheOfObjects(getElementCache());
        setParser(parser);
    }

    @Override
    protected void initialiseExpansionMethod(ComplexExpansionMethod<? extends Interaction, ? extends BinaryInteraction> expansionMethod) {
        // nothing to do as we don't expand
    }

    @Override
    protected Iterator<InteractionEvidence> createXmlIterator() {
        return new Xml25InteractionEvidenceIterator(getParser());
    }
}