package ru.rohtuasad.metrics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import ru.rohtuasad.metrics.configuration.ExecutionMetricsConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ExecutionMetricsConfiguration.class)
public @interface EnableExecutionMetrics {

}
