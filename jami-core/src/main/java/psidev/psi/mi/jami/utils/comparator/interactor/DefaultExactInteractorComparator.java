package psidev.psi.mi.jami.utils.comparator.interactor;

import psidev.psi.mi.jami.model.*;

/**
 * Default exact Interactor Comparator.
 *
 * Bioactive entities come first, then proteins, then genes, then nucleic acids, then complexes and finally InteractorPool.
 * If two interactors are from the same Interactor interface, it will use a more specific Comparator :
 * - Uses DefaultExactBioactiveEntityComparator for comparing BioactiveEntity objects.
 * - Uses DefaultExactProteinComparator for comparing Protein objects.
 * - Uses DefaultExactGeneComparator for comparing Gene objects.
 * - Uses DefaultExactNucleicAcidComparator for comparing NucleicAcids objects.
 * - Uses DefaultExactPolymerComparator for comparing Polymer objects
 * - Uses DefaultExactComplexComparator for comparing complexes
 * - Uses DefaultExactInteractorPoolComparator for comparing interactor candidates
 * - use DefaultExactInteractorBaseComparator for comparing basic interactors that are not one of the above..
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/01/13</pre>
 */

public class DefaultExactInteractorComparator {

    /**
     * Use DefaultExactInteractorComparator to know if two interactors are equals.
     * @param interactor1
     * @param interactor2
     * @return true if the two interactors are equal
     */
    public static boolean areEquals(Interactor interactor1, Interactor interactor2){

        if (interactor1 == interactor2){
            return true;
        }
        else if (interactor1 == null || interactor2 == null){
            return false;
        }
        else {
            // first check if both interactors are from the same interface

            // both are small molecules
            boolean isBioactiveEntity1 = interactor1 instanceof BioactiveEntity;
            boolean isBioactiveEntity2 = interactor2 instanceof BioactiveEntity;
            if (isBioactiveEntity1 && isBioactiveEntity2){
                return DefaultExactBioactiveEntityComparator.areEquals((BioactiveEntity) interactor1, (BioactiveEntity) interactor2);
            }
            // the small molecule is before
            else if (isBioactiveEntity1 || isBioactiveEntity2){
                return false;
            }
            else {
                // both are proteins
                boolean isProtein1 = interactor1 instanceof Protein;
                boolean isProtein2 = interactor2 instanceof Protein;
                if (isProtein1 && isProtein2){
                    return DefaultExactProteinComparator.areEquals((Protein) interactor1, (Protein) interactor2);
                }
                // the protein is before
                else if (isProtein1 || isProtein2){
                    return false;
                }
                else {
                    // both are genes
                    boolean isGene1 = interactor1 instanceof Gene;
                    boolean isGene2 = interactor2 instanceof Gene;
                    if (isGene1 && isGene2){
                        return DefaultExactGeneComparator.areEquals((Gene) interactor1, (Gene) interactor2);
                    }
                    // the gene is before
                    else if (isGene1 || isGene2){
                        return false;
                    }
                    else {
                        // both are nucleic acids
                        boolean isNucleicAcid1 = interactor1 instanceof NucleicAcid;
                        boolean isNucleicAcid2 = interactor2 instanceof NucleicAcid;
                        if (isNucleicAcid1 && isNucleicAcid2){
                            return DefaultExactNucleicAcidComparator.areEquals((NucleicAcid) interactor1, (NucleicAcid) interactor2);
                        }
                        // the nucleic acid is before
                        else if (isNucleicAcid1 || isNucleicAcid2){
                            return false;
                        }
                        else {
                            boolean isPolymer1 = interactor1 instanceof Polymer;
                            boolean isPolymer2 = interactor2 instanceof Polymer;
                            // both are polymers
                            if (isPolymer1 && isPolymer2){
                                return DefaultExactPolymerComparator.areEquals((Polymer) interactor1, (Polymer) interactor2);
                            }
                            // the polymer is before
                            else if (isPolymer1 || isPolymer2){
                                return false;
                            }
                            else {

                                // both are interactor candidates
                                boolean isCandidates1 = interactor1 instanceof InteractorPool;
                                boolean isCandidates2 = interactor2 instanceof InteractorPool;
                                if (isCandidates1 && isCandidates2){
                                    return DefaultExactInteractorPoolComparator.areEquals((InteractorPool) interactor1, (InteractorPool) interactor2);
                                }
                                // the complex is before
                                else if (isCandidates1 || isCandidates2){
                                    return false;
                                }
                                else {
                                    boolean isComplex1 = interactor1 instanceof Complex;
                                    boolean isComplex2 = interactor2 instanceof Complex;
                                    // both are complexes
                                    if (isComplex1 && isComplex2){
                                        return DefaultExactComplexComparator.areEquals((Complex) interactor1, (Complex) interactor2);
                                    }
                                    // the complex is before
                                    else if (isComplex1 || isComplex2){
                                        return false;
                                    }
                                    else {
                                        return DefaultExactInteractorBaseComparator.areEquals(interactor1, interactor2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
