package br.com.view.exemplar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.ExemplarDAO;
import br.com.dao.impl.ExemplarDAOImpl;
import br.com.entity.Exemplar;
import br.com.entity.Situacao;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.persistence.EntityManager;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class JframeAdicionarExemplar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDataAquisicao;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeAdicionarExemplar frame = new JframeAdicionarExemplar();
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
	public JframeAdicionarExemplar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 257, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDataDeAquisio = new JLabel("Data de Aquisi\u00E7\u00E3o:");
		lblDataDeAquisio.setBounds(10, 11, 104, 14);
		contentPane.add(lblDataDeAquisio);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setBounds(10, 36, 46, 14);
		contentPane.add(lblSituao);
		
		txtDataAquisicao = new JTextField();
		txtDataAquisicao.setBounds(108, 8, 98, 20);
		contentPane.add(txtDataAquisicao);
		txtDataAquisicao.setColumns(10);
		
		final JRadioButton rdbtnDisponivel = new JRadioButton("Disponivel");
		rdbtnDisponivel.setSelected(true);
		buttonGroup.add(rdbtnDisponivel);
		rdbtnDisponivel.setBounds(20, 58, 109, 23);
		contentPane.add(rdbtnDisponivel);
		
		final JRadioButton rdbtnEmprestado = new JRadioButton("Emprestado");
		buttonGroup.add(rdbtnEmprestado);
		rdbtnEmprestado.setBounds(20, 84, 109, 23);
		contentPane.add(rdbtnEmprestado);
		
		JRadioButton rdbtnManutencao = new JRadioButton("Manutencao");
		buttonGroup.add(rdbtnManutencao);
		rdbtnManutencao.setBounds(20, 110, 109, 23);
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
					Exemplar exemplar = new Exemplar(0,calAquisicao,null);
					if(rdbtnDisponivel.isSelected()){
						exemplar.setSituacao(Situacao.DISPONIVEL);
					}else if(rdbtnEmprestado.isSelected()){
						exemplar.setSituacao(Situacao.EMPRESTADO);
					}else{
						exemplar.setSituacao(Situacao.MANUTENCAO);
					}
					dao.inserir(exemplar);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Exemplar adicionado com sucesso!");
					txtDataAquisicao.setText("");
				}catch(Exception error){
					error.printStackTrace();
					JOptionPane.showMessageDialog(null, "Falha ao adicionar exemplar.");
				}finally{
					em.close();
				}
			}
		});
		btnSalvar.setBounds(40, 140, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeAdicionarExemplar.this.setVisible(false);
			}
		});
		btnFechar.setBounds(142, 140, 89, 23);
		contentPane.add(btnFechar);
	}
}
