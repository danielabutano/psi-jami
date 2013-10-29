//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.20 at 10:58:57 AM BST 
//


package psidev.psi.mi.jami.xml.extension;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.comparator.xref.UnambiguousXrefComparator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * Refers to a unique object in an external database.
 * 
 * <p>Java class for dbReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * The JAXB bindings is designed to be read-only and is not designed for writing
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlXref
    implements Xref, FileSourceContext, Locatable
{

    private CvTerm database;
    private CvTerm qualifier;
    private String id;
    private String version;
    private String secondary;
    private List<XmlAnnotation> annotations;
    private PsiXmLocator sourceLocator;
    @XmlLocation
    @XmlTransient
    private Locator locator;

    public XmlXref() {
    }

    public XmlXref(CvTerm database, String id, CvTerm qualifier) {
        this(database, id);
        this.qualifier = qualifier;
    }

    public XmlXref(CvTerm database, String id, String version, CvTerm qualifier){
        this(database, id, version);
        this.qualifier = qualifier;
    }

    public XmlXref(CvTerm database, String id, String version){
        this(database, id);
        this.version = version;
    }

    public XmlXref(CvTerm database, String id){
        if (database == null){
            throw new IllegalArgumentException("The database is required and cannot be null");
        }
        this.database = database;

        if (id == null || (id != null && id.length() == 0)){
            throw new IllegalArgumentException("The id is required and cannot be null or empty");
        }
        this.id = id;
    }


    public CvTerm getDatabase() {
        if (this.database == null){
            this.database = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED);
        }
        return this.database;
    }

    public String getId() {
        if (this.id == null){
            this.id = PsiXmlUtils.UNSPECIFIED;
        }
        return this.id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(String value) {
        this.id = value;
    }

    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @XmlAttribute(name = "version")
    public void setJAXBVersion(String value) {
        this.version = value;
    }

    public CvTerm getQualifier() {
        return this.qualifier;
    }

    /**
     * Sets the value of the db property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @XmlAttribute(name = "db", required = true)
    public void setJAXBDb(String value) {
        if (this.database == null && value != null){
            this.database = new DefaultCvTerm(value);
        }
        else if (this.database != null){
            this.database.setShortName(value != null ? value : PsiXmlUtils.UNSPECIFIED);
        }
    }

    /**
     * Sets the value of the dbAc property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @XmlAttribute(name = "dbAc")
    public void setJAXBDbAc(String value) {
        if (this.database == null && value != null){
            this.database = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED, value);
        }
        else if (this.database != null){
            this.database.setMIIdentifier(value);
        }
    }

    /**
     * Sets the value of the refType property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @XmlAttribute(name = "refType")
    public void setJAXBRefType(String value) {
        if (this.qualifier == null && value != null){
            this.qualifier = new DefaultCvTerm(value);
        }
        else if (this.qualifier != null){
            if (this.qualifier.getMIIdentifier() == null && value == null){
                this.qualifier = null;
            }
            else {
                this.qualifier.setShortName(value != null ? value : PsiXmlUtils.UNSPECIFIED);
            }
        }
    }

    /**
     * Sets the value of the refTypeAc property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @XmlAttribute(name = "refTypeAc")
    public void setJAXBRefTypeAc(String value) {
        if (this.qualifier == null && value != null){
            this.qualifier = new DefaultCvTerm(PsiXmlUtils.UNSPECIFIED, value);
        }
        else if (this.qualifier != null){
            if (PsiXmlUtils.UNSPECIFIED.equals(this.qualifier.getShortName()) && value == null){
                this.qualifier = null;
            }
            else {
                this.qualifier.setMIIdentifier(value);
            }
        }
    }

    /**
     * Sets the value of the secondary property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @XmlAttribute(name = "secondary")
    public void setJAXBSecondary(String value) {
        this.secondary = value;
    }


    /**
     * Gets the value of the attributeList property.
     * 
     * @return
     *     possible object is
     *     {@link XmlAnnotation }
     *
     */
    @XmlElementWrapper(name="attributeList")
    @XmlElement(name="attribute", required = true, type = XmlAnnotation.class)
    public List<XmlAnnotation> getJAXBAttributes() {
        return this.annotations;
    }

    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    public FileSourceLocator getSourceLocator() {
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
        }
        return sourceLocator;
    }

    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else{
            this.sourceLocator = new PsiXmLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o){
            return true;
        }

        // Xrefs are different and it has to be ExternalIdentifier
        if (!(o instanceof Xref)){
            return false;
        }

        return UnambiguousXrefComparator.areEquals(this, (Xref) o);
    }

    @Override
    public int hashCode() {
        return UnambiguousXrefComparator.hashCode(this);
    }

    @Override
    public String toString() {
        return database.toString() + ":" + id.toString() + (qualifier != null ? " (" + qualifier.toString() + ")" : "");
    }
}
