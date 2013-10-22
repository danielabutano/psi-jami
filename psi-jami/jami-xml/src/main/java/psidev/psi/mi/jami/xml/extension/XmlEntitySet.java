package psidev.psi.mi.jami.xml.extension;

import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.model.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * Default XML implementation for Entity set
 * Notes: The equals and hashcode methods have NOT been overridden because the XmlEntitySet object is a complex object.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "participantSet", propOrder = {
        "JAXBNames",
        "JAXBXref",
        "JAXBInteractionRef",
        "JAXBInteractor",
        "JAXBInteractorRef",
        "JAXBBiologicalRole",
        "JAXBFeatures",
        "JAXBAttributes"
})
public class XmlEntitySet extends AbstractXmlEntitySet<Interaction,Feature,Entity> {

    public XmlEntitySet() {
        super();
    }

    public XmlEntitySet(String interactorSetName) {
        super(new XmlInteractorSet(interactorSetName));
    }

    public XmlEntitySet(String interactorSetName, CvTerm bioRole) {
        super(new XmlInteractorSet(interactorSetName), bioRole);
    }

    public XmlEntitySet(String interactorSetName, Stoichiometry stoichiometry) {
        super(new XmlInteractorSet(interactorSetName), stoichiometry);
    }

    public XmlEntitySet(String interactorSetName, CvTerm bioRole, Stoichiometry stoichiometry) {
        super(new XmlInteractorSet(interactorSetName), bioRole, stoichiometry);
    }

    @Override
    @XmlElement(name = "names")
    public NamesContainer getJAXBNames() {
        return super.getJAXBNames();
    }

    @Override
    @XmlElement(name = "xref")
    public XrefContainer getJAXBXref() {
        return super.getJAXBXref();
    }

    @Override
    @XmlElement(name = "interactionRef")
    public Integer getJAXBInteractionRef() {
        return super.getJAXBInteractionRef();
    }

    @Override
    @XmlElement(name = "interactorRef")
    public Integer getJAXBInteractorRef() {
        return super.getJAXBInteractorRef();
    }

    @Override
    @XmlElement(name = "biologicalRole")
    public CvTerm getJAXBBiologicalRole() {
        return super.getJAXBBiologicalRole();
    }

    @Override
    @XmlElementWrapper(name="featureList")
    @XmlElements({ @XmlElement(type=XmlFeature.class,name="feature", required = true)})
    public ArrayList<Feature> getJAXBFeatures() {
        return super.getJAXBFeatures();
    }

    @Override
    @XmlElement(name = "interactor", type = XmlInteractor.class)
    public Interactor getJAXBInteractor() {
        return super.getJAXBInteractor();
    }

    @Override
    @XmlAttribute(name = "id", required = true)
    public int getJAXBId() {
        return super.getJAXBId();
    }

    @Override
    @XmlElementWrapper(name="attributeList")
    @XmlElements({ @XmlElement(type=XmlAnnotation.class, name="attribute", required = true)})
    public ArrayList<Annotation> getJAXBAttributes() {
        return super.getJAXBAttributes();
    }

    @Override
    @XmlLocation
    @XmlTransient
    public Locator getSaxLocator() {
        return super.getSaxLocator();
    }
}