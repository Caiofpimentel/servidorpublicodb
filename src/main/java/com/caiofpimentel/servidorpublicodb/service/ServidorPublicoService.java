package com.caiofpimentel.servidorpublicodb.service;

import java.util.List;
import java.util.Optional;

import com.caiofpimentel.servidorpublicodb.entity.ServidorPublico;

public interface ServidorPublicoService  {

    List<ServidorPublico> findAll();  

    Optional<ServidorPublico> findByMatricula(Long matricula);

    void save(ServidorPublico servidorPublico);
        
    void update(ServidorPublico servidorPublico);
    
    void delete(Long matricula);

    

   
}