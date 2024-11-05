package com.fiap.Sprint4.cadastroEmpresas.controller;

import com.fiap.Sprint4.cadastroEmpresas.model.Empresa;
import com.fiap.Sprint4.cadastroEmpresas.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private EmpresaService empresaService;

    // Página inicial
    @GetMapping("/")
    public String index() {
        return "index";  // Renderiza o template index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // Renderiza o template login.html
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";  // Renderiza o template cadastro.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, Model model) {
        Empresa empresa = empresaService.buscarPorEmailESenha(email, senha);
        if (empresa != null) {
            model.addAttribute("empresa", empresa);
            return "empresa-detalhes";  // Renderiza o template empresa-detalhes.html
        }
        model.addAttribute("error", "Email ou senha incorretos");
        return "login";
    }
}
