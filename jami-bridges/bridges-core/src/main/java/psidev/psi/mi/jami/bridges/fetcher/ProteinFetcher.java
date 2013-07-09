package psidev.psi.mi.jami.bridges.fetcher;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.Protein;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 14/05/13
 */
public interface ProteinFetcher {

    /**
     * Takes a string identifier and returns the proteins which match.
     * Returns an empty collection of no entries are returned
     *
     * @param identifier    The identifier to search for.
     * @return  The proteins which match the search term. Never null
     * @throws BridgeFailedException    A problem has been encountered when contacting the service
     */
    public Collection<Protein> getProteinsByIdentifier(String identifier)
            throws BridgeFailedException;

    /**
     *
     * @param identifiers
     * @return
     * @throws BridgeFailedException
     */
    public Collection<Protein> getProteinsByIdentifiers(Collection<String> identifiers)
            throws BridgeFailedException;
}