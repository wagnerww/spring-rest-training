package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradaException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

  private static final String GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois esta em uso";

  @Autowired
  GrupoRepository grupoRepository;

  public List<Grupo> listar() {
    return grupoRepository.findAll();
  }

  public Grupo buscarOutFalhar(Long grupoId) {
    return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradaException(grupoId));
  }

  public Grupo salvar(Grupo grupo) {
    return grupoRepository.save(grupo);
  }

  public void excluir(Long grupoId) {
    try {
      this.buscarOutFalhar(grupoId);
      grupoRepository.deleteById(grupoId);
      
    } catch (EmptyResultDataAccessException e) {

      throw new GrupoNaoEncontradaException(grupoId);
      
    } catch (DataIntegrityViolationException e) {
      
      throw new EntidadeEmUsoException(String.format(GRUPO_EM_USO, grupoId));
    }   

  }

}
