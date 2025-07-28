package com.luv2code.springboot.cruddemo.Controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/list")
    public String getEmployees(Model theModel)
    {
        List<Employee> list=employeeService.findAll();
        theModel.addAttribute("employees",list);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
            public String showFormForAdd(Model theModel)
    {
        Employee theEmployee=new Employee();
        theModel.addAttribute("employee",theEmployee);
        return "employees/employee-form";
    }
    @PostMapping("/save")
     public String saveEmployee(@ModelAttribute("employee")Employee theEmployee)
    {
        employeeService.save(theEmployee);
        return "redirect:/employees/list";

    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model)
    {
        Employee theEmployee=employeeService.findById(id);
        model.addAttribute("employee",theEmployee); //prefill the form
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id) {
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }





}
