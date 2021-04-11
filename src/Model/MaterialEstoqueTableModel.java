package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Beans.MaterialBeanCmEstoque;
import Conexao.Conexao;

public class MaterialEstoqueTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MaterialBeanCmEstoque> dados = new ArrayList<MaterialBeanCmEstoque>();
	private String[] colunas = {"Descricao","Estoque" };

	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return dados.get(linha).getMaterial();
		case 1:
			return dados.get(linha).getEstoque();
		}
		return null;
	}
	
	public List<MaterialBeanCmEstoque> readEstoq(String Material){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT ID, Descricao, Estoque FROM view_materialcmestoque WHERE Descricao LIKE ? ORDER BY ID ASC";
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + Material + "%");
			rs = pstmt.executeQuery();

			if (dados.size() > 0) {
				dados.clear();
			}
			while (rs.next()) {
				MaterialBeanCmEstoque matEstq = new MaterialBeanCmEstoque();
				matEstq.setId_material(rs.getInt("ID"));
				matEstq.setMaterial(rs.getString("Descricao"));
				matEstq.setEstoque(rs.getInt("Estoque"));
				dados.add(matEstq);
				this.fireTableDataChanged();
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return dados;
	}
}
