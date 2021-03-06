package psidev.psi.mi.jami.model;

import java.util.Collection;

/**
 * Interaction involving one to several molecules supported by experiments.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/12/12</pre>
 */

public interface InteractionEvidence extends Interaction<ParticipantEvidence>{

    /**
     * IMEx identifier if the interaction has been curated following IMEx curation rules.
     * It can be null if the interaction is not registered in IMEx central or does not follow the IMEx curation rules.
     * This imex id should be a shortcut to the imex-primary Xref in the collection of xrefs.
     * Ex: IM-123-2
     * @return the IMEx identifier
     */
    public String getImexId();

    /**
     * Assign an IMEx id to an interaction.
     * It will add the new imex-primary ref to the collection of xrefs
     * @param identifier : the IMEx id from IMEx central
     * @throws IllegalArgumentException if
     * - the identifier is null or empty
     */
    public void assignImexId(String identifier);

    /**
     * The experiment which determined the interaction.
     * It can be null if the interaction evidence is detached from experiment.
     * @return the experiment
     */
    public Experiment getExperiment();

    /**
     * Sets the experiment for this interaction.
     * @param experiment : experiment
     */
    public void setExperiment(Experiment experiment);

    /**
     * Sets the experiment for this interaction and add the interaction to the list of interaction evidences
     * If the given experiment is null, it will remove this interaction evidence from the previous experiment attached to this interaction
     * @param experiment : experiment
     */
    public void setExperimentAndAddInteractionEvidence(Experiment experiment);

    /**
     * The collection of variableParameterValueSet this interaction has been observed with. Each variableParameterValueSet contains
     * a set of variableParameterValues which have been used together.
     * This collection cannot be null. If the interactionEvidence does not have any variableParameterValueSet, this method should return
     * an empty set.
     * @return the collection of variableParameterValueSet
     */
    public <V extends VariableParameterValueSet> Collection<V> getVariableParameterValues();

    /**
     * The availability for this interaction. By default it is null because freely available.
     * Ex: copyrights, ...
     * @return the availability
     */
    public String getAvailability();

    /**
     * Sets the availability.
     * @param availability : availability
     */
    public void setAvailability(String availability);

    /**
     * Collection of numerical parameters for this interaction.
     * The set cannot be null. If the interaction does not have any parameters, the method should return an empty Collection.
     * Ex: IC50, ...
     * @return the parameters
     */
    public <P extends Parameter> Collection<P> getParameters();

    /**
     * Boolean value to know if the interaction is inferred from multiple experiments which on their own would not support the interaction.
     * By default is false.
     * @return true if the interaction is inferred from multiple experiments which on their own would not support the interaction
     */
    public boolean isInferred();

    /**
     * Sets the inferred boolean value
     * @param inferred : inferred boolean value
     */
    public void setInferred(boolean inferred);

    /**
     * The confidences in this interaction.
     * The Collection cannot be null. If the interaction does not have any confidences, the method should return an empty Collection.
     * Ex: author based scores, statistical confidences, ...
     * @return the confidences
     */
    public <C extends Confidence> Collection<C> getConfidences();

    /**
     * Boolean value to know if an interaction is negative.
     * It is false by default (positive interaction by default)
     * @return true if the interaction is negative
     */
    public boolean isNegative();

    /**
     * Sets the negative boolean value.
     * @param negative : negative value
     */
    public void setNegative(boolean negative);
}
