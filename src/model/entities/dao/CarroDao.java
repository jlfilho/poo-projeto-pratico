package model.entities.dao;

import java.util.List;

import model.entities.Carro;
import model.entities.Categoria;

public interface CarroDao {
	
	public void cadastrar(Carro carro);
	public void atualizar(Carro carro);
	public void excluirPorId(Integer id);
	public Carro buscarPorId(Integer id);
	public List<Carro> buscarTodos();
	List<Carro> buscarPorCategoria(Categoria categoria);

}
