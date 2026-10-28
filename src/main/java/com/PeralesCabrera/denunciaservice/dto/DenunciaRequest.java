package com.PeralesCabrera.denunciaservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record DenunciaRequest(
        @NotBlank @Size(min = 8, max = 8) String dni,
        @NotNull LocalDateTime fecha,
        @NotBlank @Size(max = 30) String tipo,
        @NotBlank @Size(max = 200) String direccion,
        @NotBlank @Size(max = 255) String descripcion
) {
}
