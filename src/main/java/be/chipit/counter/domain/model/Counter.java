package be.chipit.counter.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "counters")
@Data
public class Counter {
    @Id
    @GeneratedValue
    private UUID id;
    @Version
    @Column(name = "version")
    private int version;
    @Column(name = "seq")
    private int sequence;
    @Column(name = "name")
    private String name;

    public int increment() {
        return ++sequence;
    }
}
