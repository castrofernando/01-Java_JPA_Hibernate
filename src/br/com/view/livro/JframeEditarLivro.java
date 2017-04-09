package br.com.view.livro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.LivroDAO;
import br.com.dao.impl.LivroDAOImpl;
import br.com.entity.Livro;
import br.com.exceptions.IdNotFoundException;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class JframeEditarLivro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtISNB;
	private JTextField txtTitulo;
	private JTextField txtDataLanc;
	private JTextField txtPreco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeEditarLivro frame = new JframeEditarLivro();
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
	public JframeEditarLivro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("ISNB:");
		label.setBounds(31, 14, 46, 14);
		contentPane.add(label);

		txtISNB = new JTextField();
		txtISNB.setColumns(10);
		txtISNB.setBounds(67, 11, 67, 20);
		contentPane.add(txtISNB);

		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(67, 36, 149, 20);
		contentPane.add(txtTitulo);

		JLabel label_1 = new JLabel("Titulo:");
		label_1.setBounds(31, 39, 46, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Data Lanc.:");
		label_2.setBounds(10, 63, 56, 14);
		contentPane.add(label_2);

		txtDataLanc = new JTextField();
		txtDataLanc.setColumns(10);
		txtDataLanc.setBounds(67, 60, 149, 20);
		contentPane.add(txtDataLanc);

		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(67, 85, 149, 20);
		contentPane.add(txtPreco);

		JLabel label_3 = new JLabel("Pre\u00E7o:");
		label_3.setBounds(31, 88, 46, 14);
		contentPane.add(label_3);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				LivroDAO dao = new LivroDAOImpl(em);

				try {
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date data = sdf.parse(txtDataLanc.getText().trim());
					cal.setTime(data);
					Livro livro = new Livro(Integer.parseInt(txtISNB.getText().trim()), txtTitulo.getText().trim(),
							Float.parseFloat(txtPreco.getText().trim()), cal, null);
					dao.atualizar(livro);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Livro alterado com sucesso!");
					txtISNB.setText("");
					txtDataLanc.setText("");
					txtPreco.setText("");
					txtTitulo.setText("");
				} catch (Exception error) {
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, "Falha ao alterar livro");
				} finally {
					em.close();
				}
			}
		});
		btnSalvar.setBounds(31, 113, 89, 23);
		contentPane.add(btnSalvar);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeEditarLivro.this.setVisible(false);
			}
		});
		btnFechar.setBounds(127, 113, 89, 23);
		contentPane.add(btnFechar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				LivroDAO dao = new LivroDAOImpl(em);
				try {
					int id = Integer.parseInt(txtISNB.getText().trim());
					Livro livro = dao.buscar(id);
					if (livro == null) {
						throw new IdNotFoundException("Registro ISNB não encontrado");
					} else {
						txtISNB.setText(String.valueOf(livro.getIsnb()));
						txtTitulo.setText(livro.getTitulo());
						txtPreco.setText(String.valueOf(livro.getPreco()));
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataLanc = sdf.format(livro.getDataLancamento().getTime());
						txtDataLanc.setText(dataLanc);
					}
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
				} finally {
					em.close();
				}
			}
		});
		btnBuscar.setBounds(144, 10, 72, 23);
		contentPane.add(btnBuscar);
	}

}
