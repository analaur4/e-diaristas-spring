package br.com.adminediaristas.web.controllers;

import br.com.adminediaristas.web.dtos.FlashMessageDTO;
import br.com.adminediaristas.web.dtos.UsuarioCadastroFormDTO;
import br.com.adminediaristas.web.services.WebUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final WebUsuarioService service;

    @GetMapping
    public ModelAndView buscarTodos() {
        var modelAndView = new ModelAndView("admin/usuario/lista");
        modelAndView.addObject("usuarios", service.buscarTodos());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/usuario/cadastro-form");
        modelAndView.addObject("cadastroForm", new UsuarioCadastroFormDTO());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("cadastroForm") UsuarioCadastroFormDTO cadastroForm,
                            BindingResult result,
                            RedirectAttributes attrs) {
        if(result.hasErrors()) {
            return "admin/usuario/cadastro-form";
        }

        service.cadastrar(cadastroForm);
        attrs.addFlashAttribute("alert", new FlashMessageDTO("alert-success", "Usuário cadastrado com sucesso!"));

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/usuario/edicao-form");
        modelAndView.addObject("edicaoForm", service.buscarFormPorId(id));

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
        service.excluirPorId(id);
        attrs.addFlashAttribute("alert", new FlashMessageDTO("alert-success", "Usuário excluído com sucesso!"));

        return "redirect:/admin/usuarios";
    }
}
