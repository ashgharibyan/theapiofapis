package com.ashgharibyan.apiofapis.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashgharibyan.apiofapis.models.LoginUser;
import com.ashgharibyan.apiofapis.models.User;
import com.ashgharibyan.apiofapis.services.TemplateService;
import com.ashgharibyan.apiofapis.services.UserService;

@Controller
public class LogRegController {

	// Add once service is implemented:
	@Autowired
	private UserService userServ;
	 
	@Autowired 
	private TemplateService templateServ;

	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId!=null) {
			return "redirect:/dashboard";
		}
		// Bind empty User and LoginUser objects to the JSP
		// to capture the form input
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@GetMapping("/dashboard")
	public String home(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId==null) {
			return "redirect:/";
		}
		User user = userServ.findById(userId);
		model.addAttribute("user", user);
		
		ArrayList<String> allApis = user.getAllAPIs();
		System.out.println("--------");
		System.out.println(allApis);
		model.addAttribute("allApis",allApis);
		
		return "/LoggedUserViews/dashboard.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {

		User user = userServ.register(newUser, result);
		System.out.println(user);
		if (result.hasErrors()) {
			// Be sure to send in the empty LoginUser before
			// re-rendering the page.
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}

		// No errors!
		// TO-DO Later: Store their ID from the DB in session,
		// in other words, log them in.
		System.out.println("USER ID in REG");
		System.out.println(user.getId());
		session.setAttribute("user_id", user.getId());

		return "redirect:/dashboard";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model,
			HttpSession session) {

		// Add once service is implemented:
		User user = userServ.login(newLogin, result);

		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}

		// No errors!
		// TO-DO Later: Store their ID from the DB in session,
		// in other words, log them in.
		System.out.println("USER ID in LOGIN");
		System.out.println(user.getId());
		session.setAttribute("user_id", user.getId());
		
		return "redirect:/dashboard";
	}

}
