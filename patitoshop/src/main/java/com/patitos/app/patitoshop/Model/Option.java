package com.patitos.app.patitoshop.Model;

import java.util.ArrayList;

public class Option {

    private ArrayList<String> getColors(){
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Rojo");
        colors.add("Verde");
        colors.add("Amarillo");
        colors.add("Negro");
        return colors;
    }

    private ArrayList<String> getSizes(){
        ArrayList<String> sizes = new ArrayList<>();
        sizes.add("XLarge");
        sizes.add("Large");
        sizes.add("Medium");
        sizes.add("Small");
        sizes.add("XSmall");
        return sizes;
    }

    private ArrayList<String> getSendModes(){
        ArrayList<String> modes = new ArrayList<>();
        modes.add("Tierra");
        modes.add("Aire");
        modes.add("Mar");
        return modes;
    }

    public static ArrayList<String> colors(){
        return new Option().getColors();
    }

    public static ArrayList<String> sizes(){
        return new Option().getSizes();
    }

    public static ArrayList<String> modes(){
        return new Option().getSendModes();
    }
}
