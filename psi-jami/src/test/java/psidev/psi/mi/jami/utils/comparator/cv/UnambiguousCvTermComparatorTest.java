package psidev.psi.mi.jami.utils.comparator.cv;

import org.junit.Assert;
import org.junit.Test;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.factory.CvTermFactory;

/**
 * Unit tester for UnambiguousCvTermComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/02/13</pre>
 */

public class UnambiguousCvTermComparatorTest {

    private UnambiguousCvTermComparator comparator = new UnambiguousCvTermComparator();

    @Test
    public void test_cv_null_after() throws Exception {
        CvTerm term1 = null;
        CvTerm term2 = CvTermFactory.createChebiDatabase();

        Assert.assertTrue(comparator.compare(term1, term2) > 0);
        Assert.assertTrue(comparator.compare(term2, term1) < 0);

        Assert.assertFalse(UnambiguousCvTermComparator.areEquals(term1, term2));
        Assert.assertTrue(UnambiguousCvTermComparator.hashCode(term1) != UnambiguousCvTermComparator.hashCode(term2));
    }

    @Test
    public void test_cv_identifiers_empty_before() throws Exception {
        // null identifier
        CvTerm term1 = CvTermFactory.createMICvTerm("chebi", null);
        // chebi identifier
        CvTerm term2 = CvTermFactory.createChebiDatabase();

        Assert.assertTrue(comparator.compare(term1, term2) > 0);
        Assert.assertTrue(comparator.compare(term2, term1) < 0);

        Assert.assertFalse(UnambiguousCvTermComparator.areEquals(term1, term2));
        Assert.assertTrue(UnambiguousCvTermComparator.hashCode(term1) != UnambiguousCvTermComparator.hashCode(term2));
    }

    @Test
    public void test_cv_identifier_comparison() throws Exception {
        // uniprokb identifier
        CvTerm term1 = CvTermFactory.createMICvTerm("chebi", Xref.UNIPROTKB_MI);
        // chebi identifier
        CvTerm term2 = CvTermFactory.createMICvTerm("chebi", Xref.CHEBI_MI);

        Assert.assertTrue(comparator.compare(term1, term2) != 0);
        Assert.assertTrue(comparator.compare(term2, term1) != 0);

        Assert.assertFalse(UnambiguousCvTermComparator.areEquals(term1, term2));
        Assert.assertTrue(UnambiguousCvTermComparator.hashCode(term1) != UnambiguousCvTermComparator.hashCode(term2));
    }

    @Test
    public void test_cv_shortName_case_insensitive() throws Exception {
        CvTerm term1 = CvTermFactory.createMICvTerm("chebi", null);
        CvTerm term2 = CvTermFactory.createMICvTerm("CHeBi", null);
        term2.setFullName("chebi database");

        Assert.assertTrue(comparator.compare(term1, term2) == 0);
        Assert.assertTrue(comparator.compare(term2, term1) == 0);

        Assert.assertTrue(UnambiguousCvTermComparator.areEquals(term1, term2));
        Assert.assertTrue(UnambiguousCvTermComparator.hashCode(term1) == UnambiguousCvTermComparator.hashCode(term2));
    }

    @Test
    public void test_cv_shortName_comparison() throws Exception {
        // uniprokb
        CvTerm term1 = CvTermFactory.createMICvTerm("uniprokb", null);
        // chebi
        CvTerm term2 = CvTermFactory.createMICvTerm("chebi", null);
        term2.setFullName("chebi database");

        Assert.assertTrue(comparator.compare(term1, term2) != 0);
        Assert.assertTrue(comparator.compare(term2, term1) != 0);

        Assert.assertFalse(UnambiguousCvTermComparator.areEquals(term1, term2));
        Assert.assertTrue(UnambiguousCvTermComparator.hashCode(term1) != UnambiguousCvTermComparator.hashCode(term2));
    }
}
