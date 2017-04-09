package br.com.view;

import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.transaction.Transactional.TxType;

import br.com.dao.AutorDAO;
import br.com.dao.EditoraDAO;
import br.com.dao.EmprestimoDAO;
import br.com.dao.ExemplarDAO;
import br.com.dao.LivroDAO;
import br.com.dao.UsuarioDAO;
import br.com.dao.impl.AutorDAOImpl;
import br.com.dao.impl.EditoraDAOImpl;
import br.com.dao.impl.EmprestimoDAOImpl;
import br.com.dao.impl.ExemplarDAOImpl;
import br.com.dao.impl.LivroDAOImpl;
import br.com.dao.impl.UsuarioDAOImpl;
import br.com.entity.Autor;
import br.com.entity.Editora;
import br.com.entity.Emprestimo;
import br.com.entity.Exemplar;
import br.com.entity.Livro;
import br.com.entity.Usuario;
import br.com.exceptions.IdNotFoundException;
import br.com.singleton.EntityManagerFactorySingleton;
import br.com.view.autor.JframeAdicionarAutor;
import br.com.view.autor.JframeEditarAutor;
import br.com.view.editora.JframeCadastrarEditora;
import br.com.view.editora.JframeEditarEditora;
import br.com.view.emprestimo.JframeAdicionarEmprestimo;
import br.com.view.emprestimo.JframeEditarEmprestimo;
import br.com.view.exemplar.JframeAdicionarExemplar;
import br.com.view.exemplar.JframeEditarExemplar;
import br.com.view.livro.JframeAdicionarLivro;
import br.com.view.livro.JframeEditarLivro;
import br.com.view.usuario.JframeAdicionarUsuario;
import br.com.view.usuario.JframeEditarUsuario;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.ShutdownChannelGroupException;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TesteEx01Main {

	private JFrame frmExercicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteEx01Main window = new TesteEx01Main();
					window.frmExercicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TesteEx01Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExercicio = new JFrame();
		frmExercicio.setTitle("EXERCICIO 02 ");
		frmExercicio.setBounds(100, 100, 646, 291);
		frmExercicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExercicio.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmExercicio.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenu mnEditora = new JMenu("Editora");
		mnNewMenu.add(mnEditora);
		
		JMenuItem mntmAdicionar = new JMenuItem("Adicionar");
		mntmAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeCadastrarEditora frame  = new JframeCadastrarEditora();
				frame.setVisible(true);
			}
		});
		mnEditora.add(mntmAdicionar);
		
		JMenuItem mntmEditar = new JMenuItem("Editar");
		mntmEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JframeEditarEditora frame = new JframeEditarEditora();
				frame.setVisible(true);
			}
		});
		mnEditora.add(mntmEditar);
		
		JMenuItem mntmApagar = new JMenuItem("Apagar");
		mntmApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(JOptionPane.showInputDialog("Entre com o ID que deseja apagar."));
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				EditoraDAO dao = new EditoraDAOImpl(em);
				Editora editora = dao.buscar(id);
				if(editora==null){
					JOptionPane.showMessageDialog(null, "Registro não existe.");
				}else{
					if(JOptionPane.showConfirmDialog(null, "Deseja apagar o registro: " + editora.getNome())==0){
						try {
							dao.remover(id);
							dao.salvar();
							JOptionPane.showMessageDialog(null, "Registro apagado com sucesso.");
						} catch (IdNotFoundException e) {
							JOptionPane.showMessageDialog(null, "Registro não existe.");
							e.printStackTrace();
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
						}finally{
							em.close();
						}
					}
					
				}
				
			}
		});
		mnEditora.add(mntmApagar);
		
		JMenu mnAutor = new JMenu("Autor");
		mnNewMenu.add(mnAutor);
		
		JMenuItem mntmAdicionar_1 = new JMenuItem("Adicionar");
		mntmAdicionar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JframeAdicionarAutor frame = new JframeAdicionarAutor();
				frame.setVisible(true);
			}
		});
		mnAutor.add(mntmAdicionar_1);
		
		JMenuItem mntmEditar_2 = new JMenuItem("Editar");
		mntmEditar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JframeEditarAutor frame = new JframeEditarAutor();
				frame.setVisible(true);
			}
		});
		mnAutor.add(mntmEditar_2);
		
		JMenuItem mntmApagar_2 = new JMenuItem("Apagar");
		mntmApagar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
				AutorDAO dao = new AutorDAOImpl(em);
				
				try{
						int id = Integer.parseInt(JOptionPane.showInputDialog("Entre com o Id que deseja apagar."));
						Autor autor = dao.buscar(id);
						if(autor==null){
							throw new IdNotFoundException("Id não encontrado.");
						}else{
							if((JOptionPane.showConfirmDialog(null, "Deseja apagar o autor: " + autor.getNome() + " ?")==0)){
								dao.remover(id);
								dao.salvar();
								JOptionPane.showMessageDialog(null, "Sucesso ao remover autor");
							}
						}
					}catch(Exception error){
						JOptionPane.showMessageDialog(null,error.getMessage());
					}finally{
						em.close();
					}
				}
		});mnAutor.add(mntmApagar_2);

	JMenu mnLivro = new JMenu("Livro");mnNewMenu.add(mnLivro);

	JMenuItem mntmAdicionar_2 = new JMenuItem("Adicionar");
	mntmAdicionar_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JframeAdicionarLivro frame = new JframeAdicionarLivro();
			frame.setVisible(true);
		}
	});mnLivro.add(mntmAdicionar_2);

	JMenuItem mntmEditar_1 = new JMenuItem("Editar");
	mntmEditar_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JframeEditarLivro frame = new JframeEditarLivro();
			frame.setVisible(true);
		}
	});mnLivro.add(mntmEditar_1);

	JMenuItem mntmApagar_1 = new JMenuItem("Apagar");
	mntmApagar_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
			int isnb = Integer.parseInt(JOptionPane.showInputDialog("Entre com o ISNB que deseja remover:"));
			LivroDAO dao = new LivroDAOImpl(em);
			try{
				Livro livro = dao.buscar(isnb);
				if(livro==null){
					throw new IdNotFoundException("Livro não encontrado.");
				}else{
					if(JOptionPane.showConfirmDialog(null, "Deseja remover o livro " + livro.getTitulo() + "?")==0){
						dao.remover(isnb);
						dao.salvar();
						JOptionPane.showMessageDialog(null, "Livro removido com sucesso!");
						
					}
				}
			}catch (Exception error){
				JOptionPane.showMessageDialog(null, error.getMessage());
			}finally{
				em.close();
			}
		}
	});mnLivro.add(mntmApagar_1);

