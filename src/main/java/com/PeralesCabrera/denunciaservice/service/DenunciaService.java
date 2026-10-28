package com.PeralesCabrera.denunciaservice.service;

import com.PeralesCabrera.denunciaservice.dto.DenunciaRequest;
import com.PeralesCabrera.denunciaservice.entity.Denuncia;
import com.PeralesCabrera.denunciaservice.repository.DenunciaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DenunciaService {

    private final DenunciaRepository repository;

    public DenunciaService(DenunciaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Denuncia registrar(DenunciaRequest request) {
        Denuncia denuncia = new Denuncia();
        denuncia.setDni(request.dni());
        denuncia.setFecha(request.fecha());
        denuncia.setTipo(request.tipo().toUpperCase());
        denuncia.setDireccion(request.direccion());
        denuncia.setDescripcion(request.descripcion());
        denuncia.setEstado(1);
        return repository.save(denuncia);
    }

    @Transactional(readOnly = true)
    public List<Denuncia> listar(int offset, int limit) {
        return repository.listarPaginado(offset, limit);
    }

    @Transactional(readOnly = true)
    public Denuncia obtener(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Denuncia no encontrada"));
    }

    @Transactional(readOnly = true)
    public List<Denuncia> listarPorCiudadano(String dni) {
        return repository.findByDniOrderByFechaDesc(dni);
    }

    @Transactional
    public Denuncia atender(int id) {
        Denuncia denuncia = obtener(id);
        denuncia.setEstado(0);
        return denuncia;
    }
}
