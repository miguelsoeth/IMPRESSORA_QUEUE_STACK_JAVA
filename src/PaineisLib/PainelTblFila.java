package PaineisLib;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import impressoraLib.*;

public class PainelTblFila extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private static DefaultTableModel model;
	private JTable tb_fila;
	private JScrollPane spnDados;
	
	public PainelTblFila(int x, int y, int w, int h) {
		criarPainel(x, y, w, h);
	}
	
	public void criarPainel(int x, int y, int w, int h) {
		//Adiciona borda ao Jpanel
		//setBorder(BorderFactory.createLineBorder(Color.black));
		setBounds(x, y, w, h);
		setLayout(null);
		
		//Titulo da tabela
		title = new JLabel("Documentos na fila");
		title.setBounds(10, 0, 200, 25);
		add(title);
		
		//Modelo que define as colunas da tabela
		model = new DefaultTableModel();
		model.addColumn("Nome");
		model.addColumn("Paginas");
		
		//Criação da tabela
		tb_fila = new JTable(model);
		tb_fila.setDefaultEditor(Object.class, null);
		
		//Criação do painel dinamico para tabela
		spnDados = new JScrollPane(tb_fila);
		spnDados.setBounds(0, 25, w, h-25);
		add(spnDados);
	}

	public static void addRow(Documento doc) {
		int num_paginas = Integer.parseInt(doc.getListaPaginas().getFim().getConteudo());
		Object[] rowData = {doc.getNome(), num_paginas};
		model.addRow(rowData);
	}
	
	public static void removerUltimaLinha() {
		int rowCount = model.getRowCount();
		model.removeRow(rowCount - 1);
	}
	
	public static void removerPrimeiraLinha() {
		model.removeRow(0);
	}
	
	public static void setPaginaLinha1(int pagina) {
		model.setValueAt(pagina, 0, 1);
	}
}
