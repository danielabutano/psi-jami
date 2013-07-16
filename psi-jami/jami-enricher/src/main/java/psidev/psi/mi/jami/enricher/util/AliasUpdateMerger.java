package psidev.psi.mi.jami.enricher.util;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.utils.comparator.alias.DefaultAliasComparator;
import psidev.psi.mi.jami.utils.comparator.cv.DefaultCvTermComparator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 03/07/13
 */
public class AliasUpdateMerger {


    private Collection<Alias> toRemove = new ArrayList<Alias>();
    private Collection<Alias> fetchedToAdd = new ArrayList<Alias>();

    public Collection<Alias> getToAdd() {
        return fetchedToAdd;
    }

    public Collection<Alias> getToRemove() {
        return toRemove;
    }

    public void merge(Collection<Alias> fetchedAliases , Collection<Alias> toEnrichAliases){
        fetchedToAdd.clear();
        fetchedToAdd.addAll(fetchedAliases); // All the identifiers which were fetched

        Collection<CvTerm> identifierCvTerms = new ArrayList<CvTerm>(); // The list of all the represented databases

        // For each entry, add its CvTerm to the representatives list, if it hasn't been so already.
        for(Alias alias : fetchedToAdd){
            if(! identifierCvTerms.contains(alias.getType())) identifierCvTerms.add(alias.getType());
        }

        toRemove.clear();

        // Check all of the identifiers which are to be enriched
        for(Alias aliasIdentifier : toEnrichAliases){
            
                boolean isInFetchedList = false;
                //If it's in the list to add, mark that it's to add already
                for(Alias aliasToAdd : fetchedToAdd){
                    if(DefaultAliasComparator.areEquals(aliasToAdd, aliasIdentifier)){
                        isInFetchedList = true;
                        //No need to add it as it is already there
                        fetchedToAdd.remove(aliasToAdd);
                        break;
                    }
                }
                // If it's not in the fetched ones to add,
                // check if it's CvTerm is managed and add to removals if it is
                if(! isInFetchedList){
                    boolean managedCvTerm = false;
                    for(CvTerm cvTerm : identifierCvTerms){
                        if(DefaultCvTermComparator.areEquals(cvTerm, aliasIdentifier.getType())){
                            managedCvTerm = true;
                            break;
                        }
                    }
                    if(managedCvTerm) toRemove.add(aliasIdentifier);
                }
         

        }
    }


}