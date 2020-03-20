package com.computeiros.educagreste;

import com.computeiros.educagreste.entidades.UserEntity;
import com.computeiros.educagreste.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
class TestRepositorioUsuario {

	@Autowired
	private UserRepository userRepository;

	@Test
	void injectedComponentsAreNotNull() {
		Assert.notNull(userRepository, "A classe '" + userRepository.getClass().getName() + "' não foi injetada pelo sprint");
	}

	@Test
	void salvarUsuarioEncontrarUsuario(){
		UserEntity ue = new UserEntity("Fulano", "de Tal", "fulano.tal@algo.com", "strong_password");
		Assert.notNull(userRepository.save(ue), "Usuário não foi salvo");
		Assert.notNull(userRepository.findByEmail(ue.getEmail()), "Usuário não foi encontrado por e-mail");
	}

}
