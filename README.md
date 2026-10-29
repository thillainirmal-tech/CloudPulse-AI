# CloudPulse AI

Explainable Kubernetes signal correlation with Prometheus, Grafana and safety-first runbooks.

[![CI](https://github.com/thillainirmal-tech/CloudPulse-AI/actions/workflows/ci.yml/badge.svg)](https://github.com/thillainirmal-tech/CloudPulse-AI/actions/workflows/ci.yml)

CloudPulse correlates CPU, memory, error rate, p95 latency and restart signals into a health score, probable cause, evidence findings and read-only diagnostic plan.

Run locally with `mvn spring-boot:run` and open http://localhost:8087. Run the full observability stack with `docker compose up --build`: dashboard 8087, Prometheus 9090, Grafana 3000.

The bundled telemetry is synthetic. The analyzer never mutates Kubernetes resources; production changes require human approval.

Verify using `mvn verify`.

Roadmap: Kubernetes API read-only adapter, PromQL query packs, alert grouping, OpenTelemetry traces and SLO burn-rate windows.

Apache-2.0.
