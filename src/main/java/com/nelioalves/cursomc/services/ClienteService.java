package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> cli = clienteRepository.findById(id);

		String msg = "Objeto não encontrado! ";
		return cli.orElseThrow(() -> new ObjectNotFoundException(msg + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);

		return clienteRepository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

	public void delete(Integer id) {
		findById(id);
		try {

			clienteRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui produtos!");
		}
	}

	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objNewDTO) {
		Cliente cli = new Cliente(null, objNewDTO.getNome(), objNewDTO.getEmail(), objNewDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objNewDTO.getTipo()));
		Cidade cid = new Cidade(objNewDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objNewDTO.getLogradouro(),objNewDTO.getNumero(),objNewDTO.getComplemento(),objNewDTO.getBairro(),
				objNewDTO.getCep(),cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objNewDTO.getTelefone1());
		
		if(objNewDTO.getTelefone2() !=null){
			cli.getTelefones().add(objNewDTO.getTelefone2());
		}
		if(objNewDTO.getTelefone3() !=null){
			cli.getTelefones().add(objNewDTO.getTelefone3());
		}
		return cli;
	}
}
