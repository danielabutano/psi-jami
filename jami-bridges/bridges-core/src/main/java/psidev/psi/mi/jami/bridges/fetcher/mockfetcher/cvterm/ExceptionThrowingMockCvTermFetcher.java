package psidev.psi.mi.jami.bridges.fetcher.mockfetcher.cvterm;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.CvTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 01/07/13
 */
public class ExceptionThrowingMockCvTermFetcher
        extends MockCvTermFetcher
        implements CvTermFetcher<CvTerm>{

    String lastQuery = null;
    int count = 0;
    int maxQuery = 0;



    public ExceptionThrowingMockCvTermFetcher(int maxQuery){
        super();
        this.maxQuery = maxQuery;
    }

    @Override
    protected CvTerm getMockTermById(String identifier) throws BridgeFailedException {
        if(! localCvTerms.containsKey(identifier))  return null;
        else {
            if(! identifier.equals(lastQuery)){
                lastQuery = identifier;
                count = 0;

            }

            if(maxQuery != -1 && count >= maxQuery)
                return localCvTerms.get(identifier);
            else {
                count++;
                throw new BridgeFailedException("Mock fetcher throws because this is the "+(count-1)+" attempt of "+maxQuery);
            }
        }
    }

}