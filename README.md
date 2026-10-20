# Project 18: End-to-End GitOps CI/CD Pipeline
### Tech Stack: Jenkins • Maven • SonarQube • Docker Hub • Helm 3 • Argo CD • Kubernetes (Kind)

---

## 🎯 Architecture Overview
This project implements an enterprise-grade GitOps continuous integration and continuous deployment pipeline:

```
[Developer]
    │
    ▼ (git push)
[Application GitHub Repo]
    │ (Webhook)
    ▼
[Jenkins CI Pipeline]
    ├── 1. Code Checkout
    ├── 2. Maven Build & Unit Tests
    ├── 3. SonarQube Code Quality & Security Scan (Quality Gate)
    ├── 4. Docker Build & Tag (amd64 / Intel)
    ├── 5. Push Image to Docker Hub
    └── 6. Update Helm Chart Image Tag in GitOps Repo
            │
            ▼ (git commit image tag)
[GitOps Config Repo (Helm Charts)]
    │
    ▼ (Auto-Sync / Polling)
[Argo CD] (Running inside Kubernetes Kind Cluster)
    │
    ▼ (Deploy & Reconcile)
[Production Kubernetes Cluster (Kind)]
    ├── Deployment (Replicas)
    ├── ClusterIP Service
    └── Ingress / NodePort
```

---

## 📁 Repository Structure
```
devops-project-18-gitops/
├── README.md                     # Project documentation & guides
├── kind-config.yaml              # Local Kubernetes cluster configuration
├── docker-compose-infra.yaml     # Local CI infrastructure (Jenkins + SonarQube)
├── app/                          # Java / Maven microservice source code
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/
├── helm/                         # Helm chart packaging
│   └── java-app/
│       ├── Chart.yaml
│       ├── values.yaml
│       └── templates/
│           ├── deployment.yaml
│           ├── service.yaml
│           └── ingress.yaml
├── gitops/                       # Argo CD declarative application manifests
│   └── argo-application.yaml
└── scripts/                      # Setup and verification automation scripts
```

---

## 🚀 Quick Setup Stages
1. **Infrastructure**: Start Docker, launch Kind cluster with port mapping.
2. **CI Server**: Run SonarQube and Jenkins via Docker Compose.
3. **CD Server**: Install Argo CD inside Kind and expose the dashboard.
4. **Pipeline**: Configure Jenkinsfile with SonarQube Quality Gate.
5. **GitOps Sync**: Connect Argo CD to track Git repository and sync Helm chart.
