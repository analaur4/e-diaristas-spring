package br.com.adminediaristas.web.controllers;

import br.com.adminediaristas.core.enums.IconeEnum;
import br.com.adminediaristas.web.dtos.FlashMessageDTO;
import br.com.adminediaristas.web.dtos.ServicoFormDTO;
import br.com.adminediaristas.web.services.WebServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final WebServicoService service;

    @ModelAttribute("icones")
    public IconeEnum[] getIcones() {
        return IconeEnum.values();
    }

    @GetMapping
    public ModelAndView buscarTotos() {
        var modelAndView = new ModelAndView("admin/servico/lista");
        modelAndView.addObject("servicos", service.buscarTodos());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("form", new ServicoFormDTO());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") ServicoFormDTO form, BindingResult result, RedirectAttributes attrs) {
        if(result.hasErrors()) {
            return "/admin/servico/form";
        }

        service.cadastrar(form);
        attrs.addFlashAttribute("alert", new FlashMessageDTO("alert-success", "Serviço cadastrado com sucesso!"));

        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/servico/form");

        modelAndView.addObject("form", service.buscarPorId(id));

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, @Valid @ModelAttribute("form") ServicoFormDTO form, BindingResult result, RedirectAttributes attrs) {
        if(result.hasErrors()) {
            return "admin/servico/form";
        }

        service.editar(form, id);
        attrs.addFlashAttribute("alert", new FlashMessageDTO("alert-success", "Serviço editado com sucesso!"));

        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
        service.excluirPorId(id);
        attrs.addFlashAttribute("alert", new FlashMessageDTO("alert-success", "Serviço excluido com sucesso!"));

        return "redirect:/admin/servicos";
    }
}
