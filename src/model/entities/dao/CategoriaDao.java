package model.entities.dao;

import java.util.List;

import model.entities.Categoria;

public interface CategoriaDao {

	public void cadastrar(Categoria categoria);

	public void atualizar(Categoria categoria);

	public void excluirPorId(Integer id);

	public Categoria buscarPorId(Integer id);

	public List<Categoria> buscarTodos();

}
