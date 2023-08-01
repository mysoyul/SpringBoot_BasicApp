package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.ResourceNotFoundException;
import com.basic.myspringboot.repoository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@Controller + @ResponseBody
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private final UserRepository userRepository;

    //Constructor Injection
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id)  //Optional<User>
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @GetMapping("/email/{myemail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("myemail") String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(email + "에 해당하는 사용자가 없습니다.");
        }
        User existUser = userOptional.get();
        return ResponseEntity.ok(existUser);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userDetail.getName());
        user.setEmail(userDetail.getEmail());
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
