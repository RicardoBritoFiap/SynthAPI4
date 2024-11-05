package com.fiap.Sprint4.cadastroEmpresas.repository;

import com.fiap.Sprint4.cadastroEmpresas.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByEmailAndSenha(String email, String senha);
    Optional<Empresa> findByEmail(String email);  // Novo método para buscar por email
}
