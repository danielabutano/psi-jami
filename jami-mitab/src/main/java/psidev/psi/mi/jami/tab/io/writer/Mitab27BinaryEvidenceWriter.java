package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.tab.io.writer.feeder.Mitab27InteractionEvidenceFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Mitab 2.7 writer for binary interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */

public class Mitab27BinaryEvidenceWriter extends AbstractMitab26BinaryWriter<BinaryInteractionEvidence, ParticipantEvidence> {

    public Mitab27BinaryEvidenceWriter() {
        super();
    }

    public Mitab27BinaryEvidenceWriter(File file) throws IOException {
        super(file);
    }

    public Mitab27BinaryEvidenceWriter(OutputStream output){
        super(output);
    }

    public Mitab27BinaryEvidenceWriter(Writer writer){
        super(writer);
    }

    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new Mitab27InteractionEvidenceFeeder(getWriter()));
    }
}
