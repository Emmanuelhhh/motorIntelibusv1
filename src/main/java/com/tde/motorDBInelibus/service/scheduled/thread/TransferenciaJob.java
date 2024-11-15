package com.tde.motorDBInelibus.service.scheduled.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tde.motorDBInelibus.service.TransferenciaService;

@Component
public class TransferenciaJob {
    @Autowired
    private TransferenciaService transferenciaService;
    @Scheduled(cron = "0 * * * * ?") // Ejecuta cada hora, ajusta según necesites
    public void ejecutarTransferencia() {
    	System.out.println("INICA PROCESO DE TARSFERENCIA DE DATOS");
        Integer status = 7; // El estatus que necesitas transferir
        transferenciaService.transferirDatos(status);
    }
}
