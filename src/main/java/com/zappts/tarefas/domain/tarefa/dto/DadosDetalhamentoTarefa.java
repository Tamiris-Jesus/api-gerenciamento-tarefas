package com.zappts.tarefas.domain.tarefa.dto;

import com.zappts.tarefas.domain.tarefa.Tarefa;
import com.zappts.tarefas.domain.tarefa.enums.Status;

import java.time.LocalDateTime;

public record DadosDetalhamentoTarefa(Long id,
                                      String titulo,
                                      String descricao,
                                      Status status,
                                      LocalDateTime dataCriacao,
                                      LocalDateTime dataConclusao) {
    public DadosDetalhamentoTarefa(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus(), tarefa.getDataCriacao(), tarefa.getDataConclusao());
    }
}
