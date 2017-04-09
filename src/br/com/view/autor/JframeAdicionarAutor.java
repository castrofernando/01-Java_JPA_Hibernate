package br.com.view.autor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.AutorDAO;
import br.com.dao.impl.AutorDAOImpl;
import br.com.entity.Autor;
import br.com.entity.Sexo;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class JframeAdicionarAutor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeAdicionarAutor frame = new JframeAdicionarAutor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JframeAdicionarAutor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(36, 18, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(36, 83, 46, 14);
		contentPane.add(lblSexo);
		
		JLabel lblDataDeNasc = new JLabel("Data Nasc.:");
		lblDataDeNasc.setBounds(10, 139, 72, 14);
		contentPane.add(lblDataDeNasc);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(10, 43, 72, 14);
		contentPane.add(lblSobrenome);
		
		JRadioButton rdSexoMasc = new JRadioButton("Masculino");
		rdSexoMasc.setSelected(true);
		buttonGroup.add(rdSexoMasc);
		rdSexoMasc.setBounds(74, 79, 109, 23);
		contentPane.add(rdSexoMasc);
		
		final JRadioButton rdSexoFem = new JRadioButton("Feminino");
		buttonGroup.add(rdSexoFem);
		rdSexoFem.setBounds(74, 105, 109, 23);
		contentPane.add(rdSexoFem);
		
		txtNome = new JTextField();
		txtNome.setBounds(74, 15, 179, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(74, 40, 179, 20);
		contentPane.add(txtSobrenome);
		
		final JFormattedTextField txtDataNasc = new JFormattedTextField();
		txtDataNasc.setBounds(74, 136, 179, 20);
		contentPane.add(txtDataNasc);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				Autor autor = new Autor();
				AutorDAO dao = new AutorDAOImpl(em);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				try{
					autor.setNome(txtNome.getText().trim());
					autor.setSexo(rdSexoFem.isSelected() ? Sexo.FEMININO : Sexo.MASCULINO);
					autor.setSobrenome(txtSobrenome.getText().trim());
					Date data = sdf.parse(txtDataNasc.getText().trim());
					cal.setTime(data);
					autor.setDataNascimento(cal);
					dao.inserir(autor);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Autor adicionado com sucesso!");
					txtNome.setText("");
					txtSobrenome.setText("");
					txtDataNasc.setText("");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Falha ao adicionar autor");
					e.printStackTrace();
				}finally{
					em.close();
				}
			}
		});
		btnSalvar.setBounds(65, 176, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeAdicionarAutor.this.setVisible(false);
			}
		});
		btnFechar.setBounds(164, 176, 89, 23);
		contentPane.add(btnFechar);
	}
}
