package model.entities.dao;

import java.util.List;

import model.entities.Cliente;

public interface ClienteDao {
	public void cadastrar(Cliente cliente);
	public void atualizar(Cliente cliente);
	public void excluirPorId(Integer id);
	public Cliente buscarPorId(Integer id);
	public List<Cliente> buscarTodos();

}
