package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping("cheese")
public class CheeseController {

    static ArrayList<Cheese> cheeses = new ArrayList<>();

    //Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    //handler to display the form. Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {

        Cheese cheese = new Cheese(cheeseName, cheeseDescription);
        cheeses.add(cheese);

        // Redirect to cheese/
        return "redirect:";
    }

    //Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String showRemoveForm(Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", cheeses);

        return "cheese/remove";
    }

    //Request path: cheese/remove
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveForm(@RequestParam ArrayList<Cheese> cheeses) {

        for (Cheese aCheese: cheeses) {
            cheeses.remove(aCheese);
        }

        return "redirect:";
    }

}
