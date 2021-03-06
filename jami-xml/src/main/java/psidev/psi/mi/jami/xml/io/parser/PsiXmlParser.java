package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory;

/**
 * Interface for PsiXmlParser
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */

public interface PsiXmlParser<T extends Interaction> {
    public T parseNextInteraction() throws PsiXmlParserException;

    public void close() throws MIIOException;

    public boolean hasFinished() throws PsiXmlParserException;

    public void reInit() throws MIIOException;

    public PsiXmlParserListener getListener();

    public void setListener(PsiXmlParserListener listener);

    public void setCacheOfObjects(PsiXmlIdCache indexOfObjects);

    public PsiXmlVersion getVersion();

    public XmlInteractorFactory getInteractorFactory();

    public void setInteractorFactory(XmlInteractorFactory factory);

}
