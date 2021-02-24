package dlt.load.monitor.model;

import dlt.auth.services.IDevicePropertiesManager;
import java.io.IOException;
import java.util.Random;
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
    private int samplingInterval, sampling;
    private IDevicePropertiesManager deviceManager;

    public LoadMonitor(int samplingInterval, int sampling) {
        this.samplingInterval = samplingInterval;
        this.sampling = sampling;
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
        if (this.executor != null) {
            this.executor.shutdown();
            try {
                this.executor.awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                this.executor.shutdownNow();
            }
        }
    }

    @Override
    public void run() {
        try {
            int qtdDevices = this.deviceManager.getAllDevices().size();
            processor.updateBrokerStatus(qtdDevices);
        } catch (IOException ex) {
            Logger.getLogger(LoadMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
