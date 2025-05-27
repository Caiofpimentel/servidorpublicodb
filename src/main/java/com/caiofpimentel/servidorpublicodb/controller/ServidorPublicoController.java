package com.caiofpimentel.servidorpublicodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.caiofpimentel.servidorpublicodb.service.ServidorPublicoService;

import org.springframework.ui.Model;

@RestController
public class ServidorPublicoController {

    private ServidorPublicoService servidorService;
	
	@Autowired
	public void setServidorPublicoService(ServidorPublicoService servidorService)
	{
		this.servidorService = servidorService;
	}

    @GetMapping("/listarServidores")
    public String listAll(Model model) {
        
        model.addAttribute("servidoresPublicos", servidorService.findAll());

        return "servidoresPublicos";
       
    }

    @GetMapping("/listarServidor/{matricula}")
    public String listAll(@PathVariable Long matricula, Model model) {
        
        model.addAttribute("servidorPublico", servidorService.findByMatricula(matricula));

        return "servidoresPublicos";
       
    }

}
