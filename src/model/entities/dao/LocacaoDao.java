package model.entities.dao;

import java.util.List;

import model.entities.Locacao;

public interface LocacaoDao {
	public void cadastrar(Locacao locacao);
	public void atualizar(Locacao locacao);
	public void excluirPorId(Integer id);
	public Locacao buscarPorId(Integer id);
	public List<Locacao> buscarTodos();
	List<Locacao> buscarPorCliente(ClienteDao cliente);

}
