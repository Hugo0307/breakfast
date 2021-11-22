package br.com.mv.breakfast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mv.breakfast.dto.RequestNewForm;
import br.com.mv.breakfast.model.Breakfast;
import br.com.mv.breakfast.repository.BreakfastRepository;

@Controller
public class HomeController {
	
	@Autowired
	private BreakfastRepository br;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/breakfastList")
	public String breakfastList(Model model) {
		model.addAttribute("breakfastList", br.breakfastList());
		return "breakfastList";
	}
	
	@PostMapping("/edit")
	public String breakfast(Model model, RequestNewForm requestNewForm) {
		Breakfast breakfast = requestNewForm.toBreakfast();
		 
		model.addAttribute("breakfast", br.breakfastById(breakfast.getId()));
		return "edit";
	}
	
	@PostMapping("/home")
	public String saveBreakfastForm(RequestNewForm requestNewForm) throws Exception {
		Breakfast breakfast = requestNewForm.toBreakfast();
		br.savedBreakfast(breakfast);
		return "redirect:/breakfastList";
	}
	
	@RequestMapping("/update")
	@PutMapping
	public String updateBreakfastForm(RequestNewForm requestNewForm, Model model) {
		Breakfast breakfast = requestNewForm.toBreakfast();
		br.updateBreakfast(breakfast);
		return breakfastList(model);
	}
	
	@RequestMapping("/delete")
	@DeleteMapping
	public String deleteBreakfastForm(RequestNewForm requestNewForm, Model model) {
		Breakfast breakfast = requestNewForm.toBreakfast();
		br.deleteBreakfast(breakfast);
		return breakfastList(model);
	}

}
