## 🛒 Kubernetes 微服務訂單系統 Side Project

> 本專案為個人 Side Project，用於學習並展示 Kubernetes 微服務架構下的訂單系統設計與實作能力。  
> 涵蓋前端、後端、資料庫設計、事件驅動、Kubernetes 部署與 DevOps 流程。

---

### 🎯 專案目標

- 練習 Spring Boot / React 全端開發
- 了解微服務架構拆解流程
- 實作事件驅動與通知系統 (Kafka / RabbitMQ)
- 部署到 Kubernetes 並掌握 CI/CD 流程
- 展示完整系統設計與實作能力，作為面試作品

---

### 🏗️ 系統架構

```
                    ┌────────────────────┐
                    │      Frontend      │
                    │ (React / Next.js)  │
                    └─────────▲──────────┘
                              │ (HTTP)
                              │
                     ┌────────▼─────────┐
                     │   API Gateway    │
                     │ (Ingress / Nginx)│
                     └─▲───────┬────────┘
                       │       │
   ┌───────────────────┘       └───────────────────┐
   │                                               │
┌──▼───────────┐   ┌───────────────┐   ┌───────────▼─────────┐
│ Auth Service │   │ Catalog Svc   │   │  Order Service      │
│  (User/JWT)  │   │ (Products)    │   │ (Order CRUD)        │
└──▲───────────┘   └──────▲────────┘   └───────────┬─────────┘
   │ DB                   │ DB                     │ DB
   │                      │                        │
   │               ┌──────┴──────────┐       ┌─────▼────────┐
   │               │ Payment Service │       │ Notification │
   │               │ (Mock Pay API)  │       │ Service (MQ) │
   │               └────────▲────────┘       └─────▲────────┘
   │                        │                      │
   │                        │                      │
   │                        │          (Kafka / RabbitMQ EventBus)
   │                        │
   │                        ▼
   │                    Payment DB
   │
 User DB
```

- 前端：提供使用者介面與互動
- 後端微服務：
  - **Auth Service**：使用者登入/註冊、JWT 認證
  - **Catalog Service**：商品 CRUD
  - **Order Service**：訂單 CRUD、狀態管理
  - **Payment Service**：模擬付款
  - **Notification Service**：事件驅動通知
- 資料庫：每個服務獨立 DB（MySQL / PostgreSQL）
- 部署：Docker / Kubernetes / Helm

```
order-system/                # 單一 GitHub Repo
├── monolithic/              # Monolithic 版本 (Spring Boot + React)
│   ├── backend/
│   └── frontend/
│
├── services/                # 之後拆分的微服務
│   ├── auth-service/
│   ├── order-service/
│   ├── catalog-service/
│   ├── payment-service/
│   └── notification-service/
│
├── k8s/                     # Kubernetes manifests or Helm charts
│   ├── auth/
│   ├── order/
│   ├── catalog/
│   └── ...
│
└── docs/                    # 系統設計圖、API Spec、ERD、README

```

---

### ✅ 開發 Check List

#### ==階段 1：Monolithic 基礎版==

- [ ] 建立 Monolithic Spring Boot + React 專案
- [ ] 使用 MySQL 資料庫，完成基本 schema
- [ ] REST API：Auth / Products / Orders
- [ ] 前端頁面：登入、商品列表、下單、訂單查詢
- [ ] Dockerize 前後端，並用 docker-compose 測試

#### ==階段 2：功能加強==

- [ ] 模擬支付 API
- [ ] 訂單完成後通知 (Console 或簡單 Email)
- [ ] 錯誤處理與例外管理
- [ ] 前後端表單驗證

#### ==階段 3：微服務準備==

- [ ] 將 Monolithic 內程式依功能拆分 package
- [ ] 建立 GitHub Actions CI 流程 (Build & Test)

#### ==階段 4：初步微服務化==

- [ ] 拆分 Auth / Catalog / Order 為獨立 Spring Boot 服務
- [ ] 每個服務建 Dockerfile
- [ ] Docker Compose 本地測試多服務整合

#### ==階段 5：完整微服務化==

- [ ] 拆分 Payment / Notification 服務
- [ ] 每個服務獨立 DB schema
- [ ] 實作事件驅動 (Kafka / RabbitMQ)
- [ ] Kubernetes 部署每個服務
- [ ] 配置 Ingress / API Gateway

#### ==階段 6：進階優化==

- [ ] CI/CD 自動部署到 K8s
- [ ] 加入 Observability (Logging / Metrics / Dashboard)
- [ ] API Gateway / Service Mesh (Optional)

---

### 📌 補充（待刪

- 完整 Monolithic → Microservices 轉換過程
- Spring Boot + React 全端開發經驗
- K8s 部署與 CI/CD 實作
- 事件驅動設計與通知系統
- Database schema 設計與微服務隔離

#### ==建議小提醒：==

1. 先把基本 CRUD 與前端介面做好，功能完整比架構漂亮更重要
2. 每拆一個微服務就寫好 Dockerfile 和基本測試，後面部署才順利
3. 紀錄學習過程：每個階段可在 README 或 docs/ 放圖示、ERD、API Spec
4. 版本控制分支策略：Monolithic 用 main，微服務拆分可用 microservices branch
5. 面試展示：Demo、ERD、微服務邊界圖、事件流程圖都是加分項
