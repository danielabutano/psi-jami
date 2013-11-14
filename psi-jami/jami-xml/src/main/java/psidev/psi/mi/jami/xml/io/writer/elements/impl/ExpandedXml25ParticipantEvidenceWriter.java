package psidev.psi.mi.jami.xml.io.writer.elements.impl;

import org.codehaus.stax2.XMLStreamWriter2;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXml25ObjectIndex;
import psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXml25ElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXml25ElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXml25XrefWriter;

import javax.xml.stream.XMLStreamException;

/**
 * Expanded XML 2.5 writer for a participant evidence with full experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */

public class ExpandedXml25ParticipantEvidenceWriter extends AbstractXml25ParticipantEvidenceWriter implements ExpandedPsiXml25ElementWriter<ParticipantEvidence> {
    public ExpandedXml25ParticipantEvidenceWriter(XMLStreamWriter2 writer, PsiXml25ObjectIndex objectIndex) {
        super(writer, objectIndex, new Xml25FeatureEvidenceWriter(writer, objectIndex));
    }

    public ExpandedXml25ParticipantEvidenceWriter(XMLStreamWriter2 writer, PsiXml25ObjectIndex objectIndex, PsiXml25ElementWriter<Alias> aliasWriter, PsiXml25XrefWriter primaryRefWriter, PsiXml25XrefWriter secondaryRefWriter, PsiXml25ElementWriter<CvTerm> biologicalRoleWriter, PsiXml25ElementWriter<FeatureEvidence> featureWriter, PsiXml25ElementWriter<Annotation> attributeWriter, PsiXml25ElementWriter<Interactor> interactorWriter) {
        super(writer, objectIndex, aliasWriter, primaryRefWriter, secondaryRefWriter, biologicalRoleWriter, featureWriter != null ? featureWriter : new Xml25FeatureEvidenceWriter(writer, objectIndex), attributeWriter, interactorWriter);
    }

    @Override
    protected void writeMolecule(Interactor interactor) throws XMLStreamException {
        super.writeMoleculeRef(interactor);
    }
}
