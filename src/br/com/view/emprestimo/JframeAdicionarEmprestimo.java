package br.com.view.emprestimo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.EmprestimoDAO;
import br.com.dao.impl.EmprestimoDAOImpl;
import br.com.entity.Emprestimo;
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

public class JframeAdicionarEmprestimo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDataEmprestimo;
	private JTextField txtDataRetorno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeAdicionarEmprestimo frame = new JframeAdicionarEmprestimo();
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
	public JframeAdicionarEmprestimo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 273, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDataDeEmprestimo = new JLabel("Data de Emprestimo:");
		lblDataDeEmprestimo.setBounds(10, 11, 120, 14);
		contentPane.add(lblDataDeEmprestimo);
		
		JLabel lblDataDeRetorno = new JLabel("Data de Retorno:");
		lblDataDeRetorno.setBounds(20, 36, 99, 14);
		contentPane.add(lblDataDeRetorno);
		
		txtDataEmprestimo = new JTextField();
		txtDataEmprestimo.setBounds(119, 8, 120, 20);
		contentPane.add(txtDataEmprestimo);
		txtDataEmprestimo.setColumns(10);
		
		txtDataRetorno = new JTextField();
		txtDataRetorno.setColumns(10);
		txtDataRetorno.setBounds(119, 36, 120, 20);
		contentPane.add(txtDataRetorno);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				EmprestimoDAO dao = new EmprestimoDAOImpl(em);
				try{
					Calendar calEmprestimo = Calendar.getInstance();
					Calendar calRetorno = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dataEmprestimo = sdf.parse(txtDataEmprestimo.getText().trim());
					Date dataRetorno = sdf.parse(txtDataRetorno.getText().trim());
					calEmprestimo.setTime(dataEmprestimo);
					calRetorno.setTime(dataRetorno);
					if(calEmprestimo.after(calRetorno)){
						throw new Exception("Data de retorno deve ser depois da data de empréstimo.");
					}
					Emprestimo emprestimo = new Emprestimo(0,calEmprestimo, calRetorno);
					dao.inserir(emprestimo);
					dao.salvar();
					txtDataEmprestimo.setText("");
					txtDataRetorno.setText("");
					JOptionPane.showMessageDialog(null, "Emprestimo registrado com sucesso.");
				}catch(Exception error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, "Falha ao registrar empréstimo. " +error.getMessage());
				}finally{
					em.close();
				}
			}
		});
		btnSalvar.setBounds(51, 75, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeAdicionarEmprestimo.this.setVisible(false);
			}
		});
		btnFechar.setBounds(150, 75, 89, 23);
		contentPane.add(btnFechar);
	}

}
