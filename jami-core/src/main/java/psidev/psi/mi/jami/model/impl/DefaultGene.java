package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

/**
 * Default implementation for gene
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Gene object is a complex object.
 * To compare Gene objects, you can use some comparators provided by default:
 * - DefaultGeneComparator
 * - UnambiguousGeneComparator
 * - DefaultExactGeneComparator
 * - UnambiguousExactGeneComparator
 * - GeneComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/01/13</pre>
 */

public class DefaultGene extends DefaultMolecule implements Gene {

    private Xref ensembl;
    private Xref ensemblGenome;
    private Xref entrezGeneId;
    private Xref refseq;

    public DefaultGene(String name) {
        super(name, CvTermUtils.createGeneInteractorType());
    }

    public DefaultGene(String name, String fullName) {
        super(name, fullName, CvTermUtils.createGeneInteractorType());
    }

    public DefaultGene(String name, Organism organism) {
        super(name, CvTermUtils.createGeneInteractorType(), organism);
    }

    public DefaultGene(String name, String fullName, Organism organism) {
        super(name, fullName, CvTermUtils.createGeneInteractorType(), organism);
    }

    public DefaultGene(String name, Xref uniqueId) {
        super(name, CvTermUtils.createGeneInteractorType(), uniqueId);
    }

