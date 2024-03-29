# soft-iot-dlt-load-monitor

O `soft-iot-dlt-load-monitor` é o _bundle_ responsável por monitorar a carga <!-- (CPU, RAM e QTD dispositivos) --> do _gateway_ e enviar para o [soft-iot-dlt-client-tangle](https://github.com/larsid/soft-iot-dlt-client-tangle#readme) transações informativas sobre a carga.

## Instalação

Para instalar o `load-monitor` é necessário [configurar o repositório fonte](https://github.com/larsid/soft-iot-dlt-architecture#repositório-fonte) e em seguida executar o seguinte comando no terminal do servicemix.

    bundle:install mvn:com.github.larsid/soft-iot-dlt-load-monitor/master

O `oft-iot-dlt-load-monitor` faz uso de serviços dos bundles `soft-iot-dlt-client-tangle`, `SOFT-IoT-DLT-ID-Manager`, `soft-iot-mapping-devices`, `SOFT-IoT-DLT-Auth` e por isso os mesmos devem estar instalados e executando para que o `oft-iot-dlt-load-monitor` possa iniciar.
## Configurações

| Propriedade       | Descrição                                                             | Valor padrão |
| ----------------- | --------------------------------------------------------------------- | ------------ |
| SAMPLING_INTERVAL | Define o intervalo de amostragem em milissegundos.                    | 1000         |
| LOAD_LIMIT        | Define qual é o valor mínimo necessário para iniciar o balanceamento. | 10           |
| LB_ENTRY_TIMEOUT  | Define qual o valor em milissegundos para reenviar uma transação do tipo [`LB_ENTRY`](https://github.com/larsid/soft-iot-dlt-client-tangle/blob/master/src/main/java/dlt/client/tangle/model/transactions/Status.java#L15). | 8000         |

---

| :arrow_left: [auth](https://github.com/larsid/soft-iot-dlt-auth#readme) | ............................... :arrow_up: [Voltar ao topo](#soft-iot-dlt-load-monitor) :arrow_up: ............................... | [client-tangle](https://github.com/larsid/soft-iot-dlt-client-tangle#readme) :arrow_right: |
| :---------------------------------------------------------------------: | ---------------------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------: |
