import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import PaineisLib.PainelTblFila;
import PaineisLib.PainelTblImpressao;
import impressoraLib.Documento;
import impressoraLib.Flag;
import impressoraLib.Impressora;

public class Tela extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public Tela() {
		setTitle("Fila de Impressão");
		setResizable(false);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		criaPaineis();
		setVisible(true);
	}
	
	private void criaPaineis() {		
		getContentPane().add(new PainelTblFila(10, 50, 300, 300));
		getContentPane().add(new PainelTblImpressao(475, 50, 300, 300));
		
		Impressora impressora = new Impressora();	
				
		JButton adicionaFila = new JButton(new AbstractAction("Adicionar adicionar na fila") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome_documento = JOptionPane.showInputDialog("Digite o nome do documento:");
				
				if (nome_documento != null) {
					String num_paginas = JOptionPane.showInputDialog("Digite o numero de paginas desse documento");
					if (num_paginas != null) {
						//Cria uma novo documento com as informações
		            	Documento documento = new Documento(nome_documento, Integer.parseInt(num_paginas));
		            	//Adiciona o documento a fila da impressora
		            	impressora.adicionarDocumento(documento);	
					}
				}
			}
		});
		adicionaFila.setBounds(10, 360, 300, 30);
		add(adicionaFila);
		
		JButton removerFila = new JButton(new AbstractAction("Remover ultimo (como pilha)") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Remove o ultimo documento adicionado a fila;
				impressora.removerUltimoDocumento();
			}
		});
		removerFila.setBounds(10, 400, 300, 30);
		add(removerFila);
		
		JButton imprimirFila = new JButton(new AbstractAction(">> Imprimir >>") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Flag.Pause == false && Flag.Printing == false) {
					Flag.Printing = true;
					//Esse SwingWoker com a função doInBackground gera uma thread. permitindo o uso do programa enquanto imprime
					SwingWorker<Void, Void> worker = new SwingWorker<>() {
			            @Override
			            protected Void doInBackground() throws Exception {
			            	
			            	//enquanto (fila de impressão de documentos da impressora NÃO está vazia)
			            	 while (!impressora.getFilaImpressao().isEmpty()) {
			            		 	//pega o primeiro documento da fila e verifica se a lista de paginas está vazia
									while (!impressora.getFilaImpressao().getFirst().getListaPaginas().isEmpty()) {
										//Verifica se a impressão deve ser pausada ou não
										if (Flag.Pause == true) {
											Flag.Printing = false;
				                            // Aguarda até que a pausa seja desativada
				                            while (Flag.Pause == true) {
				                                Thread.sleep(100);
				                            }
				                        }
										//Pega o numero da ultima pagina do documento 
										int pagina = Integer.parseInt(impressora.getFilaImpressao().getFirst().getListaPaginas().getFim().getConteudo());
										//Remove a ultima pagina do documento
										impressora.getFilaImpressao().getFirst().getListaPaginas().removerUltimaPagina();
										
										System.out.println(pagina);
										
										//Atualiza o numero de paginas restantes na fila
										PainelTblFila.setPaginaLinha1(pagina-1);
										//Adiciona as informações do documento e da pagina impressa na tabela de impressão (saida da impressora) 
										PainelTblImpressao.addRow(impressora.getFilaImpressao().getFirst().getNome(), pagina);
										
										Thread.sleep(500);
									}
								/*Apos imprimir todas as paginas do primeiro documento, o remove e
								reinicia o loop do while, verificando se há mais documentos para imprimir*/
								System.out.println("removendo primeira linha");
								impressora.removerPrimeiroDocumento();	
							}
			            	Flag.Printing = false;
			                return null;
			            }
					};
					worker.execute();
				} else if (Flag.Pause == true) {
					JOptionPane.showMessageDialog(null, "Fila pausada! despause a operação para imprimir.");
				} else {
					JOptionPane.showMessageDialog(null, "Já imprimindo!");
				}
			}
		});
		imprimirFila.setBounds(317, 200, 150, 30);
		add(imprimirFila);
		
		JButton LimparImpressora = new JButton(new AbstractAction("Retirar documentos") {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Limpa a tabela de impressão (saida da impressora)
				PainelTblImpressao.ResetRows();
			}
		});
		LimparImpressora.setBounds(475, 360, 300, 30);
		add(LimparImpressora);
		
		JButton pausarImpressao = new JButton();
		pausarImpressao.setAction(new AbstractAction("Pausar Impressão") {
			
			private static final long serialVersionUID = 1L;
			
		    private boolean impressaoPausada = false;

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (impressaoPausada) {
		            pausarImpressao.setText("Pausar Impressão");
		            Flag.Pause=false;
		        } else {
		            pausarImpressao.setText("Seguir Impressão");
		            Flag.Pause=true;
		        }
		        impressaoPausada = !impressaoPausada;
		    }
		});

		pausarImpressao.setBounds(317, 250, 150, 30);
		add(pausarImpressao);		
	}

}
