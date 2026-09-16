# Requisitos atualizados — FinCore

Esta lista parte do sistema entregue e deve ser refinada durante as aulas.

## Funcionais
- **RF01** — Cadastrar uma conta com número, cliente, saldo inicial e tipo.
- **RF02** — Realizar transferências PIX.
- **RF03** — Realizar transferências TED.
- **RF04** — Calcular a tarifa conforme a operação.
- **RF05** — Negar transações quando a conta estiver bloqueada ou sem saldo suficiente.
- **RF06** — Submeter transferências à análise antifraude.
- **RF07** — Registrar o histórico das transações.
- **RF08** — Emitir cartões conforme o tipo solicitado.
- **RF09** — Publicar eventos da transação para auditoria/notificação.
- **RF10** — Enviar notificações pelos canais suportados.

## Não funcionais a validar
- **RNF01 — Segurança:** operações financeiras devem preservar integridade e impedir movimentações indevidas.
- **RNF02 — Rastreabilidade:** transações e decisões relevantes devem possuir registro verificável.
- **RNF03 — Manutenibilidade:** integrações e regras devem permitir evolução com baixo impacto.
- **RNF04 — Confiabilidade:** falhas externas não devem deixar saldos e status incoerentes.

Os critérios mensuráveis devem ser definidos pela equipe conforme as atividades.
