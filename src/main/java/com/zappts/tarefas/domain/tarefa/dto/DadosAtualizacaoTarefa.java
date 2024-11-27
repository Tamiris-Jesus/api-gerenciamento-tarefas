package com.zappts.tarefas.domain.tarefa.dto;

import com.zappts.tarefas.domain.tarefa.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAtualizacaoTarefa(
        @NotNull
        Long id,
        String titulo,
        String descricao,
        Status status,
        LocalDateTime dataConclusao
) {
}
