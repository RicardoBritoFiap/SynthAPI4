package com.fiap.synthia.dadosempresa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fiap.synthia.empresa.user.UserModel;
import com.fiap.synthia.empresa.user.UserRepository;

@Controller
@RequestMapping("/dados")
public class DadosController {

    @Autowired
    private DadosRepository dadosRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listDadosForCurrentUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        UserModel currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<DadosModel> dadosList = dadosRepository.findByUserId(currentUser.getId());
        model.addAttribute("dadosList", dadosList);
        return "dados-list"; 
    }

    @GetMapping("/form")
    public String showDadosForm(Model model) {
        model.addAttribute("dados", new DadosModel());
        return "dados-form"; 
    }

    @PostMapping
    public String createDados(@ModelAttribute @Valid DadosModel dados, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "dados-form"; 
        }
        dadosRepository.save(dados);
        return "redirect:/dados"; 
    }

    
}

