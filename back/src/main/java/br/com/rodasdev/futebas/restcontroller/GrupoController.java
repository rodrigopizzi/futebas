package br.com.rodasdev.futebas.restcontroller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodasdev.futebas.model.Grupo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//@RestController
//@Api(value="grupo", tags="grupo", description="grupo")
//@RequestMapping("grupo")
public class GrupoController {

//	@PostMapping
//	@ApiOperation(value = "Cria um grupo de jogadores", notes = "Um usuário podera ter quantos grupos desejar.")
	public ResponseEntity<Void> addGrupo(
				@ApiParam(value = "O grupo deverá ter ao menos um nome" ,required = true)  
				@Valid @RequestBody 
				Grupo grupo
			) {
		
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}
	
//	@PutMapping
//	@ApiOperation(value = "Edita o nome do grupo", notes = "O nome do grupo deverá ter ao menos 3 caracteres")
	public ResponseEntity<Void> updateGrupo(
				@ApiParam(value = "O grupo deverá ser identificado por ‘id’", required = true)
				@Valid @RequestBody
				Grupo grupo
			) {
		
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

}
