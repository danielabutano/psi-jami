package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Interactor;

/**
 * Interface for interactors in PSI-XML 2.5 standards
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */

public interface ExtendedPsiXmlInteractor extends Interactor{
    public int getId();
    public void setId(int id);
}
