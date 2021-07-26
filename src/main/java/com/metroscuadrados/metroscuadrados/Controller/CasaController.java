package com.metroscuadrados.metroscuadrados.Controller;


import com.metroscuadrados.metroscuadrados.DTO.CasaDTO;
import com.metroscuadrados.metroscuadrados.DTO.ResponseDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import com.metroscuadrados.metroscuadrados.Services.CasaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CasaController {

    @PostMapping("/casa")
    public ResponseEntity metrosCuadrados(@Valid @RequestBody CasaDTO casa) throws NotFoundException {
        ResponseDTO response = new ResponseDTO();
        response.setMetrosCuadrados(CasaService.metrosCuadrados(casa));
        response.setValor(CasaService.valorDeLaCasa(casa));
        response.setHabitacionMasGrande(CasaService.habitacionMasGrande(casa));
        response.setMetrosCuadradosPorHabitacion(CasaService.listaDeMetrosCuadradosPorHabitacion(casa));

        return new ResponseEntity(response, HttpStatus.OK);
    }
}

