package be.chipit.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCounterApplication {

	public static void main(String[] args) {
		SpringApplication.from(CounterApplication::main).with(TestCounterApplication.class).run(args);
	}

}
