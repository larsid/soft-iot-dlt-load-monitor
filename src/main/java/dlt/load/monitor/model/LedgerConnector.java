package dlt.load.monitor.model;

import dlt.client.tangle.model.transactions.Status;
import dlt.client.tangle.model.transactions.Transaction;
import dlt.client.tangle.services.ILedgerWriter;
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
        
        String source = new StringBuilder()
                .append(group)
                .append("/")
                .append(idManager.getIP())
                .toString();

        Transaction transaction = new Status(source, group, lbEntry, avgLoad, lastLoad,available);
        this.ledgerWriter.put(transaction);
    }
}
