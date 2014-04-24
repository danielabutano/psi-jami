package psidev.psi.mi.jami.xml.io.writer.elements.impl;

import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlPositionWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * XML 2.5 writer for the begin position of a range
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */

public class XmlBeginPositionWriter extends AbstractXmlPositionWriter {
    public XmlBeginPositionWriter(XMLStreamWriter writer) {
        super(writer);
    }

    @Override
    protected void writeStartPositionNode() throws XMLStreamException {
        getStreamWriter().writeStartElement("begin");
    }

    @Override
    protected void writeStartIntervalNode() throws XMLStreamException {
        getStreamWriter().writeStartElement("beginInterval");
    }

    @Override
    protected void initialiseStatusWriter() {
        super.setStatusWriter(new XmlStartStatusWriter(getStreamWriter()));
    }
}
