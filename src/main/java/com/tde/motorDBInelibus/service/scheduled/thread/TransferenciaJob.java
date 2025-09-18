package com.tde.motorDBInelibus.service.scheduled.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tde.motorDBInelibus.service.AVLCardTransferenciaService;
import com.tde.motorDBInelibus.service.AVLTransferenciaService;
import com.tde.motorDBInelibus.service.BarrasTranferenciaService;
import com.tde.motorDBInelibus.service.MSTranferenciaService;
import com.tde.motorDBInelibus.service.OdometroTransferenciaService;
import com.tde.motorDBInelibus.service.StoredProcedureServiceJPA;
import com.tde.motorDBInelibus.service.CardTransferenciaService;
import com.tde.motorDBInelibus.service.DescargasValidadorSamTransferenciaService;
import com.tde.motorDBInelibus.service.EventoMiniSigoTransferenciaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class TransferenciaJob {

    @Autowired
    private AVLTransferenciaService avltransferenciaService;
    
    @Autowired
    private AVLCardTransferenciaService avlCardtransferenciaService;
    
    @Autowired
    private BarrasTranferenciaService barrasTranferenciaService;
    
    @Autowired
    private MSTranferenciaService msTranferenciaService;
    
    
    @Autowired
    private OdometroTransferenciaService odometroTransferenciaService;
    
   @Autowired 
   private DescargasValidadorSamTransferenciaService cardTransferenciaService;
    
    @Autowired
    private EventoMiniSigoTransferenciaService eventoMiniSigoTransferenciaService;
    
    @Autowired 
    private StoredProcedureServiceJPA storedProcedureServiceJPA;
    
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    
    private static final Logger log = LoggerFactory.getLogger(TransferenciaJob.class);

    @Scheduled(cron = "0 * * * * ?")
    public void ejecutarTransferencia() {
        log.info("Inicia proceso de transferencia de datos");
        //BARRAS Y CAMARA 
       scheduledExecutorService.schedule(() -> executeSafely(() -> avltransferenciaService.transferirDatos(7)), 0, TimeUnit.SECONDS);
      scheduledExecutorService.schedule(() -> executeSafely(() -> msTranferenciaService.minisigotransferirDatos(7)), 30, TimeUnit.SECONDS);
       
       // scheduledExecutorService.schedule(() -> executeSafely(() -> barrasTranferenciaService.transferirDatos(7)), 30, TimeUnit.SECONDS);
        scheduledExecutorService.schedule( ()  -> executeSafely(() -> odometroTransferenciaService.transferirDatos(7)), 30, TimeUnit.SECONDS);
        
        //TARJETAS
         scheduledExecutorService.schedule(() -> executeSafely(() -> avlCardtransferenciaService.transferirDatos(7)), 0, TimeUnit.SECONDS);
         scheduledExecutorService.schedule(() -> executeSafely(() -> cardTransferenciaService.transferirDatos(7)), 60, TimeUnit.SECONDS); 
         scheduledExecutorService.schedule(() -> executeSafely(() -> avlCardtransferenciaService.transferirDatos(7)), 0, TimeUnit.SECONDS); 
        
       
            
        // scheduledExecutorService.schedule(() -> executeSafely(() -> eventoMiniSigoTransferenciaService.transferirDatos(7)), 90, TimeUnit.SECONDS);
      //  scheduledExecutorService.schedule(()   -> executeSafely(() -> storedProcedureServiceJPA.ejecutarStoredProcedure()),60, TimeUnit.SECONDS);
        
        log.info("Tareas programadas");
    }

    private synchronized void executeSafely(Runnable task) {
        try {
            task.run();
        } catch (Exception e) {
            log.error("Error ejecutando tarea", e);
        }
    }
}