package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.exception.ComplexExpansionException;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.BinaryInteractionFactory;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory;
import psidev.psi.mi.jami.xml.model.extension.factory.xml25.XmlBinaryInteractionFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Abstract class for a binary interaction parser
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */

public abstract class AbstractPsixmlBinaryParser<T extends Interaction, B extends BinaryInteraction> implements PsiXmlParser<B> {

    private ComplexExpansionMethod<T,B> expansionMethod;
    private Collection<B> binaryInteractions;
    private Iterator<B> binaryInteractionIterator;
    private PsiXmlParser<T> delegateParser;

    public AbstractPsixmlBinaryParser(PsiXmlParser<T> delegateParser) {
        if (delegateParser == null){
           throw new IllegalArgumentException("An AbstractPsiXmlParser is required to parse Xml interactions");
        }
        this.delegateParser = delegateParser;
        this.binaryInteractions = new ArrayList<B>();
    }

    @Override
    public B parseNextInteraction() throws PsiXmlParserException {
        // first look at loaded interaction
        if (this.binaryInteractionIterator != null && this.binaryInteractionIterator.hasNext()){
            return processAndRemoveNextBinary();
        }

        // parse normal interaction
        T interaction = this.delegateParser.parseNextInteraction();
        if (interaction == null || !getExpansionMethod().isInteractionExpandable(interaction)){
            return null;
        }

        // expand
        try {
            this.binaryInteractions.addAll(getExpansionMethod().expand(interaction));
            this.binaryInteractionIterator = this.binaryInteractions.iterator();
            if (this.binaryInteractionIterator.hasNext()){
                return processAndRemoveNextBinary();
            }
            else{
                return null;
            }
        } catch (ComplexExpansionException e) {
            return null;
        }
    }

    @Override
    public boolean hasFinished() throws PsiXmlParserException {
        if (this.binaryInteractionIterator != null && this.binaryInteractionIterator.hasNext()){
            return false;
        }
        return delegateParser.hasFinished();
    }

    @Override
    public void reInit() throws MIIOException {
        this.binaryInteractionIterator = null;
        this.binaryInteractions.clear();
        this.delegateParser.reInit();
    }

    @Override
    public void close() {
        this.binaryInteractionIterator = null;
        this.binaryInteractions.clear();
        this.delegateParser.close();
    }

    @Override
    public PsiXmlParserListener getListener() {
        return delegateParser.getListener();
    }

    @Override
    public void setListener(PsiXmlParserListener listener) {
        delegateParser.getListener();
    }

    public void setExpansionMethod(ComplexExpansionMethod<T, B> expansionMethod) {
        this.expansionMethod = expansionMethod;
        if (expansionMethod != null){
            BinaryInteractionFactory binaryFactory = instantiateInteractionFactory();
            this.expansionMethod.setBinaryInteractionFactory(binaryFactory);
        }
    }

    protected BinaryInteractionFactory instantiateInteractionFactory() {
        BinaryInteractionFactory binaryFactory;
        switch (this.delegateParser.getVersion()){
            case v3_0_0:
                binaryFactory = new psidev.psi.mi.jami.xml.model.extension.factory.xml30.XmlBinaryInteractionFactory();
                break;
            default:
                binaryFactory = new XmlBinaryInteractionFactory();
                break;
        }
        return binaryFactory;
    }

    public void setCacheOfObjects(PsiXmlIdCache indexOfObjects) {
        this.delegateParser.setCacheOfObjects(indexOfObjects);
    }

    @Override
    public XmlInteractorFactory getInteractorFactory() {
        return this.delegateParser.getInteractorFactory();
    }

    @Override
    public void setInteractorFactory(XmlInteractorFactory factory) {
        this.delegateParser.setInteractorFactory(factory);
    }

    @Override
    public PsiXmlVersion getVersion() {
        return this.delegateParser.getVersion();
    }

    protected ComplexExpansionMethod<T,B> getExpansionMethod(){
        if (expansionMethod == null){
            this.expansionMethod = initialiseDefaultExpansionMethod();
            this.expansionMethod.setBinaryInteractionFactory(instantiateInteractionFactory());
        }
        return this.expansionMethod;
    }

    protected abstract ComplexExpansionMethod<T,B> initialiseDefaultExpansionMethod();

    protected PsiXmlParser<T> getDelegateParser() {
        return delegateParser;
    }

    private B processAndRemoveNextBinary() {
        B binary = this.binaryInteractionIterator.next();
        this.binaryInteractionIterator.remove();
        return binary;
    }
}
