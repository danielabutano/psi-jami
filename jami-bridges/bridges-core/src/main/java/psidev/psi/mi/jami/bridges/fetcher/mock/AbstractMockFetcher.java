package psidev.psi.mi.jami.bridges.fetcher.mock;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;

import java.util.HashMap;
import java.util.Map;

/**
 * A general mock fetcher which can be used to quickly make new mocks with predictable behaviour.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 07/08/13
 */
public abstract class AbstractMockFetcher <T>
        implements MockFetcher<T>{

    protected Map<String, T> localMap;

    protected AbstractMockFetcher(){
        localMap = new HashMap<String , T>();
    }

    public void addEntry(String identifier, T entry){
        localMap.put(identifier, entry);
    }

    public void removeEntry(String identifier){
        localMap.remove(identifier);
    }

    public void clearEntries(){
        localMap.clear();
    }

    protected T getEntry(String identifier) throws BridgeFailedException {
        if(identifier == null) throw new IllegalArgumentException(
                "Attempted to query mock fetcher for null identifier.");
        if(! localMap.containsKey(identifier)) {
            return null;
        } else {
            return localMap.get(identifier);
        }
    }
}
