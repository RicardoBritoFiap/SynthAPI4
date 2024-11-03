package com.fiap.synthia.dadosempresa;

import com.fiap.synthia.empresa.user.UserModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "DADOS")
@AllArgsConstructor
@NoArgsConstructor
public class DadosModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dados_seq_gen")
    @SequenceGenerator(name = "dados_seq_gen", sequenceName = "dados_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String faturamento;

    @NotBlank
    @Size(min = 1, max = 100)
    private String fluxo_vendas;

    @NotBlank
    @Size(min = 1, max = 100)
    private String clientes;

    @NotBlank
    @Size(min = 1, max = 100)
    private String acessos_plataforma;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserModel usermodel;
}