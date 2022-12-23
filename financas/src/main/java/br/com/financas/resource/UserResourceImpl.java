package br.com.financas.resource;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financas.config.security.JwtTokenProvider;
import br.com.financas.dtos.UsuarioDto;
import br.com.financas.repository.RoleRepository;
import br.com.financas.repository.UsuarioRepository;

@RestController
@RequestMapping("/user")
public class UserResourceImpl {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PostMapping(value="/authenticate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> authenticate(@RequestBody UsuarioDto user){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getUsuario(), user.getSenha()));
	
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("token",
				tokenProvider.createToken(
						user.getUsuario(),
						roleRepository.findByUsername(user.getUsuario())
						)
				);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
	}
}
