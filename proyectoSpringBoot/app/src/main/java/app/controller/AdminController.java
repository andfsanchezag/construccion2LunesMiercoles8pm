package app.controller;

import app.controller.request.CreateUserRequest;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.VeterinaryService;
import app.service.interfaces.AdminService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Setter
@Getter
@NoArgsConstructor
public class AdminController implements ControllerInterface {

    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AdminService service;
    private static final String MENU = "ingrese la opcion que desea \n 1.para crear veterinario \n 2. para crear vendedor \n 3. para cerrar sesion \n";

    @Override
    public void session() throws Exception {
    }

    @GetMapping("/")
    public String vive() {
        return " esta vivo";
    }

    @PostMapping("/veterinarian")
    private ResponseEntity createVeterinarian(@RequestBody CreateUserRequest request) throws Exception {
        try {
            String name = request.getName();
            personValidator.validName(name);
            long document = personValidator.validDocument(request.getDocument());
            int age = personValidator.validAge(request.getAge());
            String userName = request.getUserName();
            userValidator.validUserName(userName);
            String password = request.getPassword();
            userValidator.validPassword(password);
            PersonDto personDto = new PersonDto();
            personDto.setName(name);
            personDto.setDocument(document);
            personDto.setAge(age);
            UserDto userDto = new UserDto();
            userDto.setPersonid(personDto);
            userDto.setUserName(userName);
            userDto.setPassword(password);
            userDto.setRole("veterinarian");
            this.service.createVeterinarian(userDto);
            System.out.println("se ha creado el usuario exitosamente");
            return new ResponseEntity<>("se ha creado el usuario exitosamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    private void createSeller() throws Exception {
        System.out.println("ingrese el nombre del vendedor");
        String name = Utils.getReader().nextLine();
        personValidator.validName(name);
        System.out.println("ingrese la cedula del vendedor");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        System.out.println("ingrese la edad del vendedor");
        int age = personValidator.validAge(Utils.getReader().nextLine());
        System.out.println("ingrese el nombre de usuario del vendedor");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);
        System.out.println("ingrese la contraseña del vendedor");
        String password = Utils.getReader().nextLine();
        userValidator.validPassword(password);
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setDocument(document);
        personDto.setAge(age);
        UserDto userDto = new UserDto();
        userDto.setPersonid(personDto);
        userDto.setUserName(userName);
        userDto.setPassword(password);
        userDto.setRole("seller");
        this.service.createSeller(userDto);
        System.out.println("se ha creado el usuario exitosamente");
    }

}
