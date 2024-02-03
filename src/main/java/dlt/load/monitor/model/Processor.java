package dlt.load.monitor.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allan Capistrano, Antonio Crispim, Uellington Damasceno
 * @version 0.0.2
 */
public class Processor {

  private final int loadLimit;
  private LedgerConnector connector;
  private long lastSentLbEntry;
  private boolean flag;
  private long lbEntryTimeout;
  private static final Logger logger = Logger.getLogger(
    Processor.class.getName()
  );

  public Processor(int loadLimit) {
    this.loadLimit = loadLimit;
  }

  protected void updateBrokerStatus(Integer qtdDevices)
    throws InterruptedException {
    boolean lbEntry = (qtdDevices >= loadLimit);
    boolean available = ((qtdDevices + 1) < loadLimit);

    logger.log(Level.INFO, "Amount of devices: {0} | Need balancing? {1}", new Object[]{qtdDevices, lbEntry});

    if (lbEntry) { //Se o gateway está sobrecarregado.
      if (
        flag ||
        System.currentTimeMillis() > this.lastSentLbEntry + lbEntryTimeout
      ) {
        // Salva o tempo da última vez que enviou uma transação do tipo LB_ENTRY.
        this.lastSentLbEntry = System.currentTimeMillis();
        this.flag = false;

        this.connector.put(
            qtdDevices,
            true,
            qtdDevices.longValue(),
            qtdDevices.longValue(),
            available
          );
      }
    } else {
      this.connector.put(
          qtdDevices,
          false,
          qtdDevices.longValue(),
          qtdDevices.longValue(),
          available
        );
      this.flag = true;
    }
  }

  public void setConnector(LedgerConnector connector) {
    this.connector = connector;
  }

  public void setLastSentLbEntry(long lastSentLbEntry) {
    this.lastSentLbEntry = lastSentLbEntry;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  public void setLbEntryTimeout(long lbEntryTimeout) {
    this.lbEntryTimeout = lbEntryTimeout;
  }
}
