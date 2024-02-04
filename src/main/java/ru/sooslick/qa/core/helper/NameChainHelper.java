package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

@UtilityClass
public class NameChainHelper {
    private final static String SEPARATOR = "->";

    public LinkedList<String> getChainLinks(String chain) {
        return Arrays.stream(chain.split(SEPARATOR))
                .map(String::trim)  // todo I also should trim names at page loading
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
