package be.chipit.counter.domain.usecases;

import be.chipit.counter.domain.model.Counter;
import be.chipit.counter.domain.ports.CounterByNamePort;
import be.chipit.counter.domain.ports.SaveCounterPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncrementCounterWithLocking {

    private final CounterByNamePort counterByNamePort;
    private final SaveCounterPort saveCounterPort;

    /**
     * In this use case we use a default transaction but explicitly lock the counter row to avoid any other client to
     * read or write the row while we are incrementing the counter and potentially doing other work. The other work here
     * is presented as sleeping.
     *
     * @param name The unique name of the counter
     * @param delayInSeconds The amount of time in seconds to "work" after incrementing the counter
     * @return The incremented counter
     */
    @Transactional
    public Counter increment(String name, long delayInSeconds) {
        Counter counter = counterByNamePort.getByName(name, true)
                .orElseGet(() -> createNew(name));
        int newValue = counter.increment();
        saveCounterPort.save(counter);
        log.info("Incremented counter {} to {}", name, newValue);
        try {
            log.info("Sleeping for {} seconds", delayInSeconds);
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
            log.error("Error while sleeping", e);
        }
        return counter;
    }

    private Counter createNew(String name) {
        var counter = new Counter();
        counter.setName(name);
        saveCounterPort.save(counter);
        return counter;
    }
}
