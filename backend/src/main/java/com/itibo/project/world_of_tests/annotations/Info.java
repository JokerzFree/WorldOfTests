package com.itibo.project.world_of_tests.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Info(
        classType = Info.ClassType.Annotation,
        description = "Annotation for description typization",
        createdBy = "JokerZ",
        lastModified = "21.03.2017"
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Info {
    public enum ClassType{
        Class, Property, Config, Controller, Entity, Model, Service, Exception, Annotation
    }

    ClassType classType() default ClassType.Class;

    String createdBy() default "Unknown";

    String lastModified() default "Unknown";

    String description() default "Empty";
}
