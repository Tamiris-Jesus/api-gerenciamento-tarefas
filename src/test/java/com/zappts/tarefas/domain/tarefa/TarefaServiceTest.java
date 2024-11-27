package com.zappts.tarefas.domain.tarefa;

import com.zappts.tarefas.domain.tarefa.dto.DadosAtualizacaoTarefa;
import com.zappts.tarefas.domain.tarefa.dto.DadosCadastroTarefa;
import com.zappts.tarefas.domain.tarefa.dto.DadosDetalhamentoTarefa;
import com.zappts.tarefas.domain.tarefa.enums.Status;
import com.zappts.tarefas.infra.exception.ValidacaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TarefaServiceTest {

    @InjectMocks
    private TarefaService service;

    @Mock
    private TarefaRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrar_DeveSalvarTarefaERetornarDetalhamento() {
        DadosCadastroTarefa dados = new DadosCadastroTarefa("Título Teste", "Descrição Teste", Status.PENDENTE);
        Tarefa tarefa = new Tarefa(dados);
        when(repository.save(any(Tarefa.class))).thenReturn(tarefa);

        DadosDetalhamentoTarefa resultado = service.cadastrar(dados);

        assertNotNull(resultado);
        assertEquals("Título Teste", resultado.titulo());
        assertEquals("Descrição Teste", resultado.descricao());
        assertEquals(Status.PENDENTE, resultado.status());
        verify(repository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void editar_DeveAtualizarTarefaERetornarDetalhamento() {
        Tarefa tarefa = new Tarefa(new DadosCadastroTarefa("Título Teste", "Descrição Teste", Status.PENDENTE));
        tarefa.setId(1L);

        DadosAtualizacaoTarefa dados = new DadosAtualizacaoTarefa(1L, "Título Atualizado", "Descrição Atualizada", Status.CONCLUIDA, LocalDateTime.now());

        when(repository.getReferenceById(1L)).thenReturn(tarefa);

        DadosDetalhamentoTarefa resultado = service.editar(dados);

        assertNotNull(resultado);
        assertEquals("Título Atualizado", resultado.titulo());
        assertEquals("Descrição Atualizada", resultado.descricao());
        assertEquals(Status.CONCLUIDA, resultado.status());

        verify(repository, times(1)).getReferenceById(1L);
        verify(repository, times(1)).save(tarefa);
    }

    @Test
    void listar_DeveRetornarListaPaginadaDeTarefas() {
        Tarefa tarefa = new Tarefa(new DadosCadastroTarefa("Título Teste", "Descrição Teste", Status.PENDENTE));
        tarefa.setId(1L);
        Page<Tarefa> page = new PageImpl<>(Collections.singletonList(tarefa));
        when(repository.findAll(any(PageRequest.class))).thenReturn(page);

        PageRequest pageable = PageRequest.of(0, 10);

        Page<DadosDetalhamentoTarefa> resultado = service.listar(pageable);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        assertEquals("Título Teste", resultado.getContent().get(0).titulo());
        assertEquals("Descrição Teste", resultado.getContent().get(0).descricao());
        assertEquals(Status.PENDENTE, resultado.getContent().get(0).status());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void excluir_DeveLancarExcecao_QuandoIdNaoExistir() {
        Long id = 1L;

        doThrow(ValidacaoException.class).when(repository).deleteById(id);

        assertThrows(ValidacaoException.class, () -> service.excluir(id));

        verify(repository, times(0)).deleteById(id);
    }

}
