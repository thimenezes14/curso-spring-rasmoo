package com.rasmoo.cliente.escola.gradecurricular.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDTO;
import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;

@RestController
@RequestMapping("/materia")
public class MateriaController {

	@Autowired
	private IMateriaRepository materiaRepository;

	@GetMapping
	public ResponseEntity<List<MateriaEntity>> listarMaterias() {
		return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MateriaEntity> consultarMateria(@PathVariable Long id) {
		try {
			Optional<MateriaEntity> materia = this.materiaRepository.findById(id);
			if(materia.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(materia.get());	
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
 
	@PostMapping
	public ResponseEntity<Boolean> cadastrarMateria(@RequestBody MateriaEntity materia) {
		try {
			this.materiaRepository.save(materia);
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false);
		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Boolean> atualizarMateria(@PathVariable Long id, @RequestBody MateriaDTO materia) {
		try {
			Optional<MateriaEntity> materiaParaAtualizar = this.materiaRepository.findById(id);
			if(materiaParaAtualizar.isPresent()) {
				MateriaEntity materiaUpdate = materiaParaAtualizar.get();
				materiaUpdate.setNome(materia.getNome());
				materiaUpdate.setCodigo(materia.getCodigo());
				materiaUpdate.setFrequencia(materia.getFrequencia());
				materiaUpdate.setHoras(materia.getHoras());
				this.materiaRepository.save(materiaUpdate);
			}
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletarMateria(@PathVariable Long id) {
		try {
			Optional<MateriaEntity> materia = this.materiaRepository.findById(id);
			if(materia.isPresent()) {
				this.materiaRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.OK).body(true);	
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
}
