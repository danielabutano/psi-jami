package psidev.psi.mi.jami.binary.expansion;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.exception.ComplexExpansionException;
import psidev.psi.mi.jami.factory.BinaryInteractionFactory;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.impl.DefaultParticipant;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The matrix Expansion method.
 * 	Complex n-ary data has been expanded to binary using the spoke model.
 * 	This assumes that all molecules in the complex interact with each other.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/06/13</pre>
 */

public class MatrixExpansion extends AbstractMatrixExpansion<Interaction, BinaryInteraction> {

    private InteractionEvidenceMatrixExpansion interactionEvidenceExpansion;
    private ModelledInteractionMatrixExpansion modelledInteractionExpansion;

    public MatrixExpansion(){
        super();
        this.interactionEvidenceExpansion = new InteractionEvidenceMatrixExpansion();
        this.modelledInteractionExpansion = new ModelledInteractionMatrixExpansion();
    }

    @Override
    public Collection<BinaryInteraction> expand(Interaction interaction) throws ComplexExpansionException {

        if (interaction instanceof InteractionEvidence){
            Collection<BinaryInteraction> binaryInteractions = new ArrayList<BinaryInteraction>(interaction.getParticipants().size());
            binaryInteractions.addAll(interactionEvidenceExpansion.expand((InteractionEvidence) interaction));
            return binaryInteractions;
        }
        else if (interaction instanceof ModelledInteraction){
            Collection<BinaryInteraction> binaryInteractions = new ArrayList<BinaryInteraction>(interaction.getParticipants().size());
            binaryInteractions.addAll(modelledInteractionExpansion.expand((ModelledInteraction) interaction));
            return binaryInteractions;
        }
        else {
            return super.expand(interaction);
        }
    }

    @Override
    protected <P extends Participant> BinaryInteraction createBinaryInteraction(Interaction interaction, P c1, P c2) {
        return getBinaryInteractionFactory().createBasicBinaryInteractionFrom(interaction, c1, c2, getMethod());
    }

    @Override
    protected Participant[] createParticipantsArray(Interaction interaction) {
        return (Participant[]) interaction.getParticipants().toArray(new DefaultParticipant[]{});
    }

    @Override
    public void setBinaryInteractionFactory(BinaryInteractionFactory factory) {
        super.setBinaryInteractionFactory(factory);
        this.interactionEvidenceExpansion.setBinaryInteractionFactory(factory);
        this.modelledInteractionExpansion.setBinaryInteractionFactory(factory);
    }
}
