package Dominio.controller;

import Dominio.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Dominio.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    // Endpoint to query a user by code
    @SuppressWarnings("rawtypes")
    @GetMapping(path = "/api/user/{code}")
    public ResponseEntity consult(@PathVariable("code") Integer code) {
        return repository.findById(code)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to get the name of a user by code
    @GetMapping(path = "/api/name/{code}")
    public String getName(@PathVariable("code") Integer code) {
        return repository.getName(code);
    }

    // Endpoint to get the phone of a user by code
    @GetMapping(path = "/api/phone/{code}")
    public Long getPhone(@PathVariable("code") Integer code) {
        return repository.getPhone(code);
    }

    // Endpoint to get the CPF of a user by code
    @GetMapping(path = "/api/cpf/{code}")
    public Long getCpf(@PathVariable("code") Integer code) {
        return repository.getCpf(code);
    }

    // Endpoint to get the RG of a user by code
    @GetMapping(path = "/api/rg/{code}")
    public Long getRg(@PathVariable("code") Integer code) {
        return repository.getRg(code);
    }

    // Endpoint to get the nationality of a user by code
    @GetMapping(path = "/api/nacionality/{code}")
    public String getNationality(@PathVariable("code") Integer code) {
        return repository.getNacionality(code);
    }

    // Endpoint to get the age of a user by code
    @GetMapping(path = "/api/age/{code}")
    public Long getAge(@PathVariable("code") Integer code) {
        return repository.getAge((code));
    }

    // Endpoint to save a user
    @PostMapping(path = "/api/user/save")
    public UserModel save(@RequestBody UserModel user) {
        return repository.save(user);
    }

    // Endpoint to get all users
    @GetMapping(path = "/api/users")
    public ResponseEntity<List<Map<String, Object>>> listUsers(@RequestParam(required = false) List<String> attributes) {
        List<UserModel> users = (List<UserModel>) repository.findAll();
        List<Map<String, Object>> results = users.stream()
                .map(user -> createDTO(user, attributes))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(results);
        }
    }

    // Private method to create a DTO with specified attributes
    private Map<String, Object> createDTO(UserModel user, List<String> attributes) {
        Map<String, Object> dto = new HashMap<>();
        if (attributes == null || attributes.isEmpty()) {
            dto.put("id", user.getCode());
            dto.put("name", user.getName());
            dto.put("phone", user.getPhone());
            dto.put("cpf", user.getCpf());
            dto.put("rg", user.getRg());
            dto.put("nationality", user.getNacionality());
            // Add more attributes as needed
        } else {
            attributes.forEach(attribute -> {
                switch (attribute) {
                    case "id":
                        dto.put("id", user.getCode());
                        break;
                    case "name":
                        dto.put("name", user.getName());
                        break;
                    case "phone":
                        dto.put("phone", user.getPhone());
                        break;
                    case "cpf":
                        dto.put("cpf", user.getCpf());
                        break;
                    case "rg":
                        dto.put("rg", user.getRg());
                        break;
                    case "nationality":
                        dto.put("nationality", user.getNacionality());
                        break;
                    case "age":
                        dto.put("age", user.getAge());
                        break;
                    // Add more cases as necessary
                }
            });
        }
        return dto;
    }


    // Endpoint to get specific attributes of a user by code
    @GetMapping(path = "/api/show")
    public ResponseEntity<Map<String, Object>> getCode(@RequestParam("id") int id, @RequestParam("attributes") List<String> attributes) {
        UserModel user = repository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Create a map to store the requested attributes and their values
        Map<String, Object> userAttributes = new HashMap<>();

        // Add the requested attributes to the map
        for (String attribute : attributes) {
            switch (attribute) {
                case "name":
                    userAttributes.put("name", user.getName());
                    break;
                case "cpf":
                    userAttributes.put("cpf", user.getCpf());
                    break;
                case "rg":
                    userAttributes.put("rg", user.getRg());
                    break;
                case "phone":
                    userAttributes.put("phone", user.getPhone());
                    break;
                case "nationality":
                    userAttributes.put("nationality", user.getNacionality());
                    break;
                case "age":
                    userAttributes.put("age", user.getAge());
                    break;
                // Add more cases as necessary
            }
        }

        return ResponseEntity.ok(userAttributes);
    }

}
