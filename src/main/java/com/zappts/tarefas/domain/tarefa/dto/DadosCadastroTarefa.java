package com.zappts.tarefas.domain.tarefa.dto;

import com.zappts.tarefas.domain.tarefa.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTarefa(
        @NotBlank
        String titulo,
        String descricao,
        @NotNull
        Status status
) {
}
