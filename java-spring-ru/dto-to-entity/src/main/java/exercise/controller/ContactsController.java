package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List<ContactDTO> show() {
        return contactRepository.findAll()
                .stream().map(this::mapContact)
                .toList();
    }

    @GetMapping("/{id}")
    public ContactDTO show(@PathVariable Long id) {
        return mapContact(contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact with id" + id + " not found")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactCreateDTO) {
        Contact contact = contactRepository.save(mapContact(contactCreateDTO));
        return mapContact(contact);
    }

    private ContactDTO mapContact(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());
        dto.setId(contact.getId());
        dto.setPhone(contact.getPhone());
        dto.setLastName(contact.getLastName());
        dto.setFirstName(contact.getFirstName());
        return dto;
    }


    private Contact mapContact(ContactCreateDTO contact) {
        Contact dto = new Contact();
        dto.setPhone(contact.getPhone());
        dto.setLastName(contact.getLastName());
        dto.setFirstName(contact.getFirstName());
        return dto;
    }

}
