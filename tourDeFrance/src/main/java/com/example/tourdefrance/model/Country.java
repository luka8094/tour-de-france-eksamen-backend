package com.example.tourdefrance.model;

// Inspireret af denne kilde: https://stackoverflow.com/questions/17741721/getting-string-value-from-enum-in-java
// Enum klasse til at klassificere de enkelte rytter's land
public enum Country {
    DENMARK("dk"),
    FRANCE("fr"),
    GERMANY("ger"),
    POLAND("pl"),
    SWEDEN("swe"),
    BELGIUM("bg"),
    CZECH_REPUBLIC("cz"),
    NORWAY("nor");

    private final String countryName;

    Country(String name) {
        countryName = name;
    }
}
