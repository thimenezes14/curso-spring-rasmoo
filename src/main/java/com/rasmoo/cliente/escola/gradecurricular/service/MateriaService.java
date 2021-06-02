package com.rasmoo.cliente.escola.gradecurricular.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repository.IMateriaRepository;

@Service
public class MateriaService implements IMateriaService {
	
	private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Erro interno do servidor: ";
	private static final String NOT_FOUND_MESSAGE = "Matéria não encontrada";
	
	@Autowired
	private IMateriaRepository materiaRepository;

	@Override
	public Boolean atualizar(MateriaEntity materia) {
		try {
			Optional<MateriaEntity> materiaParaAtualizar = this.materiaRepository.findById(materia.getId());
			if(materiaParaAtualizar.isPresent()) {
				MateriaEntity materiaUpdate = materiaParaAtualizar.get();
				materiaUpdate.setNome(materia.getNome());
				materiaUpdate.setCodigo(materia.getCodigo());
				materiaUpdate.setFrequencia(materia.getFrequencia());
				materiaUpdate.setHoras(materia.getHoras());
				this.materiaRepository.save(materiaUpdate);
				return true;
			}
			throw new MateriaException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
		} catch (MateriaException me) {
			throw me;
		} catch (Exception e) {
			throw new MateriaException(INTERNAL_SERVER_ERROR_MESSAGE + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Boolean excluir(Long id) {
		try {
			Optional<MateriaEntity> materia = this.materiaRepository.findById(id);
			if(materia.isPresent()) {
				this.materiaRepository.deleteById(id);
				return true;	
			}
			throw new MateriaException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
		} catch (MateriaException me) {
			throw me;
		} catch (Exception e) {
			throw new MateriaException(INTERNAL_SERVER_ERROR_MESSAGE + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MateriaEntity> listar() {
		try {
			return this.materiaRepository.findAll();
		} catch (MateriaException me) {
			throw me;
		} catch (Exception e) {
			throw new MateriaException(INTERNAL_SERVER_ERROR_MESSAGE + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public MateriaEntity detalhes(Long id) {
		try {
			Optional<MateriaEntity> materia = this.materiaRepository.findById(id);
			if(materia.isPresent()) {
				return materia.get();	
			} 
			throw new MateriaException(NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
		} catch (MateriaException me) {
			throw me;
		} catch (Exception e) {
			throw new MateriaException(INTERNAL_SERVER_ERROR_MESSAGE + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Boolean cadastrar(MateriaEntity materia) {
		try {
			this.materiaRepository.save(materia);
			return true;
		} catch (MateriaException me) {
			throw me;
		} catch (Exception e) {
			throw new MateriaException(INTERNAL_SERVER_ERROR_MESSAGE + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
