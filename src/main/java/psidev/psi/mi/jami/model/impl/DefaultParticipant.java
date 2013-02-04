package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.comparator.participant.UnambiguousExactParticipantBaseComparator;
import psidev.psi.mi.jami.utils.comparator.participant.UnambiguousExactParticipantComparator;
import psidev.psi.mi.jami.utils.factory.CvTermFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Default implementation for participant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/02/13</pre>
 */

public class DefaultParticipant<I, T extends Interactor, F extends Feature> implements Participant<I, T, F>, Serializable {

    protected I interaction;
    protected T interactor;
    protected CvTerm biologicalRole;
    protected Set<Xref> xrefs;
    protected Set<Annotation> annotations;
    protected Collection<F> features;
    protected Integer stoichiometry;

    public I getInteraction() {
        return this.interaction;
    }

    public void setInteraction(I interaction) {
        if (interaction == null){
            throw new IllegalArgumentException("The interaction cannot be null.");
        }
        this.interaction = interaction;
    }

    public T getInteractor() {
        return this.interactor;
    }

    public void setInteractor(T interactor) {
        if (interactor == null){
            throw new IllegalArgumentException("The interactor cannot be null.");
        }
        this.interactor = interactor;
    }

    public CvTerm getBiologicalRole() {
        return this.biologicalRole;
    }

    public void setBiologicalRole(CvTerm bioRole) {
        if (bioRole == null){
            this.biologicalRole = CvTermFactory.createUnspecifiedRole();
        }
        else {
            biologicalRole = bioRole;
        }
    }

    public Set<Xref> getXrefs() {
        return this.xrefs;
    }

    public Set<Annotation> getAnnotations() {
        return this.annotations;
    }

    public Collection<F> getFeatures() {
        return this.features;
    }

    public Integer getStoichiometry() {
        return this.stoichiometry;
    }

    public void setStoichiometry(Integer stoichiometry) {
        this.stoichiometry = stoichiometry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Participant)){
            return false;
        }

        // use UnambiguousExactParticipant comparator for equals
        return UnambiguousExactParticipantComparator.areEquals(this, (Participant) o);
    }

    @Override
    public String toString() {
        return interactor.toString() + " ( " + biologicalRole.toString() + ")";
    }

    @Override
    public int hashCode() {
        // use UnambiguousExactParticipantBase comparator for hashcode to avoid instance of calls. It is possible that
        // the method equals will return false and the hashcode will be the same but it is not a big issue
        return UnambiguousExactParticipantBaseComparator.hashCode(this);
    }
}
