package br.com.rodasdev.futebas.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GrupoDTO {
	
	private Long id;
	
	@ApiModelProperty(example = "Futebas Futebol Clube", required = true)
	private String nome;
	
}
