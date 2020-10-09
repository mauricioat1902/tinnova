package com.tinnova.cadastroveiculos.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class MarcaValidator implements ConstraintValidator<ValidaMarca, String> {
    private static final List<String> MARCAS = Arrays.asList(
            "abarth",
            "aixam",
            "alfa romeo",
            "aston martin",
            "audi",
            "austin",
            "bentley",
            "bmw",
            "burstner",
            "cadillac",
            "caterham",
            "chevrolet",
            "chrysler",
            "citroen",
            "dacia",
            "daewoo",
            "datsun",
            "dodge",
            "ds",
            "ferrari",
            "fiat",
            "ford",
            "honda",
            "hummer",
            "hyundai",
            "infiniti",
            "isuzu",
            "iveco",
            "jaguar",
            "jdm",
            "jeep",
            "jensen-healey",
            "joint",
            "kia",
            "lamborghini",
            "lancia",
            "land rover",
            "lexus",
            "ligier",
            "lincoln",
            "lotus",
            "maserati",
            "maybach",
            "mazda",
            "mercedes-benz",
            "mg",
            "microcar",
            "mini",
            "mitsubishi",
            "nissan",
            "opel",
            "peugeot",
            "porsche",
            "renault",
            "rolls royce",
            "rover",
            "saab",
            "seat",
            "skoda",
            "smart",
            "ssangyong",
            "subaru",
            "suzuki",
            "tesla",
            "toyota",
            "triumph",
            "umm",
            "volkswagen",
            "volvo"
    );

    @Override
    public boolean isValid(String marca, ConstraintValidatorContext context) {
        return MARCAS.contains(marca.toLowerCase());
    }
}