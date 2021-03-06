package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.ParticipantEvidence;

/**
 * Unambiguous Experimental participant comparator.
 *
 * It will first compares experimental roles using UnambiguousCvTermComparator. If both experimental roles are equals, it
 * will look at the identification methods using UnambiguousCvTermComparator. If both identification methods are equals, it will
 * look at the experimental preparations using UnambiguousCvTermComparator. If both experimental preparations are equals, it will
 * look at the expressed in organisms using UnambiguousOrganismComparator.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/01/13</pre>
 */

public class UnambiguousParticipantEvidenceComparator extends ParticipantEvidenceComparator {

    private static UnambiguousParticipantEvidenceComparator unambiguousExperimentalParticipantComparator;

    /**
     * Creates a new UnambiguousParticipantEvidenceComparator. It will use a UnambiguousParticipantBaseComparator to compare
     * the basic properties of a participant, a UnambiguousCvTermComparator to compare experimental roles, preparations and identification methods
     * and a UnambiguousOrganismComparator to compare expressed in organisms
     */
    public UnambiguousParticipantEvidenceComparator() {
        super(new UnambiguousExperimentalParticipantPoolComparator());
    }

    @Override
    public UnambiguousExperimentalParticipantPoolComparator getParticipantPoolComparator() {
        return (UnambiguousExperimentalParticipantPoolComparator) super.getParticipantPoolComparator();
    }

    @Override
    /**
     * It will first compares experimental roles using UnambiguousCvTermComparator. If both experimental roles are equals, it
     * will look at the identification methods using UnambiguousCvTermComparator. If both identification methods are equals, it will
     * look at the experimental preparations using UnambiguousCvTermComparator. If both experimental preparations are equals, it will
     * look at the expressed in organisms using UnambiguousOrganismComparator.
     */
    public int compare(ParticipantEvidence experimentalParticipant1, ParticipantEvidence experimentalParticipant2) {
        return super.compare(experimentalParticipant1, experimentalParticipant2);
    }

    /**
     * Use UnambiguousParticipantEvidenceComparator to know if two experimental participants are equals.
     * @param experimentalParticipant1
     * @param component2
     * @return true if the two experimental participants are equal
     */
    public static boolean areEquals(ParticipantEvidence experimentalParticipant1, ParticipantEvidence component2){
        if (unambiguousExperimentalParticipantComparator == null){
            unambiguousExperimentalParticipantComparator = new UnambiguousParticipantEvidenceComparator();
        }

        return unambiguousExperimentalParticipantComparator.compare(experimentalParticipant1, component2) == 0;
    }
}
