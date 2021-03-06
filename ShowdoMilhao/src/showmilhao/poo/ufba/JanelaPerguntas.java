package showmilhao.poo.ufba;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class JanelaPerguntas implements ActionListener  {

	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	static   JLabel	pergunta;
	static   JButton res[] = new JButton[4];
	JButton  parar = new JButton();
	JButton  pular;
	
	static  JLabel  acertar;
	static  JLabel  acumular;
	static  JLabel  pulos;
	static  JLabel tempo;
	
	static  JFrame  w1;
	
	static int i;
	static  int nDePulos = 3;
	static  int dinheiro = 0;
	static  int acumulado = 0;
	static  int oldtime = 30;
	static  int curtime = 30;
	static  int timeID = 0;

	static boolean selecionado = false;
	
	static int acertos = 1;
	static int acertos2 = 1;
	static int acertos3 = 1;
	static int nivelAtual = 1;
	
	static String certo;
	
	static File acertou = new File("acertou.wav");
	static File errou = new File("errou.wav");
	static File comeco = new File("sibugator.wav");
	static File audioDinheiro = new File("dinheiro.wav");
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		final ImageIcon abertura = new ImageIcon("abertura.png");
		JOptionPane.showMessageDialog(null, "", "Clique no OK para come�ar o jogo", JOptionPane.INFORMATION_MESSAGE, abertura);

		Audio.tocarSom(comeco, 5600);
		
		//janelas de perguntas
		
		Questionario.readQuestionario();
		new JanelaPerguntas();
		
		
		//invocando as perguntas p/ o 1º nivel - necessario 5 acertos
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
		
		w1.setVisible(true);
		setaTempo();

	}
	
	public JanelaPerguntas(){
		w1 = new JFrame();
		w1.setResizable(false);
		w1.setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaPerguntas.class.getResource("/showmilhao/poo/ufba/game_pad.png")));
		w1.setTitle("Show do Milh\u00E3o Sibulgator");
		w1.setSize(420,450);
		w1.setLocation((int)(dim.getWidth()-420)/2,(int)(dim.getHeight()-450)/2);
		w1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		tempo = new JLabel("Tempo:" + curtime);
		tempo.setBounds(12,295,120,40);
		w1.getContentPane().add(tempo);
		
		acertar = new JLabel("Acerto: R$:1000,00");
		acertar.setBounds(12,320,150,40);
		w1.getContentPane().add(acertar);
		
		acumular = new JLabel("Acumulado: R$:" + dinheiro + ",00");
		acumular.setBounds(12,350,180,40);
		w1.getContentPane().add(acumular);
		
		pulos = new JLabel ("Voce tem 3 pulos"); 
		pulos.setBounds(12,380,210,40);
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
		}
		if (nivel == 2) {
			questao = Questionario.listaQuestoes2.get(0);
		}
		if (nivel == 3) {
			questao = Questionario.listaQuestoes3.get(0);
		}
		if (nivel == 4) {
			questao = Questionario.listaQuestoes4.get(0);
		}
		
		certo = questao.respostaCorreta;
		return questao;
	}
	
	public static void setaTempo() throws InterruptedException{
		for (i = oldtime; i >= 0; i--){
			while(selecionado == true){
				System.out.println("selecionado" + selecionado);
				i = oldtime;
			}
			System.out.println("janelinha" + w1.isActive());
			Thread.sleep(1000);
			curtime = i;
			System.out.println(i);
			tempo.setText("Tempo:" + curtime);
			if (i == 0){
				JOptionPane.showMessageDialog(null, "Tempo acabou. Voce perde tudo","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
				w1.dispose();
			}
		}
	}
	
	
		//funcao para setar as perguntas na JanelaPerguntas
		public static void setarCaixa(int nivel) {
			
			Questao questao = setQuestaoByNivel(nivel); 
			
			pergunta.setText("<html> " + questao.pergunta + " </html>");
			res[0].setText(questao.resposta1);
			res[1].setText(questao.resposta2);
			res[2].setText(questao.resposta3);
			res[3].setText(questao.resposta4);
			
			//Teste
			//System.out.println(questao.respostaCorreta +  "      " + questao.nivel);
				
		}
		public static void removerQuestao (int nivel) {
			
		//novo metodo auxiliar
		
			
			if (nivel == 1) {
				Questionario.listaQuestoes.remove(0);
			}
			
			if (nivel == 2) {
				Questionario.listaQuestoes2.remove(0);
				if (acertos == 5){	
				}
			}
			
			if (nivel == 3) {
				Questionario.listaQuestoes3.remove(0);
				if (acertos == 10){
				}
			}
			
			if (nivel == 4) {
				Questionario.listaQuestoes4.remove(0);
				pulos.setText("Pergunta Final");
				if (acertos == 15){
					
				}
			}
		}
		
		//alem de setar a caixa, remove a questao que foi utilizada, evitando repeticao
		public static void setarQuestao(int nivel) {
			removerQuestao(nivel);
			setarCaixa(nivel);
		}
		
		
		public static void passarDeNivel() {
			
			if (acertos == 5 || acertos == 10) {
				++nivelAtual;
				JOptionPane.showMessageDialog(null, "Voce chegou no nivel " + nivelAtual,"Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		public static void acertarPergunta() throws IOException, InterruptedException {	
			if (acertos != 16) {
				Audio.tocarSom(acertou, 1800);
				JOptionPane.showMessageDialog(null, "Acertou", "Parabens!", JOptionPane.INFORMATION_MESSAGE);
			}
			
			passarDeNivel();
			
			curtime = 30;
			i = 30;
			tempo.setText("Tempo:" + curtime);
			
			
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
				JOptionPane.showMessageDialog(null, "Voce chegou no �LTIMO n�vel! \n" + "Ou PARA ou pode PERDER TUDO!", null, JOptionPane.INFORMATION_MESSAGE);
				Audio.tocarSom(audioDinheiro, 1550);
				setarQuestao(4);
				acertar.setText("Milhao: R$:1.000.000,00");
				acumular.setText("Acumulado: R$: 500.000,00");
			}
			
			if (acertos == 16) {
				
				sumonarPopUps();
	
				Audio ganhou = new Audio();
				ganhou.audioid = 1;
				Thread threadMusica2 = new Thread(ganhou);
			    threadMusica2.start();
				JOptionPane.showMessageDialog(null, "Parabens! Voce venceu o Jogo do Milhao Sibugator\n" 
						+ "e ganhou um premio de R$:1.000.000,00 !!!","Voce joga muito!", JOptionPane.INFORMATION_MESSAGE);
				
				sumonarPopUpsWin();
				
				selecionado = true;
				final ImageIcon ganhouimage = new ImageIcon("ganhou.jpg");
				JOptionPane.showMessageDialog(null, null, "VOC� � FERA, BICHO", JOptionPane.INFORMATION_MESSAGE, ganhouimage);
				w1.dispose();		
				System.exit(0);
			}
			
			acertos++;
		}
		
		public static void errarPergunta() throws IOException, InterruptedException {
			
			if (acertos != 16) {
				Audio.tocarSom(errou, 1800);
			}
			// 
			
			if (acertos <= 6) {
				
				acumulado = (acertos -1) * 1000;
				acumulado /= 2;
				JOptionPane.showMessageDialog(null, "Voce errou. Voce leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
	
			}
			
			if (acertos > 6 && acertos <= 11) {

				acumulado = (acertos2 -1) * 10000;
				acumulado /= 2;
				if (acertos == 11) {
					JOptionPane.showMessageDialog(null, "Voce errou. Voce leva R$:25000,00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Voce errou. Voce leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			if (acertos > 11 && acertos <= 16) {
				acumulado = (acertos3 -1) * 100000;
				acumulado /= 2;
				if (acertos == 16) {
					sumonarPopUps();
					final ImageIcon fausto = new ImageIcon("darkfausto.jpg");
					Audio perdeu = new Audio();
					perdeu.audioid = 0;
					selecionado = true;
					JOptionPane.showMessageDialog(null, "Voce perdeu tudo.","GAME OVER!", JOptionPane.INFORMATION_MESSAGE);
					Thread threadMusica = new Thread(perdeu);
				    threadMusica.start();
					JOptionPane.showMessageDialog(null, " ", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, fausto);
					System.exit(0);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Voce errou. Voce leva R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
				}
			}	
			
			w1.dispose();
			System.exit(0);
		}
		
		
		public static void sumonarPopUps() {
			JOptionPane.showMessageDialog(null, "Voce tem certeza?\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "Voce tem certeza MESMO?\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "POP UP!\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "NAO FOI DESSA VEZ\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "Voce foi DIBRADO\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "Voce tem persistencia?\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "Ich liebe dich nicht\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "ERROU\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "HUEUHEHUEUHEUHEHUE\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "BRBRBRBRBRBRBR\n" 
					+ "","", JOptionPane.INFORMATION_MESSAGE);	
		}
		
		public static void sumonarPopUpsWin() {
			JOptionPane.showMessageDialog(null, "Voce acaba de doar metade do dinheiro para a fundacao Bill Gates\n" 
					+ "e sua pesquisa de vacas com odor humano!","", JOptionPane.INFORMATION_MESSAGE);		
			JOptionPane.showMessageDialog(null, "Caiu no conto do vigario, perdeu tudo ao clicar\n" 
					+ "em OK! Fim do programa.","", JOptionPane.INFORMATION_MESSAGE);
		}
		
		public void setarBackground() {
			for (int i = 0; i < 4; i++) {
				if (res[i].getText().equals(certo)) {
					res[i].setBackground(Color.GREEN);
				}
			}
		}
		
		public void resetarBackground() throws InterruptedException {
			for (int i = 0; i < 4; i++) {
				res[i].setBackground(null);
			}
			selecionado = false;
		}
		
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 4; i++)
				{ 
				if (e.getSource() == res[i]) {
					
					try {
						
						if (acertos != 16) {
							setarBackground();
						
							if (res[i].getText().equals(certo)) {
								selecionado = true;
								acertarPergunta();
								resetarBackground();
							}
						
							else {
								res[i].setBackground(Color.RED);
								errarPergunta();
							}
						}
						else {
							selecionado = true;
							if (res[i].getText().equals(certo)) { 
								acertarPergunta();
							}
							else {
								errarPergunta();
							}
						}
					} catch (IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			
			if (e.getSource() == parar) {
				selecionado = true;
				w1.dispose();
				if (acertos <= 6) {
					
					acumulado = (acertos -1) * 1000;
					JOptionPane.showMessageDialog(null, "Voce acumulou R$:" + acumulado + ",00","Tente de novo!", JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (acertos > 6 && acertos <= 11) {

					acumulado = (acertos2 -1) * 10000;
					if (acertos == 11) {
						JOptionPane.showMessageDialog(null, "Voce acumulou R$:50000,00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Voce acumulou R$:" + acumulado + ",00","Bom Jogo!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				if (acertos > 11 && acertos <= 16) {
					acumulado = (acertos3 -1) * 100000;
					if (acertos == 16) {
						JOptionPane.showMessageDialog(null, "Voce acumulou R$:500.000,00","Muito bem!", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Voce acumulou R$:" + acumulado + ",00","Muito bem!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				System.exit(0);
			}
			
			
			if (e.getSource() == pular) {
				setarPulo();
			}
			
		}
			
			public static boolean validarPulo() {
				if (nDePulos > 0 && acertos < 15) {
					return true;
				}
				else {
					return false;
				}
			}
					
			public static void pularQuestao() {
				if (acertos < 5) {
					setarQuestao(1);
				}
				if (acertos >= 5 && acertos < 10) {
					setarQuestao(2);
				}
				if (acertos >= 10 && acertos < 15) {
					setarQuestao(3);
				}
				nDePulos--;
			}
				
			public void setarPulo() {
				if (validarPulo()) {
					pularQuestao();
					curtime = 30;
					i = 30;
					if (nDePulos != 1) {
						pulos.setText("Voce tem " + nDePulos + " pulos");
					}
					else {
						pulos.setText("Voce tem " + nDePulos + " pulo");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Voce nao tem mais pulos","Sem recursos!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		
	}

	

