package br.com.view.livro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.LivroDAO;
import br.com.dao.impl.LivroDAOImpl;
import br.com.entity.Livro;
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

public class JframeAdicionarLivro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtISNB;
	private JTextField txtTitulo;
	private JTextField txtDataLanc;
	private JTextField txtPreco;
	private JButton btnSalvar;
	private JButton btnFechar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeAdicionarLivro frame = new JframeAdicionarLivro();
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
	public JframeAdicionarLivro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 253, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIsnb = new JLabel("ISNB:");
		lblIsnb.setBounds(31, 11, 46, 14);
		contentPane.add(lblIsnb);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(31, 36, 46, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblDataLanc = new JLabel("Data Lanc.:");
		lblDataLanc.setBounds(10, 60, 56, 14);
		contentPane.add(lblDataLanc);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setBounds(31, 85, 46, 14);
		contentPane.add(lblPreo);
		
		txtISNB = new JTextField();
		txtISNB.setBounds(67, 8, 67, 20);
		contentPane.add(txtISNB);
		txtISNB.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(67, 33, 149, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtDataLanc = new JTextField();
		txtDataLanc.setBounds(67, 57, 149, 20);
		contentPane.add(txtDataLanc);
		txtDataLanc.setColumns(10);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(67, 82, 149, 20);
		contentPane.add(txtPreco);
		txtPreco.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				LivroDAO dao = new LivroDAOImpl(em);
				
				try{
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date data = sdf.parse(txtDataLanc.getText().trim());
					cal.setTime(data);
					Livro livro = new Livro(Integer.parseInt(txtISNB.getText().trim()),
															txtTitulo.getText().trim(),
															Float.parseFloat(txtPreco.getText().trim()),
															cal,
															null);
					dao.inserir(livro);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Livro inserido com sucesso!");
					txtISNB.setText("");
					txtDataLanc.setText("");
					txtPreco.setText("");
					txtTitulo.setText("");
				}catch(Exception error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, "Falha ao inserir livro");
				}finally{
					em.close();
				}
			}
		});
		btnSalvar.setBounds(31, 110, 89, 23);
		contentPane.add(btnSalvar);
		
		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeAdicionarLivro.this.setVisible(false);
			}
		});
		btnFechar.setBounds(127, 110, 89, 23);
		contentPane.add(btnFechar);
	}
}
