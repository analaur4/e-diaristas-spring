package br.com.adminediaristas.web.controllers;

import br.com.adminediaristas.core.enums.IconeEnum;
import br.com.adminediaristas.core.models.Servico;
import br.com.adminediaristas.core.repositories.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoRepository repository;

    @ModelAttribute("icones")
    public IconeEnum[] getIcones() {
        return IconeEnum.values();
    }

    @GetMapping
    public ModelAndView buscarTotos() {
        var modelAndView = new ModelAndView("admin/servico/lista");
        modelAndView.addObject("servicos", repository.findAll());

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("servico", new Servico());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Servico servico) {
        repository.save(servico);

        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("servico", repository.getById(id));

        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Servico servico) {
        repository.save(servico);

        return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        repository.deleteById(id);

        return "redirect:/admin/servicos";
    }
}
