package com.mmartin.authms.domain.model;

import com.mmartin.cqrs.event.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
class AggregateRoot<T> {

    protected final T id;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected final void registerEvent(final DomainEvent event) {
        this.domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }
}
