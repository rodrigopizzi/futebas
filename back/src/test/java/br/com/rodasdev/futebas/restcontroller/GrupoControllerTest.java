package br.com.rodasdev.futebas.restcontroller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rodasdev.futebas.dto.GrupoDTO;
import br.com.rodasdev.futebas.entity.Grupo;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class GrupoControllerTest {

	@Autowired
	private GrupoController controller;

	@Autowired
	private Validator validator;

	@Test
	public void criaNovoGrupo() {
		String nomeGrupo = "Rodas Futebol Clube";
		
		GrupoDTO grupo = new GrupoDTO();
		grupo.setNome(nomeGrupo);

		grupo = controller.addGrupo(grupo);

		assertNotEquals(0, grupo.getId().longValue());
		assertEquals(nomeGrupo, grupo.getNome());
	}

	@Test
	public void verificaTamanhoDoNomeDoGrupo() {
		Grupo grupo = new Grupo();
		grupo.setNome("R");

		Set<ConstraintViolation<Grupo>> violations = validator.validate(grupo);
		assertEquals("O tamanho do nome do grupo deverá ser no minimo 3 caracteres e no máximo 60.", violations.iterator().next().getMessage());

		grupo.setNome("O futebol, também referido como futebol de campo, futebol de onze e, controversamente, futebol associado, é um desporto de equipe jogado entre dois times de 11 jogadores cada um e um árbitro que se ocupa da correta aplicação das normas");
		violations = validator.validate(grupo);
		assertEquals("O tamanho do nome do grupo deverá ser no minimo 3 caracteres e no máximo 60.", violations.iterator().next().getMessage());
	}
	
	@Test
	public void atualizaGrupo() {
		GrupoDTO grupo = new GrupoDTO();
		grupo.setNome("Futebas Futebol Clube");
		
		grupo = controller.addGrupo(grupo);
		grupo.setNome("Futebas");
		
		grupo = controller.updateGrupo(grupo);
		assertEquals("Futebas", grupo.getNome());
	}
}
