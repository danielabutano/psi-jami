package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Position;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.model.impl.DefaultExternalIdentifier;
import psidev.psi.mi.jami.utils.comparator.cv.DefaultCvTermComparator;

/**
 * Utility class for Positions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/12/12</pre>
 */

public class PositionUtils {

    public static boolean isUndetermined(Position position){
        if (position == null){
            return false;
        }

        CvTerm undetermined = new DefaultCvTerm(Position.UNDETERMINED, new DefaultExternalIdentifier(new DefaultCvTerm(CvTerm.PSI_MI), Position.UNDETERMINED_MI));
        CvTerm status = position.getStatus();

        return DefaultCvTermComparator.areEquals(undetermined, status);
    }

    public static boolean isNTerminalRange(Position position){
        if (position == null){
            return false;
        }

        CvTerm nTerminalRange = new DefaultCvTerm(Position.N_TERMINAL_RANGE, new DefaultExternalIdentifier(new DefaultCvTerm(CvTerm.PSI_MI), Position.N_TERMINAL_RANGE_MI));
        CvTerm status = position.getStatus();

        return DefaultCvTermComparator.areEquals(nTerminalRange, status);
    }

    public static boolean isCTerminalRange(Position position){
        if (position == null){
            return false;
        }

        CvTerm cTerminalRange = new DefaultCvTerm(Position.C_TERMINAL_RANGE, new DefaultExternalIdentifier(new DefaultCvTerm(CvTerm.PSI_MI), Position.C_TERMINAL_RANGE_MI));
        CvTerm status = position.getStatus();

        return DefaultCvTermComparator.areEquals(cTerminalRange, status);
    }

    public static boolean isNTerminal(Position position){
        if (position == null){
            return false;
        }

        CvTerm nTerminal = new DefaultCvTerm(Position.N_TERMINAL, new DefaultExternalIdentifier(new DefaultCvTerm(CvTerm.PSI_MI), Position.N_TERMINAL_MI));
        CvTerm status = position.getStatus();

        return DefaultCvTermComparator.areEquals(nTerminal, status);
    }

    public static boolean isCTerminal(Position position){
        if (position == null){
            return false;
        }

        CvTerm cTerminal = new DefaultCvTerm(Position.C_TERMINAL, new DefaultExternalIdentifier(new DefaultCvTerm(CvTerm.PSI_MI), Position.C_TERMINAL_MI));
        CvTerm status = position.getStatus();

        return DefaultCvTermComparator.areEquals(cTerminal, status);
    }

    public static boolean isRaggedNTerminal(Position position){
        if (position == null){
            return false;
        }

        CvTerm nTerminalRagged = new DefaultCvTerm(Position.RAGGED_N_TERMINAL, new DefaultExternalIdentifier(new DefaultCvTerm(CvTerm.PSI_MI), Position.RAGGED_N_TERMINAL_MI));
        CvTerm status = position.getStatus();

        return DefaultCvTermComparator.areEquals(nTerminalRagged, status);
    }
}
