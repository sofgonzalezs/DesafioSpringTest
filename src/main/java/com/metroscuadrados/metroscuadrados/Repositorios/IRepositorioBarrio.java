package com.metroscuadrados.metroscuadrados.Repositorios;

import com.metroscuadrados.metroscuadrados.DTO.BarrioDTO;
import com.metroscuadrados.metroscuadrados.Excepciones.NotFoundException;

public interface IRepositorioBarrio {
        BarrioDTO BuscarBarrioPorNombre (String nombre) throws NotFoundException;
}
