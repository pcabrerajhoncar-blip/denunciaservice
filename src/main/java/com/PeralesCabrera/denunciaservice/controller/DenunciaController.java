package com.PeralesCabrera.denunciaservice.controller;

import com.PeralesCabrera.denunciaservice.dto.DenunciaRequest;
import com.PeralesCabrera.denunciaservice.entity.Denuncia;
import com.PeralesCabrera.denunciaservice.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    // POST denuncias/
    @PostMapping({"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public Denuncia registrar(@Valid @RequestBody DenunciaRequest request) {
        return service.registrar(request);
    }

    // GET denuncias/?offset=0&limit=5
    @GetMapping({"", "/"})
    public List<Denuncia> listar(@RequestParam(defaultValue = "0") int offset,
                                 @RequestParam(defaultValue = "5") int limit) {
        if (offset < 0 || limit <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "offset debe ser >= 0 y limit > 0");
        }
        return service.listar(offset, limit);
    }

    // GET denuncias/{id}
    @GetMapping("/{id}")
    public Denuncia obtener(@PathVariable int id) {
        return service.obtener(id);
    }

    // GET denuncias/ciudadano/{dni}
    @GetMapping("/ciudadano/{dni}")
    public List<Denuncia> listarPorCiudadano(@PathVariable String dni) {
        return service.listarPorCiudadano(dni);
    }

    // PUT denuncias/{id}  -> estado 1 a 0 (atendida)
    @PutMapping("/{id}")
    public Denuncia atender(@PathVariable int id) {
        return service.atender(id);
    }
}