    public DefaultGene(String name, String fullName, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createGeneInteractorType(), uniqueId);
    }

    public DefaultGene(String name, Organism organism, Xref uniqueId) {
        super(name, CvTermUtils.createGeneInteractorType(), organism, uniqueId);
    }

    public DefaultGene(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createGeneInteractorType(), organism, uniqueId);
    }

    public DefaultGene(String name, String fullName, String ensembl) {
        super(name, fullName, CvTermUtils.createGeneInteractorType());

        if (ensembl != null){
            setEnsembl(ensembl);
        }
    }

    public DefaultGene(String name, CvTerm type, Xref ensembl) {
        super(name, type != null ? type : CvTermUtils.createGeneInteractorType());
        this.ensembl = ensembl;
    }

    public DefaultGene(String name, String fullName, CvTerm type, Xref ensembl) {
        super(name, fullName, type != null ? type : CvTermUtils.createGeneInteractorType());
        this.ensembl = ensembl;
    }

    public DefaultGene(String name, CvTerm type, Organism organism, Xref ensembl) {
        super(name, type != null ? type : CvTermUtils.createGeneInteractorType(), organism);
        this.ensembl = ensembl;
    }

    public DefaultGene(String name, String fullName, CvTerm type, Organism organism, Xref ensembl) {
        super(name, fullName, type != null ? type : CvTermUtils.createGeneInteractorType(), organism);
        this.ensembl = ensembl;
    }

    public DefaultGene(String name, CvTerm type, Xref uniqueId, Xref ensembl) {
        super(name, type != null ? type : CvTermUtils.createGeneInteractorType(), uniqueId);
        this.ensembl = ensembl;
    }

    public DefaultGene(String name, String fullName, CvTerm type, Xref uniqueId, Xref ensembl) {
        super(name, fullName, type != null ? type : CvTermUtils.createGeneInteractorType(), uniqueId);
        this.ensembl = ensembl;
    }

    public DefaultGene(String name, CvTerm type, Organism organism, Xref uniqueId, Xref ensembl) {
        super(name, type != null ? type : CvTermUtils.createGeneInteractorType(), organism, uniqueId);
        this.ensembl = ensembl;
    }

    public DefaultGene(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId, Xref ensembl) {
        super(name, fullName, type != null ? type : CvTermUtils.createGeneInteractorType(), organism, uniqueId);
        this.ensembl = ensembl;
    }

    public DefaultGene(String name, Organism organism, String ensembl) {
        super(name, CvTermUtils.createGeneInteractorType(), organism);
        if (ensembl != null){
            setEnsembl(ensembl);
        }
    }

    public DefaultGene(String name, String fullName, Organism organism, String ensembl) {
        super(name, fullName, CvTermUtils.createGeneInteractorType(), organism);
        if (ensembl != null){
            setEnsembl(ensembl);
        }
    }

    public DefaultGene(String name, CvTerm type) {
        super(name, type != null ? type : CvTermUtils.createGeneInteractorType());
    }

    public DefaultGene(String name, String fullName, CvTerm type) {
        super(name, fullName, type != null ? type : CvTermUtils.createGeneInteractorType());
    }

    public DefaultGene(String name, CvTerm type, Organism organism) {
        super(name, type != null ? type : CvTermUtils.createGeneInteractorType(), organism);
    }

    public DefaultGene(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type != null ? type : CvTermUtils.createGeneInteractorType(), organism);
    }

    @Override
    protected void initialiseIdentifiers() {
        initialiseIdentifiersWith(new GeneIdentifierList());
    }

    @Override
    /**
     * Return the first ensembl identifier if provided, otherwise the first ensemblGenomes if provided, otherwise
     * the first entrez/gene id if provided, otherwise the first refseq id if provided
     * otherwise the first identifier in the list of identifiers
     */
    public Xref getPreferredIdentifier() {
        return ensembl != null ? ensembl : (ensemblGenome != null ? ensemblGenome : (entrezGeneId != null ? entrezGeneId : (refseq != null ? refseq : super.getPreferredIdentifier())));
    }

    public String getEnsembl() {
        return this.ensembl != null ? this.ensembl.getId() : null;
    }

    public void setEnsembl(String ac) {
        GeneIdentifierList geneIdentifiers = (GeneIdentifierList)getIdentifiers();

        // add new ensembl if not null
        if (ac != null){
            CvTerm ensemblDatabase = CvTermUtils.createEnsemblDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old ensembl if not null
            if (this.ensembl != null){
                geneIdentifiers.removeOnly(this.ensembl);
            }
            this.ensembl = new DefaultXref(ensemblDatabase, ac, identityQualifier);
            geneIdentifiers.addOnly(this.ensembl);
        }
        // remove all ensembl if the collection is not empty
        else if (!geneIdentifiers.isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(geneIdentifiers, Xref.ENSEMBL_MI, Xref.ENSEMBL);
            this.ensembl = null;
        }
    }

    public String getEnsemblGenome() {
        return this.ensemblGenome != null ? this.ensemblGenome.getId() : null;
    }

    public void setEnsemblGenome(String ac) {
        GeneIdentifierList geneIdentifiers = (GeneIdentifierList)getIdentifiers();

        // add new ensembl genomes if not null
        if (ac != null){
            CvTerm ensemblGenomesDatabase = CvTermUtils.createEnsemblGenomesDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old ensembl genome if not null
            if (this.ensemblGenome != null){
                geneIdentifiers.removeOnly(this.ensemblGenome);
            }
            this.ensemblGenome = new DefaultXref(ensemblGenomesDatabase, ac, identityQualifier);
            geneIdentifiers.addOnly(this.ensemblGenome);
        }
        // remove all ensembl genomes if the collection is not empty
        else if (!geneIdentifiers.isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(geneIdentifiers, Xref.ENSEMBL_GENOMES_MI, Xref.ENSEMBL_GENOMES);
            this.ensemblGenome = null;
        }
    }

    public String getEntrezGeneId() {
        return this.entrezGeneId != null ? this.entrezGeneId.getId() : null;
    }

    public void setEntrezGeneId(String id) {
        GeneIdentifierList geneIdentifiers = (GeneIdentifierList)getIdentifiers();

        // add new entrez gene id genomes if not null
        if (id != null){
            CvTerm entrezDatabase = CvTermUtils.createEntrezGeneIdDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old entrez gene id if not null
            if (this.entrezGeneId!= null){
                geneIdentifiers.removeOnly(this.entrezGeneId);
            }
            this.entrezGeneId = new DefaultXref(entrezDatabase, id, identityQualifier);
            geneIdentifiers.addOnly(this.entrezGeneId);
        }
        // remove all ensembl genomes if the collection is not empty
        else if (!geneIdentifiers.isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(geneIdentifiers, Xref.ENTREZ_GENE_MI, Xref.ENTREZ_GENE);
            this.entrezGeneId = null;
        }
    }

    public String getRefseq() {
        return this.refseq != null ? this.refseq.getId() : null;
    }

    public void setRefseq(String ac) {
        GeneIdentifierList geneIdentifiers = (GeneIdentifierList)getIdentifiers();

        // add new refseq if not null
        if (ac != null){
            CvTerm refseqDatabase = CvTermUtils.createRefseqDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove refseq if not null
            if (this.refseq!= null){
                geneIdentifiers.removeOnly(this.refseq);
            }
            this.refseq = new DefaultXref(refseqDatabase, ac, identityQualifier);
            geneIdentifiers.addOnly(this.refseq);
        }
        // remove all ensembl genomes if the collection is not empty
        else if (!geneIdentifiers.isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(geneIdentifiers, Xref.REFSEQ_MI, Xref.REFSEQ);
            this.refseq = null;
        }
    }

    protected void processAddedIdentifierEvent(Xref added) {
        // the added identifier is ensembl and it is not the current ensembl identifier
        if (ensembl != added && XrefUtils.isXrefFromDatabase(added, Xref.ENSEMBL_MI, Xref.ENSEMBL)){
            // the current ensembl identifier is not identity, we may want to set ensembl Identifier
            if (!XrefUtils.doesXrefHaveQualifier(ensembl, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the ensembl identifier is not set, we can set the ensembl identifier
                if (ensembl == null){
                    ensembl = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    ensembl = added;
                }
                // the added xref is secondary object and the current ensembl identifier is not a secondary object, we reset ensembl identifier
                else if (!XrefUtils.doesXrefHaveQualifier(ensembl, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    ensembl = added;
                }
            }
        }
        // the added identifier is ensembl genomes and it is not the current ensembl genomes identifier
        else if (ensemblGenome != added && XrefUtils.isXrefFromDatabase(added, Xref.ENSEMBL_GENOMES_MI, Xref.ENSEMBL_GENOMES)){
            // the current ensembl genomes identifier is not identity, we may want to set ensembl genomes Identifier
            if (!XrefUtils.doesXrefHaveQualifier(ensemblGenome, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the ensembl genomes Identifier is not set, we can set the ensembl genomes Identifier
                if (ensemblGenome == null){
                    ensemblGenome = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    ensemblGenome = added;
                }
                // the added xref is secondary object and the current ensembl genomes Identifier is not a secondary object, we reset ensembl genomes Identifier
                else if (!XrefUtils.doesXrefHaveQualifier(ensemblGenome, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    ensemblGenome = added;
                }
            }
        }
        // the added identifier is entrez gene id and it is not the current entrez gene id
        else if (entrezGeneId != added && XrefUtils.isXrefFromDatabase(added, Xref.ENTREZ_GENE_MI, Xref.ENTREZ_GENE)){
            // the current entrez gene id is not identity, we may want to set entrez gene id
            if (!XrefUtils.doesXrefHaveQualifier(entrezGeneId, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the entrez gene id is not set, we can set the entrez gene idr
                if (entrezGeneId == null){
                    entrezGeneId = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    entrezGeneId = added;
                }
                // the added xref is secondary object and the current entrez gene id is not a secondary object, we reset entrez gene id
                else if (!XrefUtils.doesXrefHaveQualifier(entrezGeneId, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    entrezGeneId = added;
                }
            }
        }
        // the added identifier is refseq id and it is not the current refseq id
        else if (refseq != added && XrefUtils.isXrefFromDatabase(added, Xref.REFSEQ_MI, Xref.REFSEQ)){
            // the current refseq id is not identity, we may want to set refseq id
            if (!XrefUtils.doesXrefHaveQualifier(refseq, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the refseq id is not set, we can set the refseq id
                if (refseq == null){
                    refseq = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    refseq = added;
                }
                // the added xref is secondary object and the current refseq id is not a secondary object, we reset refseq id
                else if (!XrefUtils.doesXrefHaveQualifier(entrezGeneId, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    refseq = added;
                }
            }
        }
    }

    protected void processRemovedIdentifierEvent(Xref removed) {
        if (ensembl != null && ensembl.equals(removed)){
            ensembl = XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), Xref.ENSEMBL_MI, Xref.ENSEMBL);
        }
        else if (ensemblGenome != null && ensemblGenome.equals(removed)){
            ensemblGenome = XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), Xref.ENSEMBL_GENOMES_MI, Xref.ENSEMBL_GENOMES);
        }
        else if (entrezGeneId != null && entrezGeneId.equals(removed)){
            entrezGeneId = XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), Xref.ENTREZ_GENE_MI, Xref.ENTREZ_GENE);
        }
        else if (refseq != null &&refseq.equals(removed)){
            refseq = XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), Xref.REFSEQ_MI, Xref.REFSEQ);
        }
    }

    protected void clearPropertiesLinkedToIdentifiers() {
        ensembl = null;
        ensemblGenome = null;
        entrezGeneId = null;
        refseq = null;
    }

    @Override
    /**
     * Sets the interactor type.
     * @throw IllegalArgumentException: If the give type is not gene (MI:0301)
     */
    public void setInteractorType(CvTerm type) {
        if (type == null){
            super.setInteractorType(CvTermUtils.createGeneInteractorType());
        }
        else {
            super.setInteractorType(type);
        }
    }

    @Override
    public String toString() {
        return "Gene: "
                +(getEnsembl() != null ? getEnsembl() :
                (getEnsemblGenome() != null ? getEnsemblGenome() :
                        (getEntrezGeneId() != null ? getEntrezGeneId() :
                                (getRefseq() != null ? getRefseq() : super.toString()))));
    }

    private class GeneIdentifierList extends AbstractListHavingProperties<Xref> {
        public GeneIdentifierList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Xref added) {
            processAddedIdentifierEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Xref removed) {
            processRemovedIdentifierEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToIdentifiers();
        }
    }
}
