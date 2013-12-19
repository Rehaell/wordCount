import java.awt.event.*;
import javax.swing.*;
import java.util.*;


@SuppressWarnings("serial")
public class mainWindow extends JFrame implements ActionListener {
	
	JTextArea textoArea = new JTextArea(40,100);
	
	JLabel numeroPalavras = new JLabel("Numero total de palavras contadas : ");
	JButton calcularBotao = new JButton("Contar palavras");
	JButton relatorioBotao = new JButton("Gerar relatorio");
	JButton limparBotao = new JButton("Limpar TUDO");
	Map<String, Integer> palavrasContadas = new HashMap<String, Integer>();
	
	public mainWindow(){
		setLookAndFeel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainInfo = new JPanel();
		
		JLabel textoLabel = new JLabel("Texto : ");
		mainInfo.add(textoLabel);
		
		textoArea.setLineWrap(true);
		textoArea.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(textoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		mainInfo.add(scroll);
				
		
		
		calcularBotao.addActionListener(this);
		//relatorioBotao.setEnabled(false);
		relatorioBotao.addActionListener(this);
		limparBotao.addActionListener(this);
		
		
		mainInfo.add(numeroPalavras);
		mainInfo.add(calcularBotao);
		mainInfo.add(relatorioBotao);
		mainInfo.add(limparBotao);
		add(mainInfo);
		
		pack();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		if (event.getSource() == calcularBotao )
			contarPalavras();
		else if (event.getSource() == relatorioBotao ){
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					DrawGraph.createAndShowGui(palavrasContadas);
		        }
		    });	
		}
		else if (event.getSource() == limparBotao){
			limparTudo();
		}
			
	}
	
	private void limparTudo(){
		textoArea.setText("");
		textoArea.setEditable(true);
		relatorioBotao.setEnabled(false);
		numeroPalavras.setText("Numero de palavras contadas :");
		palavrasContadas.clear();
	}
	private void contarPalavras(){
		textoArea.setEditable(false);
		String[] txt = textoArea.getText().toLowerCase().split("\\s+");
		
		for (String palavra : txt) {
			Integer count = palavrasContadas.get(palavra);
			if (count == null) {
				count = 0;
			}
			palavrasContadas.put(palavra, count + 1);
		}
		relatorioBotao.setEnabled(true);
		numeroPalavras.setText("Numero de palavras contadas : " + palavrasContadas.size());
		System.out.print(palavrasContadas.values());
	}
	
	private void setLookAndFeel(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception exc){
			//ignore error
		}
	}
	
	
	public static void main(String[] args) {
		new mainWindow();

	}

}
