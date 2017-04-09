package br.com.view.editora;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.EditoraDAO;
import br.com.dao.impl.EditoraDAOImpl;
import br.com.entity.Editora;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JframeCadastrarEditora extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCnpj;
	private JTextField txtNome;
	private JLabel lblNome;
	private JTextField txtEndereco;
	private JLabel lblEndereco;
	private JButton btnFechar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeCadastrarEditora frame = new JframeCadastrarEditora();
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
	public JframeCadastrarEditora() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 463, 164);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCnpj = new JTextField();
		txtCnpj.setBounds(69, 11, 364, 20);
		contentPane.add(txtCnpj);
		txtCnpj.setColumns(10);
		
		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(27, 14, 46, 14);
		contentPane.add(lblCnpj);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(69, 37, 364, 20);
		contentPane.add(txtNome);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(27, 40, 46, 14);
		contentPane.add(lblNome);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(69, 63, 364, 20);
		contentPane.add(txtEndereco);
		
		lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 66, 63, 14);
		contentPane.add(lblEndereco);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				EditoraDAO dao = new EditoraDAOImpl(em);
				Editora editora = new Editora();
				editora.setCnpj(txtCnpj.getText().trim());
				editora.setNome(txtNome.getText().trim());
				editora.setEndereco(txtEndereco.getText().trim());
				dao.inserir(editora);
				try{
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Registro adicionado com sucesso!");
					txtCnpj.setText("");
					txtEndereco.setText("");
					txtNome.setText("");
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Falha ao adicionar registro - " + e.getMessage());
					e.printStackTrace();
				}finally{
					em.close();
				}
			}
		});
		btnSalvar.setBounds(69, 94, 71, 23);
		contentPane.add(btnSalvar);
		
		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeCadastrarEditora.this.setVisible(false);
			}
		});
		btnFechar.setBounds(150, 94, 71, 23);
		contentPane.add(btnFechar);
	}
}
