# CloudPulse AI

Explainable Kubernetes signal correlation with Prometheus, Grafana and safety-first runbooks.

[![CI](https://github.com/thillainirmal-tech/CloudPulse-AI/actions/workflows/ci.yml/badge.svg)](https://github.com/thillainirmal-tech/CloudPulse-AI/actions/workflows/ci.yml)

## Product tour

![CloudPulse AI Kubernetes diagnosis console](docs/media/product-tour.gif)

[Open the full-resolution screenshot](docs/media/product-console.jpg) · **Recruiter demo:** analyze a telemetry snapshot, inspect the evidence-backed diagnosis, then switch to guarded live Prometheus queries without enabling cluster mutation.

[▶ Watch the recorded diagnosis demo (5 seconds)](docs/media/runtime-demo.mp4)

> **Deployment status:** tested, containerized and ready for an AWS/EKS observability deployment; no public production endpoint is claimed yet.

CloudPulse correlates CPU, memory, error rate, p95 latency and restart signals into a health score, probable cause, evidence findings and read-only diagnostic plan.

Run locally with `mvn spring-boot:run` and open http://localhost:8087. Run the full observability stack with `docker compose up --build`: dashboard 8087, Prometheus 9090, Grafana 3000.

`GET /api/live/{namespace}/{workload}` runs a guarded PromQL query pack against the configured Prometheus server and analyzes live CPU, memory, error, latency and restart signals. Prometheus failures are never replaced with invented data. The analyzer never mutates Kubernetes resources; production changes require human approval.

Verify using `mvn verify`.

Set `PROMETHEUS_URL` for a production metrics source and `GRAFANA_ADMIN_PASSWORD` before starting the supplied stack. Roadmap: Kubernetes topology adapter, alert grouping, OpenTelemetry traces and SLO burn-rate windows.

Apache-2.0.
