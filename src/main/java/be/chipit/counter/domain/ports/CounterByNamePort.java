package be.chipit.counter.domain.ports;

import be.chipit.counter.domain.model.Counter;

import java.util.Optional;

public interface CounterByNamePort {
    Optional<Counter> getByName(String name, boolean lockForUpdate);
}
