package br.com.view.usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.UsuarioDAO;
import br.com.dao.impl.UsuarioDAOImpl;
import br.com.entity.Usuario;
import br.com.exceptions.IdNotFoundException;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JframeEditarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCpf;
	private JTextField txtSobrenome;
	private JTextField txtNome;
	private JTextField txtUsuario;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeEditarUsuario frame = new JframeEditarUsuario();
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
	public JframeEditarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 244);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				UsuarioDAO dao = new UsuarioDAOImpl(em);
				Usuario usuario = new Usuario(Integer.parseInt(txtId.getText().trim()),
											txtUsuario.getText().trim(),
											txtNome.getText().trim(), 
											txtSobrenome.getText().trim(),
											txtCpf.getText().trim());
				try{
					dao.atualizar(usuario);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso.");
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
		btnSalvar.setBounds(28, 178, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeEditarUsuario.this.setVisible(false);
			}
		});
		btnFechar.setBounds(125, 178, 89, 23);
		contentPane.add(btnFechar);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(84, 136, 130, 20);
		contentPane.add(txtCpf);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(84, 105, 130, 20);
		contentPane.add(txtSobrenome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(84, 78, 130, 20);
		contentPane.add(txtNome);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(84, 45, 130, 20);
		contentPane.add(txtUsuario);
		
		JLabel label = new JLabel("Usu\u00E1rio:");
		label.setBounds(28, 48, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(35, 81, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Sobrenome:");
		label_2.setBounds(10, 106, 64, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Cpf:");
		label_3.setBounds(46, 139, 46, 14);
		contentPane.add(label_3);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(46, 17, 22, 14);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(84, 14, 52, 20);
		contentPane.add(txtId);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				UsuarioDAO dao = new UsuarioDAOImpl(em);
				try{
					Usuario usuario = dao.buscar(Integer.parseInt(txtId.getText().trim()));
					if(usuario==null){
						throw new IdNotFoundException("Usuário não encontrado");
					}else{
						txtUsuario.setText(usuario.getUsuario());
						txtNome.setText(usuario.getNome());
						txtSobrenome.setText(usuario.getSobrenome());
						txtCpf.setText(usuario.getCpf());
					}
				}catch(Exception error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, error.getMessage());
				}finally{
					em.close();
				}
				
			}
		});
		btnBuscar.setBounds(146, 13, 68, 23);
		contentPane.add(btnBuscar);
	}

}
