package entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
public class LoginAttempt {

    private AtomicInteger countOfAttempts = new AtomicInteger(0);
    private Instant lastAttemptsTime = Instant.now();

    public AtomicInteger getCountOfAttempts() {
        return countOfAttempts;
    }

    public Instant getLastAttemptsTime() {
        return lastAttemptsTime;
    }

    public void setCountOfAttempts(AtomicInteger countOfAttempts) {
        this.countOfAttempts = countOfAttempts;
    }

    public void incrementCountOfAttempts() {
        countOfAttempts.incrementAndGet();
        lastAttemptsTime = Instant.now();
    }

    public boolean isBlocked(){
        return !(countOfAttempts.get() < 2) || lastAttemptsTime.isBefore(Instant.now().minus(5, ChronoUnit.MINUTES));
    }

    public boolean isBlockedExpired(){
        return lastAttemptsTime.isAfter(Instant.now().plus(5, ChronoUnit.MINUTES));
    }

}
