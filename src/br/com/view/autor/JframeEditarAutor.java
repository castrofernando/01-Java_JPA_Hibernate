package br.com.view.autor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.AutorDAO;
import br.com.dao.impl.AutorDAOImpl;
import br.com.entity.Autor;
import br.com.entity.Sexo;
import br.com.exceptions.IdNotFoundException;
import br.com.singleton.EntityManagerFactorySingleton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.persistence.EntityManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class JframeEditarAutor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtId;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeEditarAutor frame = new JframeEditarAutor();
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
	public JframeEditarAutor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 281);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(36, 47, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(74, 44, 179, 20);
		contentPane.add(txtNome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(74, 69, 179, 20);
		contentPane.add(txtSobrenome);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(10, 72, 72, 14);
		contentPane.add(lblSobrenome);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(36, 112, 46, 14);
		contentPane.add(lblSexo);
		
		final JRadioButton rdSexoMasc = new JRadioButton("Masculino");
		buttonGroup.add(rdSexoMasc);
		rdSexoMasc.setSelected(true);
		rdSexoMasc.setBounds(74, 108, 109, 23);
		contentPane.add(rdSexoMasc);
		
		final JRadioButton rdSexoFem = new JRadioButton("Feminino");
		buttonGroup.add(rdSexoFem);
		rdSexoFem.setBounds(74, 134, 109, 23);
		contentPane.add(rdSexoFem);
		
		final JFormattedTextField txtDataNasc = new JFormattedTextField();
		txtDataNasc.setBounds(74, 165, 179, 20);
		contentPane.add(txtDataNasc);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				Autor autor = new Autor();
				AutorDAO dao = new AutorDAOImpl(em);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				try{
					autor.setCodigo(Integer.parseInt(txtId.getText().trim()));
					autor.setNome(txtNome.getText().trim());
					autor.setSexo(rdSexoFem.isSelected() ? Sexo.FEMININO : Sexo.MASCULINO);
					autor.setSobrenome(txtSobrenome.getText().trim());
					Date data = sdf.parse(txtDataNasc.getText().trim());
					cal.setTime(data);
					autor.setDataNascimento(cal);
					dao.atualizar(autor);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Autor alterado com sucesso!");
					txtNome.setText("");
					txtSobrenome.setText("");
					txtDataNasc.setText("");
				}catch(Exception error){
					JOptionPane.showMessageDialog(null, "Falha ao alterar autor");
					error.printStackTrace();
				}finally{
					em.close();
				}
			}
		});
		btnSalvar.setBounds(65, 205, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeEditarAutor.this.setVisible(false);
			}
		});
		btnFechar.setBounds(164, 205, 89, 23);
		contentPane.add(btnFechar);
		
		JLabel lblDataNasc = new JLabel("Data Nasc.:");
		lblDataNasc.setBounds(10, 168, 72, 14);
		contentPane.add(lblDataNasc);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(36, 19, 28, 14);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(74, 16, 83, 20);
		contentPane.add(txtId);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				AutorDAO dao = new AutorDAOImpl(em);
				try{
					Autor autor = dao.buscar(Integer.parseInt(txtId.getText().trim()));
					if(autor==null){
						throw new IdNotFoundException("Id não encontrado!");
					}else{
						txtNome.setText(autor.getNome());
						txtSobrenome.setText(autor.getSobrenome());
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						String dataStr = sdf.format(autor.getDataNascimento().getTime());
						txtDataNasc.setText(dataStr);
						if(autor.getSexo()==Sexo.MASCULINO){
							rdSexoMasc.setSelected(true);
						}else{
							rdSexoFem.setSelected(true);
						}
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e.getMessage());
				}finally{
					em.close();
				}
			}
		});
		btnBuscar.setBounds(164, 15, 89, 23);
		contentPane.add(btnBuscar);
	}
}
