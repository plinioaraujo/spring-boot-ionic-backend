package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Integer id){
		 Optional<Categoria> cat = categoriaRepository.findById(id);
		 
		 String msg = "Objeto não encontrado! ";
		 return cat.orElseThrow( () -> new ObjectNotFoundException(msg + id + ", Tipo: " + Categoria.class.getName() ) );
	}
	
	public Categoria insert(Categoria obj){
		obj.setId(null);
		return categoriaRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return categoriaRepository.save(obj);
	}
	
	public void  delete(Integer id){
		findById(id);
		try {
	
			categoriaRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}
}
