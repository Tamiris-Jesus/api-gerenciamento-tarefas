package com.zappts.tarefas.domain.tarefa;

import com.zappts.tarefas.domain.tarefa.dto.DadosAtualizacaoTarefa;
import com.zappts.tarefas.domain.tarefa.dto.DadosCadastroTarefa;
import com.zappts.tarefas.domain.tarefa.dto.DadosDetalhamentoTarefa;
import com.zappts.tarefas.infra.exception.ValidacaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {
    private static final Logger logger = LoggerFactory.getLogger(TarefaService.class);

    @Autowired
    private TarefaRepository repository;

    public DadosDetalhamentoTarefa cadastrar(DadosCadastroTarefa dados) {
        logger.info("Iniciando cadastro de nova tarefa com título: {}", dados.titulo());

        Tarefa tarefa = new Tarefa(dados);
        repository.save(tarefa);

        logger.info("Tarefa cadastrada com sucesso. ID: {}", tarefa.getId());
        return new DadosDetalhamentoTarefa(tarefa);
    }

    public DadosDetalhamentoTarefa editar(DadosAtualizacaoTarefa dados) {
        logger.info("Iniciando atualização da tarefa com ID: {}", dados.id());

        Tarefa tarefa = repository.getReferenceById(dados.id());
        tarefa.editarInformacoes(dados);
        repository.save(tarefa);

        logger.info("Tarefa com ID {} atualizada com sucesso.", tarefa.getId());
        return new DadosDetalhamentoTarefa(tarefa);
    }

    public Page<DadosDetalhamentoTarefa> listar(Pageable pageable) {
        logger.info("Listando tarefas com paginação. Página: {}, Tamanho: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<DadosDetalhamentoTarefa> tarefas = repository.findAll(pageable).map(DadosDetalhamentoTarefa::new);

        logger.info("Total de tarefas encontradas: {}", tarefas.getTotalElements());
        return tarefas;
    }

    public void excluir(Long id) {
        logger.info("Solicitação de exclusão para a tarefa com ID: {}", id);

        if (!repository.existsById(id)) {
            logger.warn("Tentativa de exclusão falhou. Tarefa com ID {} não encontrada.", id);
            throw new ValidacaoException("Não foi encontrada uma tarefa com esse id.");
        }

        repository.deleteById(id);
        logger.info("Tarefa com ID {} excluída com sucesso.", id);
    }
}
