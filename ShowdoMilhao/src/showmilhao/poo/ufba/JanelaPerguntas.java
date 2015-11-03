package showmilhao.poo.ufba;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JanelaPerguntas implements ActionListener  {

	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	static  JLabel	pergunta;
	static  JButton res[] = new JButton[4];
	JButton parar = new JButton();
	JButton  pular;
	
	static  JLabel  acertar;
	static  JLabel  acumular;
	static  JLabel  pulos;
	
	static  JFrame  w1;
	
	int nDePulos = 3;
	static  int dinheiro = 0;
	static  int acumulado = 0;
	
	static int acertos = 1;
	static int acertos2 = 1;
	static int acertos3 = 1;
	
	static String certo;
	
	/*
	static List<Questao> listaQuestoes = new ArrayList();
	static List<Questao> listaQuestoes2 = new ArrayList();
	static List<Questao> listaQuestoes3 = new ArrayList();
	static List<Questao> listaQuestoes4 = new ArrayList(); */
	
	public static void main(String[] args) {
		
				
		//um molde de 10 perguntas aleat�rias -> falta inserir os dados
		
		//perguntas n�vel 1
		/*
		List<String> rq1 = new ArrayList<String>();
		Questao q1 = new Questao("n1 - Qual era a cor do cavalo branco de Napole�o?", rq1, "azul", 1, 1000, 1);
		rq1.add("branco");
		rq1.add("azul");
		rq1.add("amarelo");
		rq1.add("preto");
		*/
		
		//janelas de perguntas
		
		Questionario.readQuestionario();
		new JanelaPerguntas();
		
		//invocando as perguntas p/ o 1� n�vel - necess�rio 5 acertos
		if (acertos < 5) {
			setarCaixa(1);
		}
		
		//fechando a caixa p/ testar o comando de 5 acertos
		if (acertos >= 5 && acertos < 10) {
			setarCaixa(2);
		}
		
		if (acertos >= 10 && acertos < 15) {
			setarCaixa(3);
		}
		
		if (acertos == 15) {
			setarCaixa(4);
		}

		
	}
	
	
	
	public JanelaPerguntas(){
		w1 = new JFrame();
		w1.setResizable(false);
		w1.setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaPerguntas.class.getResource("/showmilhao/poo/ufba/game_pad.png")));
		w1.setTitle("Show do Milh\u00E3o Sibulgator");
		w1.setSize(420,450);
		w1.setLocation((int)(dim.getWidth()-420)/2,(int)(dim.getHeight()-450)/2);
		w1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w1.setVisible(true);
		w1.getContentPane().setLayout(null);
		
		pergunta = new JLabel("Perguntas aqui:");
		pergunta.setBounds(52,15,300,50);
		w1.getContentPane().add(pergunta);
		
		res[0] = new JButton("	");
		res[0].setBounds(52,80,300,40);
		w1.getContentPane().add(res[0]);
		
		res[1] = new JButton("	");
		res[1].setBounds(52,140,300,40);
		w1.getContentPane().add(res[1]);
		
		res[2] = new JButton("	");
		res[2].setBounds(52,200,300, 40);
		w1.getContentPane().add(res[2]);
		
		res[3] = new JButton("	");
		res[3].setBounds(52, 260,300, 40);
		w1.getContentPane().add(res[3]);
		
		parar = new JButton("PARAR");
		parar.setBounds(272, 340,80, 40);
		w1.getContentPane().add(parar);
		
		pular = new JButton("PULAR");
		pular.setBounds(172, 340, 80, 40);
		w1.getContentPane().add(pular);
		
		acertar = new JLabel("Acerto: R$:1000,00");
		acertar.setBounds(12,320,150,40);
		w1.getContentPane().add(acertar);
		
		acumular = new JLabel("Acumulado: R$:" + dinheiro + ",00");
		acumular.setBounds(12,350,180,40);
		w1.getContentPane().add(acumular);
		
		pulos = new JLabel ("Voc� tem 3 pulos"); 
		pulos.setBounds(12,380,150,40);
		w1.getContentPane().add(pulos);
		
		parar.addActionListener(this);
		pular.addActionListener(this);
		res[0].addActionListener(this);
		res[1].addActionListener(this);
		res[2].addActionListener(this);
		res[3].addActionListener(this);
	}

	
	public static Questao setQuestaoByNivel (int nivel) {
		Questao questao = new Questao(null, null, null, null, null, null, null, null, null);
	
		if (nivel == 1) {
			questao = Questionario.listaQuestoes.get(0);
			certo = questao.respostaCorreta;
		}
		if (nivel == 2) {
			questao = Questionario.listaQuestoes2.get(0);
			certo = questao.respostaCorreta;
		}
		if (nivel == 3) {
			questao = Questionario.listaQuestoes3.get(0);
			certo = questao.respostaCorreta;
		}
		if (nivel == 4) {
			questao = Questionario.listaQuestoes4.get(0);
			certo = questao.respostaCorreta;
		}
		
		return questao;
	}
	
	
		//fun��o para setar as perguntas na JanelaPerguntas
		public static void setarCaixa(int nivel) {
			
				Questao questao = setQuestaoByNivel(nivel); 
			
				pergunta.setText("<html> " + questao.pergunta + " </html>");
				res[0].setText(questao.resposta1);
				res[1].setText(questao.resposta2);
				res[2].setText(questao.resposta3);
				res[3].setText(questao.resposta4);
				
		}
		public static void removerQuestao (int nivel) {
			
		//novo m�todo auxiliar
			
			if (nivel == 1) {
				Questionario.listaQuestoes.remove(0);
			}
			
			if (nivel == 2) {
				Questionario.listaQuestoes2.remove(0);
			}
			
			if (nivel == 3) {
				Questionario.listaQuestoes3.remove(0);
			}
			
			if (nivel == 4) {
				Questionario.listaQuestoes4.remove(0);
				pulos.setText("Pergunta Final");
			}
		}
		
		//al�m de setar a caixa, remove a quest�o que foi utilizada, evitando repeti��o
		public static void setarQuestao(int nivel) {
			removerQuestao(nivel);
			setarCaixa(nivel);
		}
		
