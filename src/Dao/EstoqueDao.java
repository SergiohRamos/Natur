package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Conexao.Conexao;

public class EstoqueDao {

	public double ConsultarPesoFardo(int ID_Material) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double valorAtingido = 0;
		try {
			String sql = "SELECT pes.peso_fardo FROM view_pesomaterial AS pes WHERE pes.id_material = ?";
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID_Material);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				valorAtingido = rs.getDouble("pes.peso_fardo");
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao consultar o estoque! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return valorAtingido;
	}

	public int BuscarEstoqueAtual(int ID_Material) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int Estoque_Atual = 0;
		try {
			conn = Conexao.getConection();
			String sql = "SELECT e.qnt_fardo FROM estoque as e WHERE e.id_material = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID_Material);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Estoque_Atual = rs.getInt("e.qnt_fardo");
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao buscar estoque atual! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Estoque_Atual;
	}

	public void AtualizarEstoque(int ID_Material, int Valor_Fardo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int Novo_Estoque = (BuscarEstoqueAtual(ID_Material) + Valor_Fardo);
		try {
			conn = Conexao.getConection();
			String sql = "UPDATE estoque SET qnt_fardo = ? WHERE id_material = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Novo_Estoque);
			pstmt.setInt(2, ID_Material);
			pstmt.executeUpdate();

			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha ao atualizar o estoque! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public void AdicionarEstoqueUnitario(int ID_Material){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int Novo_Estoque = (BuscarEstoqueAtual(ID_Material) + 1);
		try {
			conn = Conexao.getConection();
			String sql = "UPDATE estoque SET qnt_fardo = ? WHERE id_material = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Novo_Estoque);
			pstmt.setInt(2, ID_Material);
			pstmt.executeUpdate();

			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha ao atualizar o estoque! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	public int BuscarIDEstoque(int ID_Material) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ID_Estoque = 0;
		try {
			conn = Conexao.getConection();
			String sql = "SELECT id_estoque FROM estoque WHERE id_material = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID_Material);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ID_Estoque = rs.getInt("id_estoque");
			}
			
		} catch (Exception e) {
			System.out.println("Falha ao buscar Id_Estoque! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return ID_Estoque;
	}
	
	public void ZerarTriagem(int Id_Material) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE triagem SET peso_fardo = 0 WHERE id_material = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Id_Material);
			pstmt.executeUpdate();
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha remover Triagem! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
