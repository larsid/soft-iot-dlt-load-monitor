package dlt.load.monitor.model;

import dlt.client.tangle.hornet.model.transactions.IndexTransaction;
import dlt.client.tangle.hornet.model.transactions.Status;
import dlt.client.tangle.hornet.model.transactions.Transaction;
import dlt.client.tangle.hornet.services.ILedgerWriter;
import dlt.id.manager.services.IDLTGroupManager;
import dlt.id.manager.services.IIDManagerService;

/**
 *
 * @author Antonio Crispim, Uellington Damasceno
 * @version 0.0.1
 */
public class LedgerConnector {

    private ILedgerWriter ledgerWriter;
    private IDLTGroupManager groupManager;
    private IIDManagerService idManager;

    public void setLedgerWriter(ILedgerWriter ledgerWriter) {
        this.ledgerWriter = ledgerWriter;
    }

    public void setGroupManager(IDLTGroupManager groupManager){
        this.groupManager = groupManager;
    }
    
    public void setIdManager(IIDManagerService idManager){
        this.idManager = idManager;
    }
    

    public void put(int lastCharge, boolean lbEntry, Long avgLoad, Long lastLoad, boolean available) throws InterruptedException {
        String group = groupManager.getGroup();
        
        String source = this.buildSource();
        Transaction transaction = new Status(source, group, lbEntry, avgLoad, lastLoad,available);
        IndexTransaction indexedTransaction = new IndexTransaction(transaction.getType().name(), transaction);
        this.ledgerWriter.put(indexedTransaction);
    }
    
    
    private String buildSource() {
        
        String port = System.getenv("GATEWAY_PORT");
        port = port == null ? "1883" : port;
        
      return new StringBuilder(this.groupManager.getGroup())
        .append("/")
        .append(this.idManager.getIP())
        .append("/")
        .append(port)
        .toString();
    }
}
