package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultCooperativeEffect;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.reference.AbstractComplexRef;
import psidev.psi.mi.jami.xml.model.reference.AbstractEntityRef;

/**
 * XML implementation of Allostery
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */

public class XmlAllostery<T extends AllostericEffector> extends DefaultCooperativeEffect implements FileSourceContext, Allostery<T> {
    private PsiXmlLocator sourceLocator;
    private CvTerm allostericMechanism;
    private CvTerm allosteryType;
    private ModelledEntity allostericMolecule;
    private T allostericEffector;

    public XmlAllostery(CvTerm outcome) {
        super(outcome);
    }

    public XmlAllostery(CvTerm outcome, CvTerm response) {
        super(outcome, response);
    }

    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    public void setSourceLocator(FileSourceLocator locator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    public void setSourceLocator(PsiXmlLocator locator) {
        this.sourceLocator = locator;
    }

    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Allostery: "+getSourceLocator().toString():super.toString());
    }

    public CvTerm getAllostericMechanism() {
        return this.allostericMechanism;
    }

    public void setAllostericMechanism(CvTerm mechanism) {
        this.allostericMechanism = mechanism;
    }

    public CvTerm getAllosteryType() {
        return this.allosteryType;
    }

    public void setAllosteryType(CvTerm type) {
        this.allosteryType = type;
    }

    public ModelledEntity getAllostericMolecule() {
        return this.allostericMolecule;
    }

    public void setAllostericMolecule(ModelledEntity participant) {
        if (participant == null){
            throw new IllegalArgumentException("The allosteric molecule cannot be null");
        }
        this.allostericMolecule = participant;
    }

    public T getAllostericEffector() {
        return this.allostericEffector;
    }

    public void setAllostericEffector(T effector) {
        if (effector == null){
            throw new IllegalArgumentException("The allosteric effector cannot be null");
        }
        this.allostericEffector = effector;
    }

    public void addAffectedInteractionRef(int affectedInteraction, PsiXmlLocator locator){
        getAffectedInteractions().add(new ModelledInteractionRef(affectedInteraction, locator));
    }

    public void setAllostericMoleculeRef(int ref, PsiXmlLocator locator){
       this.allostericMolecule = new AllostericMoleculeRef(ref, locator);
    }

    public void setAllostericPTMRef(int ref, PsiXmlLocator locator){
        this.allostericEffector = (T)new XmlFeatureModificationEffector(ref, locator);
    }

    public void setAllostericEffectorRef(int ref, PsiXmlLocator locator){
        this.allostericEffector = (T)new XmlMoleculeEffector(ref, locator);
    }

    ////////////////////////////////// inner classes

    /**
     * participant ref for allosteric molecule
     */
    private class AllostericMoleculeRef extends AbstractEntityRef<ModelledFeature> implements ModelledEntity{
        private PsiXmlLocator sourceLocator;

        public AllostericMoleculeRef(int ref, PsiXmlLocator locator) {
            super(ref);
            this.sourceLocator = locator;
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            // have a complex participant, load it
            if (parsedObjects.containsComplexParticipant(this.ref)){
                ModelledEntity object = parsedObjects.getComplexParticipant(this.ref);
                if (object == null){
                    return false;
                }
                // use complex participant
                else {
                    setAllostericMolecule(object);
                    return true;
                }
            }
            // have a participant evidence, load the interaction as complex and then set participant
            else if (parsedObjects.containsParticipant(this.ref)){
                Entity object = parsedObjects.getParticipant(this.ref);
                if (object == null){
                    return false;
                }
                // convert participant evidence in a modelled participant and load previous complex
                else {
                    ModelledEntity reloadedObject = parsedObjects.registerModelledParticipantLoadedFrom(object);
                    if (reloadedObject != null){
                        setAllostericMolecule(reloadedObject);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Allosteric molecule Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
        }

        @Override
        protected void initialiseParticipantDelegate() {
            XmlModelledParticipant modelled = new XmlModelledParticipant();
            modelled.setId(this.ref);
            setDelegate(modelled);
        }

        public FileSourceLocator getSourceLocator() {
            return this.sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        public void setSourceLocator(PsiXmlLocator sourceLocator) {
            this.sourceLocator = sourceLocator;
        }
    }

    /**
     * interaction ref for affected cooperative interaction
     */
    private class ModelledInteractionRef extends AbstractComplexRef {
        private PsiXmlLocator sourceLocator;

        public ModelledInteractionRef(int ref, PsiXmlLocator locator) {
            super(ref);
            this.sourceLocator = locator;
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsComplex(this.ref)){
                Complex c = parsedObjects.getComplex(this.ref);
                if (c != null){
                    getAffectedInteractions().remove(this);
                    getAffectedInteractions().add(c);
                    return true;
                }
            }
            else if (parsedObjects.containsInteraction(this.ref)){
                Interaction object = parsedObjects.getInteraction(this.ref);
                if (object != null){
                    ModelledInteraction reloadedComplex = parsedObjects.registerComplexLoadedFrom(object);
                    if (reloadedComplex != null){
                        getAffectedInteractions().remove(this);
                        getAffectedInteractions().add(reloadedComplex);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Affected modelled interaction Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
        }

        public FileSourceLocator getSourceLocator() {
            return this.sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        public void setSourceLocator(PsiXmlLocator sourceLocator) {
            this.sourceLocator = sourceLocator;
        }
    }
}
