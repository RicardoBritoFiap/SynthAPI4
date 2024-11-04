package com.fiap.Sprint4.cadastroEmpresas.service;

import com.fiap.Sprint4.cadastroEmpresas.model.Empresa;
import com.fiap.Sprint4.cadastroEmpresas.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void excluir(Long id) {
        empresaRepository.deleteById(id);
    }

    public Empresa buscarPorEmailESenha(String email, String senha) {
        return empresaRepository.findByEmailAndSenha(email, senha).orElse(null);
    }

    // Novo método para obter a empresa logada pelo email
    public Empresa buscarEmpresaLogada() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return empresaRepository.findByEmail(email).orElse(null);
    }
}
