package br.com.zupacademy.transacoes.config.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsValueValidator.class)
public @interface ExistsValue {
    String message() default "Valor já cadastrado";
    String value() default "";
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};

    String field();
    Class<?> table();
}
