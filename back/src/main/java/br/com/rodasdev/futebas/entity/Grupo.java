package br.com.rodasdev.futebas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode 
@ToString
@Validated
@Getter @Setter @NoArgsConstructor
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_grupo")
	@SequenceGenerator(name="seq_grupo", sequenceName = "seq_grupo", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	@JsonProperty("id")
	private Long id;
	
	@Column
	@NotNull
	@Size(min = 3, max = 20, message = "O tamanho do nome do grupo deverá ser no minimo 3 caracteres e no máximo 20.")
	@JsonProperty("nome")
	@ApiModelProperty(example = "Futebas Futebol Clube", required = true, value = "")
	private String nome;

}
