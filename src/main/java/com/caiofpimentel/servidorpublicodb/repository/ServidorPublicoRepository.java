package com.caiofpimentel.servidorpublicodb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.caiofpimentel.servidorpublicodb.entity.ServidorPublico;


@Repository
public interface ServidorPublicoRepository extends CrudRepository<ServidorPublico, Long> {
    // Aqui você pode adicionar métodos personalizados de consulta, se necessário

}
