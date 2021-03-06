package psidev.psi.mi.jami.bridges.ontologymanager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.bridges.ols.CachedOlsOntologyTermFetcher;
import psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyAccess;
import psidev.psi.mi.jami.bridges.ontologymanager.MIOntologyTermI;
import psidev.psi.mi.jami.model.OntologyTerm;
import psidev.psi.tools.ontology_manager.impl.local.OntologyLoaderException;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * abstract class for OntologyAccess
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/11/11</pre>
 */

public abstract class AbstractMIOntologyAccess implements MIOntologyAccess {

    public static final Log log = LogFactory.getLog(AbstractMIOntologyAccess.class);

    private OntologyTermFetcher termFetcher;

    private String databaseName;
    private String databaseIdentifier;
    private Pattern databaseRegexp;
    private String parentIdentifier;
    private String ontologyID;

    public AbstractMIOntologyAccess() throws OntologyLoaderException {
        super();
        try {
            this.termFetcher = new CachedOlsOntologyTermFetcher();
        } catch (BridgeFailedException e) {
            throw new OntologyLoaderException("Impossible to create a new OLS fetcher", e);
        }
        this.databaseName = null;
        this.databaseIdentifier=null;
        this.databaseRegexp=null;
        this.parentIdentifier=null;
    }

    public AbstractMIOntologyAccess(OntologyTermFetcher termBuilder) throws OntologyLoaderException {
        super();
        if (termBuilder == null){
            throw new IllegalArgumentException("The OntologyTermWrapper fetcher must be non null");
        }
        this.termFetcher = termBuilder;
        this.databaseName = null;
        this.databaseIdentifier=null;
        this.databaseRegexp=null;
        this.parentIdentifier=null;
    }


    public AbstractMIOntologyAccess(String dbName, String dbIdentifier, Pattern dbRegexp, String parent) throws OntologyLoaderException {
        super();
        try {
            this.termFetcher = new CachedOlsOntologyTermFetcher();
        } catch (BridgeFailedException e) {
            throw new OntologyLoaderException("Impossible to create a new OLS fetcher", e);
        }
        this.databaseName = dbName;
        this.databaseIdentifier=dbIdentifier;
        this.databaseRegexp=dbRegexp;
        this.parentIdentifier=parent;
    }

    public AbstractMIOntologyAccess(OntologyTermFetcher termBuilder, String dbName, String dbIdentifier, Pattern dbRegexp, String parent) throws OntologyLoaderException {
        super();
        if (termBuilder == null){
            throw new IllegalArgumentException("The OntologyTermWrapper fetcher must be non null");
        }
        this.termFetcher = termBuilder;
        this.databaseName = dbName;
        this.databaseIdentifier=dbIdentifier;
        this.databaseRegexp=dbRegexp;
        this.parentIdentifier=parent;
    }

    protected MIOntologyTermI createNewOntologyTerm(String identifier, String name) throws BridgeFailedException {
        if (identifier != null){
            OntologyTerm fetched = this.termFetcher.fetchByIdentifier(identifier, this.databaseName != null ? this.databaseName : name);
            return fetched != null ? new OntologyTermWrapper(fetched) : null;
        }
        return null;
    }

    public String getOntologyID() {
        return this.ontologyID;
    }

    public String getDatabaseIdentifier() {
        return this.databaseIdentifier;
    }

    public String getParentFromOtherOntology() {
        return this.parentIdentifier;
    }

    public Collection<MIOntologyTermI> getRootTerms() {
        Collection<MIOntologyTermI> roots = new ArrayList<MIOntologyTermI>();

        try {
            for (OntologyTerm acc : getOntologyTermFetcher().fetchRootTerms(databaseName)){
                MIOntologyTermI term = new OntologyTermWrapper(acc);

                roots.add(term);
            }
        } catch (BridgeFailedException e) {
            if ( log.isWarnEnabled() ) {
                log.warn( "Error while loading root term from OLS for ontology: " + ontologyID, e );
            }
        }
        return roots;
    }

    public Pattern getDatabaseRegexp() {
        return this.databaseRegexp;
    }

    public OntologyTermFetcher getOntologyTermFetcher() {
        return termFetcher;
    }

