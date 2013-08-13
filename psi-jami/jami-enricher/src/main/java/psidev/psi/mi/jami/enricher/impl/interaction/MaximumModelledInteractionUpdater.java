package psidev.psi.mi.jami.enricher.impl.interaction;


import psidev.psi.mi.jami.enricher.ModelledInteractionEnricher;
import psidev.psi.mi.jami.model.*;

/**
 * An extension of the interaction enricher which only accepts ModelledInteraction
 * Overrides the default ParticipantEnricher to the modelled only form.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 09/07/13
 */
public class MaximumModelledInteractionUpdater
        extends MaximumInteractionUpdater<ModelledInteraction, ModelledParticipant, ModelledFeature>
        implements ModelledInteractionEnricher {

}