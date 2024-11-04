package com.fiap.Sprint4.cadastroEmpresas.controller;

import com.fiap.Sprint4.cadastroEmpresas.model.Empresa;
import com.fiap.Sprint4.cadastroEmpresas.model.EmpresaDTO;
import com.fiap.Sprint4.cadastroEmpresas.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Listar todas as empresas
    @GetMapping
    public List<Empresa> listarTodas() {
        return empresaService.listarTodas();
    }

    // Buscar empresa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
        return empresaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar nova empresa (Cadastro)
    @PostMapping
    public Empresa salvar(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setNome(empresaDTO.getNome());
        empresa.setDescricao(empresaDTO.getDescricao());
        empresa.setEmail(empresaDTO.getEmail());
        empresa.setSenha(empresaDTO.getSenha());
        return empresaService.salvar(empresa);
    }

    // Atualizar descrição da empresa logada
    @PostMapping("/atualizar-descricao")
    public String atualizarDescricao(@RequestParam("descricao") String descricao, Model model) {
        Empresa empresa = empresaService.buscarEmpresaLogada(); // Obter a empresa autenticada
        if (empresa != null) {
            empresa.setDescricao(descricao);
            empresaService.salvar(empresa);  // Atualiza a descrição no banco
            model.addAttribute("empresa", empresa);
        }
        return "empresa-detalhes";  // Renderiza a página de detalhes com a descrição atualizada
    }

    // Excluir empresa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (empresaService.buscarPorId(id).isPresent()) {
            empresaService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}