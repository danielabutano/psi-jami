package psidev.psi.mi.jami.enricher.listener.impl;

import psidev.psi.mi.jami.enricher.listener.CvTermEnricherListener;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;

/**
 * A manager for listeners which holds a list of listeners.
 * Listener manager allows enrichers to send events to multiple listeners.
 * A listener itself, it implements all methods
 * which will then fire the corresponding method in each entry of the listener list.
 * No contract is given to the order in which the listeners are fired.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 08/07/13
 */
public class CvTermEnricherListenerManager<C extends CvTerm>
    extends EnricherListenerManager<C, CvTermEnricherListener<C>>
    implements CvTermEnricherListener<C>{

    /**
     * A constructor to create a listener manager with no listeners.
     */
    public CvTermEnricherListenerManager(){}

    /**
     * A constructor to initiate a listener manager with as many listeners as required.
     * @param listeners     The listeners to add.
     */
    public CvTermEnricherListenerManager(CvTermEnricherListener<C>... listeners){
        super(listeners);
    }

    //=============================================================================================================

    public void onShortNameUpdate(CvTerm cv, String oldShortName) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onShortNameUpdate( cv,  oldShortName);
        }
    }

    public void onFullNameUpdate(CvTerm cv, String oldFullName) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onFullNameUpdate( cv,  oldFullName);
        }
    }

    public void onMIIdentifierUpdate(CvTerm cv, String oldMI) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onMIIdentifierUpdate( cv,  oldMI) ;
        }
    }

    public void onMODIdentifierUpdate(CvTerm cv, String oldMOD) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onMODIdentifierUpdate(cv, oldMOD);
        }
    }

    public void onPARIdentifierUpdate(CvTerm cv, String oldPAR) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onPARIdentifierUpdate(cv, oldPAR);
        }
    }

    public void onAddedIdentifier(CvTerm cv, Xref added) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onAddedIdentifier(cv, added);
        }
    }

    public void onRemovedIdentifier(CvTerm cv, Xref removed) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onRemovedIdentifier(cv, removed);
        }
    }

    public void onAddedXref(CvTerm cv, Xref added) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onAddedXref(cv, added);
        }
    }

    public void onRemovedXref(CvTerm cv, Xref removed) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onRemovedXref(cv, removed);
        }
    }

    public void onAddedAlias(CvTerm cv, Alias added) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onRemovedAlias(cv, added);
        }
    }

    public void onRemovedAlias(CvTerm o, Alias removed) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onRemovedAlias(o, removed);
        }
    }

    public void onAddedAnnotation(CvTerm o, Annotation added) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onAddedAnnotation(o, added);
        }
    }

    public void onRemovedAnnotation(CvTerm o, Annotation removed) {
        for(CvTermEnricherListener listener : getListenersList()){
            listener.onRemovedAnnotation(o, removed);
        }
    }
}
