package com.onsystem.wscapp.pantheon.input.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.function.Function;

public class Utils {


    //TODO test
    public static <T, R> boolean elementsRepeatedInList(final @NotEmpty List<T> objects,
                                                        final @Nullable Function<T, R> fn_extract_data_in_object) {
        //If is one elements
        //TODO CollectionUtils.containsAny()?
        if (fn_extract_data_in_object == null) {
            for (int i = 0; i < objects.size(); i++) {
                T o1 = objects.get(i);
                for (int j = 0; j < objects.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    T o2 = objects.get(j);
                    if (equalsMethod(o1, o2)) {
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < objects.size(); i++) {
                R o1 = fn_extract_data_in_object.apply(objects.get(i));
                for (int j = 0; j < objects.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    R o2 = fn_extract_data_in_object.apply(objects.get(j));
                    if (equalsMethod(o1, o2)) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    private static <T> boolean equalsMethod(T t, T o) {
        return t.equals(o);
    }
}
