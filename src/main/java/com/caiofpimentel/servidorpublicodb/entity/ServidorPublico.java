package com.caiofpimentel.servidorpublicodb.entity;

import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "servidorpublico")
public class ServidorPublico {
    
    @Id   
    private Long matricula;

    private String nome;
    private String foto;
    private String orgao;
    private String vinculo;
    private String cargo;
    private String lotacao;
    private String exercicio;
    private String email;
    private String telefone;
    private String celular;
    private String cpf;
    private String naturalidade;

}
