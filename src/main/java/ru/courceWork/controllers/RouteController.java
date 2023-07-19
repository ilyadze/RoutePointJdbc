package ru.courceWork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.courceWork.dao.RouteDAO;
import ru.courceWork.models.Route;
//import ru.alishev.springcourse.dao.PersonDAO;
//import ru.alishev.springcourse.models.Person;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    private final RouteDAO routeDAO;

    @Autowired
    public RouteController(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    @GetMapping()
    public List<Route> getRoutes() {
        return routeDAO.index();
    }

    @GetMapping("/{id}")
    public Route getRoute(@PathVariable("id")int id){
        return routeDAO.show(id);
    }

    @GetMapping("/new")
    public String newRoute(@ModelAttribute("route") Route route) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("route")  Route route,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        routeDAO.save(route);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit( @PathVariable("id") int id) {
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("route") Route route, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        routeDAO.update(id, route);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        routeDAO.delete(id);
        return "redirect:/people";
    }

}
