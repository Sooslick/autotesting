package ru.sooslick.qa.core;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

@UtilityClass
public class NameChainUtils {
    private final static String SEPARATOR = "->";

    public LinkedList<String> getChainLinks(String chain) {
        return Arrays.stream(chain.split(SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
