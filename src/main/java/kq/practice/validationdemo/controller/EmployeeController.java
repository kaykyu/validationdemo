package kq.practice.validationdemo.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import kq.practice.validationdemo.model.Employee;
import kq.practice.validationdemo.repo.EmployeeRepo;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo repo;

    @GetMapping("/list")
    public String employeeList(Model model) {
        List<Employee> employees = repo.findAll();
        model.addAttribute("employees", employees);
        return "employeelist";
    }

    @GetMapping("/addnew")
    public String employeeAdd(Model model) {

        Employee emp = new Employee();
        model.addAttribute("employee", emp);

        return "employeeadd";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result,
            Model model) throws FileNotFoundException {

        if (result.hasErrors()) {
            return "employeeadd";
        }

        repo.save(employeeForm);
        model.addAttribute("savedEmployee", employeeForm);

        return "success";
    }

    @GetMapping("/employeedelete/{email}")
    public String deleteEmployee(@PathVariable("email") String email) {

        Employee emp = repo.findByEmail(email);
        repo.delete(emp);
        return "redirect:/employees/list";
    }

    @GetMapping("/employeeupdate/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model model) {

        Employee emp = repo.findByEmail(email);
        model.addAttribute("employee", emp);
        return "employeeupdate";
    }

    @PostMapping("/updEmployee")
    public String updEmployeeRecord(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "employeeupdate";
        }

        repo.updateEmployee(employeeForm);
        return "redirect:/employees/list";
    }
}
