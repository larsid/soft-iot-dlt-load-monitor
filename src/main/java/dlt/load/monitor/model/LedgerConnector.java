package dlt.load.monitor.model;

import dlt.client.tangle.enums.TransactionType;
import dlt.client.tangle.model.Transaction;
import dlt.client.tangle.services.ILedgerWriter;
import dlt.id.manager.services.IDLTGroupManager;
import dlt.id.manager.services.IIDManagerService;

/**
 *
 * @author Uellington Damasceno
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
    

    public void put(int lastCharge, boolean lbEntry) throws InterruptedException {
        String group = groupManager.getGroup();
        
        String source = new StringBuilder()
                .append(group)
                .append("/")
                .append(idManager.getIP())
                .toString();

        String target = "";  // Não precisa.
        long timestamp = System.currentTimeMillis(); //Ir para o construtor.
        String deviceSwap = ""; //Não precisa.

        TransactionType type = (lbEntry)
                ? TransactionType.LB_ENTRY
                : TransactionType.LB_STATUS;

        Transaction transaction = new Transaction(source, type, 0, 0, lbEntry, group, target, timestamp, deviceSwap);
        this.ledgerWriter.put(transaction);
    }
}
