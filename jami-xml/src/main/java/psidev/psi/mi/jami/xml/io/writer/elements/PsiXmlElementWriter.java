package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.exception.MIIOException;

/**
 * Interface for writers of PSI-XML 2.5 elements
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */

public interface PsiXmlElementWriter<T extends Object> {

    public void write(T object) throws MIIOException;
}
