package com.PeralesCabrera.denunciaservice.repository;

import com.PeralesCabrera.denunciaservice.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {

    /** Paginación por offset/limit exactos, ordenada por id. */
    @Query(value = "SELECT * FROM denuncias ORDER BY id LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Denuncia> listarPaginado(@Param("offset") int offset, @Param("limit") int limit);

    List<Denuncia> findByDniOrderByFechaDesc(String dni);
}
