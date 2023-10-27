package com.example.demo.Person;

import com.example.demo.Product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    private Object productDto;

    public PersonDto getPerson(int id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return toPersonDto(person);
    }

    public List<PersonDto> getPersons() {
        return personRepository
                .findAll()
                .stream().map(this::toPersonDto)
                .toList();
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }

    private PersonDto toPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setLastName(person.getLastName());
        personDto.setFirstName(person.getFirstName());
        personDto.setProducts(person.getProducts().stream().map(product -> {
            ProductDto animalDto = new ProductDto();
            ProductDto.setId(product.getId());
            ProductDto.setName(product.getName());
            return productDto;
        }).toList());
        return personDto;
    }
}
