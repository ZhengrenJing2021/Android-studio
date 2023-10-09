package com.homework.myapplication.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    private static  List<String> shapes = new ArrayList<>();

    public static String getShape(int j) {
        shapes.add("circle");
        shapes.add("cone");
        shapes.add("oval");
        shapes.add("cylinder");
        shapes.add("hexagon");
        shapes.add("cube");
        shapes.add("octagon");
        shapes.add("triangle");
        return shapes.get(j);
    }
}
