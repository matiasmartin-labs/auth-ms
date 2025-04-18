package com.mmartin.authms.cqrs.command;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String value();
}
