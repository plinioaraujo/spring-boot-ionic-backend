package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id){
		 Optional<Categoria> cat = categoriaRepository.findById(id);
		 
		 String msg = "Objeto não encontrado! ";
		 return cat.orElseThrow( () -> new ObjectNotFoundException(msg + id + ", Tipo: " + Categoria.class.getName() ) );
	}
	
	public Categoria insert(Categoria obj){
		obj.setId(null);
		return categoriaRepository.save(obj);
	}
}
