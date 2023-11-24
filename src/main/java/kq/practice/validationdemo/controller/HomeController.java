package kq.practice.validationdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/pagea")
    public String PageA(Model model, HttpSession session) {

        if (session.getAttribute("fullname") != null) {
            model.addAttribute("sessionData", session.getAttribute("fullname").toString());
        } else {
            model.addAttribute("sessionData", "no season object data");
        }
        return "pagea";
    }

    @PostMapping("/pagea")
    public String PageAPost(@RequestBody MultiValueMap<String, String> form, Model model, HttpSession session) {

        String fullName = form.getFirst("fullname");
        System.out.println(fullName);

        session.setAttribute("fullname", fullName);
        model.addAttribute("name", session.getAttribute("fullname").toString());

        return "pageb";
    }

    @GetMapping("/pageb")
    public String PageB(Model model, HttpSession session) {

        String fullName = session.getAttribute("fullname").toString();
        model.addAttribute("name", fullName);
        return "pagec";
    }

    @PostMapping("/pagec")
    public String PageC(Model model, HttpSession session) {
        session.invalidate();
        return "pagea";
    }

}
