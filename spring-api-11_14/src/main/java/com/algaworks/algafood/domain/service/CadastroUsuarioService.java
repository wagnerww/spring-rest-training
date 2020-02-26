package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradaException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

  private static final String MSG_USUARIO = "Usuário de código %d não pode ser removido, pois está em uso";

  @Autowired
  UsuarioRepository usuarioRepository;

  public List<Usuario> listar() {
    return usuarioRepository.findAll();
  }

  public Usuario buscarOuFalhar(Long id) {
    return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradaException(id));
  }

  public Usuario salvar(Usuario usuario) {

    // Isso foi feito de forma customizada em CustomRepository
    // por que o jpa já sincroniza os dados quando faz o set, então ele já diz que
    // existe um email
    // e com o detach do entiti manager ele não faz essa sincronização
    usuarioRepository.detach(usuario);

    Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

    if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
      throw new NegocioException(
          String.format("Já existe um usuário cadastrado com este email %s", usuario.getEmail()));
    }

    return usuarioRepository.save(usuario);
  }

  public void excluir(Long usuarioId) {
    try {

      this.buscarOuFalhar(usuarioId);

      usuarioRepository.deleteById(usuarioId);

    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(String.format(MSG_USUARIO, usuarioId));
    }
  }

  @Transactional
  public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
    Usuario usuario = this.buscarOuFalhar(usuarioId);

    if (usuario.senhaNaoCoincideCom(senhaAtual)) {
      throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
    }

    usuario.setSenha(novaSenha);
  }

}
