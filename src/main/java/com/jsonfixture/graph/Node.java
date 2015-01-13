package com.jsonfixture.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by sgururaj on 1/9/15.
 */
public class Node<T> {
    private T data;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return data.equals(((Node<T>)obj).data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
