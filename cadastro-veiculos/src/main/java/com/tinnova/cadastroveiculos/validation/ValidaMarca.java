package com.tinnova.cadastroveiculos.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MarcaValidator.class)
public @interface ValidaMarca {

    String message() default "Marca de carro inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
