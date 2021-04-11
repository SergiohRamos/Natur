package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Beans.FornecedorBeans;
import Conexao.Conexao;

public class CadastrarDao {

	public void cadastrar(FornecedorBeans fornecedorBeans) {

		if (fornecedorBeans != null) {
			Connection conn = null;

			try {
				conn = Conexao.getConection();

				String sql = "INSERT INTO fornecedor (nome_fornecedor, cpf_fornecedor, senha_fornecedor, uf_fornecedor, cidade_fornecedor, cep_fornecedor, tele_fornecedor, email_fornecedor ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt;	
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fornecedorBeans.getNome());
				pstmt.setString(2, fornecedorBeans.getCPF());
				pstmt.setString(3, fornecedorBeans.getSenha());
				pstmt.setString(4, fornecedorBeans.getUF());
				pstmt.setString(5, fornecedorBeans.getCidade());
				pstmt.setString(6, fornecedorBeans.getCep());
				pstmt.setString(7, fornecedorBeans.getTelefone());
				pstmt.setString(8, fornecedorBeans.getEmail());
				pstmt.execute();
				
				Conexao.FecharConexao(conn, pstmt);
				
			} catch (Exception e) {
				System.err.print("Falha no cadastro do fornecedor! \n Erro: " + e.getMessage());
			}
		}
		else {
			System.out.println("Fornecedor enviado por parametro está vazio!");
		}
	}

	public boolean checkCPF(FornecedorBeans fornecedorBeans) {
		
		boolean checkCPF = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			conn = Conexao.getConection();
			
			sql = "SELECT cpf_fornecedor FROM fornecedor WHERE cpf_fornecedor = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fornecedorBeans.getCPF());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkCPF = false;
			} else {
				checkCPF = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
			
		} catch (Exception e) {
			System.err.print("Erro na validação do CPF. \n Erro: " + e.getMessage());
		}
		return checkCPF;
	}

}