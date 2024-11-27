package com.zappts.tarefas.domain.tarefa;

import com.zappts.tarefas.domain.tarefa.dto.DadosAtualizacaoTarefa;
import com.zappts.tarefas.domain.tarefa.dto.DadosCadastroTarefa;
import com.zappts.tarefas.domain.tarefa.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Consumer;

@Entity
@Table
@Data
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    public Tarefa(DadosCadastroTarefa dados){
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.status = dados.status();
        this.dataCriacao = LocalDateTime.now();
    }

    public void editarInformacoes(DadosAtualizacaoTarefa dados){
            atribuirSeForNaoNulo(dados.titulo(), this::setTitulo);
            atribuirSeForNaoNulo(dados.descricao(), this::setDescricao);
            atribuirSeForNaoNulo(dados.status(), this::setStatus);
            atribuirSeForNaoNulo(dados.dataConclusao(), this::setDataConclusao);
    }

    private <T> void atribuirSeForNaoNulo(T valor, Consumer<T> setter) {
        if (Objects.nonNull(valor) && !valor.toString().trim().isEmpty()) {
            setter.accept(valor);
        }
    }
}
