package com.tde.motorDBInelibus.service.scheduled.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tde.motorDBInelibus.service.AVLCardTransferenciaService;
import com.tde.motorDBInelibus.service.AVLTransferenciaService;
import com.tde.motorDBInelibus.service.BarrasTranferenciaService;
import com.tde.motorDBInelibus.service.MSTranferenciaService;
import com.tde.motorDBInelibus.service.OdometroTransferenciaService;
import com.tde.motorDBInelibus.service.DescargasValidadorSamTransferenciaService;
import com.tde.motorDBInelibus.service.EventoMiniSigoTransferenciaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TransferenciaJob {

    private static final Logger log = LoggerFactory.getLogger(TransferenciaJob.class);

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

    /**
     * Pool fijo: máximo 3 transferencias en paralelo
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Scheduled(cron = "0 * * * * ?")
    public void ejecutarTransferencia() {

        log.info("Inicia proceso de transferencia de datos");

        //submit(() -> avltransferenciaService.transferirDatos(7));
        //submit(() -> msTranferenciaService.minisigotransferirDatos(7));
       // submit(() -> odometroTransferenciaService.transferirDatos(7));

        submit(() -> avlCardtransferenciaService.transferirDatos(7));
       // submit(() -> cardTransferenciaService.transferirDatos(7));

        // submit(() -> barrasTranferenciaService.transferirDatos(7));
        // submit(() -> eventoMiniSigoTransferenciaService.transferirDatos(7));

        log.info("Tareas enviadas al pool");
    }

    private void submit(Runnable task) {
        executorService.submit(() -> {
            long start = System.currentTimeMillis();
            try {
                task.run();
            } catch (Exception e) {
                log.error("Error ejecutando tarea de transferencia", e);
            } finally {
                long time = System.currentTimeMillis() - start;
                log.info("Tarea finalizada en {} ms", time);
            }
        });
    }

    /**
     * Cierre ordenado del pool al apagar la app
     */
    @javax.annotation.PreDestroy
    public void shutdown() {
        log.info("Cerrando ExecutorService...");
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}