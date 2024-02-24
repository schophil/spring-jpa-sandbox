package be.chipit.counter.adapters.repository;

import be.chipit.counter.domain.model.Counter;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface JpaCounterRepository extends JpaRepository<Counter, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Counter c where u.name = ?1")
    Optional<Counter> getByNameLocked(String name);

    Optional<Counter> getByName(String name);
}
