package br.com.view.exemplar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.ExemplarDAO;
import br.com.dao.impl.ExemplarDAOImpl;
import br.com.entity.Exemplar;
import br.com.entity.Situacao;
import br.com.exceptions.IdNotFoundException;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class JframeEditarExemplar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDataAquisicao;
	private JTextField txtId;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeEditarExemplar frame = new JframeEditarExemplar();
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
	public JframeEditarExemplar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 261, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Data de Aquisi\u00E7\u00E3o:");
		label.setBounds(10, 55, 104, 14);
		contentPane.add(label);
		
		txtDataAquisicao = new JTextField();
		txtDataAquisicao.setColumns(10);
		txtDataAquisicao.setBounds(108, 52, 98, 20);
		contentPane.add(txtDataAquisicao);
		
		JLabel label_1 = new JLabel("Situa\u00E7\u00E3o:");
		label_1.setBounds(10, 80, 46, 14);
		contentPane.add(label_1);
		
		final JRadioButton rdbtnDisponivel = new JRadioButton("Disponivel");
		buttonGroup.add(rdbtnDisponivel);
		rdbtnDisponivel.setSelected(true);
		rdbtnDisponivel.setBounds(20, 102, 109, 23);
		contentPane.add(rdbtnDisponivel);
		
		final JRadioButton rdbtnEmprestado = new JRadioButton("Emprestado");
		buttonGroup.add(rdbtnEmprestado);
		rdbtnEmprestado.setBounds(20, 128, 109, 23);
		contentPane.add(rdbtnEmprestado);
		
		final JRadioButton rdbtnManutencao = new JRadioButton("Manutencao");
		buttonGroup.add(rdbtnManutencao);
		rdbtnManutencao.setBounds(20, 154, 109, 23);
		contentPane.add(rdbtnManutencao);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				ExemplarDAO dao = new ExemplarDAOImpl(em);
				try{
					Calendar calAquisicao = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dataAquisicao = sdf.parse(txtDataAquisicao.getText().trim());
					calAquisicao.setTime(dataAquisicao);
					Exemplar exemplar = new Exemplar(Integer.parseInt(txtId.getText().trim()),calAquisicao,null);
					if(rdbtnDisponivel.isSelected()){
						exemplar.setSituacao(Situacao.DISPONIVEL);
					}else if(rdbtnEmprestado.isSelected()){
						exemplar.setSituacao(Situacao.EMPRESTADO);
					}else{
						exemplar.setSituacao(Situacao.MANUTENCAO);
					}
					dao.atualizar(exemplar);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Exemplar alterado com sucesso!");
					txtDataAquisicao.setText("");
				}catch(Exception error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, "Falha ao alterar exemplar.");
				}finally{
					em.close();
				}
			}
		});
		btnSalvar.setBounds(40, 184, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeEditarExemplar.this.setVisible(false);
			}
		});
		btnFechar.setBounds(142, 184, 89, 23);
		contentPane.add(btnFechar);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 30, 46, 14);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(40, 27, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				ExemplarDAO dao = new ExemplarDAOImpl(em);
				try{
					Exemplar exemplar = dao.buscar(Integer.parseInt(txtId.getText().trim()));
					if(exemplar==null){
						throw new IdNotFoundException("Exemplar não encontrado.");
					}else{
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataAquisicao = sdf.format(exemplar.getDataAquisicao().getTime());
						txtDataAquisicao.setText(dataAquisicao);
						if(exemplar.getSituacao()==Situacao.DISPONIVEL){
							rdbtnDisponivel.setSelected(true);
						}else if(exemplar.getSituacao()==Situacao.EMPRESTADO){
							rdbtnEmprestado.setSelected(true);
						}else{
							rdbtnManutencao.setSelected(true);
						}
					}
				}catch(Exception error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, error.getMessage());
				}finally{
					em.close();
				}
			}
		});
		btnBuscar.setBounds(141, 26, 65, 23);
		contentPane.add(btnBuscar);
	}

}
