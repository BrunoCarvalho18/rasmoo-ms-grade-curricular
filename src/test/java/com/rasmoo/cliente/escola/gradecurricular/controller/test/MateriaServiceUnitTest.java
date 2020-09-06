package com.rasmoo.cliente.escola.gradecurricular.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;
import com.rasmoo.cliente.escola.gradecurricular.service.MateriaService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MateriaServiceUnitTest {
	
	@Mock
	private IMateriaRepository materiaRepository;
	
	@InjectMocks
	private MateriaService materiaService;
	
	private static MateriaEntity materiaEntity;
	
	@BeforeAll
	public static void init() {
		materiaEntity = new MateriaEntity();
		materiaEntity.setId(1L);
		materiaEntity.setCodigo("ILP");
		materiaEntity.setFrequencia(1);
		materiaEntity.setHoras(64);
		materiaEntity.setNome("INTRODUCAO A LINNGUAGEM DE PROGRAMACAO");
	}
	
	@Test
	public void testListarSucesso(){
		List<MateriaEntity> listMateria =new ArrayList<>();
		listMateria.add(materiaEntity);
		
		Mockito.when(this.materiaRepository.findAll()).thenReturn(listMateria);
		
		List<MateriaDto> listMateriaDto = this.materiaService.listar();	
		
		assertNotNull(listMateriaDto);
		assertEquals("ILP", listMateriaDto.get(0).getCodigo());
		assertEquals("/materia/1", listMateriaDto.get(0).getLinks().getRequiredLink("self").getHref());
		
		Mockito.verify(this.materiaRepository,times(1)).findAll();
	}
	
	
	@Test
	public void testListarPorHorarioMinimo(){
		List<MateriaEntity> listMateria =new ArrayList<>();
		listMateria.add(materiaEntity);
		
		Mockito.when(this.materiaRepository.findByHoraMinima(64)).thenReturn(listMateria);
		
		List<MateriaDto> listMateriaDto = this.materiaService.listarPorHorarioMinimo(64);	
		
		assertNotNull(listMateriaDto);
		assertEquals("ILP", listMateriaDto.get(0).getCodigo());
		assertEquals(1, listMateriaDto.size());
		
		
		Mockito.verify(this.materiaRepository,times(1)).findByHoraMinima(64);
	}
	
	@Test
	public void testConsultarSucesso() {
		Mockito.when(this.materiaRepository.findById(1L)).thenReturn(Optional.of(materiaEntity));
		MateriaDto materiaDto = this.materiaService.consultar(1L);
		
		assertNotNull(materiaDto);
		assertEquals("ILP", materiaDto.getCodigo());

		
		Mockito.verify(this.materiaRepository,times(1)).findById(1L);
		
	}


}
