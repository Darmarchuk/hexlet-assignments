package exercise.controller;

import exercise.mapper.GuestMapper;
import exercise.model.Guest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exercise.repository.GuestRepository;
import exercise.dto.GuestDTO;
import exercise.dto.GuestCreateDTO;
import exercise.exception.ResourceNotFoundException;

@RestController
@RestControllerAdvice
@RequestMapping("/guests")
public class GuestsController {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private GuestMapper guestMapper;

    @GetMapping(path = "")
    public List<GuestDTO> index() {
        var products = guestRepository.findAll();
        return products.stream()
                .map(p -> guestMapper.map(p))
                .toList();
    }

    @GetMapping(path = "/{id}")
    public GuestDTO show(@PathVariable long id) {

        var guest =  guestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Guest with id " + id + " not found"));
        var guestDto = guestMapper.map(guest);
        return guestDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    GuestDTO create(@Valid @RequestBody GuestCreateDTO dto ){
        Guest guest= guestMapper.map(dto);
        return guestMapper.map( guestRepository.save(guest));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", "400" );
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