    public abstract void loadOntology(String ontologyID, String name, String version, String format, URI uri) throws OntologyLoaderException;

    public void setOntologyDirectory(File directory) {
         // nothing to do
    }

    /**
     * Method that is used by the validator to determine a Set of Ontology terms that are valid terms
     * for a particular rule. E.g. according to the flags, this can be the term corresponding to the
     * provided accession or its children or both.
     *
     * @param accession     the accession (ID) of a ontology term.
     * @param allowChildren flag weather or not to allow child terms of the specified accession.
     * @param useTerm       flag weather or not to use the given accession as one of the valid terms.
     * @return a Set of OntologyTerms that are valid (in terms of the validator).
     */
    public Set<MIOntologyTermI> getValidTerms( String accession, boolean allowChildren, boolean useTerm ) {
        Set<MIOntologyTermI> validTerms = new HashSet<MIOntologyTermI>();
        MIOntologyTermI term = getTermForAccession( accession );
        if ( term != null ) {
            if ( useTerm ) {
                validTerms.add( term );
            }
            if ( allowChildren ) {
                collectChildren(term.getDelegate(), validTerms);
            }
        }
        return validTerms;
    }

    private void collectChildren(OntologyTerm term, Set<MIOntologyTermI> children) {
        for (OntologyTerm child : term.getChildren()){
            if (children.add(new OntologyTermWrapper(child))){
                collectChildren(child, children);
            }
        }
    }

    public MIOntologyTermI getTermForAccession(String accession) {
        // if we don't even have a valid input, there is no point in trying to query OLS
        if (accession == null) { return null; }

        try {
            return createNewOntologyTerm(accession, null);
        } catch (BridgeFailedException e) {
            if ( log.isWarnEnabled() ) {
                log.warn( "Error while loading term from OLS : " + accession, e );
            }
        }
        return null;
    }

    public boolean isObsolete(MIOntologyTermI term) {
        return term.getObsoleteMessage() != null;
    }

    public Set<MIOntologyTermI> getDirectParents(MIOntologyTermI term) {
        Collection<OntologyTerm> parents = term.getDelegate().getParents();
        Set<MIOntologyTermI> directParents = new HashSet<MIOntologyTermI>(parents.size());
        for (OntologyTerm parent : parents){
            directParents.add(new OntologyTermWrapper(parent));
        }
        return directParents;
    }

    public Set<MIOntologyTermI> getDirectChildren(MIOntologyTermI term) {
        Collection<OntologyTerm> children = term.getDelegate().getChildren();
        Set<MIOntologyTermI> directChildren = new HashSet<MIOntologyTermI>(children.size());
        for (OntologyTerm child : children){
            directChildren.add(new OntologyTermWrapper(child));
        }
        return directChildren;
    }

    public Set<MIOntologyTermI> getAllParents(MIOntologyTermI term) {
        Set<MIOntologyTermI> allParents = getDirectParents(term);
        Set<MIOntologyTermI> allParentsClone = new HashSet<MIOntologyTermI>(allParents);
        for (MIOntologyTermI termParent : allParentsClone){
            allParents.addAll(getAllParents(termParent));
        }
        return allParents;
    }

    public Set<MIOntologyTermI> getAllChildren(MIOntologyTermI term) {
        Set<MIOntologyTermI> allChildren = getDirectChildren(term);
        Set<MIOntologyTermI> allChildrenClone = new HashSet<MIOntologyTermI>(allChildren);
        for (MIOntologyTermI termChild : allChildrenClone){
            allChildren.addAll(getAllChildren(termChild));
        }
        return allChildren;
    }

    public abstract boolean isOntologyUpToDate() throws OntologyLoaderException;

    public boolean isUseTermSynonyms() {
        return true;
    }

    public void setUseTermSynonyms(boolean useTermSynonyms) {
         throw new UnsupportedOperationException("The MI ontology fetcher always load term synonyms");
    }

    public String getDatabaseName() {
        return databaseName;
    }

    protected void setOntologyID(String ontologyID) {
        this.ontologyID = ontologyID;
    }

    protected void setOntologyTermFetcher(OntologyTermFetcher termFetcher) {
        this.termFetcher = termFetcher;
    }
}
