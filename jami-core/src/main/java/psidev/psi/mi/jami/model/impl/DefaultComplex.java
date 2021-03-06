package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.ChecksumUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Default implementation for complexes
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Complex object is a complex object.
 * To compare Complex objects, you can use some comparators provided by default:
 * - DefaultComplexComparator
 * - UnambiguousComplexComparator
 * - DefaultExactComplexComparator
 * - UnambiguousExactComplexComparator
 * - ComplexComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/02/13</pre>
 */

public class DefaultComplex extends DefaultInteractor implements Complex {

    private Collection<InteractionEvidence> interactionEvidences;
    private Collection<ModelledParticipant> components;
    private Annotation physicalProperties;
    private Collection<ModelledConfidence> confidences;
    private Collection<ModelledParameter> parameters;

    private Source source;
    private Collection<CooperativeEffect> cooperativeEffects;
    private Checksum rigid;
    private Date updatedDate;
    private Date createdDate;
    private CvTerm interactionType;

    private Alias recommendedName;
    private Alias systematicName;

    private CvTerm evidenceType;

    public DefaultComplex(String name, CvTerm interactorType) {
        super(name, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType());
    }

    public DefaultComplex(String name, String fullName, CvTerm interactorType) {
        super(name, fullName, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType());
    }

