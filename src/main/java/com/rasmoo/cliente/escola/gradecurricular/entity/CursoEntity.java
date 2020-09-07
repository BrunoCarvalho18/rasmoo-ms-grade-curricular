package com.rasmoo.cliente.escola.gradecurricular.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name= "tb_curso")
@Data
@NoArgsConstructor
public class CursoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3356132226033738489L;
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name = "increment", strategy="increment")
	@Column(name="id")
	private Long id;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="nome")
	private String nome;
	
	@Column(name="cod")
	private String codigo;
	
	@Column(name="freq")
	private int frequencia;
	
	@OneToMany(fetch= FetchType.EAGER)
	@JoinColumn(name="materia_id")
	private List<MateriaEntity> materias;

}
