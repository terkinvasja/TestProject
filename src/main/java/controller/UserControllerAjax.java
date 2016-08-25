package controller;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;
import service.UserService;

import java.util.List;

@Controller
public class UserControllerAjax
{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    //-------------------Retrieve All Users--------------------------------------------------------
    @RequestMapping(value = "/usersajax/users/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listUsers(Model model)
    {
        List<User> users = this.userService.listUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    //-------------------Create a User--------------------------------------------------------
    @RequestMapping(value = "/usersajax/users/", method = RequestMethod.POST)
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder ucBuilder)
    {
        logger.info("Creating User. User details: " + user);

        userService.addUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/usersajax/users/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a User --------------------------------------------------------
    @RequestMapping(value = "/usersajax/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        logger.info("Updating User " + id);

        User currentUser = userService.getUserById(id);

        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.updateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------
    @RequestMapping(value = "/usersajax/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);

        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.removeUser(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    //------------------- Find a User --------------------------------------------------------
    @RequestMapping(value = "/usersajax/users/find/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findUsers(@PathVariable("name") String name) {
        logger.info("Find User " + name);

        List<User> users = userService.getUsersByName(name);

        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}