    public DefaultComplex(String name, CvTerm interactorType, Organism organism) {
        super(name, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), organism);
    }

    public DefaultComplex(String name, String fullName, CvTerm interactorType, Organism organism) {
        super(name, fullName, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), organism);
    }

    public DefaultComplex(String name, CvTerm interactorType, Xref uniqueId) {
        super(name, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), uniqueId);
    }

    public DefaultComplex(String name, String fullName, CvTerm interactorType, Xref uniqueId) {
        super(name, fullName, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), uniqueId);
    }

    public DefaultComplex(String name, CvTerm interactorType, Organism organism, Xref uniqueId) {
        super(name, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), organism, uniqueId);
    }

    public DefaultComplex(String name, String fullName, CvTerm interactorType, Organism organism, Xref uniqueId) {
        super(name, fullName, interactorType != null ? interactorType : CvTermUtils.createComplexInteractorType(), organism, uniqueId);
    }

    public DefaultComplex(String name) {
        super(name, CvTermUtils.createComplexInteractorType());
    }

    public DefaultComplex(String name, String fullName) {
        super(name, fullName, CvTermUtils.createComplexInteractorType());
    }

    public DefaultComplex(String name, Organism organism) {
        super(name, CvTermUtils.createComplexInteractorType(), organism);
    }

    public DefaultComplex(String name, String fullName, Organism organism) {
        super(name, fullName, CvTermUtils.createComplexInteractorType(), organism);
    }

    public DefaultComplex(String name, Xref uniqueId) {
        super(name, CvTermUtils.createComplexInteractorType(), uniqueId);
    }

    public DefaultComplex(String name, String fullName, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createComplexInteractorType(), uniqueId);
    }

    public DefaultComplex(String name, Organism organism, Xref uniqueId) {
        super(name, CvTermUtils.createComplexInteractorType(), organism, uniqueId);
    }

    public DefaultComplex(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createComplexInteractorType(), organism, uniqueId);
    }

    @Override
    protected void initialiseAliases() {
        initialiseAliasesWith(new ComplexAliasList());
    }

    protected void initialiseInteractionEvidences(){
        this.interactionEvidences = new ArrayList<InteractionEvidence>();
    }

    protected void initialiseInteractionEvidencesWith(Collection<InteractionEvidence> interactionEvidences){
        if (interactionEvidences == null){
            this.interactionEvidences = Collections.EMPTY_LIST;
        }
        else{
            this.interactionEvidences = interactionEvidences;
        }
    }

    protected void initialiseCooperativeEffects(){
        this.cooperativeEffects = new ArrayList<CooperativeEffect>();
    }

    protected void initialiseCooperativeEffectsWith(Collection<CooperativeEffect> cooperativeEffects){
        if (cooperativeEffects == null){
            this.cooperativeEffects = Collections.EMPTY_LIST;
        }
        else{
            this.cooperativeEffects = cooperativeEffects;
        }
    }

    protected void initialiseConfidences(){
        this.confidences = new ArrayList<ModelledConfidence>();
    }

    protected void initialiseConfidencesWith(Collection<ModelledConfidence> confidences){
        if (confidences == null){
            this.confidences = Collections.EMPTY_LIST;
        }
        else {
            this.confidences = confidences;
        }
    }

    protected void initialiseParameters(){
        this.parameters = new ArrayList<ModelledParameter>();
    }

    protected void initialiseParametersWith(Collection<ModelledParameter> parameters){
        if (parameters == null){
            this.parameters = Collections.EMPTY_LIST;
        }
        else {
            this.parameters = parameters;
        }
    }

    protected void initialiseComponents(){
        this.components = new ArrayList<ModelledParticipant>();
    }

    protected void initialiseComponentsWith(Collection<ModelledParticipant> components){
        if (components == null){
            this.components = Collections.EMPTY_LIST;
        }
        else{
            this.components = components;
        }
    }

    @Override
    protected void initialiseAnnotations() {
        initialiseAnnotationsWith(new ComplexAnnotationList());
    }

    public Collection<InteractionEvidence> getInteractionEvidences() {
        if (interactionEvidences == null){
            initialiseInteractionEvidences();
        }
        return this.interactionEvidences;
    }

    @Override
    protected void initialiseChecksums(){
        initialiseChecksumsWith(new ComplexChecksumList());
    }

    public Source getSource() {
        return this.source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Collection<ModelledParticipant> getParticipants() {
        if (components == null){
            initialiseComponents();
        }
        return this.components;
    }

    public boolean addParticipant(ModelledParticipant part) {
        if (part == null){
            return false;
        }
        if (components == null){
            initialiseComponents();
        }
        part.setInteraction(this);
        return components.add(part);
    }

    public boolean removeParticipant(ModelledParticipant part) {
        if (part == null){
            return false;
        }
        if (components == null){
            initialiseComponents();
        }
        part.setInteraction(null);
        if (components.remove(part)){
            return true;
        }
        return false;
    }

    public boolean addAllParticipants(Collection<? extends ModelledParticipant> participants) {
        if (participants == null){
            return false;
        }

        boolean added = false;
        for (ModelledParticipant p : participants){
            if (addParticipant(p)){
                added = true;
            }
        }
        return added;
    }

    public boolean removeAllParticipants(Collection<? extends ModelledParticipant> participants) {
        if (participants == null){
            return false;
        }

        boolean removed = false;
        for (ModelledParticipant p : participants){
            if (removeParticipant(p)){
                removed = true;
            }
        }
        return removed;
    }

    public Collection<ModelledConfidence> getModelledConfidences() {
        if (confidences == null){
            initialiseConfidences();
        }
        return this.confidences;
    }

    public Collection<ModelledParameter> getModelledParameters() {
        if (parameters == null){
            initialiseParameters();
        }
        return this.parameters;
    }

    public Collection<CooperativeEffect> getCooperativeEffects() {
        if (cooperativeEffects == null){
            initialiseCooperativeEffects();
        }
        return this.cooperativeEffects;
    }

    public String getPhysicalProperties() {
        return this.physicalProperties != null ? this.physicalProperties.getValue() : null;
    }

    public void setPhysicalProperties(String properties) {
        ComplexAnnotationList complexAnnotationList = (ComplexAnnotationList)getAnnotations();

        // add new physical properties if not null
        if (properties != null){

            CvTerm complexPhysicalProperties = CvTermUtils.createComplexPhysicalProperties();
            // first remove old physical property if not null
            if (this.physicalProperties != null){
                complexAnnotationList.removeOnly(this.physicalProperties);
            }
            this.physicalProperties = new DefaultAnnotation(complexPhysicalProperties, properties);
            complexAnnotationList.addOnly(this.physicalProperties);
        }
        // remove all physical properties if the collection is not empty
        else if (!complexAnnotationList.isEmpty()) {
            AnnotationUtils.removeAllAnnotationsWithTopic(complexAnnotationList, Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
            physicalProperties = null;
        }
    }

    public String getRecommendedName() {
        return this.recommendedName != null ? this.recommendedName.getName() : null;
    }

    public void setRecommendedName(String name) {
        ComplexAliasList complexAliasList = (ComplexAliasList)getAliases();

        // add new recommended name if not null
        if (name != null){

            CvTerm recommendedName = CvTermUtils.createComplexRecommendedName();
            // first remove old recommended name if not null
            if (this.recommendedName != null){
                complexAliasList.removeOnly(this.recommendedName);
            }
            this.recommendedName = new DefaultAlias(recommendedName, name);
            complexAliasList.addOnly(this.recommendedName);
        }
        // remove all recommended name if the collection is not empty
        else if (!complexAliasList.isEmpty()) {
            AliasUtils.removeAllAliasesWithType(complexAliasList, Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
            recommendedName = null;
        }
    }

    public String getSystematicName() {
        return this.systematicName != null ? this.systematicName.getName() : null;
    }

    public void setSystematicName(String name) {
        ComplexAliasList complexAliasList = (ComplexAliasList)getAliases();

        // add new systematic name if not null
        if (name != null){

            CvTerm systematicName = CvTermUtils.createComplexSystematicName();
            // first remove systematic name  if not null
            if (this.systematicName != null){
                complexAliasList.removeOnly(this.systematicName);
            }
            this.systematicName = new DefaultAlias(systematicName, name);
            complexAliasList.addOnly(this.systematicName);
        }
        // remove all systematic name  if the collection is not empty
        else if (!complexAliasList.isEmpty()) {
            AliasUtils.removeAllAliasesWithType(complexAliasList, Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
            systematicName = null;
        }
    }

    public CvTerm getEvidenceType() {
        return this.evidenceType;
    }

    public void setEvidenceType(CvTerm eco) {
        this.evidenceType = eco;
    }

    protected void processAddedAnnotationEvent(Annotation added) {
        if (physicalProperties == null && AnnotationUtils.doesAnnotationHaveTopic(added, Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES)){
            physicalProperties = added;
        }
    }

    protected void processRemovedAnnotationEvent(Annotation removed) {
        if (physicalProperties != null && physicalProperties.equals(removed)){
            physicalProperties = AnnotationUtils.collectFirstAnnotationWithTopic(getAnnotations(), Annotation.COMPLEX_PROPERTIES_MI, Annotation.COMPLEX_PROPERTIES);
        }
    }

    protected void clearPropertiesLinkedToAnnotations() {
        physicalProperties = null;
    }

    @Override
    /**
     * Sets the interactor type for this complex.
     * If the given interactorType is null, it will set the interactor type to 'complex' (MI:0314)
     */
    public void setInteractorType(CvTerm interactorType) {
        if (interactorType == null){
           super.setInteractorType(CvTermUtils.createComplexInteractorType());
        }
        else{
            super.setInteractorType(interactorType);
        }
    }

    public String getRigid() {
        return this.rigid != null ? this.rigid.getValue() : null;
    }

    public void setRigid(String rigid) {
        Collection<Checksum> checksums = getChecksums();
        if (rigid != null){
            CvTerm rigidMethod = CvTermUtils.createRigid();
            // first remove old rigid
            if (this.rigid != null){
                checksums.remove(this.rigid);
            }
            this.rigid = new DefaultChecksum(rigidMethod, rigid);
            checksums.add(this.rigid);
        }
        // remove all smiles if the collection is not empty
        else if (!checksums.isEmpty()) {
            ChecksumUtils.removeAllChecksumWithMethod(checksums, Checksum.RIGID_MI, Checksum.RIGID);
            this.rigid = null;
        }
    }

    public Date getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(Date updated) {
        this.updatedDate = updated;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date created) {
        this.createdDate = created;
    }

    public CvTerm getInteractionType() {
        return this.interactionType;
    }

    public void setInteractionType(CvTerm term) {
        this.interactionType = term;
    }

    public Collection<Annotation> getAnnotations() {
        return super.getAnnotations();
    }

    public Collection<Checksum> getChecksums() {
        return super.getChecksums();
    }

    public Collection<Xref> getXrefs() {
        return super.getXrefs();
    }

    public Collection<Xref> getIdentifiers() {
        return super.getIdentifiers();
    }

    public Collection<Alias> getAliases() {
        return super.getAliases();
    }

    @Override
    public String toString() {
        return "Complex: "+super.toString();
    }

    protected void processAddedChecksumEvent(Checksum added) {
        if (rigid == null && ChecksumUtils.doesChecksumHaveMethod(added, Checksum.RIGID_MI, Checksum.RIGID)){
            // the rigid is not set, we can set the rigid
            rigid = added;
        }
    }

    protected void processRemovedChecksumEvent(Checksum removed) {
        if (rigid == removed){
            rigid = ChecksumUtils.collectFirstChecksumWithMethod(getChecksums(), Checksum.RIGID_MI, Checksum.RIGID);
        }
    }

    protected void clearPropertiesLinkedToChecksums() {
        rigid = null;
    }

    protected void processAddedAliasEvent(Alias added) {
        if (recommendedName == null && AliasUtils.doesAliasHaveType(added, Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME)){
            recommendedName = added;
        }
        else if (systematicName == null && AliasUtils.doesAliasHaveType(added, Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME)){
            systematicName = added;
        }
    }

    protected void processRemovedAliasEvent(Alias removed) {
        if (recommendedName != null && recommendedName.equals(removed)){
            recommendedName = AliasUtils.collectFirstAliasWithType(getAliases(), Alias.COMPLEX_RECOMMENDED_NAME_MI, Alias.COMPLEX_RECOMMENDED_NAME);
        }
        else if (systematicName != null && systematicName.equals(removed)){
            systematicName = AliasUtils.collectFirstAliasWithType(getAliases(), Alias.COMPLEX_SYSTEMATIC_NAME_MI, Alias.COMPLEX_SYSTEMATIC_NAME);
        }
    }

    protected void clearPropertiesLinkedToAliases() {
        this.recommendedName = null;
        this.systematicName = null;
    }

    private class ComplexAnnotationList extends AbstractListHavingProperties<Annotation> {
        public ComplexAnnotationList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Annotation added) {
            processAddedAnnotationEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Annotation removed) {
            processRemovedAnnotationEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToAnnotations();
        }
    }

    private class ComplexChecksumList extends AbstractListHavingProperties<Checksum> {
        public ComplexChecksumList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Checksum added) {
            processAddedChecksumEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Checksum removed) {
            processRemovedChecksumEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToChecksums();
        }
    }

    private class ComplexAliasList extends AbstractListHavingProperties<Alias> {
        public ComplexAliasList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Alias added) {
            processAddedAliasEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Alias removed) {
            processRemovedAliasEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToAliases();
        }
    }
}
