package com.jsonfixture.pluggable;

import com.google.common.base.Optional;

import java.util.List;

/**
 * Created by sgururaj on 1/9/15.
 */

/**
 * Detects references to other objects in a given line.
 * for example
 * {
 *   name: !order1
 * }
 *
 * will detect a reference to !order1
 */
public interface ReferenceDetector {
    /**
     * stops at the detection of first reference and returns an optional position
     * @param data
     * @return the position of the reference in the string
     */
    Optional<Position> detectFirstReference(String data);

    /**
     * scans entire string to detect all references.
     * @param data
     * @return list of all positions in the reference in the string
     */
    List<Position> detectAllReferences(String data);


}
