package br.com.credsystem.test.carteira.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.credsystem.test.carteira.model.Client;
import br.com.credsystem.test.carteira.repository.ClientRepository;
import br.com.credsystem.test.carteira.request.AddClientRequest;
import br.com.credsystem.test.carteira.request.UpdateClientRequest;
import br.com.credsystem.test.carteira.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository repository;

	@Override
	public List<Client> listAllClient() {
		return repository.listAllClient();
	}

	@Override
	public Client listClientById(Integer clientId) {
		return repository.listClientById(clientId);
	}

	@Override
	public String deleteClientById(Integer clientId) {
		Boolean result = repository.deleteClient(clientId); 
		return result ? "Cliente deletado com sucesso" : "Erro ao deletar cliente.";
	}

	@Override
	public String updateClient(UpdateClientRequest updateClient) {
		
		Client client = new Client();
		client.setId(updateClient.getId());
		client.setEmail(updateClient.getEmail());
		client.setName(updateClient.getName());
		client.setSalary(updateClient.getSalary());
		Boolean result = repository.updateClient(client);
		return result ? "Cliente atualizado com sucesso" : "Erro ao atualizar cliente.";
	}

	@Override
	public String addClient(AddClientRequest client) {
		if(verifyDuplicityEmail(client.getEmail())) {
			return "Email Duplicado";
		}
		Integer id = repository.addClient(client.getName(), client.getEmail(), client.getSalary());
		return "Adicionado com sucesso. ID = ".concat(id.toString());
	}
	
	private Boolean verifyDuplicityEmail(String email) {
		return repository.verifyDuplicity(email);
	}

}
