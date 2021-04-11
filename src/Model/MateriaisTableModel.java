package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Beans.MaterialBean;
import Conexao.Conexao;

public class MateriaisTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MaterialBean> dados = new ArrayList<MaterialBean>();
	private String[] colunas = { "Descricao", "Preço p/ Kg" };

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
			return dados.get(linha).getPreco();
		}
		return null;
	}

	public void addRow(MaterialBean MaterialBean) {
		this.fireTableDataChanged();
	}

	public List<MaterialBean> read(String Material) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM view_material WHERE material LIKE ?";
			conn = Conexao.getConection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + Material + "%");
			rs = pstmt.executeQuery();

			if (dados.size() > 0) {
				dados.clear();
			}
			while (rs.next()) {
				MaterialBean materialBean = new MaterialBean();
				materialBean.setId_material(rs.getInt("id_material"));
				materialBean.setMaterial(rs.getString("material"));
				materialBean.setPreco(rs.getDouble("preco"));
				dados.add(materialBean);
				this.fireTableDataChanged();
			}
			Conexao.FecharConexao(conn, pstmt, rs);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return dados;
	}
	
	public int RetornaIDMaterial(int Index){
		return this.dados.get(Index).getId_material();
	}
}
