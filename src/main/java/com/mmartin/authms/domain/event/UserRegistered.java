package com.mmartin.authms.domain.event;

import com.mmartin.authms.cqrs.event.DomainEvent;
import com.mmartin.authms.domain.model.vo.Username;

public record UserRegistered(Username username) implements DomainEvent {
}
