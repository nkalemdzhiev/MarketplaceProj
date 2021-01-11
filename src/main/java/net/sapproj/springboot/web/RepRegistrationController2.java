package net.sapproj.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sapproj.springboot.service.AdminService;
import net.sapproj.springboot.web.dto.UserRegistrationDto;
import net.sapproj.springboot.web.dto.RepresRegistrationDto;

@Controller
@RequestMapping("/registration_repres")
public class RepRegistrationController2 {

	private AdminService userService;

	public RepRegistrationController2(AdminService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public RepresRegistrationDto userRegistrationDto() {
        return new RepresRegistrationDto();
    }
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration_repres";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") RepresRegistrationDto registrationDto) {
		userService.save(registrationDto);
		return "redirect:/registration_repres?success";
	}
}
