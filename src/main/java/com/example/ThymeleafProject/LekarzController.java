package com.example.ThymeleafProject;

import com.example.ThymeleafProject.model.SkierowanieDoLekarza;
import com.example.ThymeleafProject.service.LekarzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LekarzController {

    public static class NotFoundException extends RuntimeException {

    }

    @Autowired
    LekarzService lekarzService;

    @GetMapping("/listSkierowania")
    public String listaSkierowania(Model model) {
        model.addAttribute("skierowania", lekarzService.listSkierowaniaDoLekarza());
        return "list-skierowania";
    }

    @GetMapping("/addSkierowanie")
    public String addSkierowanie(Model model) {
        model.addAttribute("skierowanieDoLekarza", new SkierowanieDoLekarza());
        return "add-skierowanie";
    }

    @PostMapping("/addSkierowanie")
    public String createSkierowanie(@Valid @ModelAttribute SkierowanieDoLekarza skierowanieDoLekarza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("skierowanieDoLekarza",skierowanieDoLekarza);
            return "add-skierowanie";
        }
        lekarzService.createSkierowanieDoLekarza(skierowanieDoLekarza.getLekarz(), skierowanieDoLekarza.getPacjent(), skierowanieDoLekarza.getTermin());
        return "redirect:/listSkierowania";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFound()
    {
        return "404";
    }

    @GetMapping("/listSkierowania/{iD}")
    public String showDetailsSkierowanie(@PathVariable int iD, Model model) {
        SkierowanieDoLekarza skierowanieDoLekarza = lekarzService.getSkierowanieDoLekarza(iD);
        if (skierowanieDoLekarza == null) {
            throw new NotFoundException();
        }
        model.addAttribute("skierowanieDoLekarza", skierowanieDoLekarza);
        return "skierowanie-details";
    }
    @GetMapping("listSkierowania/modyfikuj/{iD}")
    public String modyfikujSkierowanie(@PathVariable int iD, Model model) {
        model.addAttribute("skierowanieDoLekarza", lekarzService.getSkierowanieDoLekarza(iD));
        return "modyfikuj-skierowanie";
    }

    @PostMapping("/modyfikuj")
    public String updateSkierowanie(@Valid @ModelAttribute SkierowanieDoLekarza skierowanieDoLekarza, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("skierowanieDoLekarza", skierowanieDoLekarza);
            return "modyfikuj-skierowanie";
        }
        lekarzService.updateSkierowanie(skierowanieDoLekarza);
        return String.format("redirect:/listSkierowania/%d", skierowanieDoLekarza.getiD());
    }

    @GetMapping("/usun/{iD}")
    public String usunSkierowanie(@PathVariable int iD) {
        lekarzService.deleteSkierowanieDoLekarza(iD);
        return "redirect:/listSkierowania";
    }
}


