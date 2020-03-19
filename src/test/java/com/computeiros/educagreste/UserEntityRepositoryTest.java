package com.computeiros.educagreste;

import com.computeiros.educagreste.entities.UserEntity;
import com.computeiros.educagreste.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;


@SpringBootTest
class UserEntityRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void injectedComponentsAreNotNull() {
		Assert.notNull(userRepository, "The class '" + userRepository.getClass().getName() + "' was not injected by Spring");
	}

	@Test
	void salvarUsuarioEncontrarUsuario(){
		UserEntity ue1,ue = new UserEntity("Fulano", "de Tal", "fulano.tal@algo.com", "strong_password");
		Assert.notNull(userRepository.save(ue), "Usuário não foi salvo");
		Assert.notNull(userRepository.findByEmail(ue.getEmail()), "Usuário não foi encontrado por e-mail");
	}

}
