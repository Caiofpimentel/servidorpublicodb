package com.caiofpimentel.servidorpublicodb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.caiofpimentel.servidorpublicodb.entity.ServidorPublico;
import com.caiofpimentel.servidorpublicodb.service.ServidorPublicoService;

import org.springframework.ui.Model;

@Controller
public class ServidorPublicoController {

    private ServidorPublicoService servidorService;
	
	@Autowired
	public void setServidorPublicoService(ServidorPublicoService servidorService)
	{
		this.servidorService = servidorService;
	}

    @GetMapping("/listarServidores")
    public String listAll(Model model) {
        
        model.addAttribute("servidorespublicos", servidorService.findAll());

        return "servidorespublicos";
       
    }

    @GetMapping("/listarServidor/{matricula}")
    public String listAll(@PathVariable Long matricula, Model model) {
        
        model.addAttribute("servidorpublico", servidorService.findByMatricula(matricula).get());

        return "servidorpublico";
       
    }

    @GetMapping("/excluirServidor/{matricula}")
    public String excluirServidor(@PathVariable Long matricula) {
        
        servidorService.delete(matricula);
        
        return "redirect:/listarServidores";
    }

    @GetMapping("/formularioEditarServidor/{matricula}")
    public String formEditarServidor(@PathVariable Long matricula, Model model) {
        Optional<ServidorPublico> servidorEncontrado = servidorService.findByMatricula(matricula);
        model.addAttribute("servidorPublico", servidorEncontrado);
        return "editarservidorpublico";
      
    }

    @PostMapping("/editarServidor/{matricula}")
    public String editarServidor(@ModelAttribute ServidorPublico servidor) {
        
        servidorService.update(servidor);
        
        return "redirect:/listarServidores";
    }


    @GetMapping("/formularioNovoServidor")
    public String formNovoServidor(Model model) {

        model.addAttribute("servidorPublico", new ServidorPublico());
        return "novoservidorpublico";
        
      
    }

    @PostMapping("/cadastrarServidor")
    public String cadastrarServidor(@ModelAttribute ServidorPublico novoServidor) {

        Optional<ServidorPublico> servidorExistente = servidorService.findByMatricula(novoServidor.getMatricula());
        if (servidorExistente.isPresent()) {
            // Se o servidor já existe, redireciona para a página de erro ou lista
            return "redirect:/mensagem";
        } else {
            // Se não existe, salva o novo servidor
            servidorService.save(novoServidor);
             return "redirect:/listarServidores";
        }
      
    }

    @GetMapping("/mensagem")
    public String mensagem(Model model) {
        model.addAttribute("erroMatriculaExistente", true);
        return "erro/mensagem";
    }

}
