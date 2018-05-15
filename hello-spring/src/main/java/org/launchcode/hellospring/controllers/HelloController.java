package org.launchcode.hellospring.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class HelloController {

    @RequestMapping(value="")
    @ResponseBody

    public String index(HttpServletRequest request){
        String name = request.getParameter("name");

        if (name == null) {
            name = "World";
        }
        return "Hello " + name;
    }

    @RequestMapping(value="hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloForm() {
        String html = "<form method='post'>" +
                "<input type='text' name='name' />" +
                "<select name='language'>" +
                    "<option value='English'>English</option>" +
                    "<option value='French'>French</option>" +
                    "<option value='German'>German</option>" +
                    "<option value='Spanish'>Spanish</option>" +
                    "<option value='Italian'>Italian</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me!'/>" +
                "</form>";
        return html;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String helloPost(HttpServletRequest request){
        String name = request.getParameter("name");
        String language = request.getParameter("language");
        return createMessage(name, language);
    }

    @RequestMapping(value = "hello/{name}")
    @ResponseBody
    public String helloUrlSegment(@PathVariable String name) {
        return "Hello " + name;

    }


    @RequestMapping(value="goodbye")
    @ResponseBody
    public String goodbye() {
        return "redirect:/";
    }

    public static String createMessage(String name, String language) {
        String greeting = "Hello";
        if (language.equals("French")) {
            greeting = "Bonjour";
        } else if (language.equals("German")){
            greeting = "Hallo";
        } else if (language.equals("Spanish")) {
            greeting = "Hola";
        } else if (language.equals("Italian")) {
            greeting = "Ciao";
        }

        return greeting + " " + name + "!";

    }
}
