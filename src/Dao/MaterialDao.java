package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.BackupFornecedorBean;
import Beans.MaterialBean;
import Beans.PreTriagemBean;
import Conexao.Conexao;

public class MaterialDao {

	public List<MaterialBean> PesquisarDadosMaterial(MaterialBean mb) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		List<MaterialBean> Materiais = new ArrayList<MaterialBean>();

		try {
			conn = Conexao.getConection();
			sql = "SELECT (vm.id_material)ID, (vm.material) Material, (vm.preco)Preço FROM view_material as vm WHERE vm.material like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + mb.getMaterial() + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MaterialBean materialbean = new MaterialBean();
				materialbean.setId_material(rs.getInt("ID"));
				materialbean.setMaterial(rs.getString("Material"));
				materialbean.setPreco(rs.getDouble("Preço"));
				Materiais.add(materialbean);
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Erro ao selecionar materiais. zn Erro: " + e.getMessage());
		}
		return Materiais;
	}

	public MaterialBean BuscaID_Preco(MaterialBean MaterialBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = Conexao.getConection();
			sql = "SELECT vm.id_material, vm.preco FROM view_material AS vm WHERE vm.material = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MaterialBean.getMaterial());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				MaterialBean.setId_material(rs.getInt("vm.id_material"));
				MaterialBean.setPreco(rs.getDouble("vm.preco"));
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao buscar ID e preço do material! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return MaterialBean;
	}

	public int InserirNovoMaterial(String Material, double Preco) { // Add um novo material na tabela material
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ChaveGerada = 0;
		String addMaterial = "INSERT INTO material VALUES (?, ?)";
		String addPrecoMaterial = "INSERT INTO preco_material VALUES(?, ?)";
		String addTriagem = "INSERT INTO triagem VALUES (DEFAULT, ?, ?)";
		String addEstoque = "INSERT INTO estoque VALUES (DEFAULT, ?, ?)";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(addMaterial, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, 0);
			pstmt.setString(2, Material);
			pstmt.execute();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				ChaveGerada = rs.getInt(1);
			}
			Conexao.FecharConexao(conn, pstmt, rs);

			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(addPrecoMaterial);
			pstmt.setInt(1, ChaveGerada);
			pstmt.setDouble(2, Preco);
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);

			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(addTriagem);
			pstmt.setInt(1, ChaveGerada);
			pstmt.setInt(2, 0);
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);

			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(addEstoque);
			pstmt.setInt(1, ChaveGerada);
			pstmt.setInt(2, 0);
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha ao inserir novo material! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return ChaveGerada;
	}

	public void AtualizarMaterial(MaterialBean MaterialBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String AtualizarMateriais = "UPDATE material SET material = ? WHERE id_material = ?";
		String AtualizarPreco = "UPDATE preco_material SET preco = ? WHERE id_material = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(AtualizarMateriais);
			pstmt.setString(1, MaterialBean.getMaterial());
			pstmt.setInt(2, MaterialBean.getId_material());
			pstmt.executeUpdate();
			Conexao.FecharConexao(conn, pstmt);

			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(AtualizarPreco);
			pstmt.setDouble(1, MaterialBean.getPreco());
			pstmt.setInt(2, MaterialBean.getId_material());
			pstmt.executeUpdate();
			Conexao.FecharConexao(conn, pstmt);
		} catch (Exception e) {
			System.out.println("Falha ao atualizar material! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void ExcluirMaterial(MaterialBean MaterialBean) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String DeletarMaterial = "DELETE FROM material WHERE id_material = ? ";
		String DeletarPrecoMaterial = "DELETE FROM preco_material WHERE id_material = ?";
		String DeletarTriagem = "DELETE FROM triagem WHERE id_material = ?";
		String DeletarEstoque = "DELETE FROM estoque WHERE id_material = ?";

		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(DeletarMaterial);
			pstmt.setInt(1, MaterialBean.getId_material());
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);

			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(DeletarPrecoMaterial);
			pstmt.setInt(1, MaterialBean.getId_material());
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);

			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(DeletarTriagem);
			pstmt.setInt(1, MaterialBean.getId_material());
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);

			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(DeletarEstoque);
			pstmt.setInt(1, MaterialBean.getId_material());
			pstmt.execute();
			Conexao.FecharConexao(conn, pstmt);

		} catch (Exception e) {
			System.out.println("Falha ao deletar material! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

//	public boolean VerificacaoTriagem(int Id_Material) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		boolean TemPesagem = false;
//		int Pesagem = 0;
//		String sql = "SELECT t.peso_fardo FROM triagem AS t WHERE t.id_material = ?";
//		try {
//			conn = Conexao.getConection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1,Id_Material);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				Pesagem = rs.getInt("t.peso_fardo");
//				if(Pesagem != 0) {
//					System.out.println("Existe alguma quantia no triagem ---- " + Pesagem);
//					TemPesagem = true;
//					if(Pesagem >= 125) {
//						Pesagem -= 125;
//					}
//					
//				}else {
//					System.out.println("Nao tem nenhum peso na triagem!");
//				}
//			}
//		} catch (Exception e) {
//			System.out.println("Erro na verificacao da tabela triagem! \n Erro: " + e.getMessage());
//			e.printStackTrace();
//		}
//		return TemPesagem;
//	}

	public boolean VerificarExistenciaMaterial(String NomeMaterial) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean Existe = false;
		String sql = "SELECT * FROM material WHERE material LIKE ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + NomeMaterial + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Existe = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao verificar existencia do material! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Existe;
	}

	public boolean VerificarItem(int Id_Material) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean Existe = false;
		String sql = "SELECT * FROM view_pedidos AS vp WHERE vp.`ID Material` = ?";
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Id_Material);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Existe = true;
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			System.out.println("Falha ao verificar item! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Existe;
	}

	public List<BackupFornecedorBean> BuscarFornecedoresCmIDMaterial(int ID_Material) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String PegaDados= "SELECT * FROM view_pretriagem AS vp WHERE vp.Id_Material = ?";
		List<BackupFornecedorBean> Dados = new ArrayList<BackupFornecedorBean>();
		try {
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(PegaDados);
			pstmt.setInt(1, ID_Material);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BackupFornecedorBean BackupFornecedorBean = new BackupFornecedorBean();
				
				BackupFornecedorBean.setId_PreTri(rs.getInt("Id_PreTri"));
				BackupFornecedorBean.setId_Fornecedor(rs.getInt("Id_Fornecedor"));
				BackupFornecedorBean.setId_Funcionario(rs.getInt("Id_Funcionario"));
				BackupFornecedorBean.setId_Material(rs.getInt("Id_Material"));
				BackupFornecedorBean.setPeso_Material(rs.getDouble("Peso_Material"));
				BackupFornecedorBean.setData_Venda(rs.getString("Data_Venda"));
				
				Dados.add(BackupFornecedorBean);
			}
			Conexao.FecharConexao(conn, pstmt, rs);	
		} catch (Exception e) {
			System.out.println("Falha ao buscar id pre triagem que possuam o ID_Material! \n Erro: " + e.getMessage());
			e.printStackTrace();
		}
		return Dados;
	}
}
