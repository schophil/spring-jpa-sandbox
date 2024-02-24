package be.chipit.counter.adapters.repository;

import be.chipit.counter.domain.model.Counter;
import be.chipit.counter.domain.ports.CounterByNamePort;
import be.chipit.counter.domain.ports.SaveCounterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounterRepository implements CounterByNamePort, SaveCounterPort {

    private final JpaCounterRepository jpaCounterRepository;

    @Override
    public void save(Counter counter) {
        jpaCounterRepository.save(counter);
        // force a flush
        jpaCounterRepository.flush();
    }

    @Override
    public Optional<Counter> getByName(String name, boolean lockForUpdate) {
        if (lockForUpdate) {
            return jpaCounterRepository.getByNameLocked(name);
        }
        return jpaCounterRepository.getByName(name);
    }
}
