package com.mmartin.authms.domain.event;

import com.mmartin.authms.domain.model.vo.Username;
import com.mmartin.cqrs.event.DomainEvent;

public record UserRegistered(Username username) implements DomainEvent {
}
