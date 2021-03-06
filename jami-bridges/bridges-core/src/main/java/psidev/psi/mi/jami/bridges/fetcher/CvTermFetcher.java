package psidev.psi.mi.jami.bridges.fetcher;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.CvTerm;

import java.util.Collection;

/**
 * The interface for finding a CvTerm or extension of CvTerm.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 08/05/13
 */
public interface CvTermFetcher<C extends CvTerm>{

    /**
     * Uses the identifier and the name of the database to search for a complete form of the cvTerm.
     * @param termIdentifier    The identifier for the CvTerm to fetch.
     * @param miOntologyName  The name of the ontology to search for. Eg, psi-mi, psi-mod, go. Must not be Null.
     * @return  A full cvTerm which matches the search term or null if one cannot be found.
     * @throws BridgeFailedException
     */
    public C fetchByIdentifier(String termIdentifier, String miOntologyName)
            throws BridgeFailedException;

    /**
     * Uses the identifier and a cvTerm denoting the database to search to fetch a complete from of the term.
     * @param termIdentifier     The identifier for the CvTerm to fetch
     * @param ontologyDatabase  The cvTerm of the ontology to search for.
     * @return  A fully enriched cvTerm which matches the search term or null if one cannot be found.
     * @throws BridgeFailedException
     */
    public C fetchByIdentifier(String termIdentifier, CvTerm ontologyDatabase)
            throws BridgeFailedException;

    /**
     * Uses the name of the term and the name of the database to search for a complete form of the term.
     * @param searchName    A full or short name for the term to be searched for.
     * @param miOntologyName  The ontology to search for the term in.
     * @return  A fully enriched cvTerm which matches the search term or null if one cannot be found.
     * @throws BridgeFailedException
     */
    public C fetchByName(String searchName, String miOntologyName)
            throws BridgeFailedException;

    /**
     * Uses the name of the term and the name of the database to search for a complete form of the term.
     * <p>
     * If the term can not be resolved to a database, then this method may return null.
     *
     * @param searchName    A full or short name for the term to be searched for.
     * @return  A fully enriched cvTerm which matches the search term or null if one cannot be found.
     * @throws BridgeFailedException
     */
    public Collection<C> fetchByName(String searchName)
            throws BridgeFailedException;


    /**
     * Uses the identifier and the name of the database to search for a complete form of the cvTerm.
     * @param termIdentifiers       The identifier for the CvTerm to fetch and the corresponding ontology database name.
     * @param miOntologyName  The name of the ontology to search for the names in.
     * @return  A fully enriched cvTerm which matches the search term or null if one cannot be found.
     * @throws BridgeFailedException
     */
    public Collection<C> fetchByIdentifiers(Collection<String> termIdentifiers, String miOntologyName)
            throws BridgeFailedException;

    /**
     * Uses the identifier and a cvTerm denoting the database to search to fetch a complete from of the term.
     * @param termIdentifiers       The identifier for the CvTerms to fetch.
     * @param ontologyDatabase      The name of the ontology to search for the terms in.
     * @return  A fully enriched cvTerm which matches the search term or null if one cannot be found.
     * @throws BridgeFailedException
     */
    public Collection<C> fetchByIdentifiers(Collection<String> termIdentifiers, CvTerm ontologyDatabase)
            throws BridgeFailedException;

    /**
     * Uses the name of the term and the name of the database to search for a complete form of the term.
     * @param searchNames   A full or short name for the term to be searched for.
     * @param miOntologyName  The name of the database to search for the names in.
     * @return              A fully enriched cvTerm which matches the search term or null if one cannot be found.
     * @throws BridgeFailedException
     */
    public Collection<C> fetchByNames(Collection<String> searchNames, String miOntologyName)
            throws BridgeFailedException;

    /**
     * Finds the CvTerms which match the exact names provided.
     * <p>
     * If the a term found by the search can not be resolved to a database, this method may return null.
     *
     * @param searchNames   A collection full or short names for the term to be searched for.
     * @return              A collection of cvTerms which matched a search term.
     * @throws BridgeFailedException
     */
    public Collection<C> fetchByNames(Collection<String> searchNames)
            throws BridgeFailedException;

}