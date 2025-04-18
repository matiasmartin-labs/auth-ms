package com.mmartin.authms.cqrs.command;

public interface CommandHandler<C extends CommandRequest<R>, R> {

    R execute(C command);

}
