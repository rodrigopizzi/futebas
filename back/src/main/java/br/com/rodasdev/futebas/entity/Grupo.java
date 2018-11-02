package br.com.rodasdev.futebas.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_grupo")
	@SequenceGenerator(name="seq_grupo", sequenceName = "seq_grupo", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column
	@NotNull
	@Size(min = 3, max = 60, message = "O tamanho do nome do grupo deverá ser no minimo 3 caracteres e no máximo 60.")
	private String nome;
	
	@Column
	@NotNull
	private ZonedDateTime dataCadastro;
	
	public Grupo() {
		dataCadastro = ZonedDateTime.now();
	}

}
