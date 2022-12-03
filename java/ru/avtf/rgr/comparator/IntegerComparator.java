package ru.avtf.rgr.comparator;

import ru.avtf.rgr.usertype.type.IntegerClass;

import java.io.Serializable;

public class IntegerComparator implements Comparator, Serializable {
    @Override
    public int compare(Object o1, Object o2) {
        return ((IntegerClass)o1).getValue() - ((IntegerClass)o2).getValue();
    }
}
