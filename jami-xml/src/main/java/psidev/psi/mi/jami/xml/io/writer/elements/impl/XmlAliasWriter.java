package psidev.psi.mi.jami.xml.io.writer.elements.impl;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML25 writer for aliases
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */

public class XmlAliasWriter implements PsiXmlElementWriter<Alias> {
    private XMLStreamWriter streamWriter;

    public XmlAliasWriter(XMLStreamWriter writer){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the XML25AliasWriter");
        }
        this.streamWriter = writer;
    }

    @Override
    public void write(Alias object) throws MIIOException {
        if (object != null){
            try {
                // write start
                this.streamWriter.writeStartElement("alias");
                // write alias type
                if (object.getType() != null){
                    CvTerm type = object.getType();
                    this.streamWriter.writeAttribute("type", type.getShortName());
                    if (type.getMIIdentifier() != null){
                        this.streamWriter.writeAttribute("typeAc", type.getMIIdentifier());
                    }
                }
                // write name
                this.streamWriter.writeCharacters(object.getName());
                // write end alias
                this.streamWriter.writeEndElement();

            } catch (XMLStreamException e) {
                throw new MIIOException("Impossible to write the alias : "+object.toString(), e);
            }
        }
    }
}
