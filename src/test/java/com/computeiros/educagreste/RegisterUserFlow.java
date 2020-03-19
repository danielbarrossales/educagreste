package com.computeiros.educagreste;

import com.computeiros.educagreste.controladores.CadastroUsuarioController;
import com.computeiros.educagreste.dtos.CadastroUsuarioDto;
import com.computeiros.educagreste.entidades.UserEntity;
import com.computeiros.educagreste.repositories.UserRepository;
import com.computeiros.educagreste.servicos.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;


@SpringBootTest
class RegisterUserFlow {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CadastroUsuarioController cadastroUsuarioController;

	@Test
	void injectedComponentsAreNotNull() {
		Assert.notNull(userRepository, "A classe '" + userRepository.getClass().getName() + "' não foi injetada pelo sprint");
		Assert.notNull(userService, "A classe '" + userRepository.getClass().getName() + "' não foi injetada pelo sprint");
	}

	@Test
	void salvarUsuarioEncontrarUsuario(){
		UserEntity ue = new UserEntity("Fulano", "de Tal", "fulano.tal@algo.com", "strong_password");
		Assert.notNull(userRepository.save(ue), "Usuário não foi salvo");
		Assert.notNull(userRepository.findByEmail(ue.getEmail()), "Usuário não foi encontrado por e-mail");
	}

	@Test
	void salvarUsuarioService() {
		var usuarioDto = new CadastroUsuarioDto();
		usuarioDto.setEmail("cicrano@algo.com");
		usuarioDto.setNome("Cicrano");
		usuarioDto.setSobrenome(("de Beltrano"));
		usuarioDto.setSenha("asdfghk");
		usuarioDto.setTermos(true);
		Assert.notNull(userService.save(usuarioDto), "Serviço falhou ao tentar salvar usuário");
		Assert.isTrue((userService.emailOcupied(usuarioDto.getEmail())), "UserService erroneamente afirma que o e-mail não esta sendo usado.");
		Assert.isTrue(!(userService.emailOcupied("asdasd@asdasdas.com")), "UserService erroneamente afirma que o e-mail esta sendo usado.");
	}

	@Test
	void salvarUsuarioControlador(){
		var usuarioDto = new CadastroUsuarioDto();
		usuarioDto.setEmail("cicranoa@algoa.coam");
		usuarioDto.setNome("Cicranoa");
		usuarioDto.setSobrenome(("de Beltrano"));
		usuarioDto.setSenha("asdfghk");
		usuarioDto.setTermos(true);
		var result = new BindingResult() {
			@Override
			public String getObjectName() {
				return null;
			}

			@Override
			public void setNestedPath(String s) {

			}

			@Override
			public String getNestedPath() {
				return null;
			}

			@Override
			public void pushNestedPath(String s) {

			}

			@Override
			public void popNestedPath() throws IllegalStateException {

			}

			@Override
			public void reject(String s) {

			}

			@Override
			public void reject(String s, String s1) {

			}

			@Override
			public void reject(String s, Object[] objects, String s1) {

			}

			@Override
			public void rejectValue(String s, String s1) {

			}

			@Override
			public void rejectValue(String s, String s1, String s2) {

			}

			@Override
			public void rejectValue(String s, String s1, Object[] objects, String s2) {

			}

			@Override
			public void addAllErrors(Errors errors) {

			}

			@Override
			public boolean hasErrors() {
				return false;
			}

			@Override
			public int getErrorCount() {
				return 0;
			}

			@Override
			public List<ObjectError> getAllErrors() {
				return null;
			}

			@Override
			public boolean hasGlobalErrors() {
				return false;
			}

			@Override
			public int getGlobalErrorCount() {
				return 0;
			}

			@Override
			public List<ObjectError> getGlobalErrors() {
				return null;
			}

			@Override
			public ObjectError getGlobalError() {
				return null;
			}

			@Override
			public boolean hasFieldErrors() {
				return false;
			}

			@Override
			public int getFieldErrorCount() {
				return 0;
			}

			@Override
			public List<FieldError> getFieldErrors() {
				return null;
			}

			@Override
			public FieldError getFieldError() {
				return null;
			}

			@Override
			public boolean hasFieldErrors(String s) {
				return false;
			}

			@Override
			public int getFieldErrorCount(String s) {
				return 0;
			}

			@Override
			public List<FieldError> getFieldErrors(String s) {
				return null;
			}

			@Override
			public FieldError getFieldError(String s) {
				return null;
			}

			@Override
			public Object getFieldValue(String s) {
				return null;
			}

			@Override
			public Class<?> getFieldType(String s) {
				return null;
			}

			@Override
			public Object getTarget() {
				return null;
			}

			@Override
			public Map<String, Object> getModel() {
				return null;
			}

			@Override
			public Object getRawFieldValue(String s) {
				return null;
			}

			@Override
			public PropertyEditor findEditor(String s, Class<?> aClass) {
				return null;
			}

			@Override
			public PropertyEditorRegistry getPropertyEditorRegistry() {
				return null;
			}

			@Override
			public String[] resolveMessageCodes(String s) {
				return new String[0];
			}

			@Override
			public String[] resolveMessageCodes(String s, String s1) {
				return new String[0];
			}

			@Override
			public void addError(ObjectError objectError) {

			}
		};
		Assert.isTrue(cadastroUsuarioController.cadastrarNovoUsuario(usuarioDto, result) == "redirect:/cadastro?sucesso", "Não ocorreu redirecionamento");
		Assert.isTrue(cadastroUsuarioController.cadastrarNovoUsuario(usuarioDto, result) != "redirect:/cadastro?sucesso", "Foi redirecionado quando não deveria");

	}

}
