package dlt.load.monitor.model;

import dlt.client.tangle.services.ILedgerWriter;

/**
 *
 * @author Uellington Damasceno
 * @version 0.0.1
 */
public class LedgerConnector {
    private ILedgerWriter ledgerWriter;
    
    public void setLedgerWriter(ILedgerWriter ledgerWriter){
        this.ledgerWriter = ledgerWriter;
    }
}
