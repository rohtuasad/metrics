package ru.rohtuasad.metrics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
class ExecutionCounter {

  final ConcurrentMap<String, AtomicLong> counterMap = new ConcurrentHashMap<>();

  public long incrementFor(String method) {
    return counterMap.computeIfAbsent(method, p -> new AtomicLong()).incrementAndGet();
  }

  public long getCount(String method) {
    AtomicLong l = counterMap.get(method);
    return l == null ? 0 : l.get();
  }
}
