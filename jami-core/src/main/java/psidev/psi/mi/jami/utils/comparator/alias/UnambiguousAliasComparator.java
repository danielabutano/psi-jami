package psidev.psi.mi.jami.utils.comparator.alias;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.utils.comparator.cv.UnambiguousCvTermComparator;

/**
 * Unambiguous alias comparator.
 * It will first compare alias types using UnambiguousCvTermComparator and then alias names (case sensitive)
 * - Two aliases which are null are equals
 * - The alias which is not null is before null.
 * - If the alias types are not set, compares the names (case sensitive)
 * - If both alias types are set, use UnambiguousCvTermComparator to compare the alias types. If they are equals, compares the names (case sensitive)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/12/12</pre>
 */

public class UnambiguousAliasComparator extends AliasComparator {

    private static UnambiguousAliasComparator unambiguousAliasComparator;

    /**
     * Creates a new AliasComparator with DefaultCvTermComparator
     */
    public UnambiguousAliasComparator() {
        super(new UnambiguousCvTermComparator());
    }

    @Override
    public UnambiguousCvTermComparator getTypeComparator() {
        return (UnambiguousCvTermComparator) super.getTypeComparator();
    }

    @Override
    /**
     * It will first compare alias types using UnambiguousCvTermComparator and then alias names (case sensitive)
     * - Two aliases which are null are equals
     * - The alias which is not null is before null.
     * - If the alias types are not set, compares the names (case sensitive)
     * - If both alias types are set, use UnambiguousCvTermComparator to compare the alias types. If they are equals, compares the names (case sensitive)
     * @param alias1
     * @param alias2
     */
    public int compare(Alias alias1, Alias alias2) {
        return super.compare(alias1, alias2);
    }

    /**
     * Use UnambiguousAliasComparator to know if two aliases are equals.
     * @param alias1
     * @param alias2
     * @return true if the two aliases are equal
     */
    public static boolean areEquals(Alias alias1, Alias alias2){
        if (unambiguousAliasComparator == null){
            unambiguousAliasComparator = new UnambiguousAliasComparator();
        }

        return unambiguousAliasComparator.compare(alias1, alias2) == 0;
    }

    /**
     *
     * @param alias
     * @return the hashcode consistent with the equals method for this comparator
     */
    public static int hashCode(Alias alias){
        if (unambiguousAliasComparator == null){
            unambiguousAliasComparator = new UnambiguousAliasComparator();
        }
        if (alias == null){
            return 0;
        }

        int hashcode = 31;
        CvTerm type = alias.getType();
        hashcode = 31*hashcode + UnambiguousCvTermComparator.hashCode(type);

        String name = alias.getName();
        hashcode = 31*hashcode + name.hashCode();

        return hashcode;
    }
}
