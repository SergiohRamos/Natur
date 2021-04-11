package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import Beans.AdmPedidosBean;
import Beans.AdministradorLoginBean;
import Beans.ClienteBean;
import Beans.MaterialBean;
import Beans.FornecedorBean;
import Beans.FuncionarioBean;
import Conexao.Conexao;

public class AdministradorDao {

	public boolean checkLogin(AdministradorLoginBean alb) {

		boolean check = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			conn = Conexao.getConection();
			sql = "SELECT cpf_adm, senha_adm FROM administrador WHERE cpf_adm = ? AND senha_adm = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, alb.getcpf_adm());
			pstmt.setString(2, alb.getSenha_adm());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = true;
			} else {
				check = false;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro na validação do login do Administrador! \n Erro: " + e.getMessage());
		}
		return check;
	}
	
	public boolean VerificarSituacaoMaterial(int ID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String VerificarMaterial = "SELECT * FROM view_pedidos AS vp WHERE vp.`ID Material` = ?";
		boolean Situacao = false;
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(VerificarMaterial);
			pstmt.setInt(1, ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Situacao = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha na verificação da situação do material! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Situacao;
	}

	public int BuscarIDEstoque(int Id_Material) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String BuscaIdEstoque = "SELECT * FROM estoque WHERE id_material = ?";
		int Id_Estoque = 0;

		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(BuscaIdEstoque);
			pstmt.setInt(1, Id_Material);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Id_Estoque = rs.getInt("id_estoque");
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro ao buscar Id do estoque! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Id_Estoque;
	}

	// Se retornar um valor > 0 quer dizer que tem estoque
	public int VerificarEstoque(int Id_Material) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT qnt_fardo FROM estoque AS e WHERE id_material = ?";
		int Estoque = 0;

		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Id_Material);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Estoque = rs.getInt("qnt_fardo");
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao buscar estoque! zn Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Estoque;
	}
	
	public void DescontarDoEstoque(int NovoEstoque, int IdMaterial) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE estoque SET qnt_fardo = ? WHERE id_material = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, NovoEstoque);
			pstmt.setInt(2, IdMaterial);
			pstmt.executeUpdate();
			
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha ao descontar do estoque! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void InserirExpedicao(AdmPedidosBean PedidosBean, int Id_Estoque) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String Expedicao = "INSERT INTO expedicao VALUES (DEFAULT, ?, ?, ?)";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(Expedicao);
			pstmt.setInt(1, PedidosBean.getID_Item());
			pstmt.setInt(2, PedidosBean.getID_Pedido());
			pstmt.setInt(3, Id_Estoque);
			pstmt.execute();

			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Erro ao fazer a expedição! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void AtualizarStatusPedido(AdmPedidosBean PedidosBean, String Situacao) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE item_pedido SET situacao = ? WHERE id_item = ? AND id_pedido = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Situacao);
			pstmt.setInt(2, PedidosBean.getID_Item());
			pstmt.setInt(3, PedidosBean.getID_Pedido());
			pstmt.executeUpdate();

			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha ao atualizar status do pedido! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void RemoverPedidoAprovado(AdmPedidosBean PedidosBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM item_pedido WHERE id_item = ? AND id_pedido = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PedidosBean.getID_Item());
			pstmt.setInt(2, PedidosBean.getID_Pedido());
			pstmt.executeUpdate();

			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha ao atualizar status do pedido! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	

	public boolean BuscarFornecedores(String Nome, List<String> Nomes) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT nome_fornecedor FROM fornecedor WHERE nome_fornecedor LIKE ?";
		boolean Verificar = false;
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + Nome + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Nomes.add(rs.getString("nome_fornecedor"));
				Verificar = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Verificar;
	}

	public FornecedorBean BuscarDadosFornecedor(String NomeFornecedor, FornecedorBean FornecedorBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM fornecedor WHERE nome_fornecedor LIKE ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + NomeFornecedor + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				FornecedorBean.setId_fornecedor(rs.getInt("id_fornecedor"));
				FornecedorBean.setNome_fornecedor(rs.getString("nome_fornecedor"));
				FornecedorBean.setCpf_fornecedor(rs.getString("cpf_fornecedor"));
				FornecedorBean.setCidade_fornecedor(rs.getString("cidade_fornecedor"));
				FornecedorBean.setUf_fornecedor(rs.getString("uf_fornecedor"));
				FornecedorBean.setCep_fornecedor(rs.getString("cep_fornecedor"));
				FornecedorBean.setTele_fornecedor(rs.getString("tele_fornecedor"));
				FornecedorBean.setEmail_fornecedor(rs.getString("email_fornecedor"));
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro ao consultar fornecedor! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return FornecedorBean;
	}

	public boolean BuscarClientes(String Nome, List<String> ListaNomes) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT nm_cliente FROM clientes WHERE nm_cliente LIKE ?";
		boolean Verificar = false;
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + Nome + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ListaNomes.add(rs.getString("nm_cliente"));
				Verificar = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Verificar;
	}

	public ClienteBean BuscarDadosClientes(String NomeCliente, ClienteBean ClienteBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM clientes WHERE nm_cliente LIKE ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + NomeCliente + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ClienteBean.setId_Cliente(rs.getInt("id_cliente"));
				ClienteBean.setNome_Cliente(rs.getString("nm_cliente"));
				ClienteBean.setCnpj_Cliente(rs.getString("cnpj_cliente"));
				ClienteBean.setCep_Cliente(rs.getString("cep_cliente"));
				ClienteBean.setCidade_Cliente(rs.getString("cidade_cliente"));
				ClienteBean.setUf_Cliente(rs.getString("uf_cliente"));
				ClienteBean.setTelefone_Cliente(rs.getString("tele_cliente"));
				ClienteBean.setEmail_Cliente(rs.getString("email_cliente"));
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro ao consultar fornecedor! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return ClienteBean;
	}

	public boolean BuscarFuncionarios(String NomeFuncionario, List<String> ListaFuncionarios) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT nome_funcionario FROM funcionario WHERE nome_funcionario LIKE ?";
		boolean Verificar = false;
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + NomeFuncionario + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ListaFuncionarios.add(rs.getString("nome_funcionario"));
				Verificar = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao buscar nome dos funcionarios! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Verificar;
	}

	public FuncionarioBean BuscarDadosFuncionarios(String NomeFuncionario, FuncionarioBean FuncionarioBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM funcionario WHERE nome_funcionario LIKE ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + NomeFuncionario + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				FuncionarioBean.setID_funcionario(rs.getInt("id_funcionario"));
				FuncionarioBean.setNome_Funcionario(rs.getString("nome_funcionario"));
				FuncionarioBean.setCpf_Funcionario(rs.getString("cpf_funcionario"));
				FuncionarioBean.setTelefone_Funcionario(rs.getString("telefone_funcionario"));
				FuncionarioBean.setEmail_Funcionario(rs.getString("email_funcionario"));
				FuncionarioBean.setUf_Funcionario(rs.getString("uf_funcionario"));
				FuncionarioBean.setCidade_Funcionario(rs.getString("cidade_funcionario"));
				FuncionarioBean.setCep_Funcionario(rs.getString("cep_funcionario"));
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao buscar dados do funcionario! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return FuncionarioBean;
	}
	
	//Verifica se algum fornecedor vendeu esse item
	public boolean VerificarPreTriagem(int Id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM view_pretriagem AS vp WHERE vp.Id_Material = ?";
		boolean Situacao = false;
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				Situacao = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao verificar situação do material na table PreTriagem \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Situacao;
	}
	
	//Verifica se há algum pedido que possua esse esse material
	public boolean VerificarPedido(int Id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM view_pedidos AS vp WHERE vp.`ID Material` = ?";
		boolean Situacao = false;
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				Situacao = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao verificar situação do material na table PreTriagem \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Situacao;
	}
	
	public void AtualizarSituacao(AdmPedidosBean AdmPedidosBean){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE item_pedido SET situacao = 'Aprovado' WHERE id_item = ? AND id_pedido = ?";
		
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, AdmPedidosBean.getID_Item());
			pstmt.setInt(2, AdmPedidosBean.getID_Pedido());
			pstmt.executeUpdate();
			
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha Mudar situação do material aprovado \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
