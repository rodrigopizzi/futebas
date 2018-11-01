package br.com.rodasdev.futebas.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodasdev.futebas.entity.Grupo;
import br.com.rodasdev.futebas.repository.GrupoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value="grupo", tags="grupo", description="grupo")
@RequestMapping("grupo")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;

	@PostMapping
	@ApiOperation(value = "Cria um grupo de jogadores", notes = "Um usuário podera ter quantos grupos desejar.")
	public Grupo addGrupo(
				@ApiParam(value = "O grupo deverá ter ao menos um nome" ,required = true)  
				@Valid @RequestBody 
				Grupo grupo
			) {
		
		return grupoRepository.save(grupo);
	}
	
	@PutMapping
	@ApiOperation(value = "Edita o nome do grupo", notes = "O nome do grupo deverá ter ao menos 3 caracteres")
	public Grupo updateGrupo(
				@ApiParam(value = "O grupo deverá ser identificado por ‘id’", required = true)
				@Valid @RequestBody
				Grupo grupo
			) {
		
		return grupoRepository.save(grupo);
	}

}
