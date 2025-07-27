package ru.sooslick.qa.core.helper;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Utility class to work with name chains.
 */
@UtilityClass
public class NameChainHelper {

    /**
     * Separator for name chain links.
     */
    public final static String SEPARATOR = "->";

    /**
     * Method splits given chain by separator and writes all parts to linked list.
     *
     * @param chain name chain to split.
     * @return linked list of links in order from first to last.
     */
    public LinkedList<String> getChainLinks(String chain) {
        return Arrays.stream(chain.split(SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Splits given name chain by separator and returns it's first name
     *
     * @param chain name chain to split
     * @return first name in chain
     */
    public String getFirst(String chain) {
        return getChainLinks(chain).get(0);
    }
}
