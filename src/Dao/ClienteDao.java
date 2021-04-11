
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import Beans.ClienteBean;
import Beans.ClienteLoginBean;
import Beans.ItemBean;
import Conexao.Conexao;

public class ClienteDao {

	public void CadastrarCliente(ClienteBean ClienteBean) {

		if (ClienteBean != null) {
			Connection conn = null;

			try {
				conn = Conexao.getConection();

				String sql = "INSERT INTO clientes (nm_cliente, cnpj_cliente, sen_cliente, cep_cliente, uf_cliente, cidade_cliente, tele_cliente, email_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ClienteBean.getNome_Cliente());
				pstmt.setString(2, ClienteBean.getCnpj_Cliente());
				pstmt.setString(3, ClienteBean.getSenha_Cliente());
				pstmt.setString(4, ClienteBean.getCep_Cliente());
				pstmt.setString(5, ClienteBean.getUf_Cliente());
				pstmt.setString(6, ClienteBean.getCidade_Cliente());
				pstmt.setString(7, ClienteBean.getTelefone_Cliente());
				pstmt.setString(8, ClienteBean.getEmail_Cliente());

				pstmt.execute();

				Conexao.FecharConexao(conn, pstmt);

			} catch (Exception e) {
				System.out.println("Falha ao cadastrar cliente! \n ERRO: " + e.getMessage());
			}
		} else {
			System.out.println("Parâmetro 'ClienteBean cb' está vazio.");
		}
	}

	public void AlterarDadosCliente(ClienteBean ClienteBean) {

		if (ClienteBean != null) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE clientes SET nm_cliente = ?, cnpj_cliente = ?, cep_cliente = ?,uf_cliente = ? ,cidade_cliente = ?, tele_cliente = ?, email_cliente = ? WHERE id_cliente = ?";
			try {
				conn = Conexao.getConection();
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, ClienteBean.getNome_Cliente());
				pstmt.setString(2, ClienteBean.getCnpj_Cliente());
				pstmt.setString(3, ClienteBean.getCep_Cliente());
				pstmt.setString(4, ClienteBean.getUf_Cliente());
				pstmt.setString(5, ClienteBean.getCidade_Cliente());
				pstmt.setString(6, ClienteBean.getTelefone_Cliente());
				pstmt.setString(7, ClienteBean.getEmail_Cliente());
				pstmt.setInt(8, ClienteBean.getId_Cliente());
				pstmt.executeUpdate();

				Conexao.FecharConexao(conn, pstmt);
			} catch (Exception e) {
				System.out.println("Falha ao atualizar dados do cliente! \n ERRO: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("Parâmetro 'ClienteBean cb' está vazio.");
		}
	}

	public boolean checkLoginCliente(ClienteLoginBean clb) {

		boolean check = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			conn = Conexao.getConection();
			sql = "SELECT cnpj_cliente, sen_cliente FROM clientes WHERE cnpj_cliente = ? AND sen_cliente = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clb.getCnpj_cliente());
			pstmt.setString(2, clb.getSenha_cliente());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro na validação do Login Cliente. \n Erro: " + e.getMessage());
		}
		return check;
	}

	public boolean checkCNPJ(ClienteBean ClienteBean) {

		boolean checkCNPJ = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			conn = Conexao.getConection();

			sql = "SELECT cnpj_cliente FROM clientes WHERE cnpj_cliente = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ClienteBean.getCnpj_Cliente());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkCNPJ = false;
			} else {
				checkCNPJ = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha na Verificação do CNPJ! \n Erro: " + e.getMessage());
		}
		return checkCNPJ;
	}

	public void BuscasDadosCliente(String CNPJ, ClienteBean ClienteBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = Conexao.getConection();
			sql = "SELECT id_cliente, nm_cliente, cnpj_cliente, cep_cliente, email_cliente,uf_cliente,cidade_cliente, tele_cliente FROM clientes WHERE cnpj_cliente = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CNPJ);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ClienteBean.setId_Cliente(rs.getInt("id_cliente"));
				ClienteBean.setNome_Cliente(rs.getString("nm_cliente"));
				ClienteBean.setCnpj_Cliente(rs.getString("cnpj_cliente"));
				ClienteBean.setCep_Cliente(rs.getString("cep_cliente"));
				ClienteBean.setEmail_Cliente(rs.getString("email_cliente"));
				ClienteBean.setUf_Cliente(rs.getString("uf_cliente"));
				ClienteBean.setCidade_Cliente(rs.getString("cidade_cliente"));
				ClienteBean.setTelefone_Cliente(rs.getString("tele_cliente"));
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha na Verificação do CNPJ! \n Erro: " + e.getMessage());
		}
	}

	public int RealizarPedido(ClienteBean ClienteBean, String DataAtual) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int ChaveGerada = 0;
		try {
			conn = Conexao.getConection();

			sql = "INSERT INTO pedido VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, ClienteBean.getId_Cliente());
			pstmt.setString(3, DataAtual);
			pstmt.execute();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				ChaveGerada = rs.getInt(1);
			}
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha na realização do pedido! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return ChaveGerada;
	}

	public int InserirItem(ItemBean ItemBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int ChaveGerada = 0;
		try {
			conn = Conexao.getConection();

			sql = "INSERT INTO item VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, ItemBean.getId_Material());
			pstmt.setInt(3, ItemBean.getQuantidade_Fardos());
			pstmt.setDouble(4, ItemBean.getPeso_Kg());
			pstmt.execute();
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				ChaveGerada = rs.getInt(1);
			}
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha na inserção do item! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return ChaveGerada;
	}
	
	public void InserirItem_Pedido(int ID_Pedido,int Id_Item) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = Conexao.getConection();

			sql = "INSERT INTO item_pedido VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Id_Item);
			pstmt.setInt(2, ID_Pedido);
			pstmt.setString(3, "Espera");
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha na inserção do item_pedido! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void InserirContasAPagar(int ID_Item, int ID_Cliente, double Custo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO contas_receber VALUES(DEFAULT, ?, ?, ?)";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID_Item);
			pstmt.setInt(2, ID_Cliente);
			pstmt.setDouble(3, Custo);
			pstmt.execute();
			
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha em registrar conta a receber! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
