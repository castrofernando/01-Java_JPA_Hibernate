package br.com.view.usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.UsuarioDAO;
import br.com.dao.impl.UsuarioDAOImpl;
import br.com.entity.Usuario;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JframeAdicionarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeAdicionarUsuario frame = new JframeAdicionarUsuario();
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
	public JframeAdicionarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 251, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setBounds(28, 14, 46, 14);
		contentPane.add(lblUsurio);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(84, 11, 130, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(35, 47, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(84, 44, 130, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(10, 72, 64, 14);
		contentPane.add(lblSobrenome);
		
		JLabel lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(46, 105, 46, 14);
		contentPane.add(lblCpf);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(84, 71, 130, 20);
		contentPane.add(txtSobrenome);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(84, 102, 130, 20);
		contentPane.add(txtCpf);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				UsuarioDAO dao = new UsuarioDAOImpl(em);
				Usuario usuario = new Usuario(0,
											txtUsuario.getText().trim(),
											txtNome.getText().trim(), 
											txtSobrenome.getText().trim(),
											txtCpf.getText().trim());
				try{
					dao.inserir(usuario);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso.");
					txtUsuario.setText("");
					txtCpf.setText("");
					txtNome.setText("");
					txtSobrenome.setText("");
				}catch(Exception error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null,"Falha ao adicionar usuário: " + error.getMessage());
				}finally{
					em.close();
				}
			}
		});
		btnSalvar.setBounds(28, 144, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeAdicionarUsuario.this.setVisible(false);
			}
		});
		btnFechar.setBounds(125, 144, 89, 23);
		contentPane.add(btnFechar);
	}
}
