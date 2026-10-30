package dev.thillainirmal.cloudpulse;

import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SnapshotValidationTest {
    @Test void rejectsImpossibleTelemetryAndUnsafeLabels() {
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            var invalid = new PulseAnalyzer.Snapshot("../cluster", "payments api", 120, -1, 101, -10, -2);
            assertThat(factory.getValidator().validate(invalid)).hasSizeGreaterThanOrEqualTo(7);
        }
    }
}
