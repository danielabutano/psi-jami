package psidev.psi.mi.jami.xml.io.writer.elements.impl;

import org.codehaus.stax2.XMLStreamWriter2;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXml25ObjectIndex;
import psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXml25ElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXml25ElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXml25XrefWriter;

import javax.xml.stream.XMLStreamException;

/**
 * Expanded XML 2.5 writer for a named modelled participant having a fullname and a shortname.
 * It ignores experimental details.
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */

public class ExpandedXml25NamedModelledParticipantWriter  extends AbstractXml25ParticipantWriter<ModelledParticipant, ModelledFeature> implements ExpandedPsiXml25ElementWriter<ModelledParticipant> {
    public ExpandedXml25NamedModelledParticipantWriter(XMLStreamWriter2 writer, PsiXml25ObjectIndex objectIndex) {
        super(writer, objectIndex, new Xml25ModelledFeatureWriter(writer, objectIndex));
    }

    public ExpandedXml25NamedModelledParticipantWriter(XMLStreamWriter2 writer, PsiXml25ObjectIndex objectIndex, PsiXml25ElementWriter<Alias> aliasWriter, PsiXml25XrefWriter primaryRefWriter, PsiXml25XrefWriter secondaryRefWriter, PsiXml25ElementWriter<CvTerm> biologicalRoleWriter, PsiXml25ElementWriter<ModelledFeature> featureWriter, PsiXml25ElementWriter<Annotation> attributeWriter, PsiXml25ElementWriter<Interactor> interactorWriter) {
        super(writer, objectIndex, aliasWriter, primaryRefWriter, secondaryRefWriter, biologicalRoleWriter, featureWriter, attributeWriter, interactorWriter);
    }

    @Override
    protected void writeMolecule(Interactor interactor) throws XMLStreamException {
        super.writeMoleculeDescription(interactor);
    }

    @Override
    protected void writeExperimentalPreparations(ModelledParticipant object) {
        // nothing to do
    }

    @Override
    protected void writeExperimentalRoles(ModelledParticipant object) {
        // nothing to do
    }

    @Override
    protected void writeParticipantIdentificationMethods(ModelledParticipant object) {
        // nothing to do
    }

    @Override
    protected void writeExperimentalInteractor(ModelledParticipant object) {
        // nothing to do
    }

    @Override
    protected void writeHostOrganisms(ModelledParticipant object) {
        // nothing to do
    }

    @Override
    protected void writeConfidences(ModelledParticipant object) {
        // nothing to do
    }

    @Override
    protected void writeParameters(ModelledParticipant object) {
        // nothing to do
    }
}
