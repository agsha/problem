package com.jsonfixture.pluggable;

import com.google.common.base.MoreObjects;

/**
* Created by sgururaj on 1/9/15.
*/
public class Position {
    private int from;
    private int to;
    private String referenceName;

    public Position(int from, int to, String referenceName) {
        this.from = from;
        this.to = to;
        this.referenceName = referenceName;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public String getReferenceName() {
        return referenceName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("from", from).add("to", to).add("name", referenceName).toString();
    }
}
