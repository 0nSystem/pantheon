package com.onsystem.wscapp.pantheon.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.function.Function;

public class Utils {


    //TODO test
    public static <T, R> boolean elementsRepeatedInList(final @NotEmpty List<T> objects,
                                                        final @Nullable Function<T, R> fn_extract_data_in_object) {

        //TODO CollectionUtils.containsAny()?
        if (fn_extract_data_in_object == null) {
            return objects.stream()
                    .anyMatch(o1 ->
                            objects.stream().anyMatch(o2 -> equalsMethod(o1, o2))
                    );
        } else {
            return objects.stream()
                    .anyMatch(o1 ->
                            objects.stream()
                                    .anyMatch(o2 -> equalsMethod(
                                            fn_extract_data_in_object.apply(o1),
                                            fn_extract_data_in_object.apply(o2))
                                    )
                    );
        }

    }
    private static <T> boolean equalsMethod(T t, T o) {
        return t.equals(o);
    }
}
