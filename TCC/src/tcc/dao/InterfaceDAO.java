package tcc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public interface InterfaceDAO<E> {
	
	public LinkedList<E> listarTodos() 	  throws ClassNotFoundException, SQLException;
	public boolean adicionar(E parametro) throws ClassNotFoundException, SQLException;
	public boolean editar(E parametro) 	  throws ClassNotFoundException, SQLException;
	public boolean excluir(E parametro)   throws ClassNotFoundException, SQLException;
	public E popular(ResultSet rs)        throws SQLException, ClassNotFoundException;
	public E buscarPorID(int id) 		  throws ClassNotFoundException, SQLException;
}
