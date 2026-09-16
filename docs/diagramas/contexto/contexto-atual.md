# Diagrama de contexto atual

```mermaid
flowchart LR
  Cliente[Cliente] --> FinCore[FinCore]
  FinCore --> PIX[Serviço PIX legado]
  FinCore --> TED[Serviço TED legado]
  FinCore --> SMS[Gateway SMS legado]
  FinCore --> WA[API WhatsApp legada]
```

O diagrama mostra apenas o limite do sistema e suas relações externas.
