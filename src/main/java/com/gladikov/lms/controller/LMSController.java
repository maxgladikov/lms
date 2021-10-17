package com.gladikov.lms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class LMSController {

    @Autwired
    private EmployeeService employeeService;

    //display the list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {

        model.addAttribute("listEmployees",
                employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model ){
        //create model attribute to bind form data
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        //save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value="id") long id, Model model) {
        //get employee from the service
        Employee employee=employeeService.getEmployeeById(id);
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value="id") long id, Model model) {

        //call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

}


