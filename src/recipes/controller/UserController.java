package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import recipes.entity.User;
import recipes.services.UserServices;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping(path = "/api/register")
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@RequestBody @Valid User user) {
        userServices.registerUser(user);
    }

}
