package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.model.ModelledInteraction;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.6 writer for Modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */

public class Mitab26ModelledInteractionWriter extends Mitab25ModelledInteractionWriter {

    public Mitab26ModelledInteractionWriter() {
        super();
    }

    public Mitab26ModelledInteractionWriter(File file) throws IOException {
        super(file);
    }

    public Mitab26ModelledInteractionWriter(OutputStream output) {
        super(output);
    }

    public Mitab26ModelledInteractionWriter(Writer writer) {
        super(writer);
    }

    public Mitab26ModelledInteractionWriter(File file, ComplexExpansionMethod<ModelledInteraction, ModelledBinaryInteraction> expansionMethod) throws IOException {
        super(file, expansionMethod);
    }

    public Mitab26ModelledInteractionWriter(OutputStream output, ComplexExpansionMethod<ModelledInteraction, ModelledBinaryInteraction> expansionMethod) {
        super(output, expansionMethod);
    }

    public Mitab26ModelledInteractionWriter(Writer writer, ComplexExpansionMethod<ModelledInteraction, ModelledBinaryInteraction> expansionMethod) {
        super(writer, expansionMethod);
    }

    @Override
    protected void initialiseWriter(Writer writer){
        setBinaryWriter(new Mitab26ModelledBinaryWriter(writer));
    }

    @Override
    protected void initialiseOutputStream(OutputStream output) {
        setBinaryWriter(new Mitab26ModelledBinaryWriter(output));
    }

    @Override
    protected void initialiseFile(File file) throws IOException {
        setBinaryWriter(new Mitab26ModelledBinaryWriter(file));
    }
}