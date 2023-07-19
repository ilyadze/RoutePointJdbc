package ru.courceWork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.courceWork.dao.PointDAO;
import ru.courceWork.models.Point;
import ru.courceWork.util.PointErrorResponse;
import ru.courceWork.util.PointNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/points")
public class PointController {

    private final PointDAO pointDAO;

    @Autowired
    public PointController(PointDAO pointDAO) {
        this.pointDAO = pointDAO;
    }

    @GetMapping()
    public List<Point> getPoints() {
        return pointDAO.index();
    }

    @GetMapping("/{id}")
    public Point getPoint(@PathVariable("id") int id) {
        System.out.println(pointDAO.show(id));
        return pointDAO.show(id);
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("point") Point point) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("point")  Point point,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        pointDAO.save(point);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit( @PathVariable("id") int id) {
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("point") Point point, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        pointDAO.update(id, point);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        pointDAO.delete(id);
        return "redirect:/people";
    }

    @ExceptionHandler
    private ResponseEntity<PointErrorResponse> handleException(PointNotFoundException e) {
        PointErrorResponse response = new PointErrorResponse(
                "Point with this id wasn't found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
