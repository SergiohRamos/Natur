package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Beans.ClienteCompraTableBean;

public class ClienteCompraTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ClienteCompraTableBean> Dados = new ArrayList<>();
	private String[] Colunas = {"Material", "Fardos", "Peso em Kg", "Custo"};
	
	@Override
	public String getColumnName(int coluna) {
		return Colunas[coluna];
	}
	@Override
	public int getColumnCount() {
		return Colunas.length;
	}
	@Override
	public int getRowCount() {
		return Dados.size();
	}
	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna){
		case 0:
			return Dados.get(linha).getMaterial();
		case 1:
			return Dados.get(linha).getQuantidade_Fardo();
		case 2:
			return Dados.get(linha).getPeso_Kg();
		case 3:
			return Dados.get(linha).getCusto();
		}
		return null;
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void addRow(ClienteCompraTableBean ClienteCompraTableBean){
		this.Dados.add(ClienteCompraTableBean);
		fireTableDataChanged();
	}
	
	public void removeRow(int selectedRow) {
		this.Dados.remove(selectedRow);
		fireTableDataChanged();
	}
	
	public void clear() {
		this.Dados.clear();
		this.fireTableDataChanged();
	}
	
}
