package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> list = new ArrayList<>();
        String myDelimeter = "\u2639";

        for(String delimeter : delimiters){
            source = source.replaceAll(delimeter, myDelimeter);
        }

        String[] parts = source.split(myDelimeter);

        for(String part : parts){
            if(!part.isEmpty()) list.add(part);
        }

        return list;
    }
}
