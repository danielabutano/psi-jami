package psidev.psi.mi.jami.binary.expansion;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.model.impl.DefaultParticipantEvidence;
import psidev.psi.mi.jami.utils.InteractionUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * Matrix expansion for InteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/06/13</pre>
 */

public class InteractionEvidenceMatrixExpansion extends AbstractMatrixExpansion<InteractionEvidence, BinaryInteractionEvidence>{

    @Override
    protected Collection<BinaryInteractionEvidence> createNewSelfBinaryInteractionsFrom(InteractionEvidence interaction) {
        return Collections.singletonList(getBinaryInteractionFactory().createSelfBinaryInteractionEvidenceFrom(interaction));
    }

    @Override
    protected Collection<BinaryInteractionEvidence> createBinaryInteractionWrappersFrom(InteractionEvidence interaction) {
        return Collections.singletonList(getBinaryInteractionFactory().createBinaryInteractionEvidenceWrapperFrom(interaction));
    }

    @Override
    protected ComplexType findInteractionCategory(InteractionEvidence interaction) {
        return InteractionUtils.findInteractionEvidenceCategoryOf(interaction);
    }

    @Override
    protected <P extends Participant> BinaryInteractionEvidence createBinaryInteraction(InteractionEvidence interaction, P c1, P c2) {
        return getBinaryInteractionFactory().createBinaryInteractionEvidenceFrom(interaction, (ParticipantEvidence)c1, (ParticipantEvidence)c2, getMethod());
    }

    @Override
    protected ParticipantEvidence[] createParticipantsArray(InteractionEvidence interaction) {
        return interaction.getParticipants().toArray(new DefaultParticipantEvidence[]{});
    }
}
