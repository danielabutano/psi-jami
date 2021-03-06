package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Position;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.PositionUtils;
import psidev.psi.mi.jami.utils.comparator.range.UnambiguousPositionComparator;

import java.util.logging.Logger;

/**
 * Default implementation for Position
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousPositionComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */

public class DefaultPosition implements Position {

    private CvTerm status;
    private long start;
    private long end;
    private boolean isPositionUndetermined;

    private static final Logger log = Logger.getLogger("DefaultPosition");

    /**
     * Create a new Position with status = range.
     * @param start : the fuzzy start
     * @param end : the fuzzy end
     */
    public DefaultPosition(long start, long end){
        if (start > end){
            throw new IllegalArgumentException("The start cannot be after the end.");
        }
        this.start = start;
        this.end = end;
        this.status = CvTermUtils.createRangeStatus();
        isPositionUndetermined = false;
    }

    public DefaultPosition(CvTerm status, long start, long end){
        if (start > end){
            throw new IllegalArgumentException("The start cannot be after the end.");
        }
        this.start = start;
        this.end = end;
        if (status == null){
            throw new IllegalArgumentException("The position status is required and cannot be null");
        }
        this.status = status;
        isPositionUndetermined = (PositionUtils.isUndetermined(this) || PositionUtils.isCTerminalRange(this) || PositionUtils.isNTerminalRange(this));
    }

    public DefaultPosition(CvTerm status, long position){
        if (status == null){
            throw new IllegalArgumentException("The position status is required and cannot be null");
        }
        this.status = status;

        isPositionUndetermined = (PositionUtils.isUndetermined(this) || PositionUtils.isCTerminalRange(this) || PositionUtils.isNTerminalRange(this));
        this.start = position;
        this.end = position;
    }

    /**
     * This constructor will create an undetermined status if the position is 0 and a certain status if the position is not 0.
     *
     * @param position
     */
    public DefaultPosition(long position){
        if (position == 0){
            start = position;
            end = position;
            this.status = CvTermUtils.createUndeterminedStatus();
            isPositionUndetermined = true;
        }
        else {
            start = position;
            end = position;
            this.status = CvTermUtils.createCertainStatus();
            isPositionUndetermined = false;
        }
    }

    public CvTerm getStatus() {
        return this.status;
    }

    public long getStart() {
        return this.start;
    }

    public long getEnd() {
        return this.end;
    }

    public boolean isPositionUndetermined() {
        return this.isPositionUndetermined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Position)){
            return false;
        }

        return UnambiguousPositionComparator.areEquals(this, (Position) o);
    }

    @Override
    public String toString() {
        return getStatus().toString() + ": " + getStart()  +".."+ getEnd();
    }

    @Override
    public int hashCode() {
        return UnambiguousPositionComparator.hashCode(this);
    }
}
