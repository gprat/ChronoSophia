package com.webapp.site;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.site.entities.Category;
import com.webapp.site.entities.Chronology;
import com.webapp.site.entities.Event;
import com.webapp.site.entities.Figure;


@Controller
@RequestMapping("chronology")
public class ChronologyController {

	@Inject ChronologyService chronologyService;
	@Inject FigureService figureService;
	@Inject CategoryService categoryService;
	@Inject CityService cityService;
	@Inject EventService eventService;
	@Inject UserService userService;
	@Inject ObjectMapper objectMapper;
	
	@RequestMapping(value = {"", "list"}, method = RequestMethod.GET)
	public String list(Map<String, Object> model){
		model.put("chronologies", chronologyService.getAllChronologies());
		return "chronology/list";
	}
	
	@RequestMapping(value = {"/{id}/view"}, method = RequestMethod.GET)
	public String view(@PathVariable("id") long id, Model model){
		try {
			model.addAttribute("chronologieJSON", objectMapper.writeValueAsString(this.chronologyService.getChronologies(id)));
			model.addAttribute("titre",this.chronologyService.getChronology(id).getName());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "chronology/view";
	}
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String createChronology(Map<String, Object> model){
		ChronologyForm chronologyForm = new ChronologyForm();
		model.put("chronologyForm", chronologyForm);
		model.put("selectEventForm",new SelectEventForm());
		model.put("AvailableEventList", eventService.getAllEvents());
		try{
			model.put("figuresJSON", objectMapper.writeValueAsString(this.figureService.getAllFigures()));
			model.put("categoriesJSON", objectMapper.writeValueAsString(this.categoryService.getAllCategories()));
			model.put("citiesJSON", objectMapper.writeValueAsString(this.cityService.getAllCities()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "chronology/chronologyForm";
	}
	
	@RequestMapping(value="filter", method = RequestMethod.POST)
	public String FilterEvents(@ModelAttribute("selectEventForm") SelectEventForm selectEventForm,Map<String, Object> model) {
		ChronologyForm chronologyForm = new ChronologyForm();
		chronologyForm.eventList=selectEventForm.eventsToExclude;
		model.put("chronologyForm", chronologyForm);
		try{
			model.put("figuresJSON", objectMapper.writeValueAsString(this.figureService.getAllFigures()));
			model.put("categoriesJSON", objectMapper.writeValueAsString(this.categoryService.getAllCategories()));
			model.put("citiesJSON", objectMapper.writeValueAsString(this.cityService.getAllCities()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		List<Long> categoryList = new ArrayList<>();
		if(selectEventForm.categories!=null&&selectEventForm.categories!=""){
			new ArrayList<String>(Arrays.asList(selectEventForm.categories.split(","))).forEach(idCategory->categoryList.add(Long.parseLong(idCategory)));
		}
		List<Long> figureList = new ArrayList<>();
		if(selectEventForm.figures!=null&&selectEventForm.figures!=""){
			new ArrayList<String>(Arrays.asList(selectEventForm.figures.split(","))).forEach(idFigure->figureList.add(Long.parseLong(idFigure)));
		}
		List<Long> cityList = new ArrayList<>();
		if(selectEventForm.cities!=null&&selectEventForm.cities!=""){
			new ArrayList<String>(Arrays.asList(selectEventForm.cities.split(","))).forEach(idCity->cityList.add(Long.parseLong(idCity)));
		}
		List<Long> eventListToExclude = new ArrayList<>();
		if(selectEventForm.eventsToExclude!=null&&selectEventForm.eventsToExclude!=""){
			new ArrayList<String>(Arrays.asList(selectEventForm.eventsToExclude.split(","))).forEach(idEvent->eventListToExclude.add(Long.parseLong(idEvent)));
			model.put("SelectedEventList", eventService.getEventsById(eventListToExclude));
		}
		model.put("AvailableEventList", eventService.getEventsFiltered(categoryList, figureList, cityList, eventListToExclude));
		
		return "chronology/chronologyForm";
	}
	
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public View createChronology(ChronologyForm chronologyForm){
		Chronology chronology;
		if(chronologyForm.id!=null&&chronologyForm.id!=0){
			chronology=chronologyService.getChronology(chronologyForm.id);
		}
		else{
			chronology=new Chronology();
		}
		chronology.setName(chronologyForm.name);
		List<Event> eventList = new ArrayList<>();
		if(chronologyForm.eventList!=null){
			new ArrayList<String>(Arrays.asList(chronologyForm.eventList.split(","))).forEach(idEvent->eventList.add(eventService.getEvent(Long.parseLong(idEvent))));
		}
		chronology.setEvents(eventList);
		chronology.setUser(this.userService.getUser(1));
		chronology.setCategory(categoryService.getCategory(Long.parseLong(chronologyForm.getCategory())));
		chronologyService.save(chronology);
		return new RedirectView("/chronology/list", true, false);
	}
	
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String ShowUpdateChronologyForm(@PathVariable("id") long id, Model model) {
		ChronologyForm chronologyForm = this.chronologyService.getChronologyForm(id);
		Chronology chronology = this.chronologyService.getChronology(id);
		model.addAttribute("chronologyForm", chronologyForm);
		model.addAttribute("selectEventForm",new SelectEventForm());
		List<Long> eventListToExclude = new ArrayList<>();
		if(chronologyForm.eventList!=null&&chronologyForm.eventList!=""){
			new ArrayList<String>(Arrays.asList(chronologyForm.eventList.split(","))).forEach(idEvent->eventListToExclude.add(Long.parseLong(idEvent)));
			model.addAttribute("SelectedEventList", eventService.getEventsById(eventListToExclude));
		}
		model.addAttribute("AvailableEventList", eventService.getEventsFiltered(null, null, null, eventListToExclude));
		try{
			model.addAttribute("figuresJSON", objectMapper.writeValueAsString(this.figureService.getAllFigures()));
			model.addAttribute("categoriesJSON", objectMapper.writeValueAsString(this.categoryService.getAllCategories()));
			model.addAttribute("citiesJSON", objectMapper.writeValueAsString(this.cityService.getAllCities()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "chronology/chronologyForm";
	}
	
	@RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
	public View deleteChronology(@PathVariable("id") long id){
		this.chronologyService.delete(id);
		return new RedirectView("/chronology/list", true, false);
	}
}