//		public String obterRespostaCorreta(Questao questao) {
//			return questao.respostaCorreta;
//		}
		
		
		//M�todos criados para otimizar eventos nos bot�es de resposta
		public static void acertarPergunta() {
			JOptionPane.showMessageDialog(null, "Acertou", "Parab�ns!", JOptionPane.INFORMATION_MESSAGE);
			
			if (acertos < 5) {
				setarQuestao(1);
				int dinheiro = acertos * 1000;
				acumular.setText("Acumulado: R$" + dinheiro + ",00");
				Questao questao = Questionario.listaQuestoes.get(0);
				String valor = questao.getValor();
				acertar.setText("Acerto: R$:"+ valor + ",00");
			}
			if (acertos >= 5 && acertos < 10) {
				setarQuestao(2);
				Questao questao = Questionario.listaQuestoes2.get(0);
				String valor = questao.getValor();
				acertar.setText("Acerto: R$:"+ valor + ",00");
				
				if (acertos == 5) {
					acumular.setText("Acumulado: R$:5000,00");
				}
				
				else {
					int dinheiro = acertos2 * 10000;
					acumular.setText("Acumulado: R$" + dinheiro + ",00");
					acertos2++;
				}

				
			}
			if (acertos >= 10 && acertos < 15) {
				setarQuestao(3);
				Questao questao = Questionario.listaQuestoes3.get(0);
				String valor = questao.getValor();
				acertar.setText("Acerto: R$:"+ valor + ",00");
				
				if (acertos == 10) {
					acumular.setText("Acumulado: R$:50000,00");
				}
				
				else {
					int dinheiro = acertos3 * 100000;
					acumular.setText("Acumulado: R$" + dinheiro + ",00");
					acertos3++;
				}
			}
			if (acertos == 15) {
				setarQuestao(4);
				acertar.setText("Milh�o: R$:1.000.000,00");
				acumular.setText("Acumulado: R$: 500.000,00");
			}
			
			if (acertos == 16) {
				w1.dispose();
				JOptionPane.showMessageDialog(null, "Parab�ns! Voc� venceu o Jogo do Milh�o Sibugator\n" 
						+ "e ganhou um pr�mio de R$:1.000.000,00 !!!","Voc� joga muito!", JOptionPane.INFORMATION_MESSAGE);
					
			}
			
			acertos++;
		}
		
		public static void errarPergunta() {
			//w1.dispose();
			if (acertos <= 6) {
				
				acumulado = (acertos -1) * 1000;
				acumulado /= 2;
				JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
				w1.dispose();
			}
			
			
			if (acertos > 6 && acertos <= 11) {

				acumulado = (acertos2 -1) * 10000;
				acumulado /= 2;
				if (acertos == 11) {
					JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:25000,00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			if (acertos > 11 && acertos <= 16) {
				acumulado = (acertos3 -1) * 100000;
				acumulado /= 2;
				if (acertos == 16) {
					JOptionPane.showMessageDialog(null, "Voc� perdeu tudo.","GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
		
		public void actionPerformed(ActionEvent e) {
			
			//Tratamento de evento nos bot�es de resposta
			
			if (e.getSource() == res[0]) {
				if (res[0].getText().equals(certo)) {
					acertarPergunta();
				}
				else {
					errarPergunta();
				}
			}
		
			if (e.getSource() == res[1]) {
				if (res[1].getText().equals(certo)) {
					acertarPergunta();
				}
				else {
					errarPergunta();
				}
			}
			
			if (e.getSource() == res[2]) {
				if (res[2].getText().equals(certo)) {
					acertarPergunta();
				}
				else {
					errarPergunta();
				}
			}
			if (e.getSource() == res[3]) {
				if (res[3].getText().equals(certo)) {
					acertarPergunta();
				}
				else {
					errarPergunta();
				}
			}
			
			
			//Tratamento de evento do bot�o parar
			if (e.getSource() == parar) {
				//w1.dispose();
				if (acertos <= 6) {
					
					acumulado = (acertos -1) * 1000;
					JOptionPane.showMessageDialog(null, "Voc� acumulou R$:" + acumulado + ",00","Tente de novo!", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (acertos > 6 && acertos <= 11) {

					acumulado = (acertos2 -1) * 10000;
					if (acertos == 11) {
						JOptionPane.showMessageDialog(null, "Voc� acumulou R$:50000,00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Voc� acumulou R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				if (acertos > 11 && acertos <= 16) {
					acumulado = (acertos3 -1) * 100000;
					if (acertos == 16) {
						JOptionPane.showMessageDialog(null, "Voc� acumulou R$:500.000,00","Muito bem!", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Voc� acumulou R$:" + acumulado + ",00","Muito bem!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			
				w1.dispose();
			}
			
			//Tratamento de evento do bot�o pular
			if (e.getSource() == pular) {		
				
				if (nDePulos > 0) {
					nDePulos--;
					
					if (acertos < 5) {
						setarQuestao(1);
						
						if (nDePulos != 1) {
							pulos.setText("Voc� tem " + nDePulos + " pulos");
						}
						else {
							pulos.setText("Voc� tem " + nDePulos + " pulo");
						}
					}
					
					if (acertos >= 5 && acertos < 10) {
						setarQuestao(2);
						
						if (nDePulos != 1) {
							pulos.setText("Voc� tem " + nDePulos + " pulos");
						}
						else {
							pulos.setText("Voc� tem " + nDePulos + " pulo");
						}
					}
					if (acertos >= 10 && acertos < 15) {
						setarQuestao(3);
						
						if (nDePulos != 1) {
							pulos.setText("Voc� tem " + nDePulos + " pulos");
						}
						else {
							pulos.setText("Voc� tem " + nDePulos + " pulo");
						}
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Voc� n�o tem mais pulos","Sem recursos!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			
			//Tratamento de eventos dos bot�es de resposta
			/*
			if (e.getSource() == res[0]) {
				
				if (res[0].getText().equals(certo)) {
					
					JOptionPane.showMessageDialog(null, "Acertou", "Parab�ns!", JOptionPane.INFORMATION_MESSAGE);
					
					if (acertos < 5) {
						setarQuestao(1);
						int dinheiro = acertos * 1000;
						acumular.setText("Acumulado: R$" + dinheiro + ",00");
						Questao questao = Questionario.listaQuestoes.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
					}
					if (acertos >= 5 && acertos < 10) {
						setarQuestao(2);
						Questao questao = Questionario.listaQuestoes2.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
						
						if (acertos == 5) {
							acumular.setText("Acumulado: R$:5000,00");
						}
						
						else {
							int dinheiro = acertos2 * 10000;
							acumular.setText("Acumulado: R$" + dinheiro + ",00");
							acertos2++;
						}
		
						
					}
					if (acertos >= 10 && acertos < 15) {
						setarQuestao(3);
						Questao questao = Questionario.listaQuestoes3.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
						
						if (acertos == 10) {
							acumular.setText("Acumulado: R$:50000,00");
						}
						
						else {
							int dinheiro = acertos3 * 100000;
							acumular.setText("Acumulado: R$" + dinheiro + ",00");
							acertos3++;
						}
					}
					if (acertos == 15) {
						setarQuestao(4);
						acertar.setText("Milh�o: R$:1.000.000,00");
						acumular.setText("Acumulado: R$: 500.000,00");
					}
					
					if (acertos == 16) {
						w1.dispose();
						JOptionPane.showMessageDialog(null, "Parab�ns! Voc� venceu o Jogo do Milh�o Sibugator\n" 
								+ "e ganhou um pr�mio de R$:1.000.000,00 !!!","Voc� joga muito!", JOptionPane.INFORMATION_MESSAGE);
							
					}
					
					acertos++;
				}
				else {
					w1.dispose();
					if (acertos <= 6) {
						
						acumulado = (acertos -1) * 1000;
						acumulado /= 2;
						JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
					}
					
					if (acertos > 6 && acertos <= 11) {

						acumulado = (acertos2 -1) * 10000;
						acumulado /= 2;
						if (acertos == 11) {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:25000,00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					if (acertos > 11 && acertos <= 16) {
						acumulado = (acertos3 -1) * 100000;
						acumulado /= 2;
						if (acertos == 16) {
							JOptionPane.showMessageDialog(null, "Voc� perdeu tudo.","GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
			
			if (e.getSource() == res[1]) {
				
				if (res[1].getText().equals(certo)) {
				
					JOptionPane.showMessageDialog(null, "Acertou", "Parab�ns!", JOptionPane.INFORMATION_MESSAGE);
					
					if (acertos < 5) {
						setarQuestao(1);
						int dinheiro = acertos * 1000;
						acumular.setText("Acumulado: R$" + dinheiro + ",00");
						Questao questao = Questionario.listaQuestoes.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
					}
					if (acertos >= 5 && acertos < 10) {
						setarQuestao(2);
						Questao questao = Questionario.listaQuestoes2.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
						
						if (acertos == 5) {
							acumular.setText("Acumulado: R$:5000,00");
						}
						
						else {
							int dinheiro = acertos2 * 10000;
							acumular.setText("Acumulado: R$" + dinheiro + ",00");
							acertos2++;
						}
					}
					if (acertos >= 10 && acertos < 15) {
						setarQuestao(3);
						Questao questao = Questionario.listaQuestoes3.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
						
						if (acertos == 10) {
							acumular.setText("Acumulado: R$:50000,00");
						}
						
						else {
							int dinheiro = acertos3 * 100000;
							acumular.setText("Acumulado: R$" + dinheiro + ",00");
							acertos3++;
						}
					}
					if (acertos == 15) {
						setarQuestao(4);
						acertar.setText("Milh�o: R$:1.000.000,00");
						acumular.setText("Acumulado: R$: 500.000,00");
					}
					
					if (acertos == 16) {
						w1.dispose();
						JOptionPane.showMessageDialog(null, "Parab�ns! Voc� venceu o Jogo do Milh�o Sibugator\n" 
								+ "e ganhou um pr�mio de R$:1.000.000,00 !!!","Voc� joga muito!", JOptionPane.INFORMATION_MESSAGE);
							
					}
					
					acertos++;
				}
				else {
					w1.dispose();
					if (acertos <= 6) {
						
						acumulado = (acertos -1) * 1000;
						acumulado /= 2;
						JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
					}
					
					if (acertos > 6 && acertos <= 11) {

						acumulado = (acertos2 -1) * 10000;
						acumulado /= 2;
						if (acertos == 11) {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:25000,00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					if (acertos > 11 && acertos <= 16) {
						acumulado = (acertos3 -1) * 100000;
						acumulado /= 2;
						if (acertos == 16) {
							JOptionPane.showMessageDialog(null, "Voc� perdeu tudo.","GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
			
			if (e.getSource() == res[2]) {
				
				if (res[2].getText().equals(certo)) {
					
					JOptionPane.showMessageDialog(null, "Acertou", "Parab�ns!", JOptionPane.INFORMATION_MESSAGE);

					if (acertos < 5) {
						setarQuestao(1);
						int dinheiro = acertos * 1000;
						acumular.setText("Acumulado: R$" + dinheiro + ",00");
						Questao questao = Questionario.listaQuestoes.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
					}
					if (acertos >= 5 && acertos < 10) {
						setarQuestao(2);
						Questao questao = Questionario.listaQuestoes2.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
						
						if (acertos == 5) {
							acumular.setText("Acumulado: R$:5000,00");
						}
						
						else {
							int dinheiro = acertos2 * 10000;
							acumular.setText("Acumulado: R$" + dinheiro + ",00");
							acertos2++;
						}
					}
					if (acertos >= 10 && acertos < 15) {
						setarQuestao(3);
						Questao questao = Questionario.listaQuestoes3.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
						
						if (acertos == 10) {
							acumular.setText("Acumulado: R$:50000,00");
						}
						
						else {
							int dinheiro = acertos3 * 100000;
							acumular.setText("Acumulado: R$" + dinheiro + ",00");
							acertos3++;
						}
					}
					if (acertos == 15) {
						setarQuestao(4);
						acertar.setText("Milh�o: R$:1.000.000,00");
						acumular.setText("Acumulado: R$: 500.000,00");
					}
					
					if (acertos == 16) {
						w1.dispose();
						JOptionPane.showMessageDialog(null, "Parab�ns! Voc� venceu o Jogo do Milh�o Sibugator\n" 
								+ "e ganhou um pr�mio de R$:1.000.000,00 !!!","Voc� joga muito!", JOptionPane.INFORMATION_MESSAGE);
							
					}
					
					acertos++;
				}
				else {
					w1.dispose();
					if (acertos <= 6) {
						
						acumulado = (acertos -1) * 1000;
						acumulado /= 2;
						JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
					}
					
					if (acertos > 6 && acertos <= 11) {

						acumulado = (acertos2 -1) * 10000;
						acumulado /= 2;
						if (acertos == 11) {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:25000,00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					if (acertos > 11 && acertos <= 16) {
						acumulado = (acertos3 -1) * 100000;
						acumulado /= 2;
						if (acertos == 16) {
							JOptionPane.showMessageDialog(null, "Voc� perdeu tudo.","GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
				}
			}
			
			if (e.getSource() == res[3]) {
				
				if (res[3].getText().equals(certo)) {
		
					JOptionPane.showMessageDialog(null, "Acertou", "Parab�ns!", JOptionPane.INFORMATION_MESSAGE);
					
					if (acertos < 5) {
						setarQuestao(1);
						int dinheiro = acertos * 1000;
						acumular.setText("Acumulado: R$" + dinheiro + ",00");
						Questao questao = Questionario.listaQuestoes.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
					}
					if (acertos >= 5 && acertos < 10) {
						setarQuestao(2);
						Questao questao = Questionario.listaQuestoes2.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
						
						if (acertos == 5) {
							acumular.setText("Acumulado: R$:5000,00");
						}
						
						else {
							int dinheiro = acertos2 * 10000;
							acumular.setText("Acumulado: R$" + dinheiro + ",00");
							acertos2++;
						}
					}
					if (acertos >= 10 && acertos < 15) {
						setarQuestao(3);
						Questao questao = Questionario.listaQuestoes3.get(0);
						String valor = questao.getValor();
						acertar.setText("Acerto: R$:"+ valor + ",00");
						
						if (acertos == 10) {
							acumular.setText("Acumulado: R$:50000,00");
						}
						
						else {
							int dinheiro = acertos3 * 100000;
							acumular.setText("Acumulado: R$" + dinheiro + ",00");
							acertos3++;
						}
					}
					if (acertos == 15) {
						setarQuestao(4);
						acertar.setText("Milh�o: R$:1.000.000,00");
						acumular.setText("Acumulado: R$: 500.000,00");
					}
					
					if (acertos == 16) {
						w1.dispose();
						JOptionPane.showMessageDialog(null, "Parab�ns! Voc� venceu o Jogo do Milh�o Sibugator\n" 
								+ "e ganhou um pr�mio de R$:1.000.000,00 !!!","Voc� joga muito!", JOptionPane.INFORMATION_MESSAGE);
							
					}
					
					acertos++;
				}
				else {
					w1.dispose();
					if (acertos <= 6) {
						
						acumulado = (acertos -1) * 1000;
						acumulado /= 2;
						JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
					}
					
					if (acertos > 6 && acertos <= 11) {

						acumulado = (acertos2 -1) * 10000;
						acumulado /= 2;
						if (acertos == 11) {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:25000,00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					if (acertos > 11 && acertos <= 16) {
						acumulado = (acertos3 -1) * 100000;
						acumulado /= 2;
						if (acertos == 16) {
							JOptionPane.showMessageDialog(null, "Voc� perdeu tudo.","GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(null, "Voc� errou. Voc� leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} 
			} */
			
				
		}
		
	}
