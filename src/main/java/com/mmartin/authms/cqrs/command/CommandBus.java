package com.mmartin.authms.cqrs.command;

public interface CommandBus {

    <R> R send(CommandRequest<R> command);
}
