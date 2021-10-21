package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

import javax.annotation.PostConstruct;
import javax.faces.model.ListDataModel;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private ListDataModel<Fornecedores>itens;

	public ListDataModel<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fornecedores> itens) {
		this.itens = itens;
	}

@PostConstruct
public void prepararPesquisa() throws ClassNotFoundException {

	try {
		Class.forName("org.postgresql.Driver");
		Connection conexao = ConexaoFactory.conectar();
		FornecedoresDAO fdao = new FornecedoresDAO();
		ArrayList<Fornecedores>lista = fdao.listar();
		itens = new ListDataModel<Fornecedores>(lista);
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

}