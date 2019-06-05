package com.company;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public abstract class Dictionary<W, F> implements FrequencyChecker<W, F> {
    public abstract boolean exists(W word);

    public abstract Set<AbstractMap.Entry<W, F>> entrySet();
}
