package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.IntToLongFunction;
import java.util.function.ToLongFunction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {

	public void Salvar(Fornecedores f) throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into public.\"Fornecedores\"");
		sql.append("(descricao) ");
		sql.append("values (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.executeUpdate();

	}

	public void Excluir(Fornecedores f) throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("delete from public.\"Fornecedores\"");
		sql.append("where codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();

	}

	public void Editar(Fornecedores f) throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("update public.\"Fornecedores\"");
		sql.append(" set descricao = ? ");
		sql.append("where codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();

	}

	public Fornecedores BuscaPorCodigo(Fornecedores f) throws SQLException, ClassNotFoundException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, descricao from public.\"Fornecedores\"");
		sql.append(" where codigo = ?");		

		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		if(resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));		}
			
		return retorno;

	}

	public ArrayList<Fornecedores>buscarPorDescricao(Fornecedores f)throws SQLException, ClassNotFoundException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM public.\"Fornecedores\" ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + f.getDescricao() + "%");
		
		
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()){
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
		}

		return lista;
	}

	public ArrayList<Fornecedores>listar()throws SQLException, ClassNotFoundException{

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM public.\"Fornecedores\" ");
		sql.append("ORDER BY descricao ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
				
		ResultSet resultado = comando.executeQuery();
		 
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()){
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}

		return lista;
	}

	public static void main(String[] args) throws ClassNotFoundException {

        FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			
			ArrayList<Fornecedores>lista = fdao.listar();
			
			for (Fornecedores f : lista){
			System.out.println(f);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao buscar");
			e.printStackTrace();
		}   
	}
}
