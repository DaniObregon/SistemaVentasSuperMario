package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import modelo.Login01;
import modelo.LoginDAO;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField txtPass;
	
	Login01 lg = new Login01();
	LoginDAO login = new LoginDAO();
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//--------------------------------------------------   METODO MAIN   ------------------------------------------------------------
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					Login frame = new Login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
			}
		});
	}

	//------------------------------------------------------------------------------------------------------------------------------

	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sistema.class.getResource("/img/star.png")));
		setTitle("Log-in plumber!");
		setBounds(100, 100, 524, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//----------------------------------------------------------  JPANEL
		
		JPanel panel_1 = new JPanel();
		panel_1.add(new JLabel(new ImageIcon(Login.class.getResource("/img/nivel-mario.jpg"))));
		panel_1.setBackground(new Color(240, 249, 254));
		panel_1.setBounds(183, 11, 272, 323);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(211, 211, 211, 150));//El cuarto parámetro aplica transparencia
		panel_2.setBounds(10, 282, 121, 30);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(229, 9, 127, 150));//El cuarto parámetro aplica transparencia
		panel_3.setBounds(141, 281, 121, 31);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(144, 193, 249));
		panel.setBounds(-18, 0, 272, 345);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//----------------------------------------------------------  JLABEL
		
		JLabel lblNewLabel_4 = new JLabel("Síguenos en");
		lblNewLabel_4.setBounds(142, 281, 120, 31);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/img/instagram.png")));
		
		JLabel lblNewLabel_2 = new JLabel("Correo electrónico");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Helvetica Neue LT Std", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 108, 143, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/img/padlock04.png")));
		lblNewLabel_1.setBounds(41, -12, 170, 158);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Helvetica Neue LT Std", Font.BOLD, 14));
		lblNewLabel_3.setBounds(10, 164, 143, 14);
		panel_1.add(lblNewLabel_3);
		
		//----------------------------------------------------------  JTEXTFIELD
		
		txtCorreo = new JTextField();
		txtCorreo.setOpaque(false);//Fondo transparente JTextField
		txtCorreo.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));// Configura bordes del JTextField
		txtCorreo.setBounds(10, 133, 225, 20);
		txtCorreo.setFont(new Font("Helvetica Neue LT Std", Font.BOLD, 14));
		panel_1.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setOpaque(false);
		txtPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		txtPass.setBounds(10, 189, 225, 20);
		panel_1.add(txtPass);
		
		//----------------------------------------------------------  KeyListener txtCorreo
		
		txtCorreo.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String textoCorreo = txtCorreo.getText().trim();
					char[] textoPass = txtPass.getPassword();
					if(textoCorreo.equals("") || textoPass == null) {
						JOptionPane.showMessageDialog(null, "Debes ingresar usuario y pass", "ERROR", 2);
					}else {
						validar();
					}
				}
				
			}
		});
		
		//----------------------------------------------------------  KeyListener txtPass
		
		/*txtPass.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String textoCorreo = txtCorreo.getText().trim();
					char[] textoPass = txtPass.getPassword();
					if(textoCorreo.equals("") || textoPass == null) {
						JOptionPane.showMessageDialog(null, "Debes ingresar usuario y pass", "ERROR", 2);
					}else {
						validar();
					}
				}
				
			}
		});*/
		
		txtPass.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String textoCorreo = txtCorreo.getText().trim();
					char[] textoPass = txtPass.getPassword();
					if(textoCorreo.equals("") || textoPass == null) {
						JOptionPane.showMessageDialog(null, "Debes ingresar usuario y pass", "ERROR", 2);
					}else {
						validar();
					}
				}
				
			}
			
		});
		
		//----------------------------------------------------------  JBUTTON
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBackground(new Color(210, 195, 95));
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciar.setForeground(new Color(0, 0, 0));
		btnIniciar.setBounds(70, 225, 115, 30);
		panel_1.add(btnIniciar);
		
		//----------------------------------------------------------  ActionListener btnIniciar
		
		btnIniciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				validar();
				
			}
			
		});

		//----------------------------------------------------------  JLABEL
		
		JLabel lblNewLabel_5 = new JLabel("Síguenos en");
		lblNewLabel_5.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setIcon(new ImageIcon(Login.class.getResource("/img/facebook.png")));
		lblNewLabel_5.setBounds(0, 0, 121, 30);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(Login.class.getResource("/img/level01.png")));
		lblNewLabel_11.setBounds(0, 0, 272, 323);
		panel_1.add(lblNewLabel_11);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Login.class.getResource("/img/Racoon Mario.png")));
		lblNewLabel_6.setBounds(-17, 73, 324, 284);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(Login.class.getResource("/img/mario.png")));
		lblNewLabel_7.setForeground(new Color(240, 249, 254));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_7.setBounds(40, 72, 128, 29);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setIcon(new ImageIcon(Login.class.getResource("/img/tienda.png")));
		lblNewLabel_8.setBounds(40, 11, 128, 29);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setIcon(new ImageIcon(Login.class.getResource("/img/super.png")));
		lblNewLabel_9.setBounds(26, 42, 158, 29);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(255, 0, 253, 345);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(Login.class.getResource("/img/marioPowerUps340x480.jpg")));
		lblNewLabel_10.setBounds(255, 0, 253, 345);
		contentPane.add(lblNewLabel_10);
		
	}
	
	public void validar() {
		
		String correo = txtCorreo.getText();
		String pass = String.valueOf(txtPass.getPassword());
		
		if(!"".equals(correo) || !"".equals(pass)) {
			
			lg = login.log(correo, pass);
			
			if(lg.getCorreo() != null && lg.getPass() != null) {
				
				Sistema sys = new Sistema();
				
				sys.setVisible(true);
				
				dispose();
				
			}else {
				
				JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
				
			}
		}
	}
}
