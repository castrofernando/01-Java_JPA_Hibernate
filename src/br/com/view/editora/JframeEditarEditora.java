package br.com.view.editora;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.EditoraDAO;
import br.com.dao.impl.EditoraDAOImpl;
import br.com.entity.Editora;
import br.com.exceptions.CommitException;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JframeEditarEditora extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCnpj;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeEditarEditora frame = new JframeEditarEditora();
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
	public JframeEditarEditora() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 458, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(27, 40, 46, 14);
		contentPane.add(lblCnpj);

		txtCnpj = new JTextField();
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(69, 37, 364, 20);
		contentPane.add(txtCnpj);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(69, 63, 364, 20);
		contentPane.add(txtNome);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(27, 66, 46, 14);
		contentPane.add(lblNome);

		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setBounds(10, 92, 63, 14);
		contentPane.add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(69, 89, 364, 20);
		contentPane.add(txtEndereco);

		JButton button = new JButton("Salvar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				EditoraDAO dao = new EditoraDAOImpl(em);
				try {
					Editora editora = dao.buscar(Integer.parseInt(txtCodigo.getText()));
					editora.setCnpj(txtCnpj.getText().trim());
					editora.setEndereco(txtEndereco.getText().trim());
					editora.setNome(txtNome.getText().trim());
					dao.atualizar(editora);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Editora editada com sucesso!");
				} catch (CommitException error) {
					JOptionPane.showMessageDialog(null, "Falha ao salvar alteração");
					error.printStackTrace();
				} finally {
					em.close();
				}
			}
		});
		button.setBounds(69, 120, 87, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("Fechar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JframeEditarEditora.this.setVisible(false);
			}
		});
		button_1.setBounds(166, 120, 86, 23);
		contentPane.add(button_1);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(69, 9, 86, 20);
		contentPane.add(txtCodigo);

		JLabel lblCodigo = new JLabel("C\u00F3digo:");
		lblCodigo.setBounds(27, 12, 46, 14);
		contentPane.add(lblCodigo);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				EditoraDAO dao = new EditoraDAOImpl(em);
				try {
					Editora editora = dao.buscar(Integer.parseInt(txtCodigo.getText().trim()));
					if (editora == null) {
						JOptionPane.showMessageDialog(null, "Id não encontrado!");
					}
					txtCnpj.setText(editora.getCnpj().trim());
					txtNome.setText(editora.getNome().trim());
					txtEndereco.setText(editora.getEndereco().trim());
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
					error.printStackTrace();
				} finally {
					em.close();
				}

			}
		});
		btnBuscar.setBounds(165, 8, 71, 23);
		contentPane.add(btnBuscar);
	}

}
