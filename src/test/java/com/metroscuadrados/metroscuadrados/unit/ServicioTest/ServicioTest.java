package com.metroscuadrados.metroscuadrados.unit.ServicioTest;


import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.DTO.CasaDTO;
import com.metroscuadrados.metroscuadrados.DTO.HabitacionDTO;
import com.metroscuadrados.metroscuadrados.DTO.ResponseDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrioImp;
import com.metroscuadrados.metroscuadrados.Services.CasaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


    @DisplayName("Test Tu Casita Tasaciones")
    @ExtendWith(MockitoExtension.class)
    public class ServicioTest {

        @Mock
        CasaService service;

        @InjectMocks
        IRepositorioBarrioImp barrio;

        @Mock
        NotFoundException exceptionMock;


        @DisplayName("Verificar que el total de metros cuadrados totales  calculados por propiedad sea el correcto.")
        @Test
        public void metrosCuadradosTest() {
            // Datos falsos necesarios para el metodo
            CasaDTO mockCasa;
            HabitacionDTO hab1 = new HabitacionDTO("C1Hab1", 100, 200);
            HabitacionDTO hab2 = new HabitacionDTO("C1Hab2", 50, 20);
            List<HabitacionDTO> listaDeHabitaciones = new ArrayList<>();
            listaDeHabitaciones.add(hab1);
            listaDeHabitaciones.add(hab2);
            BarrioDTO barrio = new BarrioDTO("Almagro", 50);
            mockCasa = new CasaDTO("CasaTest1", "DirCasa1", "palermo", listaDeHabitaciones);

            // Respuesta esperada del metodo
            ResponseDTO responseExpected = new ResponseDTO();
            responseExpected.setMetrosCuadrados(21000);

            //Respuesta del metodo verdadera es la que sale en postman
            Double respuestaGenerada = service.metrosCuadrados(mockCasa);

            //Assert, es decir, verificar que en realidad devolvio lo que esperamos
            Assertions.assertEquals(responseExpected.getMetrosCuadrados(), respuestaGenerada);

        }


        @DisplayName("Verificar que el barrio de entrada exista en el repositorio de barrios.")
        @Test
        public void BuscarBarrioPorNombreTest () throws NotFoundException {
            //Barrio falso a testear
            BarrioDTO mockBarrioExistente = new BarrioDTO("palermo", 30);
            // BarrioDTO mockBarrioNoExistente = new BarrioDTO("quilmes",10);

            //Respuesta del metodo verdadero
            BarrioDTO respuestaGenerada = barrio.BuscarBarrioPorNombre(mockBarrioExistente.getNombre());

            //Verificamos que me devuelve lo que espero, espero que la respuesta sea del tipo BarrioDTO
            Assertions.assertEquals(mockBarrioExistente, respuestaGenerada);
        }

        @DisplayName("Verificar que efectivamente se devuelva habitaciones con mayor tamaño.")
        @Test
        public void habitacionMasGrandeTest (){
            // Datos falsos necesarios para el metodo
            CasaDTO mockCasa;
            HabitacionDTO hab1 = new HabitacionDTO("C1Hab1", 100, 200);
            HabitacionDTO hab2 = new HabitacionDTO("C1Hab2", 50, 20);
            List<HabitacionDTO> listaDeHabitaciones = new ArrayList<>();
            listaDeHabitaciones.add(hab1);
            listaDeHabitaciones.add(hab2);
            BarrioDTO barrio = new BarrioDTO("Almagro", 50);
            mockCasa = new CasaDTO("CasaTest1", "DirCasa1", "palermo", listaDeHabitaciones);

            ResponseDTO responseExpected = new ResponseDTO();
            responseExpected.setHabitacionMasGrande(hab1);

            HabitacionDTO respuestaGenerada = service.habitacionMasGrande(mockCasa);

            Assertions.assertEquals(responseExpected.getHabitacionMasGrande(),respuestaGenerada);


        }

        @DisplayName("Verificar que efectivamente el total de metros cuadrados por habitacion sea el correcto.")
        @Test
        public void  listaDeMetrosCuadradosPorHabitacion(){
            CasaDTO mockCasa;
            HabitacionDTO hab1 = new HabitacionDTO("C1Hab1", 100, 200);
            HabitacionDTO hab2 = new HabitacionDTO("C1Hab2", 50, 20);
            List<HabitacionDTO> listaDeHabitaciones = new ArrayList<>();
            listaDeHabitaciones.add(hab1);
            listaDeHabitaciones.add(hab2);
            mockCasa = new CasaDTO("CasaTest1", "DirCasa1", "palermo", listaDeHabitaciones);

            List <Double> metrosCuadradosPorHabitacion = new ArrayList<>();
            metrosCuadradosPorHabitacion.add(20000.0);
            metrosCuadradosPorHabitacion.add(1000.0);

            ResponseDTO responseExpected = new ResponseDTO();
            responseExpected.setMetrosCuadradosPorHabitacion(metrosCuadradosPorHabitacion);


            List<Double> respuestaGenerada = service.listaDeMetrosCuadradosPorHabitacion(mockCasa);

            Assertions.assertEquals(responseExpected.getMetrosCuadradosPorHabitacion(),respuestaGenerada);



        }
    }

