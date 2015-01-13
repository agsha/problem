package com.jsonfixture.parser;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
/**
 * Created by sgururaj on 1/9/15.
 */
public class Symbol {
    private String name;
    private String qualifiedClassName;

    public Symbol(String name, String qualifiedClassName) {
        this.name = Preconditions.checkNotNull(name);
        this.qualifiedClassName = Preconditions.checkNotNull(qualifiedClassName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    public void setQualifiedClassName(String qualifiedClassName) {
        this.qualifiedClassName = qualifiedClassName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", name).add("class", qualifiedClassName).toString();
    }
}
