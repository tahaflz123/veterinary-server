package com.veterinaryserver.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.veterinaryserver.entity.animal.Animal;
import com.veterinaryserver.model.request.AnimalCreationRequest;
import com.veterinaryserver.model.request.AnimalUpdateRequest;
import com.veterinaryserver.model.response.SearchResponse;
import com.veterinaryserver.service.AnimalService;


@Controller
@RequestMapping("/api/animal")
public class AnimalController {

	private final Logger logger = LoggerFactory.getLogger(AnimalController.class);
	
	@Autowired
	private AnimalService animalService;
	
	
	@GetMapping("/create")
	public String createAnimalForm(Model model) {
		model.addAttribute("createanimal", new AnimalCreationRequest());
		return "animal/createanimal";
	}
	
	@PostMapping("/create")
	public ModelAndView createAnimal(@ModelAttribute AnimalCreationRequest request) {
		Animal animal = animalService.createAnimal(request);
		ModelAndView mav = new ModelAndView("animal/animalview");
		mav.addObject("animal",animal);
		return mav;
	}
	
	@GetMapping
	public ModelAndView findById(@PathParam("id") Long id) {
		ModelAndView mav = new ModelAndView("animal/animalview");
		Animal animal = this.animalService.findById(id);
		mav.addObject("animal", animal);
		return mav;
	}
	
	@GetMapping("/all")
	public ModelAndView findAll(){
		List<Animal> animals = this.animalService.findAll();
		ModelAndView mav = new ModelAndView("animal/animals");
		mav.addObject("animals", animals);
		return mav;
	}
	
	@GetMapping("/update")
	public String updateAnimalAbout(@PathParam("id")Long id,Model model) {
		model.addAttribute("update", new AnimalUpdateRequest());
		model.addAttribute("id", id);
		return "animal/animalupdate";
	}
	
	@PostMapping("/update")
	public ModelAndView updateAnimalAbout(@PathParam("id") Long id,@ModelAttribute AnimalUpdateRequest request) {
		logger.info("Animal Update Request {}", request.toString());
		Animal animal = this.animalService.updateAnimal(id, request);
		ModelAndView mav = new ModelAndView("animal/animalview");
		mav.addObject("animal", animal);
		return mav;
	}
	
	@GetMapping("/admin/delete")
	public String deleteWithGetRequest(@PathParam("id") Long id) {
		this.animalService.deleteById(id);
		return "redirect:/api/animal/all";
	}
	
	@GetMapping("/search")
	public ModelAndView searchAnimals(@PathParam("q") String q) {
		List<Animal> animals = this.animalService.findAnimalWithAnimalNameAndUserName(q);
		ModelAndView mav = new ModelAndView("search/searchanimal");
		mav.addObject("animals", animals);
		return mav;
	}
	
	@DeleteMapping("/admin/delete")
	public String deleteById(@PathParam("id") Long id) {
		this.animalService.deleteById(id);
		return "redirect:/api/animal/all";
	}
	
	
}
