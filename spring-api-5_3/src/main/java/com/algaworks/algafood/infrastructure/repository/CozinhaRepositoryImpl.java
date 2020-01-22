package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> listar(){
		TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
		
		return query.getResultList();
	}
	
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		Cozinha cozinha = this.buscar(id);
		
		if(cozinha != null) {
			manager.remove(cozinha);
			return;
		}
		
		
		throw new EmptyResultDataAccessException(1);
	}

	@Override
	public List<Cozinha> consultarPorNome(String nome) {
		// return manager.createQuery("from Cozinha where nome = :nome", Cozinha.class).setParameter("nome", nome).getResultList();
		return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class).setParameter("nome", "%" + nome + "%").getResultList();
	}
	
	
	
}
