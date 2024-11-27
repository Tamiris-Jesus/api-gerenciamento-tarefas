package com.zappts.tarefas.controller;

import com.zappts.tarefas.domain.tarefa.TarefaService;
import com.zappts.tarefas.domain.tarefa.dto.DadosAtualizacaoTarefa;
import com.zappts.tarefas.domain.tarefa.dto.DadosCadastroTarefa;
import com.zappts.tarefas.domain.tarefa.dto.DadosDetalhamentoTarefa;
import com.zappts.tarefas.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarefas")
public class TarefaController {
    @Autowired
    private TarefaService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTarefa> cadastrar(@RequestBody DadosCadastroTarefa dados) {
        DadosDetalhamentoTarefa tarefaDetalhada = service.cadastrar(dados);
        return new ResponseEntity<>(tarefaDetalhada, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoTarefa> editar(@RequestBody DadosAtualizacaoTarefa dados) {
        DadosDetalhamentoTarefa tarefaDetalhada = service.editar(dados);
        return new ResponseEntity<>(tarefaDetalhada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ValidacaoException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTarefa>> listar(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DadosDetalhamentoTarefa> tarefas = service.listar(pageable);
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }


}
