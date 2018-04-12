package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido findById(Integer id){
		 Optional<Pedido> cli = pedidoRepository.findById(id);
		 
		 String msg = "Objeto não encontrado! ";
		 return cli.orElseThrow( () -> new ObjectNotFoundException(msg + id + ", Tipo: " + Pedido.class.getName() ) );
	}
	
}
