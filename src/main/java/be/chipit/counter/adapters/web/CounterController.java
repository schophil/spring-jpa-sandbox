package be.chipit.counter.adapters.web;

import be.chipit.counter.domain.model.Counter;
import be.chipit.counter.domain.usecases.IncrementCounterWithLocking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CounterController {

    private final IncrementCounterWithLocking incrementCounterWithLocking;

    @PostMapping("/increment-with-locking")
    public Counter incrementWithLocking(@RequestParam String name, @RequestParam long delayInSeconds) {
        return incrementCounterWithLocking.increment(name, delayInSeconds);
    }

    @PostMapping("/increment-without-locking")
    public Counter incrementWithoutLocking(@RequestParam String name, @RequestParam long delayInSeconds) {
        return incrementCounterWithLocking.increment(name, delayInSeconds);
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public Map<String, Object> handle(Exception e) {
        return Map.of("error", e.getMessage());
    }
}
