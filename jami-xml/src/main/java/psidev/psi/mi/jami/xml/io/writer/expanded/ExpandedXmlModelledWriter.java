package psidev.psi.mi.jami.xml.io.writer.expanded;

import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Expanded PSI-XML writer for abstract interactions (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */

public class ExpandedXmlModelledWriter extends AbstractExpandedXmlWriter<ModelledInteraction> {

    public ExpandedXmlModelledWriter() {
        super(ModelledInteraction.class);
    }

    public ExpandedXmlModelledWriter(File file) throws IOException, XMLStreamException {
        super(ModelledInteraction.class, file);
    }

    public ExpandedXmlModelledWriter(OutputStream output) throws XMLStreamException {
        super(ModelledInteraction.class, output);
    }

    public ExpandedXmlModelledWriter(Writer writer) throws XMLStreamException {
        super(ModelledInteraction.class, writer);
    }

    public ExpandedXmlModelledWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(ModelledInteraction.class, streamWriter, cache);
    }

    @Override
    protected Source extractSourceFromInteraction() {
        return getCurrentInteraction().getSource() != null ? getCurrentInteraction().getSource() : super.extractSourceFromInteraction();
    }

    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(false, false, PsiXmlType.expanded, InteractionCategory.modelled, ComplexType.n_ary);
    }
}
