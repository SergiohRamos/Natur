package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Beans.FuncionarioBean;
import Conexao.Conexao;

public class FuncionarioDao {

	public void CadastrarFuncionario(FuncionarioBean FuncionarioBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO funcionario VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, FuncionarioBean.getNome_Funcionario());
			pstmt.setString(2, FuncionarioBean.getCpf_Funcionario());
			pstmt.setString(3, FuncionarioBean.getUf_Funcionario());
			pstmt.setString(4, FuncionarioBean.getCidade_Funcionario());
			pstmt.setString(5, FuncionarioBean.getCep_Funcionario());
			pstmt.setString(6, FuncionarioBean.getEmail_Funcionario());
			pstmt.setString(7, FuncionarioBean.getTelefone_Funcionario());
			pstmt.setString(8, FuncionarioBean.getSenha_Funcionario());
			pstmt.execute();

			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha ao cadastrar funcionario! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean VerificaCadastroExistente(FuncionarioBean FuncionarioBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean Verificacao = false;
		String sql = "SELECT f.cpf_funcionario FROM funcionario as f WHERE f.cpf_funcionario = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, FuncionarioBean.getCpf_Funcionario());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Verificacao = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao validar cadastro do funcionario! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Verificacao;
	}
	
	public boolean VerificarLogin(FuncionarioBean FuncionarioBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean Verificacao = false;
		String sql = "SELECT f.cpf_funcionario FROM funcionario as f WHERE f.cpf_funcionario = ? AND f.senha_funcionario = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, FuncionarioBean.getCpf_Funcionario());
			pstmt.setString(2, FuncionarioBean.getSenha_Funcionario());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Verificacao = true;
			}
		} catch (Exception e) {
			System.out.println("Falha ao verificar login! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Verificacao;
	}
	
	public void BuscarDadosFuncionario(String CPF, FuncionarioBean FuncionarioBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM funcionario WHERE cpf_funcionario = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CPF);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				FuncionarioBean.setID_funcionario(rs.getInt("id_funcionario"));
				FuncionarioBean.setNome_Funcionario(rs.getString("nome_funcionario"));
				FuncionarioBean.setCpf_Funcionario(rs.getString("cpf_funcionario"));
				FuncionarioBean.setUf_Funcionario(rs.getString("uf_funcionario"));
				FuncionarioBean.setCidade_Funcionario(rs.getString("cidade_funcionario"));
				FuncionarioBean.setCep_Funcionario(rs.getString("cep_funcionario"));
				FuncionarioBean.setEmail_Funcionario(rs.getString("email_funcionario"));
				FuncionarioBean.setTelefone_Funcionario(rs.getString("telefone_funcionario"));				
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao buscar dados do funcionario! zn Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void AlterarDadosFuncionario(FuncionarioBean FuncionarioBean){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE funcionario SET nome_funcionario = ?, cpf_funcionario = ?, uf_funcionario = ?, cidade_funcionario = ?, cep_funcionario = ?, email_funcionario = ?, telefone_funcionario = ? WHERE id_funcionario = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, FuncionarioBean.getNome_Funcionario());
			pstmt.setString(2, FuncionarioBean.getCpf_Funcionario());
			pstmt.setString(3, FuncionarioBean.getUf_Funcionario());
			pstmt.setString(4, FuncionarioBean.getCidade_Funcionario());
			pstmt.setString(5, FuncionarioBean.getCep_Funcionario());
			pstmt.setString(6, FuncionarioBean.getEmail_Funcionario());
			pstmt.setString(7, FuncionarioBean.getTelefone_Funcionario());
			pstmt.setInt(8, FuncionarioBean.getID_funcionario());
			pstmt.executeUpdate();
			
			Conexao.getConection();
		} catch (Exception e) {
			System.out.println("Falha ao alterar dados do funcionario! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
