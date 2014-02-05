package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultExperiment;
import psidev.psi.mi.jami.model.impl.DefaultNamedExperiment;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;
import psidev.psi.mi.jami.model.impl.DefaultPublication;

import java.util.HashSet;
import java.util.Set;

/**
 * Factory for experiment
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/13</pre>
 */

public class ExperimentUtils {

    public static Experiment createUnknownBasicExperiment(){
        return new DefaultExperiment(PublicationUtils.createUnknownBasicPublication(), CvTermUtils.createUnspecifiedMethod());
    }

    public static Experiment createExperimentWithoutPublication(){
        return new DefaultExperiment(null, CvTermUtils.createUnspecifiedMethod());
    }

    public static Experiment createBasicExperimentForModelledInteractions(){
        return new DefaultExperiment(PublicationUtils.createBasicPublicationForModelledInteractions(), CvTermUtils.createUnspecifiedMethod());
    }

    public static Experiment createBasicExperimentForComplexes(){
        return new DefaultExperiment(PublicationUtils.createBasicPublicationForModelledInteractions(), CvTermUtils.createMICvTerm(Experiment.INFERRED_BY_CURATOR, Experiment.INFERRED_BY_CURATOR_MI));
    }

    public static Experiment createBasicExperimentForComplexes(int taxid){
        Experiment experiment = new DefaultExperiment(PublicationUtils.createBasicPublicationForModelledInteractions(), CvTermUtils.createMICvTerm(Experiment.INFERRED_BY_CURATOR,Experiment.INFERRED_BY_CURATOR_MI));
        experiment.setHostOrganism(new DefaultOrganism(taxid));
        return experiment;
    }

    public static Experiment createBasicExperimentForComplexes(int taxid, String name){
        Experiment experiment = new DefaultExperiment(PublicationUtils.createBasicPublicationForModelledInteractions(), CvTermUtils.createMICvTerm(Experiment.INFERRED_BY_CURATOR,Experiment.INFERRED_BY_CURATOR_MI));
        experiment.setHostOrganism(new DefaultOrganism(taxid, name));
        return experiment;
    }
    
    public static String getPubmedId(Experiment exp){
       if (exp == null){
          return null; 
       }
        else if (exp.getPublication() == null){
           return null; 
       }
        else{
           return exp.getPublication().getPubmedId();
       }
    }

    public static String getDoiId(Experiment exp){
        if (exp == null){
            return null;
        }
        else if (exp.getPublication() == null){
            return null;
        }
        else{
            return exp.getPublication().getDoi();
        }
    }

    public static Xref getPubmedReference(Experiment exp){
        if (exp == null){
            return null;
        }
        else{
            return PublicationUtils.getPubmedReference(exp.getPublication());
        }
    }

    public static Xref getDoiReference(Experiment exp){
        if (exp == null){
            return null;
        }
        else{
            return PublicationUtils.getDoiReference(exp.getPublication());
        }
    }

    public static Experiment createExperiment(String pubmedId, CvTerm interactionDetectionMethod,
                                              Organism hostOrganism) {
        Experiment experiment = new DefaultExperiment(new DefaultPublication(pubmedId), interactionDetectionMethod);
        experiment.setHostOrganism(hostOrganism);

        return experiment;
    }

    public static NamedExperiment createExperiment(String name, String pubmedId, CvTerm interactionDetectionMethod,
                                              Organism hostOrganism) {
        NamedExperiment experiment = new DefaultNamedExperiment(new DefaultPublication(pubmedId), interactionDetectionMethod);
        experiment.setHostOrganism(hostOrganism);
        experiment.setShortName(name);

        return experiment;
    }

    public static CvTerm extractCommonParticipantDetectionMethodFrom(Experiment exp){
        CvTerm commonDetectionMethod = null;
        if (exp != null && !exp.getInteractionEvidences().isEmpty()){
            Set<CvTerm> detectionMethods = new HashSet<CvTerm>(exp.getInteractionEvidences().size());
            boolean isFirst = true;

            for (InteractionEvidence interaction : exp.getInteractionEvidences()){
                for (ParticipantEvidence p : interaction.getParticipants()){
                    if (isFirst){
                        detectionMethods.addAll(p.getIdentificationMethods());
                        isFirst = false;
                    }
                    // one of the participants does not have any detection methods so we cannot extract a common method for this experiment
                    else if (p.getIdentificationMethods().isEmpty() && !detectionMethods.isEmpty()){
                        detectionMethods.clear();
                        break;
                    }
                    else {
                        detectionMethods.retainAll(p.getIdentificationMethods());
                        if (detectionMethods.isEmpty()){
                            break;
                        }
                    }
                }
            }

            if (!detectionMethods.isEmpty()){
                commonDetectionMethod = detectionMethods.iterator().next();
            }
        }

        return commonDetectionMethod;
    }
}
