package dev.tpjava.colleguesapi.util;

import dev.tpjava.colleguesapi.service.CollegueService;

import java.util.Arrays;
import java.util.List;

public abstract class Constantes {
    public static final List<String> NOMS = Arrays.asList(
            "Martin",
            "Bernard",
            "Thomas",
            "Petit",
            "Robert",
            "Richard",
            "Durand",
            "Dubois",
            "Moreau",
            "Laurent",
            "Simon",
            "Michel",
            "Lef",
            "Leroy",
            "Roux",
            "David",
            "Bertrand",
            "Morel",
            "Fournier",
            "Girard"
    );

    public static final List<String> PRENOMS = Arrays.asList(
            "Jean",
            "Marie",
            "Pierre",
            "Jeanne",
            "Michel",
            "Françoise",
            "André",
            "Monique",
            "Philippe",
            "Catherine",
            "René",
            "Nathalie",
            "Louis",
            "Isabelle",
            "Alain",
            "Jacqueline",
            "Jacques",
            "Anne",
            "Bernard",
            "Sylvie",
            "Marcel",
            "Martine",
            "Daniel",
            "Madeleine",
            "Roger",
            "Nicole",
            "Robert",
            "Suzanne",
            "Paul",
            "Hélène",
            "Claude",
            "Christine",
            "Christian",
            "Marguerite",
            "Henri",
            "Denise",
            "Georges",
            "Louise",
            "Nicolas",
            "Christiane"
    );

    public static final CollegueService COLLEGUE_SERVICE = new CollegueService(100);
}
