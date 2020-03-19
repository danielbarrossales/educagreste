package com.computeiros.educagreste.controladores;

import com.computeiros.educagreste.dtos.CadastroUsuarioDto;
import com.computeiros.educagreste.servicos.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Controller
@RequestMapping("/registration")
public class CadastroUsuarioController {

    @Autowired
    private UserService userService;

    @ModelAttribute("usuario")
    public CadastroUsuarioDto userRegistrationDto() {
        return new CadastroUsuarioDto();
    }

    @GetMapping
    public String mostrarFormularioCadastro(Model model) {
        return "cadastro";
    }

    @PostMapping
    public String cadastrarNovoUsuario(@ModelAttribute("usuario") @Valid CadastroUsuarioDto usuarioDto,
                                       BindingResult result) {

        if (userService.emailOcupied(usuarioDto.getEmail())) {
            result.rejectValue("email", null, "Já existe uma conta registrada com este e-mail");
            return "cadastro";
        }
        /*
        if (result.hasErrors()) {
            return "cadastro";
        }*/

        userService.save(usuarioDto);
        return "redirect:/cadastro?sucesso";
    }
}