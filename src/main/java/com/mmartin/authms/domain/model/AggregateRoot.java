package com.mmartin.authms.domain.model;

import com.mmartin.authms.cqrs.event.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor
class AggregateRoot<ID> {

    protected final ID id;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected final void registerEvent(final DomainEvent event) {
        this.domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }
}
