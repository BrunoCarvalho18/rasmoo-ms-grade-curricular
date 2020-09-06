package com.rasmoo.cliente.escola.gradecurricular.model;


import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CursoModel {
	
	private Long id;

	@NotBlank(message= "nome deve ser preenchido")
	@Size(min=10,max=30)
	private String nome;

	@NotBlank(message= "codigo deve ser preenchido")
	@Size(min=5,max=30)
	private String codigo;

	private List<Long>materias;

}
