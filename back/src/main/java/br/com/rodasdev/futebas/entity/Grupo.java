package br.com.rodasdev.futebas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@EqualsAndHashCode 
@ToString
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_grupo")
	@SequenceGenerator(name="seq_grupo", sequenceName = "seq_grupo", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	@Getter @Setter private Long id;
	
	@Column
	@NotNull
	@Size(min = 3, max = 20)
	@Getter @Setter private String nome;

}
