package psidev.psi.mi.jami.xml.io.writer.compact;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Compact PSI-XML writer for abstract interactions (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */

public class CompactXmlModelledWriter extends AbstractCompactXmlWriter<ModelledInteraction> {

    public CompactXmlModelledWriter() {
        super(ModelledInteraction.class);
    }

    public CompactXmlModelledWriter(File file) throws IOException, XMLStreamException {
        super(ModelledInteraction.class, file);
    }

    public CompactXmlModelledWriter(OutputStream output) throws XMLStreamException {
        super(ModelledInteraction.class, output);
    }

    public CompactXmlModelledWriter(Writer writer) throws XMLStreamException {
        super(ModelledInteraction.class, writer);
    }

    public CompactXmlModelledWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(ModelledInteraction.class, streamWriter, cache);
    }

    @Override
    protected void registerAvailabilities(ModelledInteraction interaction) {
        // nothing to do
    }

    @Override
    protected void registerInteractionProperties() {
        super.registerInteractionProperties();
        for (CooperativeEffect effect : getCurrentInteraction().getCooperativeEffects()){
            for (ModelledInteraction interaction : effect.getAffectedInteractions()){
                registerAllInteractorsAndExperimentsFrom(interaction);
            }
        }
    }

    @Override
    protected void registerExperiment(ModelledInteraction interaction) {
        Experiment exp = getInteractionWriter().extractDefaultExperimentFrom(interaction);
        if (exp != null){
            getExperiments().add(exp);
        }
    }

    @Override
    protected Source extractSourceFromInteraction() {
        return getCurrentInteraction().getSource() != null ? getCurrentInteraction().getSource() : super.extractSourceFromInteraction();
    }

    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(false, false, PsiXmlType.compact, InteractionCategory.modelled, ComplexType.n_ary);
    }
}
