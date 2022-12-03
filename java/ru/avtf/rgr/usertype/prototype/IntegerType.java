package ru.avtf.rgr.usertype.prototype;

import android.os.Build;
import android.support.annotation.RequiresApi;

import ru.avtf.rgr.comparator.Comparator;
import ru.avtf.rgr.comparator.IntegerComparator;
import ru.avtf.rgr.usertype.type.IntegerClass;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.stream.Collectors;

public class IntegerType implements ProtoType {

    @Override
    public String typeName() {
        return "Integer";
    }

    @Override
    public Object create() {
        // генерация случайных чисел [-1000; 1000]
        int min = -1000;
        int max = 1000;
        Random rand = new Random();
        IntegerClass intValue = new IntegerClass(rand.nextInt((max - min)) + min);
        return intValue;
    }

    @Override
    public Object clone(Object obj) {
        IntegerClass copyInt = new IntegerClass(((IntegerClass)obj).getValue());
        return copyInt;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Object readValue(InputStream inputStream) {
        IntegerClass integerClass = new IntegerClass(Integer.parseInt(new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"))));
        return integerClass;
    }

    @Override
    public Object parseValue(String someString) {
        return new IntegerClass(Integer.parseInt(someString));
    }

    @Override
    public Comparator getTypeComparator() {
        Comparator comparator = new IntegerComparator();
        return comparator;
    }
}
