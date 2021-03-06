package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Publication;

import java.util.Collection;

/**
 * Interface for XmlPublicationWriter.
 *
 * It allows to write only the attributes of a publication if required.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */

public interface PsiXmlPublicationWriter extends PsiXmlElementWriter<Publication> {
    public void writeAllPublicationAttributes(Publication object, Collection<Annotation> attributesToFilter);
    public void writeAllPublicationAttributes(Publication object);
}
