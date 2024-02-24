package be.chipit.counter.domain.ports;

import be.chipit.counter.domain.model.Counter;

public interface SaveCounterPort {
    void save(Counter counter);
}
