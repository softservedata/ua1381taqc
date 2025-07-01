package com.softserve.param;

import java.util.stream.Stream;

public class StringParams {

    private static Stream<String> blankStrings() {
        return Stream.of(null, "", "  ", "\t\n");
    }
}
