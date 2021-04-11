package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Beans.PreTriagemBean;
import Conexao.Conexao;

public class PreTriagemDao {

	public int InserirDadosPreTriagem(PreTriagemBean ptb) {
		int ChaveGerada = 0;

		if (ptb != null) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";

			try {
				conn = Conexao.getConection();

				sql = "INSERT INTO pre_triagem VALUES (?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, 0);
				pstmt.setInt(2, ptb.getId_material());
				pstmt.setInt(3, ptb.getId_funcionario());
				pstmt.setDouble(4, ptb.getPeso());
				pstmt.execute();

				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					ChaveGerada = rs.getInt(1);
				}
				Conexao.FecharConexao(conn, pstmt);
			} catch (Exception e) {
				System.out.println("Erro na inserção na tabela pre_triagem! \n Erro: " + e.getMessage());
			}
		} else {
			System.out.println("Valor passado por parametro(ptb) é nulo!");
		}
		return ChaveGerada;
	}

	public void InserirTableFornPreTriagem(int ID_Fornecedor, int ID_PreTriagem, String DataAtual) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = Conexao.getConection();
			String sql = "INSERT INTO fornecedor_pretriagem VALUES (default, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID_Fornecedor);
			pstmt.setInt(2, ID_PreTriagem);
			pstmt.setString(3, DataAtual);
			pstmt.execute();

			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Erro ao inserir na tabela Fornecedor_PreTriagem! \n Erro: " + e.getMessage());
		}
	}
}
