package dlt.load.monitor.model;

import br.uefs.larsid.extended.mapping.devices.services.IDevicePropertiesManager;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uellington Damasceno
 */
public class LoadMonitor implements Runnable {

    private final ScheduledExecutorService executor;
    private Processor processor;
    private int samplingInterval;
    private IDevicePropertiesManager deviceManager;

    public LoadMonitor(int samplingInterval, int sampling) {
        this.samplingInterval = samplingInterval;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void setDeviceManager(IDevicePropertiesManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    public void start() {
        this.executor.scheduleAtFixedRate(this, 0, this.samplingInterval, TimeUnit.SECONDS);
    }

    public void stop() {
        this.executor.shutdown();
        try {
            if (this.executor.awaitTermination(500, TimeUnit.MILLISECONDS) && !this.executor.isShutdown()) {
                this.executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            this.executor.shutdownNow();
        }
    }

    @Override
    public void run() {
        try {
            int qtdDevices;
            qtdDevices = this.deviceManager.getAllDevices().size();
            this.processor.updateBrokerStatus(qtdDevices);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(LoadMonitor.class.getName()).log(Level.SEVERE, null, ex);
            this.stop();
        }
    }
}
