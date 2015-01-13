package com.jsonfixture.parser;

import com.google.common.base.MoreObjects;

/**
* Created by sgururaj on 1/9/15.
*/
public class SymbolData {
    private Symbol symbol;
    private String json;

    SymbolData(Symbol symbol, String json) {
        this.symbol = symbol;
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("symbol", symbol).add("json", json).toString();
    }
}
