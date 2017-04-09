package br.com.view.emprestimo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.EmprestimoDAO;
import br.com.dao.impl.EmprestimoDAOImpl;
import br.com.entity.Emprestimo;
import br.com.exceptions.IdNotFoundException;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class JframeEditarEmprestimo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDataRetorno;
	private JTextField txtDataEmprestimo;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeEditarEmprestimo frame = new JframeEditarEmprestimo();
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
	public JframeEditarEmprestimo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 266, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
					Emprestimo emprestimo = new Emprestimo(Integer.parseInt(txtId.getText().trim()), calEmprestimo, calRetorno);
					dao.atualizar(emprestimo);
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
		btnSalvar.setBounds(51, 109, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeEditarEmprestimo.this.setVisible(false);
			}
		});
		btnFechar.setBounds(150, 109, 89, 23);
		contentPane.add(btnFechar);
		
		txtDataRetorno = new JTextField();
		txtDataRetorno.setColumns(10);
		txtDataRetorno.setBounds(119, 70, 120, 20);
		contentPane.add(txtDataRetorno);
		
		txtDataEmprestimo = new JTextField();
		txtDataEmprestimo.setColumns(10);
		txtDataEmprestimo.setBounds(119, 42, 120, 20);
		contentPane.add(txtDataEmprestimo);
		
		JLabel label = new JLabel("Data de Emprestimo:");
		label.setBounds(10, 45, 120, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Data de Retorno:");
		label_1.setBounds(20, 70, 99, 14);
		contentPane.add(label_1);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(20, 11, 46, 14);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(51, 8, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				EmprestimoDAO dao = new EmprestimoDAOImpl(em);
				try{
					Emprestimo emprestimo = dao.buscar(Integer.parseInt(txtId.getText().trim()));
					if(emprestimo==null){
						throw new IdNotFoundException("Id não encontrado");
					}else{
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataEmprestimo = sdf.format(emprestimo.getDataEmprestimo().getTime());
						String dataRetorno = sdf.format(emprestimo.getDataRetorno().getTime());
						txtDataEmprestimo.setText(dataEmprestimo);
						txtDataRetorno.setText(dataRetorno);
					}				
				}catch(Exception error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, error.getMessage());
				}finally{
					em.close();
				}
			}
		});
		btnBuscar.setBounds(150, 8, 89, 23);
		contentPane.add(btnBuscar);
	}

}
