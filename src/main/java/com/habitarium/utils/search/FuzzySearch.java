package com.habitarium.utils.search;

import main.java.entity.Property;

import java.util.*;

public class FuzzySearch {

    public static int distance(String s1, String s2) {
        int s1Size = s1.length() + 1;
        int s2Size = s2.length() + 1;
        int[][] d = new int[s1Size][s2Size];
        for (int[] row : d)
            Arrays.fill(row, 0);

        for (int i = 1; i < s1Size; i++) {
            d[i][0] = i;
        }
        for (int j = 1; j < s2Size; j++) {
            d[0][j] = j;
        }
        int substitutionCost;
        for (int i = 1; i < s1Size; i++) {
            for (int j = 1; j < s2Size; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    substitutionCost = 0;
                else
                    substitutionCost = 1;

                d[i][j] = Math.min(d[i - 1][j] + 1, // deletion
                        Math.min(d[i][j - 1] + 1, // insertion
                                d[i - 1][j - 1] + substitutionCost) // substitution
                );
            }
        }
        return d[s1Size - 1][s2Size - 1];
    }

    public static Property getPropertySearch(List<Property> propertyList, String pattern) {
        Map<Integer, Property> closestString = new HashMap<>();

        for (Property property : propertyList) {
            closestString.put(distance(pattern, property.getNeighbour()), property);
        }
        int indexClosestStr = Collections.min(closestString.keySet());
        return closestString.get(indexClosestStr);
    }

}
