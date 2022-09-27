package com.example.ej7.crudvalidation.Persona;

import com.example.ej7.crudvalidation.Persona.DTOS.PersonaDtoIn;
import com.example.ej7.crudvalidation.Persona.DTOS.PersonaDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    //POST -> Al final del archivo hay unos objetos Persona para añadir
    @PostMapping
    public void insertPersona(@RequestBody PersonaDtoIn persona){
        personaService.addPersona(new Persona(persona));
    }

    //PUT
    @PutMapping
    public void actualizaPersona(@RequestBody PersonaDtoIn persona) {
        Consumer<Persona> actualizarPersona = person -> {
            try {
                personaService.getPersonaById(person.getId_persona()); //Si la persona no existe se lanza excepcion
                personaService.modifyPersona(person); //Si existe, se actualiza. Nota que aquí solo se llega si no ha saltado la excepción
            } catch (FileNotFoundException personaNoEncontrada) {
                System.out.println("No se ha encontrado la persona con este id: " + person.getId_persona() + " y por tanto no se ha podido actualizar");
            }
        };
        actualizarPersona.accept(new Persona(persona));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void borrarPersona(@PathVariable("id") Integer id) {
        personaService.deletePersona(id);
    }

    //GET
    @GetMapping("/{id}")
    public PersonaDtoOut mostrarPersonaPorId(@PathVariable("id") Integer id) {
        try {
            return personaService.getPersonaById(id); //Si la persona no existe se lanza excepcion
        } catch (FileNotFoundException personaNoEncontrada) {
            System.out.println("No se ha encontrado la persona con este id: " + id);
            return null;
        }
    }
    @GetMapping("/usuario/{usuario}")
    public List<PersonaDtoOut> mostrarPersonaPorNombre(@PathVariable("usuario") String usuario) {
        List<PersonaDtoOut> listaPersonas  = personaService.getPeopleByUsuarioAttribute(usuario);
        return listaPersonas;
    }
    @GetMapping("/all")
    public List<PersonaDtoOut> mostrarTodasPersonas() {
        return personaService.getAllThePeople();
    }

}

/*
{
    "usuario":"empleado1",
    "password":"biugiuub",
    "name":"Alberto",
    "surname":"Garcia",
    "company_email":"alberto.gdepablos@bosonit.com",
    "personal_email":"garciadepablos82@gmail.com",
    "city":"Madrid",
    "active":true,
    "created_date":"2022-09-01",
    "imagen_url":"https://minneapolisseo1.blogspot.com/2014/10/paisajes-naturales-12-nuevas-imagenes.html",
    "termination_date":null
}

{
    "usuario":"empleado2",
    "password":"ykuygiulg",
    "name":"Laura",
    "surname":"Gallego",
    "company_email":"laura.gallego@nissan.com",
    "personal_email":"laurita@gmail.com",
    "city":"Madrid",
    "active":true,
    "created_date":"2022-09-01",
    "imagen_url":"https://loquesea.com",
    "termination_date":null
}
*/
