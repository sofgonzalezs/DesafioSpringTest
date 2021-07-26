package com.metroscuadrados.metroscuadrados.Services;

import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.DTO.CasaDTO;
import com.metroscuadrados.metroscuadrados.DTO.HabitacionDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;
import com.metroscuadrados.metroscuadrados.Repositorios.IRepositorioBarrioImp;

import java.util.ArrayList;
import java.util.List;

public class CasaService {

    public static double metrosCuadradosPorHabitacion(HabitacionDTO habitacion){
            return habitacion.getAncho() * habitacion.getLargo();

    }

    public static double metrosCuadrados(CasaDTO casa){
        double resultado = 0;
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {
            resultado += metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i));
        }

        return resultado;
    }

    public static double valorDeLaCasa(CasaDTO casa) throws NotFoundException {
        IRepositorioBarrioImp traerDatosBarrio = new IRepositorioBarrioImp();
        BarrioDTO barrioTraido = traerDatosBarrio.BuscarBarrioPorNombre(casa.getBarrio());
        return barrioTraido.getValor() * metrosCuadrados(casa);
    }

    public static HabitacionDTO habitacionMasGrande(CasaDTO casa){
        double mayor = 0;
        int posMayor = 0;
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {
            if(metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i)) > mayor){
                mayor = metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i));
                posMayor = i;
            }
        }
        return casa.getHabitaciones().get(posMayor);
    }

    public static List<Double> listaDeMetrosCuadradosPorHabitacion(CasaDTO casa){
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < casa.getHabitaciones().size(); i++) {
            list.add(metrosCuadradosPorHabitacion(casa.getHabitaciones().get(i)));

        }
        return list;
    }


}