JMenu mnUsuario = new JMenu("Usu\u00E1rio");
mnNewMenu.add(mnUsuario);
JMenuItem mntmAdicionar_3 = new JMenuItem("Adicionar");
mntmAdicionar_3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		JframeAdicionarUsuario frame = new JframeAdicionarUsuario();
		frame.setVisible(true);
	}
});
mnUsuario.add(mntmAdicionar_3);
JMenuItem mntmEditar_3 = new JMenuItem("Editar");
mntmEditar_3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		JframeEditarUsuario frame = new JframeEditarUsuario();
		frame.setVisible(true);
	}
});
mnUsuario.add(mntmEditar_3);
JMenuItem mntmApagar_3 = new JMenuItem("Apagar");
mntmApagar_3.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		int id = Integer.parseInt(JOptionPane.showInputDialog("Entre com o ID que deseja remover:"));
		UsuarioDAO dao = new UsuarioDAOImpl(em);
		try{
			Usuario usuario = dao.buscar(id);
			if(usuario==null){
				throw new IdNotFoundException("Usuário não encontrado.");
			}else{
				if(JOptionPane.showConfirmDialog(null, "Deseja remover o usuário " + usuario.getNome() + "?")==0){
					dao.remover(id);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "usuario removido com sucesso!");
					
				}
			}
		}catch (Exception error){
			JOptionPane.showMessageDialog(null, error.getMessage());
		}finally{
			em.close();
		}
	}
});
mnUsuario.add(mntmApagar_3);
JMenu mnEmprestimo = new JMenu("Emprestimo");
mnNewMenu.add(mnEmprestimo);
JMenuItem mntmAdicionar_4 = new JMenuItem("Adicionar");
mntmAdicionar_4.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		JframeAdicionarEmprestimo frame = new JframeAdicionarEmprestimo();
		frame.setVisible(true);
	}
});
mnEmprestimo.add(mntmAdicionar_4);
JMenuItem mntmEditar_4 = new JMenuItem("Editar");
mntmEditar_4.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		JframeEditarEmprestimo frame = new JframeEditarEmprestimo();
		frame.setVisible(true);
	}
});
mnEmprestimo.add(mntmEditar_4);
JMenuItem mntmApagar_4 = new JMenuItem("Apagar");
mntmApagar_4.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		int id = Integer.parseInt(JOptionPane.showInputDialog("Entre com o ID que deseja remover:"));
		EmprestimoDAO dao = new EmprestimoDAOImpl(em);
		try{
			Emprestimo emprestimo = dao.buscar(id);
			if(emprestimo==null){
				throw new IdNotFoundException("Emprestimo não encontrado.");
			}else{
				if(JOptionPane.showConfirmDialog(null, "Deseja remover o Emprestimo ?")==0){
					dao.remover(id);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "emprestimo removido com sucesso!");
					
				}
			}
		}catch (Exception error){
			JOptionPane.showMessageDialog(null, error.getMessage());
		}finally{
			em.close();
		}
	}
});
mnEmprestimo.add(mntmApagar_4);
JMenu mnExemplar = new JMenu("Exemplar");
mnNewMenu.add(mnExemplar);
JMenuItem mntmAdicionar_5 = new JMenuItem("Adicionar");
mntmAdicionar_5.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		JframeAdicionarExemplar frame = new JframeAdicionarExemplar();
		frame.setVisible(true);
	}
});
mnExemplar.add(mntmAdicionar_5);
JMenuItem mntmEditar_5 = new JMenuItem("Editar");
mntmEditar_5.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		JframeEditarExemplar frame = new JframeEditarExemplar();
		frame.setVisible(true);
	}
});
mnExemplar.add(mntmEditar_5);
JMenuItem mntmApagar_5 = new JMenuItem("Apagar");
mntmApagar_5.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		int id = Integer.parseInt(JOptionPane.showInputDialog("Entre com o ID que deseja remover:"));
		ExemplarDAO dao = new ExemplarDAOImpl(em);
		try{
			Exemplar exemplar = dao.buscar(id);
			if(exemplar==null){
				throw new IdNotFoundException("Exemplar não encontrado.");
			}else{
				if(JOptionPane.showConfirmDialog(null, "Deseja remover o Exemplar ?")==0){
					dao.remover(id);
					dao.salvar();
					JOptionPane.showMessageDialog(null, "Exemplar removido com sucesso!");
					
				}
			}
		}catch (Exception error){
			JOptionPane.showMessageDialog(null, error.getMessage());
		}finally{
			em.close();
		}
	}
});
mnExemplar.add(mntmApagar_5);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
