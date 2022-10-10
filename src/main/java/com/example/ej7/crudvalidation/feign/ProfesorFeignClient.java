package com.example.ej7.crudvalidation.feign;

import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOutSimple;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//En name, el servicio que voy a consumir
//En url, el puerto donde estará corriendo ese servicio
@FeignClient(name = "profesor-service", url = "http://localhost:8081")
public interface ProfesorFeignClient {

    //Defino el método:
    @GetMapping("/profesor/{id}")
    ProfesorDtoOutSimple mostrarProfesorPorId(@PathVariable("id") String profesor_id);
}
