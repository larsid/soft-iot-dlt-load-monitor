# soft-iot-dlt-load-monitor

O `soft-iot-dlt-load-monitor` é o _bundle_ responsável por monitorar a carga <!-- (CPU, RAM e QTD dispositivos) --> do _gateway_ e enviar para o [soft-iot-dlt-client-tangle](https://github.com/larsid/soft-iot-dlt-client-tangle#readme) transações informativas sobre a carga.

## Instalação

Para instalar o `load-monitor` é necessário [configurar o repositório fonte](https://github.com/larsid/soft-iot-dlt-architecture#repositório-fonte) e em seguida executar o seguinte comando no terminal do servicemix.

    bundle:install mvn:com.github.larsid/soft-iot-dlt-load-monitor/master

O `load-monitor` faz uso do serviço `ILedgerWriter` do `soft-iot-dlt-client-tangle` e por isso o mesmo deve estar instalado e executando para que o monitor possa iniciar.

## Configurações

| Propriedade       | Descrição                                                             | Valor padrão |
| ----------------- | --------------------------------------------------------------------- | ------------ |
| SAMPLING_INTERVAL | Define o intervalo de amostragem em milisegundos.                     | 1000         |
| LOAD_LIMIT        | Define qual é o valor minimo necessário para iniciar o balanceamento. | 10           |

---

| :arrow_left: [auth](https://github.com/larsid/soft-iot-dlt-auth#readme) | ............................... :arrow_up: [Voltar ao topo](#soft-iot-dlt-load-monitor) :arrow_up: ............................... | [client-tangle](https://github.com/larsid/soft-iot-dlt-client-tangle#readme) :arrow_right: |
| :---------------------------------------------------------------------: | ---------------------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------: |
