package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.PGProperty;
import org.postgresql.util.GT;
import org.postgresql.util.PSQLException;
import org.postgresql.util.PSQLState;

public class ConexaoFactory {

	private static final String USUARIO = "postgres";
	private static final String SENHA = "postgres";
	private static final String URL = "jdbc:postgresql:farmacia";

	public static Connection conectar() throws SQLException, ClassNotFoundException {

		Class.forName("org.postgresql.Driver");
		Connection conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
		return conexao;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
	
		try{
		Connection conexao = ConexaoFactory.conectar();
		System.out.println("Conectado com sucesso.");
		}
		
		catch (SQLException ex) {
			System.out.println("Conexão falhou!!! "+ex);
		}
	}
}
