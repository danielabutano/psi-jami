package psidev.psi.mi.jami.binary.expansion;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.exception.ComplexExpansionException;
import psidev.psi.mi.jami.factory.BinaryInteractionFactory;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;

import java.util.Collection;

/**
 * The method by which complex n-ary data is expanded into binary data. This may be performed manually on data input, or computationally on data export.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/06/13</pre>
 */

public interface ComplexExpansionMethod<T extends Interaction, B extends BinaryInteraction> {

    public static final String BIPARTITE_EXPANSION_MI = "MI:1062";
    public static final String BIPARTITE_EXPANSION = "bipartite expansion";
    public static final String BIPARTITE = "bipartite";
    public static final String SPOKE_EXPANSION_MI = "MI:1060";
    public static final String SPOKE_EXPANSION = "spoke expansion";
    public static final String SPOKE = "spoke";
    public static final String MATRIX_EXPANSION_MI = "MI:1061";
    public static final String MATRIX_EXPANSION = "matrix expansion";
    public static final String MATRIX = "matrix expansion";

    /**
     * The method represented by the ComplexExpansionMethod object.
     * It is a controlled vocabulary term and cannot be null.
     * @return the complex expansion method
     */
    public CvTerm getMethod();

    /**
     * Method to know if this ComplexExpansionMethod can expand the given Interaction
     * @param interaction
     * @return true if this interaction can be expanded with this ComplexExpansionMethod
     */
    public boolean isInteractionExpandable(T interaction);

    /**
     * Expand the interaction in a collection of BinaryInteraction.
     * The collection cannot be null.
     * @param interaction
     * @return collection of binary interactions expanded from the original interaction object
     * @throws psidev.psi.mi.jami.exception.ComplexExpansionException if the interaction cannot be expanded with this method
     */
    public Collection<B> expand(T interaction) throws ComplexExpansionException;

    /**
     *
     * @return the factory used to create new BinaryInteractions
     */
    public BinaryInteractionFactory getBinaryInteractionFactory();

    /**
     * Sets the binary interaction factory
     * @param factory
     */
    public void setBinaryInteractionFactory(BinaryInteractionFactory factory);
}
