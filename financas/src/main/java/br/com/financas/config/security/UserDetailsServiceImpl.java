package br.com.financas.config.security;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.financas.model.UsuarioModel;
import br.com.financas.repository.UsuarioRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	
	final UsuarioRepository usuarioRepository;
	
	public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioModel usuarioModel = usuarioRepository.findByUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com username: " + username));
		return new User(usuarioModel.getUsername(), usuarioModel.getPassword(), true, true, true, true, usuarioModel.getAuthorities());
	}

}
