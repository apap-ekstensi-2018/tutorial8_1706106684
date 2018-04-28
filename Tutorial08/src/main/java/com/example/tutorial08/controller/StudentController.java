package com.example.tutorial08.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tutorial08.model.StudentModel;
import com.example.tutorial08.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentDAO;

    @RequestMapping("/admin/student/add")
    public String add (Model model)
    {
        model.addAttribute("pageTitle", "Add New Student");

        return "form-add";
    }


    @RequestMapping("/admin/student/add/submit")
    public String addSubmit (
            Model model,
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gpa", required = false) double gpa)
    {
        StudentModel student = new StudentModel(npm, name, gpa);
        studentDAO.addStudent(student);
        model.addAttribute("pageTitle", "Add Student");

        return "success-add";
    }


    @RequestMapping("/student/view")
    public String view (Model model, @RequestParam(value = "npm", required = false) String npm)
    {
        StudentModel student = studentDAO.selectStudent(npm);

        if (student != null) {
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "View Student");

            return "view";
        } else {
            model.addAttribute("npm", npm);
            model.addAttribute("pageTitle", "Student Not Found");

            return "not-found";
        }
    }


    @RequestMapping("/student/view/{npm}")
    public String viewPath (Model model, @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent(npm);

        if (student != null) {
            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "View Student");

            return "view";
        } else {
            model.addAttribute("npm", npm);
            model.addAttribute("pageTitle", "Student Not Found");

            return "not-found";
        }
    }

    @RequestMapping("/admin/student/viewall")
    public String view (Model model)
    {
        List<StudentModel> students = studentDAO.selectAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("pageTitle", "View All Students");

        return "viewall";
    }

    @RequestMapping("/admin/student/update/{npm}")
    public String updatePath (Model model, @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent(npm);
        model.addAttribute("pageTitle", "Update Student");

        if (student != null) {
        	model.addAttribute("student", student);

            return "form-update";
        } else {
            model.addAttribute("pageTitle", "Student Not Found");

            return "not-found";
        }
    }

    @PostMapping("/admin/student/update/submit")
    public String updateSubmit (Model model, @ModelAttribute StudentModel student)
    {
        model.addAttribute("pageTitle", "Update Student");

    	if (student.getNpm().isEmpty() || student.getName().isEmpty()) {
    		return "failed-update";
    	} else {
			studentDAO.updateStudent(student);

			return "success-update";
    	}
    }

    @RequestMapping("/admin/student/delete/{npm}")
    public String delete (Model model, @PathVariable(value = "npm") String npm)
    {
        StudentModel student = studentDAO.selectStudent(npm);

        if (student != null) {
			studentDAO.deleteStudent(npm);
            model.addAttribute("pageTitle", "Delete Student");

			return "delete";
        } else {
            model.addAttribute("npm", npm);
            model.addAttribute("pageTitle", "Student Not Found");

            return "not-found";
        }
    }

}
