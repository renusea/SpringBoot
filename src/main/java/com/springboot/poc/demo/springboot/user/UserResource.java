package com.springboot.poc.demo.springboot.user;

import com.springboot.poc.demo.springboot.exception.PDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Locale;
import java.util.function.Supplier;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private MessageSource messageSource;

   /* private Supplier<Link> self = () ->{
        return WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveUsers()).withRel(("self"));
    };*/

    private Supplier<Link> self = () -> {
        return ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveUsers()).withRel(("self"));
    };

    @GetMapping("/internationalized")
    public String getInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale){
        return messageSource.getMessage("good.morning.message", null, locale);
//        return "Good Morning";
    }

    @GetMapping("/users")
    public ResponseEntity retrieveUsers() {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(userDaoService.findAll());
        //EntityModel<UserResponse> resource = new EntityModel<>(userResponse); //  For SpringBoot 2.2.3-RELEASE version
        Resource<UserResponse> resource = new Resource<UserResponse>(userResponse);
        //WebMvcLinkBuilder self = linkTo(methodOn(this.getClass()).retrieveUsers());
        resource.add(self.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity findUser(@PathVariable Integer id) throws PDException {
        User u = userDaoService.find(id);
        if (u == null) {
            throw new PDException("Not Found", 404, "User not found");
        }

        // For springBoot 2.2.X
        //EntityModel<User> resource = new EntityModel<>(u);
        //WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveUsers());
        //WebMvcLinkBuilder self = linkTo(methodOn(this.getClass()).findUser(id));


        Resource<User> resource = new Resource<User>(u);
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveUsers());
        ControllerLinkBuilder self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findUser(id));

        resource.add(linkTo.withRel(("all-users")));
        resource.add(self.withRel(("self")));
        return ResponseEntity.ok(resource);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        User u = userDaoService.add(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void addUser(@PathVariable Integer id) throws PDException {
        User u = userDaoService.delete(id);
        if (u == null)
            throw new PDException("Not Found", 404, "User not found");
    }

}
