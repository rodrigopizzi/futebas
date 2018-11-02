package br.com.rodasdev.futebas.restcontroller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodasdev.futebas.dto.GrupoDTO;
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
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ApiOperation(value = "Cria um grupo de jogadores", notes = "Um usuário podera ter quantos grupos desejar.")
	public GrupoDTO addGrupo(
				@ApiParam(value = "O grupo deverá ter ao menos um nome" ,required = true)  
				@Valid @RequestBody 
				GrupoDTO grupo
			) {
		
		Grupo grupoEntity = new Grupo();
		modelMapper.map(grupo, grupoEntity);
		grupoEntity = grupoRepository.save(grupoEntity);
		return modelMapper.map(grupoEntity, GrupoDTO.class);
	}
	
	@PutMapping
	@ApiOperation(value = "Edita o nome do grupo", notes = "O nome do grupo deverá ter ao menos 3 caracteres")
	public GrupoDTO updateGrupo(
				@ApiParam(value = "O grupo deverá ser identificado por ‘id’", required = true)
				@Valid @RequestBody
				GrupoDTO grupo
			) {
		
		Grupo grupoEntity = grupoRepository.findById(grupo.getId()).get();
		modelMapper.map(grupo, grupoEntity);
		return modelMapper.map(grupoRepository.save(grupoEntity), GrupoDTO.class);
	}

}
