package vista;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PRAcroForm;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.Cliente;
import modelo.ClienteDAO;
import modelo.Config;
import modelo.Detalle;
import modelo.Eventos;
import modelo.Productos;
import modelo.ProductosDAO;
import modelo.Proveedor;
import modelo.ProveedorDAO;
import modelo.Venta;
import modelo.VentaDAO;
import reportes.Excel;
import reportes.Grafico;

import com.toedter.calendar.JDateChooser;

public class Sistema extends JFrame {

	// ------------------------------------------- CAMPOS/INSTANCIAS--------------------------------------------------------------------
	
	Cliente cl = new Cliente();
	ClienteDAO client = new ClienteDAO();

	Proveedor pr = new Proveedor();
	ProveedorDAO prDao = new ProveedorDAO();

	Productos prod = new Productos();
	ProductosDAO prodDao = new ProductosDAO();
	
	Venta v = new Venta();
	VentaDAO vDAO = new VentaDAO();
	
	Detalle dV = new Detalle();
	
	Config conf = new Config();
	
	Eventos event = new Eventos();
	
	Date fechaVenta = new Date();
	String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);

	private JPanel contentPane;
	
	private JTextField txtCodigoNuevaVenta;
	private JTextField txtDescripcionNuevaVenta;
	private JTextField txtCantidadNuevaVenta;
	private JTextField txtPrecioNuevaVenta;
	private JTextField txtStockDisponibleNuevaVenta;
	private JTextField txtIdCliente;
	private JTextField txtDniCliente;
	private JTextField txtNombreCliente;
	private JTextField txtTelefonoCliente;
	private JTextField txtDireccionCliente;
	private JTextField txtRazonCliente;
	private JTextField txtDniProveedor;
	private JTextField txtNombreProveedor;
	private JTextField txtTelefonoProveedor;
	private JTextField txtDireccionProveedor;
	private JTextField txtRazonProveedor;
	private JTextField txtCodigoProducto;
	private JTextField txtDescripcionProducto;
	private JTextField txtStockProducto;
	private JTextField txtPrecioProducto;
	private JTextField txtDniDatosEmpresa;
	private JTextField txtNombreDatosEmpresa;
	private JTextField txtTelefonoDatosEmpresa;
	private JTextField txtDireccionDatosEmpresa;
	private JTextField txtRazonDatosEmpresa;
	private JTextField txtTelefonoClienteNuevaVenta;
	private JTextField txtDireccionClienteNuevaVenta;
	private JTextField txtRazonClienteNuevaVenta;
	private JTextField txtIdProveedor;
	private JTextField txtIdProductos;
	private JTextField txtIdVenta;
	private JTextField txtIdNuevaVenta;
	private JTextField txtNombreClienteNuevaVenta;
	private JTextField txtDniClienteNuevaVenta;
	private JTextField txtIdDatosEmpresa;
	private JTextField txtVendedorNuevaVenta;
	
	private JLabel lblTotalNuevaVenta;
	
	private JLabel lblIdDatosEmpresa;

	JTabbedPane tabbedPane01;

	JComboBox comboBoxProveedor;

	private JTable tablaNuevaVenta;
	private JTable tablaClientes;
	private JTable tablaProveedores;
	private JTable tablaProductos;
	private JTable tablaVentas;
	
	DefaultTableModel modeloNuevaVenta;
	DefaultTableModel modeloClientes;
	DefaultTableModel modeloProveedores;
	DefaultTableModel modeloProductos;
	DefaultTableModel modeloVentas;
	
	private String[] nomColTabNuevaVenta;
	private Object [][] datosFilasTabNuevaVenta; 

	private String[] nomColTabClientes;
	private Object[][] datosFilasTabClientes;

	private String[] nomColTabProveedores;
	private Object[][] datosFilasTabProveedores;

	private String[] nomColTabProductos;
	private Object[][] datosFilasTabProductos;
	
	private String[] nomColTabVentas;
	private Object[][] datosFilasTabVentas;
	
	int item;
	double totalAPagar; // = 0.00

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//--------------------------------------------------- METODO MAIN -------------------------------------------------------------//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistema frame = new Sistema();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ------------------------------------------------------------------------------------------------------------------------------
	
	
	// -------------------------------------------- Constructor Sistema (JFrame) ----------------------------------------------------

	public Sistema() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sistema.class.getResource("/img/star.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 544);
		setTitle("TIENDA SUPER MARIO!");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		

		// -----------------------------------------------------------------------------------------------------------------------

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 249, 254));
		panel.setBounds(0, 0, 171, 505);
		contentPane.add(panel);
		panel.setLayout(null);

		// -------------------------------------------------------- JBUTTON ----- BOTONES PRINCIPALES

		// -------------------------------------------------------- JBUTTON NUEVA VENTA + ActionListener

		JButton btnNuevaVenta = new JButton("Nueva Venta");
		btnNuevaVenta.setIcon(new ImageIcon(Sistema.class.getResource("/img/coin03.png")));
		btnNuevaVenta.setBounds(10, 140, 151, 44);
		panel.add(btnNuevaVenta);
		
		btnNuevaVenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabbedPane01.setSelectedIndex(0);
				limpiarVenta();
				limpiarDatosClienteNuevaVenta();
				cleanTableNuevaVenta();
			}
		});

		// -------------------------------------------------------- JBUTTON Clientes + ActionListener

		JButton btnClientes = new JButton("Clientes");
		btnClientes.setIcon(new ImageIcon(Sistema.class.getResource("/img/Clientes.png")));
		btnClientes.setBounds(10, 200, 151, 44);
		panel.add(btnClientes);

		btnClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cleanTableCliente();
				listarCliente();
				tabbedPane01.setSelectedIndex(1);
			}
		});

		// -------------------------------------------------------- JBUTTON PROVEEDORES + ActionListener

		JButton btnProveedores = new JButton("Proveedores");
		btnProveedores.setIcon(new ImageIcon(Sistema.class.getResource("/img/proveedor.png")));
		btnProveedores.setBounds(10, 260, 151, 44);
		panel.add(btnProveedores);

		btnProveedores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cleanTableProveedor();
				listarProveedor();
				tabbedPane01.setSelectedIndex(2);
			}
		});

		// -------------------------------------------------------- JBUTTON PRODUCTOS + ActionListener

		JButton btnProductos = new JButton("Productos");
		btnProductos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cleanTableProductos();
				listarProductos();
				tabbedPane01.setSelectedIndex(3);
			}
		});

		btnProductos.setIcon(new ImageIcon(Sistema.class.getResource("/img/producto.png")));
		btnProductos.setBounds(10, 320, 151, 44);
		panel.add(btnProductos);

		// -------------------------------------------------------- JBUTTON VENTAS + ActionListener

		JButton btnVentas = new JButton("Ventas");
		btnVentas.setIcon(new ImageIcon(Sistema.class.getResource("/img/compras.png")));
		btnVentas.setBounds(10, 380, 151, 44);
		panel.add(btnVentas);
		
		btnVentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabbedPane01.setSelectedIndex(4);
				cleanTableVentas();
				listarVentas();
			}
		});

		// -------------------------------------------------------- JBUTTON CONFIGURACION + ActionListener

		JButton btnConfiguracion = new JButton("Configuración");
		btnConfiguracion.setIcon(new ImageIcon(Sistema.class.getResource("/img/config.png")));
		btnConfiguracion.setBounds(10, 440, 151, 44);
		panel.add(btnConfiguracion);
		
		btnConfiguracion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tabbedPane01.setSelectedIndex(5);
			}
		});

		// ----------------------------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(Sistema.class.getResource("/img/punto-ventas.png")));
		lblNewLabel_8.setBounds(206, 21, 608, 88);
		contentPane.add(lblNewLabel_8);

		// -------------------------------------------------------- JLABEL HONGUITO + STAR

		JLabel lblRedMushroom = new JLabel("");
		lblRedMushroom.setIcon(new ImageIcon(Sistema.class.getResource("/img/Mushroom.png")));
		lblRedMushroom.setBounds(-39, -41, 284, 345);
		panel.add(lblRedMushroom);

		JLabel lblStar = new JLabel("");
		lblStar.setIcon(new ImageIcon(Sistema.class.getResource("/img/Star.png")));
		lblStar.setBounds(-39, 255, 284, 250);
		panel.add(lblStar);

		// -------------------------------------------------------- JTABBEDPANE PRINCIPAL

		tabbedPane01 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane01.setBounds(169, 173, 736, 332);
		contentPane.add(tabbedPane01);

		// ------------------------------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------TAB 01-----------------------------------------------------

		JPanel panel01_NuevaVenta = new JPanel();
		tabbedPane01.addTab("Nueva Venta", null, panel01_NuevaVenta, null);
		panel01_NuevaVenta.setLayout(null);

		// -------------------------------------------------------- JLABEL

		JLabel lblCodigoNuevaVenta = new JLabel("Código");
		lblCodigoNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigoNuevaVenta.setBounds(10, 11, 75, 14);
		panel01_NuevaVenta.add(lblCodigoNuevaVenta);

		JLabel lblDescripcionNuevaVenta = new JLabel("Descripción");
		lblDescripcionNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcionNuevaVenta.setBounds(95, 11, 86, 14);
		panel01_NuevaVenta.add(lblDescripcionNuevaVenta);

		JLabel lblCantidadNuevaVenta = new JLabel("Cantidad");
		lblCantidadNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidadNuevaVenta.setBounds(226, 12, 56, 14);
		panel01_NuevaVenta.add(lblCantidadNuevaVenta);

		JLabel lblPrecioNuevaVenta = new JLabel("Precio");
		lblPrecioNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrecioNuevaVenta.setBounds(292, 12, 46, 14);
		panel01_NuevaVenta.add(lblPrecioNuevaVenta);

		JLabel lblStockNuevaVenta = new JLabel("Stock disponible");
		lblStockNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStockNuevaVenta.setBounds(377, 12, 92, 14);
		panel01_NuevaVenta.add(lblStockNuevaVenta);

		Label lblDniNuevaVenta = new Label("DNI");
		lblDniNuevaVenta.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDniNuevaVenta.setBounds(10, 241, 86, 16);
		panel01_NuevaVenta.add(lblDniNuevaVenta);

		Label lblNombreNuevaVenta = new Label("Nombre");
		lblNombreNuevaVenta.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombreNuevaVenta.setBounds(106, 241, 62, 16);
		panel01_NuevaVenta.add(lblNombreNuevaVenta);

		lblTotalNuevaVenta = new JLabel("****");
		lblTotalNuevaVenta.setHorizontalAlignment(JLabel.CENTER);
		lblTotalNuevaVenta.setBounds(646, 254, 75, 32);
		panel01_NuevaVenta.add(lblTotalNuevaVenta);

		// -------------------------------------------------------- JBUTTON
		
		// -------------------------------------------------------- JBUTTON ELIMINAR NUEVA VENTA + ActionListener

		JButton btnEliminarNuevaVenta = new JButton("");
		btnEliminarNuevaVenta.setIcon(new ImageIcon(Sistema.class.getResource("/img/eliminar.png")));
		btnEliminarNuevaVenta.setBounds(691, 28, 30, 30);
		panel01_NuevaVenta.add(btnEliminarNuevaVenta);
		btnEliminarNuevaVenta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int confirmacionVenta = JOptionPane.showConfirmDialog(null, "Eliminar venta?");
				if(confirmacionVenta == 0) {
					modeloNuevaVenta = (DefaultTableModel) tablaNuevaVenta.getModel();
					modeloNuevaVenta.removeRow(tablaNuevaVenta.getSelectedRow());
					totalPagar();
					txtCodigoNuevaVenta.requestFocus();
				}
			}
		});

		// -------------------------------------------------------- JBUTTON GENERAR/IMPRIMIR NUEVA VENTA + ActionListener
		
		JButton btnImprimirNuevaVenta = new JButton("");
		btnImprimirNuevaVenta.setIcon(new ImageIcon(Sistema.class.getResource("/img/print.png")));
		btnImprimirNuevaVenta.setBounds(433, 247, 66, 47);
		panel01_NuevaVenta.add(btnImprimirNuevaVenta);
		btnImprimirNuevaVenta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(tablaNuevaVenta.getRowCount() > 0) {
					
					if(!"".equals(txtNombreClienteNuevaVenta.getText())) {
						
						int confirmacionVenta = JOptionPane.showConfirmDialog(null, "Generar venta?");
						
						if (confirmacionVenta == 0) {
							registrarVenta();
							registrarDetalle();
							
							actualizarStock();
							pdf();
							limpiarVenta();
							limpiarDatosClienteNuevaVenta();
							cleanTableNuevaVenta();
							lblTotalNuevaVenta.setText("****");
							
							//Al presionar el botón imprimir/generar venta también reinician/borran los campos 
							//con el objeto de generar una venta nueva incluyendo el "total a pagar".
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Ingrese un cliente");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "No has ingresado productos en la venta");
				}
				

			}
		});

		// ---------------------------------------------------- JTEXTFIEL
		
		//---------------------------------------------------------------------------- JTEXTFIELD CODIGO NUEVA VENTA + KeyListener
		
		txtCodigoNuevaVenta = new JTextField();
		txtCodigoNuevaVenta.setBounds(10, 36, 75, 20);
		panel01_NuevaVenta.add(txtCodigoNuevaVenta);
		txtCodigoNuevaVenta.setColumns(10);
		
		txtCodigoNuevaVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					if (!"".equals(txtCodigoNuevaVenta.getText())) {

						String cod = txtCodigoNuevaVenta.getText();

						prod = prodDao.buscarProductos(cod);

						if (prod.getNombre() != null) {

							txtDescripcionNuevaVenta.setText("" + prod.getNombre());
							txtPrecioNuevaVenta.setText("" + prod.getPrecio());
							txtStockDisponibleNuevaVenta.setText("" + prod.getStock());
							txtCantidadNuevaVenta.requestFocus();

						} else {

							limpiarVenta();
							txtCodigoNuevaVenta.requestFocus();

						}

					} else {
						JOptionPane.showMessageDialog(null, "Ingrese el código del producto");
						txtCodigoNuevaVenta.requestFocus();
					}
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e); //----------------------------------------------------------------------SOLO PERMITE NUMEROS
			}
		});

		//-------------------------------------------------------------------------  JTEXTFIELD DESCRIPCION NUEVA VENTA + KEYLISTENER
		
		txtDescripcionNuevaVenta = new JTextField();
		txtDescripcionNuevaVenta.setBounds(95, 36, 121, 20);
		panel01_NuevaVenta.add(txtDescripcionNuevaVenta);
		txtDescripcionNuevaVenta.setColumns(10);
		
		txtDescripcionNuevaVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);//--------------------------------------------------------------------------SOLO PERMITE LETRAS
			}
		});
		
		//----------------------------------------------------------------------------- JTEXTFIELD CANTIDAD NUEVA VENTA + KEYLISTENER

		txtCantidadNuevaVenta = new JTextField();
		txtCantidadNuevaVenta.setBounds(226, 37, 56, 20);
		panel01_NuevaVenta.add(txtCantidadNuevaVenta);
		txtCantidadNuevaVenta.setColumns(10);
		txtCantidadNuevaVenta.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					
					if(!"".equals(txtCantidadNuevaVenta.getText())) { // && (Double.parseDouble(textFieldCantidadNuevaVenta.getText()) % 2) == 0
						
						String cod = txtCodigoNuevaVenta.getText();
						String descripcion = txtDescripcionNuevaVenta.getText();
						int cant = Integer.parseInt(txtCantidadNuevaVenta.getText());
						double precio = Double.parseDouble(txtPrecioNuevaVenta.getText());
						int stock = Integer.parseInt(txtStockDisponibleNuevaVenta.getText());
						double total = cant*precio;
						
						if(stock >= cant) {
							
							item = item+1;
							modeloNuevaVenta = (DefaultTableModel) tablaNuevaVenta.getModel();
							
							for (int i = 0; i < tablaNuevaVenta.getRowCount(); i++) {
								
								if(tablaNuevaVenta.getValueAt(i, 1).equals(txtDescripcionNuevaVenta.getText())) {
									
									JOptionPane.showMessageDialog(null, "El producto ya está registrado en ésta tabla");
									return;
								}
							}
							
							ArrayList lista = new ArrayList();
							lista.add(item);
							lista.add(cod);
							lista.add(descripcion);
							lista.add(cant);
							lista.add(precio);
							//lista.add(stock);
							lista.add(total);
							
							Object[] obj = new Object[5];
							
							obj[0] = lista.get(1);
							obj[1] = lista.get(2);
							obj[2] = lista.get(3);
							obj[3] = lista.get(4);
							obj[4] = lista.get(5);
							
							modeloNuevaVenta.addRow(obj);
							tablaNuevaVenta.setModel(modeloNuevaVenta);
							totalPagar();
							limpiarVenta();
							txtCodigoNuevaVenta.requestFocus();
							
						}else {
							JOptionPane.showMessageDialog(null, "Stock no disponible");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe ingresar una cantidad valida");
					}
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		
		//------------------------------------------------------------------------------------------------------------------------

		txtPrecioNuevaVenta = new JTextField();
		txtPrecioNuevaVenta.setEditable(false);
		txtPrecioNuevaVenta.setBounds(292, 37, 75, 20);
		panel01_NuevaVenta.add(txtPrecioNuevaVenta);
		txtPrecioNuevaVenta.setColumns(10);

		txtStockDisponibleNuevaVenta = new JTextField();
		txtStockDisponibleNuevaVenta.setBounds(377, 37, 92, 20);
		panel01_NuevaVenta.add(txtStockDisponibleNuevaVenta);
		txtStockDisponibleNuevaVenta.setColumns(10);
		
		txtTelefonoClienteNuevaVenta = new JTextField();
		txtTelefonoClienteNuevaVenta.setBounds(250, 261, 12, 22);
		panel01_NuevaVenta.add(txtTelefonoClienteNuevaVenta);
		txtTelefonoClienteNuevaVenta.setColumns(10);
		txtTelefonoClienteNuevaVenta.setVisible(false);

		txtDireccionClienteNuevaVenta = new JTextField();
		txtDireccionClienteNuevaVenta.setBounds(272, 261, 12, 22);
		panel01_NuevaVenta.add(txtDireccionClienteNuevaVenta);
		txtDireccionClienteNuevaVenta.setColumns(10);
		txtDireccionClienteNuevaVenta.setVisible(false);

		txtRazonClienteNuevaVenta = new JTextField();
		txtRazonClienteNuevaVenta.setBounds(295, 261, 12, 22);
		panel01_NuevaVenta.add(txtRazonClienteNuevaVenta);
		txtRazonClienteNuevaVenta.setColumns(10);
		txtRazonClienteNuevaVenta.setVisible(false);
		
		txtNombreClienteNuevaVenta = new JTextField();
		txtNombreClienteNuevaVenta.setEditable(false);
		txtNombreClienteNuevaVenta.setBounds(106, 261, 134, 22);
		panel01_NuevaVenta.add(txtNombreClienteNuevaVenta);

		txtDniClienteNuevaVenta = new JTextField();
		txtDniClienteNuevaVenta.setBounds(10, 261, 86, 22);
		panel01_NuevaVenta.add(txtDniClienteNuevaVenta);
		txtDniClienteNuevaVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					if(!"".equals(txtDniClienteNuevaVenta.getText())) {
						
						int dni = Integer.parseInt(txtDniClienteNuevaVenta.getText());
						cl = client.buscarCliente(dni);
						
						if(cl.getNombre() != null) {
							
							txtNombreClienteNuevaVenta.setText("" + cl.getNombre());
							txtTelefonoClienteNuevaVenta.setText("" + cl.getTelefono());
							txtDireccionClienteNuevaVenta.setText("" + cl.getDireccion());
							txtRazonClienteNuevaVenta.setText("" + cl.getRazonSocial());
							
						}else {
							txtDniClienteNuevaVenta.setText("");
							JOptionPane.showMessageDialog(null, "Cliente no registrado");
						}
					}
				}
			}
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
			
		});

		// -------------------------------------------------------- TABLA NUEVA VENTA ---------------------------------------
		
		nomColTabNuevaVenta = new String[] {"Codigo", "descripción", "Cantidad", "Precio", "Total"};
		datosFilasTabNuevaVenta = new Object[][] { };

		modeloNuevaVenta = new DefaultTableModel(datosFilasTabNuevaVenta, nomColTabNuevaVenta);
		tablaNuevaVenta = new JTable(modeloNuevaVenta);

		tablaNuevaVenta.setBounds(10, 67, 800, 168);
		tablaNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		JScrollPane scrollPaneNuevaVenta = new JScrollPane(tablaNuevaVenta);
		scrollPaneNuevaVenta.setBounds(10, 67, 711, 168);
		panel01_NuevaVenta.add(scrollPaneNuevaVenta);

		//tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(15);// Modifica tamaño de columna específica
		
		//-------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblTotalAPagarNuevaVenta = new JLabel("Total a pagar:");
		lblTotalAPagarNuevaVenta.setIcon(new ImageIcon(Sistema.class.getResource("/img/money03.png")));
		lblTotalAPagarNuevaVenta.setBounds(509, 247, 134, 47);
		panel01_NuevaVenta.add(lblTotalAPagarNuevaVenta);
		
		// -------------------------------------------------------- JTEXTFIELD ID NUEVA VENTA

		txtIdNuevaVenta = new JTextField();
		txtIdNuevaVenta.setBounds(558, 37, 19, 20);
		panel01_NuevaVenta.add(txtIdNuevaVenta);
		txtIdNuevaVenta.setColumns(10);
		
		// -------------------------------------------------------- JLABEL VENDEDOR NUEVA VENTA
		
		txtVendedorNuevaVenta = new JTextField("Daniel");
		txtVendedorNuevaVenta.setBounds(479, 37, 66, 20);
		panel01_NuevaVenta.add(txtVendedorNuevaVenta);
		
		JLabel lblNewLabel_1 = new JLabel("Vendedor");
		lblNewLabel_1.setBounds(479, 12, 66, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel01_NuevaVenta.add(lblNewLabel_1);
		
		
		//-------------------------------------------------------------------------------------JBUTTON GRAFICAR
		
		
		JDateChooser myDate = new JDateChooser();
		myDate.setBounds(255, 262, 112, 20);
		panel01_NuevaVenta.add(myDate);
		
		JButton btnGraficar = new JButton("");
		btnGraficar.setIcon(new ImageIcon(Sistema.class.getResource("/img/report_dash01.png")));
		btnGraficar.setBounds(375, 256, 30, 30);
		panel01_NuevaVenta.add(btnGraficar);
		btnGraficar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(myDate.getDate());
				
				Grafico.graficar(fechaReporte);
				
			}
		});
		
		//-------------------------------------------------------------------------------------------------------------------------
		
		JLabel lblIdNuevaVenta = new JLabel("Id Venta");
		lblIdNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdNuevaVenta.setBounds(558, 11, 49, 14);
		panel01_NuevaVenta.add(lblIdNuevaVenta);
		
		JLabel lblSeleccionarNuevaVenta = new JLabel("Seleccionar");
		lblSeleccionarNuevaVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSeleccionarNuevaVenta.setBounds(255, 241, 112, 14);
		panel01_NuevaVenta.add(lblSeleccionarNuevaVenta);

		// -------------------------------------------------------------TAB 02-----------------------------------------------------
		// ------------------------------------------------------ TABLA CLIENTE ---------------------------------------------------

		JPanel panel_02Clientes = new JPanel();
		tabbedPane01.addTab("Clientes", null, panel_02Clientes, null);
		panel_02Clientes.setLayout(null);

		JPanel panelClientes = new JPanel();
		panelClientes.setLayout(null);
		panelClientes.setBounds(230, 55, 492, 238);//(230, 55, 431, 238);
		panel_02Clientes.add(panelClientes);

		nomColTabClientes = new String[] { "ID", "DNI", "Nombre", "Teléfono", "Dirección", "Razon Social" };
		datosFilasTabClientes = new Object[][] {
			
		};

		modeloClientes = new DefaultTableModel(datosFilasTabClientes, nomColTabClientes);
		tablaClientes = new JTable(modeloClientes);

		tablaClientes.addMouseListener(new MouseAdapter() {// Envía los valores de la fila seleccionada a los JTextField
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = tablaClientes.rowAtPoint(e.getPoint());

				txtIdCliente.setText(tablaClientes.getValueAt(fila, 0).toString());
				txtDniCliente.setText(tablaClientes.getValueAt(fila, 1).toString());
				txtNombreCliente.setText(tablaClientes.getValueAt(fila, 2).toString());
				txtTelefonoCliente.setText(tablaClientes.getValueAt(fila, 3).toString());
				txtDireccionCliente.setText(tablaClientes.getValueAt(fila, 4).toString());
				txtRazonCliente.setText(tablaClientes.getValueAt(fila, 5).toString());

			}
		});

		tablaClientes.setBounds(230, 55, 3, 238);
		tablaClientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		JScrollPane scrollPaneClientes = new JScrollPane(tablaClientes);
		scrollPaneClientes.setBounds(0, 0, 492, 235);
		panelClientes.add(scrollPaneClientes);

		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(15);// Modifica tamaño de columna específica

		// -------------------------------------------------------- JLABEL

		JLabel lblNewLabel_9 = new JLabel("DNI:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_9.setBounds(10, 25, 76, 14);
		panel_02Clientes.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Nombre:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setBounds(10, 55, 76, 14);
		panel_02Clientes.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Teléfono:");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_11.setBounds(10, 85, 76, 14);
		panel_02Clientes.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Dirección:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_12.setBounds(10, 115, 76, 14);
		panel_02Clientes.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Razón social:");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_13.setBounds(10, 145, 76, 14);
		panel_02Clientes.add(lblNewLabel_13);

		// -------------------------------------------------- JTEXTFIEL CLIENTE

		txtDniCliente = new JTextField();
		txtDniCliente.setBounds(96, 22, 113, 20);
		panel_02Clientes.add(txtDniCliente);
		txtDniCliente.setColumns(10);
		txtDniCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		
		txtNombreCliente = new JTextField();
		txtNombreCliente.setBounds(96, 52, 113, 20);
		panel_02Clientes.add(txtNombreCliente);
		txtNombreCliente.setColumns(10);
		txtNombreCliente.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);
			}
			
		});

		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.setBounds(96, 82, 113, 20);
		panel_02Clientes.add(txtTelefonoCliente);
		txtTelefonoCliente.setColumns(10);
		txtTelefonoCliente.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);;
			}
			
		});

		txtDireccionCliente = new JTextField();
		txtDireccionCliente.setBounds(96, 112, 113, 20);
		panel_02Clientes.add(txtDireccionCliente);
		txtDireccionCliente.setColumns(10);

		txtRazonCliente = new JTextField();
		txtRazonCliente.setBounds(96, 142, 113, 20);
		panel_02Clientes.add(txtRazonCliente);
		txtRazonCliente.setColumns(10);
		txtRazonCliente.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);
			}
		});

		// ---------------------------------------------------JBUTTON CLIENTE------------------------------------------------

		// ---------------------------------------------------JBUTTON------GUARDAR CLIENTE-----------------------------------

		JButton btnGuardarCliente = new JButton("");
		btnGuardarCliente.setIcon(new ImageIcon(Sistema.class.getResource("/img/GuardarTodo.png")));
		btnGuardarCliente.setBounds(21, 184, 89, 40);
		panel_02Clientes.add(btnGuardarCliente);

		btnGuardarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText())
						|| !"".equals(txtIdCliente.getText()) || !"".equals(txtDireccionCliente.getText())
						|| !"".equals(txtTelefonoCliente.getText())) {

					cl.setDni(Integer.parseInt(txtDniCliente.getText()));
					cl.setNombre(txtNombreCliente.getText());
					cl.setTelefono(Integer.parseInt(txtTelefonoCliente.getText()));
					cl.setDireccion(txtDireccionCliente.getText());
					cl.setRazonSocial(txtRazonCliente.getText());

					client.registrarCliente(cl);
					cleanTableCliente();
					limpiarCliente();
					listarCliente();

					JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");

				} else {
					JOptionPane.showMessageDialog(null, "Debes completar los campos obligatorios");
				}

			}
		});

		// -------------------------------------------------- JBUTTON ----- ACTUALIZAR CLIENTE

		JButton btnActualizarCliente = new JButton("");
		btnActualizarCliente.setIcon(new ImageIcon(Sistema.class.getResource("/img/Actualizar (2).png")));
		btnActualizarCliente.setBounds(120, 184, 89, 40);
		panel_02Clientes.add(btnActualizarCliente);

		btnActualizarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if ("".equals(txtIdCliente.getText())) {

					JOptionPane.showMessageDialog(null, "Seleccione una fila");

				} else {

					if (!"".equals(txtDniCliente.getText()) && !"".equals(txtNombreCliente.getText())
							&& !"".equals(txtTelefonoCliente.getText()) && !"".equals(txtDireccionCliente.getText())
							&& !"".equals(txtRazonCliente.getText())) {

						cl.setDni(Integer.parseInt(txtDniCliente.getText()));
						cl.setNombre(txtNombreCliente.getText());
						cl.setTelefono(Integer.parseInt(txtTelefonoCliente.getText()));
						cl.setDireccion(txtDireccionCliente.getText());
						cl.setRazonSocial(txtRazonCliente.getText());
						cl.setId(Integer.parseInt(txtIdCliente.getText()));
						JOptionPane.showMessageDialog(null, "Cliente actualizado");
						client.modificarCliente(cl);
						cleanTableCliente();
						limpiarCliente();
						listarCliente();

					} else {
						JOptionPane.showMessageDialog(null, "*Todos los campos son obligatorios*");
					}
				}

			}
		});

		// -------------------------------------------------- JBUTTON ----- ELIMINAR CLIENTE

		JButton btnEliminarCliente = new JButton("");
		btnEliminarCliente.setIcon(new ImageIcon(Sistema.class.getResource("/img/eliminar.png")));
		btnEliminarCliente.setBounds(21, 241, 89, 40);
		panel_02Clientes.add(btnEliminarCliente);

		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!"".equals(txtIdCliente.getText())) {

					int pregunta = JOptionPane.showConfirmDialog(null, "Eliminar cliente?");

					if (pregunta == 0) {

						int id = Integer.parseInt(txtIdCliente.getText());
						client.eliminarCliente(id);
						cleanTableCliente();
						limpiarCliente();
						listarCliente();
						
						JOptionPane.showMessageDialog(null, "Cliente eliminado");

					}
				}
			}
		});

		// -------------------------------------------------- JBUTTON ----- NUEVO CLIENTE

		JButton btnNuevoCliente = new JButton("");
		btnNuevoCliente.setIcon(new ImageIcon(Sistema.class.getResource("/img/nuevo.png")));
		btnNuevoCliente.setBounds(120, 241, 89, 40);
		panel_02Clientes.add(btnNuevoCliente);

		btnNuevoCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiarCliente();
			}
		});

		txtIdCliente = new JTextField();
		txtIdCliente.setBounds(230, 22, 21, 20);
		panel_02Clientes.add(txtIdCliente);
		txtIdCliente.setColumns(10);
		//txtIdCliente.setVisible(false);// ------------------------------------------------OCULTA JTextFiel txtIdCliente
		txtIdCliente.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(233, 55, 428, 238);
		panel_02Clientes.add(scrollPane_1);
		scrollPane_1.setLayout(null);

		// ------------------------------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------TAB 03-----------------------------------------------------
		// ----------------------------------------------------- TABLA PROVEEDOR --------------------------------------------------

		JPanel panel_03Proveedores = new JPanel();
		tabbedPane01.addTab("Proveedores", null, panel_03Proveedores, null);
		panel_03Proveedores.setLayout(null);

		JPanel panelProveedores = new JPanel();
		panelProveedores.setLayout(null);
		panelProveedores.setBounds(230, 55, 492, 238);
		panel_03Proveedores.add(panelProveedores);

		nomColTabProveedores = new String[] { "ID", "DNI", "Nombre", "Teléfono", "Dirección", "Razon Social" };
		datosFilasTabProveedores = new Object[][] {

				};

		modeloProveedores = new DefaultTableModel(datosFilasTabProveedores, nomColTabProveedores);
		tablaProveedores = new JTable(modeloProveedores);

		tablaProveedores.setBounds(230, 55, 3, 238);
		tablaProveedores.setFont(new Font("Tahoma", Font.BOLD, 11));
		JScrollPane scrollPaneProveedores = new JScrollPane(tablaProveedores);
		scrollPaneProveedores.setBounds(0, 0, 492, 235);
		panelProveedores.add(scrollPaneProveedores);

		tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(15);// Modifica tamaño de columna específica

		tablaProveedores.addMouseListener(new MouseAdapter() {// Envía los valores de la fila seleccionada a los
																// JTextField

			public void mouseClicked(MouseEvent e) {

				int fila = tablaProveedores.rowAtPoint(e.getPoint());

				txtIdProveedor.setText(tablaProveedores.getValueAt(fila, 0).toString());
				txtDniProveedor.setText(tablaProveedores.getValueAt(fila, 1).toString());
				txtNombreProveedor.setText(tablaProveedores.getValueAt(fila, 2).toString());
				txtTelefonoProveedor.setText(tablaProveedores.getValueAt(fila, 3).toString());
				txtDireccionProveedor.setText(tablaProveedores.getValueAt(fila, 4).toString());
				txtRazonProveedor.setText(tablaProveedores.getValueAt(fila, 5).toString());
			}
		});

		// -------------------------------------------------------- JLABEL

		JLabel lblNewLabel_14 = new JLabel("DNI:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_14.setBounds(10, 25, 76, 14);
		panel_03Proveedores.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Nombre:");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_15.setBounds(10, 55, 76, 14);
		panel_03Proveedores.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("Teléfono:");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_16.setBounds(10, 85, 76, 14);
		panel_03Proveedores.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("Dirección:");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_17.setBounds(10, 115, 76, 14);
		panel_03Proveedores.add(lblNewLabel_17);

		JLabel lblNewLabel_18 = new JLabel("Razón social:");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_18.setBounds(10, 145, 76, 14);
		panel_03Proveedores.add(lblNewLabel_18);

		// ----------------------------------------------------- JTEXTFIEL
		
		txtIdProveedor = new JTextField();
		txtIdProveedor.setBounds(230, 22, 21, 20);
		panel_03Proveedores.add(txtIdProveedor);
		txtIdProveedor.setColumns(10);
		txtIdProveedor.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		txtDniProveedor = new JTextField();
		txtDniProveedor.setBounds(96, 22, 113, 20);
		panel_03Proveedores.add(txtDniProveedor);
		txtDniProveedor.setColumns(10);
		txtDniProveedor.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		txtNombreProveedor = new JTextField();
		txtNombreProveedor.setBounds(96, 52, 113, 20);
		panel_03Proveedores.add(txtNombreProveedor);
		txtNombreProveedor.setColumns(10);
		txtNombreProveedor.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);
			}
		});

		txtTelefonoProveedor = new JTextField();
		txtTelefonoProveedor.setBounds(96, 82, 113, 20);
		panel_03Proveedores.add(txtTelefonoProveedor);
		txtTelefonoProveedor.setColumns(10);
		txtTelefonoProveedor.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		txtDireccionProveedor = new JTextField();
		txtDireccionProveedor.setBounds(96, 112, 113, 20);
		panel_03Proveedores.add(txtDireccionProveedor);
		txtDireccionProveedor.setColumns(10);

		txtRazonProveedor = new JTextField();
		txtRazonProveedor.setBounds(96, 142, 113, 20);
		panel_03Proveedores.add(txtRazonProveedor);
		txtRazonProveedor.setColumns(10);
		txtRazonProveedor.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);
			}
		});

		// ----------------------------------------------------- JBUTTON ----- GUARDAR PROVEEDOR

		JButton btnGuardarProveedor = new JButton("");
		btnGuardarProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/img/GuardarTodo.png")));
		btnGuardarProveedor.setBounds(21, 184, 89, 40);
		panel_03Proveedores.add(btnGuardarProveedor);

		btnGuardarProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (!"".equals(txtDniProveedor.getText()) || !"".equals(txtNombreProveedor.getText())
						|| !"".equals(txtTelefonoProveedor.getText()) || !"".equals(txtDireccionProveedor.getText())
						|| !"".equals(txtRazonProveedor.getText())) {

					pr.setDni(Integer.parseInt(txtDniProveedor.getText()));
					pr.setNombre(txtNombreProveedor.getText());
					pr.setTelefono(Integer.parseInt(txtTelefonoProveedor.getText()));
					pr.setDireccion(txtDireccionProveedor.getText());
					pr.setRazonSocial(txtRazonProveedor.getText());

					JOptionPane.showMessageDialog(null, "Proveedor registrado exitosamente");

					prDao.registrarProveedor(pr);
					cleanTableProveedor();
					limpiarProveedor();
					listarProveedor();

				} else {
					JOptionPane.showMessageDialog(null, "*Todos los campos son obligatorios(2)*");
				}
			}
		});

		// ----------------------------------------------------- JBUTTON ----- ACTUALIZAR PROVEEDOR

		JButton btnActualizarProveedor = new JButton("");
		btnActualizarProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/img/Actualizar (2).png")));
		btnActualizarProveedor.setBounds(120, 184, 89, 40);
		panel_03Proveedores.add(btnActualizarProveedor);

		btnActualizarProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if ("".equals(txtIdProveedor.getText())) {

					JOptionPane.showMessageDialog(null, "Seleccione una fila");

				} else {

					if (!"".equals(txtDniProveedor.getText()) && !"".equals(txtNombreProveedor.getText())
							&& !"".equals(txtTelefonoProveedor.getText()) && !"".equals(txtDireccionProveedor.getText())
							&& !"".equals(txtRazonProveedor.getText())) {

						pr.setDni(Integer.parseInt(txtDniProveedor.getText()));
						pr.setNombre(txtNombreProveedor.getText());
						pr.setTelefono(Integer.parseInt(txtTelefonoProveedor.getText()));
						pr.setDireccion(txtDireccionProveedor.getText());
						pr.setRazonSocial(txtRazonProveedor.getText());
						pr.setId(Integer.parseInt(txtIdProveedor.getText()));

						prDao.modificarProveedor(pr);

						cleanTableProveedor();
						limpiarProveedor();
						listarProveedor();

						JOptionPane.showMessageDialog(null, "Proveedor actualizado");

					}

				}

			}
		});

		// ----------------------------------------------------- JBUTTON ----- ELIMINAR PROVEEDOR

		JButton btnEliminarProveedor = new JButton("");
		btnEliminarProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/img/eliminar.png")));
		btnEliminarProveedor.setBounds(21, 241, 89, 40);
		panel_03Proveedores.add(btnEliminarProveedor);

		btnEliminarProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (!"".equals(txtIdProveedor.getText())) {

					int pregunta = JOptionPane.showConfirmDialog(null, "Eliminar proveedor?");

					if (pregunta == 0) {

						int id = Integer.parseInt(txtIdProveedor.getText());
						prDao.eliminarProveedor(id);
						cleanTableProveedor();
						limpiarProveedor();
						listarProveedor();

					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una fila");
					}
				}
			}
		});

		// ----------------------------------------------------- JBUTTON ----- NUEVO PROVEEDOR

		JButton btnNuevoProveedor = new JButton("");
		btnNuevoProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/img/nuevo.png")));
		btnNuevoProveedor.setBounds(120, 241, 89, 40);
		panel_03Proveedores.add(btnNuevoProveedor);

		btnNuevoProveedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiarProveedor();
			}
		});

		// -------------------------------------------------------------TAB 04-----------------------------------------------------
		// ------------------------------------------------------- TABLA PRODUCTOS ------------------------------------------------

		JPanel panel_04Productos = new JPanel();
		tabbedPane01.addTab("Productos", null, panel_04Productos, null);
		panel_04Productos.setLayout(null);

		JPanel panelProductos = new JPanel();
		panelProductos.setLayout(null);
		panelProductos.setBounds(230, 55, 492, 238);
		panel_04Productos.add(panelProductos);

		nomColTabProductos = new String[] { "ID", "Código", "Descripción", "Proveedor", "Stock", "Precio" };
		datosFilasTabProductos = new Object[][] {

			 };

		modeloProductos = new DefaultTableModel(datosFilasTabProductos, nomColTabProductos);
		tablaProductos = new JTable(modeloProductos);

		tablaProductos.setBounds(230, 55, 3, 238);
		tablaProductos.setFont(new Font("Tahoma", Font.BOLD, 11));
		JScrollPane scrollPaneProductos = new JScrollPane(tablaProductos);
		scrollPaneProductos.setBounds(0, 0, 492, 235);
		panelProductos.add(scrollPaneProductos);

		tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(15);// Modifica tamaño de columna específica
		tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(25);// Modifica tamaño de columna específica

		tablaProductos.addMouseListener(new MouseAdapter() {// Envía los valores de la fila seleccionada a los JTextField

			public void mouseClicked(MouseEvent e) {

				int fila = tablaProductos.rowAtPoint(e.getPoint());

				txtIdProductos.setText(tablaProductos.getValueAt(fila, 0).toString());
				txtCodigoProducto.setText(tablaProductos.getValueAt(fila, 1).toString());
				txtDescripcionProducto.setText(tablaProductos.getValueAt(fila, 2).toString());
				comboBoxProveedor.setSelectedItem(tablaProductos.getValueAt(fila, 3).toString());
				txtStockProducto.setText(tablaProductos.getValueAt(fila, 4).toString());
				txtPrecioProducto.setText(tablaProductos.getValueAt(fila, 5).toString());
			}
		});

		// ------------------------------------------------------- JLABEL

		JLabel lblNewLabel_19 = new JLabel("Código:");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_19.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_19.setBounds(10, 25, 76, 14);
		panel_04Productos.add(lblNewLabel_19);

		JLabel lblNewLabel_20 = new JLabel("Descripción:");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_20.setBounds(10, 55, 76, 14);
		panel_04Productos.add(lblNewLabel_20);

		JLabel lblNewLabel_21 = new JLabel("Stock:");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_21.setBounds(10, 85, 76, 14);
		panel_04Productos.add(lblNewLabel_21);

		JLabel lblNewLabel_22 = new JLabel("Precio:");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_22.setBounds(10, 115, 76, 14);
		panel_04Productos.add(lblNewLabel_22);

		JLabel lblNewLabel_23 = new JLabel("Proveedor:");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_23.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_23.setBounds(10, 145, 76, 14);
		panel_04Productos.add(lblNewLabel_23);

		// ------------------------------------------------------- JTEXTFIEL

		txtIdProductos = new JTextField();
		txtIdProductos.setBounds(200, 22, 20, 20);
		panel_04Productos.add(txtIdProductos);
		txtIdProductos.setColumns(10);
		txtIdProductos.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		txtCodigoProducto = new JTextField();
		txtCodigoProducto.setBounds(96, 22, 94, 20);
		panel_04Productos.add(txtCodigoProducto);
		txtCodigoProducto.setColumns(10);
		txtCodigoProducto.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		txtDescripcionProducto = new JTextField();
		txtDescripcionProducto.setBounds(96, 52, 124, 20);
		panel_04Productos.add(txtDescripcionProducto);
		txtDescripcionProducto.setColumns(10);
		/*
		txtDescripcionProducto.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);
			}
		});*/

		txtStockProducto = new JTextField();
		txtStockProducto.setBounds(96, 82, 124, 20);
		panel_04Productos.add(txtStockProducto);
		txtStockProducto.setColumns(10);
		txtStockProducto.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		txtPrecioProducto = new JTextField();
		txtPrecioProducto.setBounds(96, 112, 124, 20);
		panel_04Productos.add(txtPrecioProducto);
		txtPrecioProducto.setColumns(10);
		txtPrecioProducto.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberDecimalKeyPress(e, txtPrecioProducto);
			}
		});

		// ------------------------------------------------------- JCOMBOBOX

		comboBoxProveedor = new JComboBox();
		comboBoxProveedor.setEditable(true);
		comboBoxProveedor.setBounds(96, 141, 124, 22);
		panel_04Productos.add(comboBoxProveedor);

		// ----------------------------------------------------- JCOMBOBOX METODO CONSULTA PROVEEDORES A BASE DE DATOS

		prodDao.consultarProveedor(comboBoxProveedor);

		AutoCompleteDecorator.decorate(comboBoxProveedor);// Método de autocompletado para el JComboBox

		// ------------------------------------------------------------------------------------------------------------

		// ----------------------------------------------------- JBUTTON

		// ----------------------------------------------------- JBUTTON ----- GUARDAR PRODUCTO

		JButton btnGuardarProducto = new JButton("");
		btnGuardarProducto.setIcon(new ImageIcon(Sistema.class.getResource("/img/GuardarTodo.png")));
		btnGuardarProducto.setBounds(20, 174, 89, 35);
		panel_04Productos.add(btnGuardarProducto);

		btnGuardarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (!"".equals(txtCodigoProducto.getText()) && !"".equals(txtDescripcionProducto.getText())
						&& !"".equals(comboBoxProveedor.getSelectedItem()) && !"".equals(txtStockProducto.getText())
						&& !"".equals(txtPrecioProducto.getText())) {

					prod.setCodigo(txtCodigoProducto.getText());
					prod.setNombre(txtDescripcionProducto.getText());
					prod.setProveedor(comboBoxProveedor.getSelectedItem().toString());
					prod.setStock(Integer.parseInt(txtStockProducto.getText()));
					prod.setPrecio(Double.parseDouble(txtPrecioProducto.getText()));
					prodDao.registrarProductos(prod);

					JOptionPane.showMessageDialog(null, "Producto registrado exitosamente");

					cleanTableProductos();
					limpiarProductos();
					listarProductos();

				} else {
					JOptionPane.showMessageDialog(null, "*Todos los campos son obligatorios(3)*");
				}
			}
		});

		// ----------------------------------------------------- JBUTTON ----- ACTUALIZAR PRODUCTO

		JButton btnActualizarProducto = new JButton("");
		btnActualizarProducto.setIcon(new ImageIcon(Sistema.class.getResource("/img/Actualizar (2).png")));
		btnActualizarProducto.setBounds(128, 174, 89, 35);
		panel_04Productos.add(btnActualizarProducto);

		btnActualizarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if ("".equals(txtIdProductos.getText())) {

					JOptionPane.showMessageDialog(null, "Seleccione una fila");

				} else {

					if (!"".equals(txtCodigoProducto.getText()) && !"".equals(txtDescripcionProducto.getText())
							&& !"".equals(txtStockProducto.getText()) && !"".equals(txtPrecioProducto.getText())) {

						prod.setCodigo(txtCodigoProducto.getText());
						prod.setNombre(txtDescripcionProducto.getText());
						prod.setProveedor(comboBoxProveedor.getSelectedItem().toString());
						prod.setStock(Integer.parseInt(txtStockProducto.getText()));
						prod.setPrecio(Double.parseDouble(txtPrecioProducto.getText()));
						prod.setId(Integer.parseInt(txtIdProductos.getText()));

						prodDao.modificarProducto(prod);
						cleanTableProductos();
						limpiarProductos();
						listarProductos();

						JOptionPane.showMessageDialog(null, "Producto actualizado exitosamente");

					} else {
						JOptionPane.showMessageDialog(null, "*Todos los campos son obligatorios(3)*");
					}
				}
			}
		});

		// ----------------------------------------------------- JBUTTON ----- ELIMINAR PRODUCTO

		JButton btnEliminarProducto = new JButton("");
		btnEliminarProducto.setIcon(new ImageIcon(Sistema.class.getResource("/img/eliminar.png")));
		btnEliminarProducto.setBounds(20, 222, 89, 35);
		panel_04Productos.add(btnEliminarProducto);

		btnEliminarProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (!"".equals(txtIdProductos.getText())) {

					int pregunta = JOptionPane.showConfirmDialog(null, "Eliminar producto?");

					if (pregunta == 0) {

						int id = Integer.parseInt(txtIdProductos.getText());
						prodDao.eliminarProducto(id);
						cleanTableProductos();
						limpiarProductos();
						listarProductos();

					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una fila");
					}
				}
			}
		});

		// ----------------------------------------------------- JBUTTON ----- NUEVO PRODUCTO

		JButton btnNuevoProducto = new JButton("");
		btnNuevoProducto.setIcon(new ImageIcon(Sistema.class.getResource("/img/nuevo.png")));
		btnNuevoProducto.setBounds(128, 222, 89, 35);
		panel_04Productos.add(btnNuevoProducto);

		btnNuevoProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiarProductos();
			}
		});

		// ----------------------------------------------------- JBUTTON ----- EXCEL PRODUCTO

		JButton btnExcelProducto = new JButton("");
		btnExcelProducto.setIcon(new ImageIcon(Sistema.class.getResource("/img/excel.png")));
		btnExcelProducto.setBounds(73, 262, 89, 35);
		panel_04Productos.add(btnExcelProducto);

		btnExcelProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Excel.reporte();
			}
		});

		// ------------------------------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------TAB 05-----------------------------------------------------
		
		JPanel panel_05Ventas = new JPanel();
		tabbedPane01.addTab("Ventas", null, panel_05Ventas, null);
		panel_05Ventas.setLayout(null);
		
		JPanel panelVentas = new JPanel();
		panelVentas.setLayout(null);
		panelVentas.setBounds(10, 60, 711, 235);
		panel_05Ventas.add(panelVentas);
		
		nomColTabVentas = new String[] {"Id", "Cliente", "Vendedor", "Total"};
		datosFilasTabVentas = new Object[][] { };

		modeloVentas = new DefaultTableModel(datosFilasTabVentas, nomColTabVentas);
		tablaVentas = new JTable(modeloVentas);
		
		tablaVentas.setBounds(10, 80, 651, 235);
		tablaVentas.setFont(new Font("Tahoma", Font.BOLD, 11));
		JScrollPane scrollPaneVenta = new JScrollPane(tablaVentas);
		scrollPaneVenta.setBounds(0, 0, 711, 168);
		panelVentas.add(scrollPaneVenta);
		
		tablaVentas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				int fila = tablaVentas.rowAtPoint(e.getPoint());
				txtIdVenta.setText(tablaVentas.getValueAt(fila, 0).toString());
				
			}
		});

		//tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(15);// Modifica tamaño de columna específica
		

		JButton btnPdfVentas = new JButton("");
		btnPdfVentas.setIcon(new ImageIcon(Sistema.class.getResource("/img/pdf.png")));
		btnPdfVentas.setBounds(10, 13, 63, 36);
		panel_05Ventas.add(btnPdfVentas);
		
		btnPdfVentas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					
					int id = Integer.parseInt(txtIdVenta.getText());
					File file = new File("src/pdf/venta-00" + id + ".pdf");
					Desktop.getDesktop().open(file);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		txtIdVenta = new JTextField();
		txtIdVenta.setBounds(83, 26, 22, 22);
		panel_05Ventas.add(txtIdVenta);
		txtIdVenta.setColumns(10);
		txtIdVenta.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		// ------------------------------------------------------------------------------------------------------------------------
		// -------------------------------------------------------------TAB 06-----------------------------------------------------

		JPanel panel_06Configuracion = new JPanel();
		tabbedPane01.addTab("Configuración", null, panel_06Configuracion, null);
		panel_06Configuracion.setLayout(null);

		// ------------------------------------------------------- JLABEL

		JLabel lblNewLabel_24 = new JLabel("Datos de la empresa");
		lblNewLabel_24.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_24.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_24.setBounds(190, 11, 241, 33);
		panel_06Configuracion.add(lblNewLabel_24);

		JLabel lblDniDatosEmpresa = new JLabel("DNI");
		lblDniDatosEmpresa.setBounds(10, 65, 156, 14);
		panel_06Configuracion.add(lblDniDatosEmpresa);

		JLabel lblNombreDatosEmpresa = new JLabel("Nombre");
		lblNombreDatosEmpresa.setBounds(202, 65, 196, 14);
		panel_06Configuracion.add(lblNombreDatosEmpresa);

		JLabel lblTelefonoDatosEmpresa = new JLabel("Teléfono");
		lblTelefonoDatosEmpresa.setBounds(445, 65, 173, 14);
		panel_06Configuracion.add(lblTelefonoDatosEmpresa);

		JLabel lblDireccionDatosEmpresa = new JLabel("Dirección");
		lblDireccionDatosEmpresa.setBounds(10, 128, 156, 14);
		panel_06Configuracion.add(lblDireccionDatosEmpresa);

		JLabel lblRazonDatosEmpresa = new JLabel("Razón Social");
		lblRazonDatosEmpresa.setBounds(202, 128, 196, 14);
		panel_06Configuracion.add(lblRazonDatosEmpresa);

		// ------------------------------------------------------- JTEXTFIEL

		txtDniDatosEmpresa = new JTextField();
		txtDniDatosEmpresa.setBounds(10, 83, 156, 20);
		panel_06Configuracion.add(txtDniDatosEmpresa);
		txtDniDatosEmpresa.setColumns(10);
		txtDniDatosEmpresa.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		txtNombreDatosEmpresa = new JTextField();
		txtNombreDatosEmpresa.setBounds(202, 83, 196, 20);
		panel_06Configuracion.add(txtNombreDatosEmpresa);
		txtNombreDatosEmpresa.setColumns(10);
		txtNombreDatosEmpresa.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);
			}
		});

		txtTelefonoDatosEmpresa = new JTextField();
		txtTelefonoDatosEmpresa.setBounds(445, 83, 173, 20);
		panel_06Configuracion.add(txtTelefonoDatosEmpresa);
		txtTelefonoDatosEmpresa.setColumns(10);
		txtTelefonoDatosEmpresa.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});

		txtDireccionDatosEmpresa = new JTextField();
		txtDireccionDatosEmpresa.setBounds(10, 146, 156, 20);
		panel_06Configuracion.add(txtDireccionDatosEmpresa);
		txtDireccionDatosEmpresa.setColumns(10);

		txtRazonDatosEmpresa = new JTextField();
		txtRazonDatosEmpresa.setBounds(202, 146, 196, 20);
		panel_06Configuracion.add(txtRazonDatosEmpresa);
		txtRazonDatosEmpresa.setColumns(10);
		txtRazonDatosEmpresa.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.textKeyPress(e);
			}
		});
		
		txtIdDatosEmpresa = new JTextField();
		txtIdDatosEmpresa.setBounds(446, 146, 20, 20);
		panel_06Configuracion.add(txtIdDatosEmpresa);
		txtIdDatosEmpresa.setColumns(10);
		txtIdDatosEmpresa.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				event.numberKeyPress(e);
			}
		});
		
		lblIdDatosEmpresa = new JLabel("Id");
		lblIdDatosEmpresa.setBounds(446, 128, 20, 14);
		panel_06Configuracion.add(lblIdDatosEmpresa);
		
		//-----------------OCULTO JTEXTFIELD ID DATOS EMPRESA Y JLABEL ID DATOS EMPRESA
		//txtIdDatosEmpresa.setVisible(false);
		//lblIdDatosEmpresa.setVisible(false);

		// ------------------------------------------------------- JBUTTON

		JButton btnActualizarDatosEmpresa = new JButton("Actualizar");
		btnActualizarDatosEmpresa.setIcon(new ImageIcon(Sistema.class.getResource("/img/Actualizar (2).png")));
		btnActualizarDatosEmpresa.setBounds(244, 211, 127, 33);
		panel_06Configuracion.add(btnActualizarDatosEmpresa);
		
		btnActualizarDatosEmpresa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

					if (!"".equals(txtDniDatosEmpresa.getText()) && !"".equals(txtNombreDatosEmpresa.getText())
							&& !"".equals(txtTelefonoDatosEmpresa.getText()) && !"".equals(txtDireccionDatosEmpresa.getText())
							&& !"".equals(txtRazonDatosEmpresa.getText())) {

						conf.setDni(Integer.parseInt(txtDniDatosEmpresa.getText()));
						conf.setNombre(txtNombreDatosEmpresa.getText());
						conf.setTelefono(Integer.parseInt(txtTelefonoDatosEmpresa.getText()));
						conf.setDireccion(txtDireccionDatosEmpresa.getText());
						conf.setRazonSocial(txtRazonDatosEmpresa.getText());
						conf.setId(Integer.parseInt(txtIdDatosEmpresa.getText()));
						
						JOptionPane.showMessageDialog(null, "Datos de la empresa actualizados");
						
						prodDao.modificarDatosEmpresa(conf);
						
						listarConfig();
						
					} else {
						JOptionPane.showMessageDialog(null, "*Todos los campos son obligatorios*");
					}
				}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(169, -391, 736, 567);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Sistema.class.getResource("/img/nivel-mario.jpg")));
		
		
		//----------------------------------------------------------------Metodo que recopila los datos de la empresa desde la BBDD
		listarConfig();
		

		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//---------------------------------------------------------FIN CONSTRUCTOR SISTEMA---------------------------------------------------------//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// ----------------------------------------------------------- METODOS CLIENTE

	// ----------------------------------------------------------- listarCliente()

	public void listarCliente() {

		List<Cliente> ListarCl = client.listarCliente();

		modeloClientes = (DefaultTableModel) tablaClientes.getModel();

		Object[] obj = new Object[6];

		for (int i = 0; i < ListarCl.size(); i++) {

			obj[0] = ListarCl.get(i).getId();
			obj[1] = ListarCl.get(i).getDni();
			obj[2] = ListarCl.get(i).getNombre();
			obj[3] = ListarCl.get(i).getTelefono();
			obj[4] = ListarCl.get(i).getDireccion();
			obj[5] = ListarCl.get(i).getRazonSocial();

			modeloClientes.addRow(obj);

		}

		tablaClientes.setModel(modeloClientes);

	}

	// ----------------------------------------------------------- cleanTableCliente()

	public void cleanTableCliente() {

		for (int i = 0; i < modeloClientes.getRowCount(); i++) {
			modeloClientes.removeRow(i);
			i = i - 1;
		}
	}

	// ----------------------------------------------------------- limpiarCliente()

	private void limpiarCliente() {

		txtIdCliente.setText("");
		txtDniCliente.setText("");
		txtNombreCliente.setText("");
		txtTelefonoCliente.setText("");
		txtDireccionCliente.setText("");
		txtRazonCliente.setText("");

	}

	// ----------------------------------------------------------- METODOS PROVEEDOR

	// ----------------------------------------------------------- listarProveedor()

	public void listarProveedor() {

		List<Proveedor> ListarPr = prDao.listarProveedor();

		modeloProveedores = (DefaultTableModel) tablaProveedores.getModel();

		Object[] obj = new Object[6];

		for (int i = 0; i < ListarPr.size(); i++) {

			obj[0] = ListarPr.get(i).getId();
			obj[1] = ListarPr.get(i).getDni();
			obj[2] = ListarPr.get(i).getNombre();
			obj[3] = ListarPr.get(i).getTelefono();
			obj[4] = ListarPr.get(i).getDireccion();
			obj[5] = ListarPr.get(i).getRazonSocial();

			modeloProveedores.addRow(obj);

		}

		tablaProveedores.setModel(modeloProveedores);

	}

	// ----------------------------------------------------------- limpiarProveedor()

	private void limpiarProveedor() {

		txtIdProveedor.setText("");
		txtDniProveedor.setText("");
		txtNombreProveedor.setText("");
		txtTelefonoProveedor.setText("");
		txtDireccionProveedor.setText("");
		txtRazonProveedor.setText("");

	}

	// ----------------------------------------------------------- cleanTableProveedor()

	public void cleanTableProveedor() {

		for (int i = 0; i < modeloProveedores.getRowCount(); i++) {
			modeloProveedores.removeRow(i);
			i = i - 1;
		}
	}

	// ----------------------------------------------------------- METODOS PRODUCTOS
	
	// ----------------------------------------------------------- cleanTableProductos()

	public void cleanTableProductos() {

		for (int i = 0; i < modeloProductos.getRowCount(); i++) {
			modeloProductos.removeRow(i);
			i = i - 1;
		}
	}

	// ----------------------------------------------------------- limpiarProductos()

	private void limpiarProductos() {

		txtIdProductos.setText("");
		txtCodigoProducto.setText("");
		comboBoxProveedor.setSelectedItem(null);
		txtDescripcionProducto.setText("");
		txtStockProducto.setText("");
		txtPrecioProducto.setText("");

	}

	// ----------------------------------------------------------- listarProductos()

	public void listarProductos() {

		List<Productos> ListarProd = prodDao.listarProductos();

		modeloProductos = (DefaultTableModel) tablaProductos.getModel();

		Object[] obj = new Object[6];

		for (int i = 0; i < ListarProd.size(); i++) {

			obj[0] = ListarProd.get(i).getId();
			obj[1] = ListarProd.get(i).getCodigo();
			obj[2] = ListarProd.get(i).getNombre();
			obj[3] = ListarProd.get(i).getProveedor();
			obj[4] = ListarProd.get(i).getStock();
			obj[5] = ListarProd.get(i).getPrecio();

			modeloProductos.addRow(obj);
		}
		tablaProductos.setModel(modeloProductos);
	}
	
	// ----------------------------------------------------------- METODOS NUEVA VENTA
	
	private void totalPagar() {
		
		totalAPagar = 0.00;
		int numFila = tablaNuevaVenta.getRowCount();
		String lala = ".-";
		
		for (int i = 0; i < numFila; i++) {
			double calcular = Double.parseDouble(String.valueOf(tablaNuevaVenta.getModel().getValueAt(i, 4)));
			totalAPagar = totalAPagar + calcular;
		}
		
		lblTotalNuevaVenta.setText(String.format("$" + "%.2f.-", totalAPagar));//Prueba modificado  * (Double.parseDouble(txtCantidadNuevaVenta.getText()))
		
		System.out.println("Total a pagar " + totalAPagar); // * (Double.parseDouble(txtCantidadNuevaVenta.getText()))
	}
	
	private void limpiarVenta() {
		
		txtCodigoNuevaVenta.setText("");
		txtDescripcionNuevaVenta.setText("");
		txtCantidadNuevaVenta.setText("");
		txtStockDisponibleNuevaVenta.setText("");
		txtPrecioNuevaVenta.setText("");
		txtIdNuevaVenta.setText("");
	}
	
	private void limpiarDatosClienteNuevaVenta() {
		txtNombreClienteNuevaVenta.setText("");
		txtDniClienteNuevaVenta.setText("");
		txtTelefonoClienteNuevaVenta.setText("");
		txtDireccionClienteNuevaVenta.setText("");
		txtRazonClienteNuevaVenta.setText("");
	}
	
	private void registrarVenta() {
		
		String cliente = txtNombreClienteNuevaVenta.getText(); 
		String vendedor = txtVendedorNuevaVenta.getText();
		double montoTotal = totalAPagar;
		
		v.setCliente(cliente);
		v.setVendedor(vendedor);
		v.setTotal(montoTotal);
		v.setFecha(fechaActual);
		
		vDAO.registrarVenta(v);
	}
	
	private void registrarDetalle() {
		
		int id = vDAO.idVenta();
		
		for (int i = 0; i < tablaNuevaVenta.getRowCount(); i++) {
			String cod = tablaNuevaVenta.getValueAt(i, 0).toString();
			int cant = Integer.parseInt(tablaNuevaVenta.getValueAt(i, 2).toString());
			double precio = Double.parseDouble(tablaNuevaVenta.getValueAt(i, 3).toString());
			
			dV.setCod_prod(cod);
			dV.setCantidad(cant);
			dV.setPrecio(precio);
			dV.setId(id);
			
			vDAO.registrarDetalle(dV);
		}
	}
	
	public void cleanTableNuevaVenta() {
		
		for (int i = 0; i < modeloNuevaVenta.getRowCount(); i++) {
			modeloNuevaVenta.removeRow(i);
			i = i - 1;
		}
	}
	
	private void actualizarStock() {
		for (int i = 0; i < tablaNuevaVenta.getRowCount(); i++) {
			
			String cod = tablaNuevaVenta.getValueAt(i, 0).toString();
			int cant = Integer.parseInt(tablaNuevaVenta.getValueAt(i, 2).toString());
			prod = prodDao.buscarProductos(cod);
			int stockActual = prod.getStock() - (cant);
			vDAO.actualizarStock(stockActual, cod);
			
		}
	}
	
	//--------------------------------------------------------------CONFIG
	
	public void listarConfig() {
		
		conf = prodDao.buscarDatosEmpresa();
		
		txtIdDatosEmpresa.setText("" + conf.getId());
		txtDniDatosEmpresa.setText("" + conf.getDni());
		txtNombreDatosEmpresa.setText("" + conf.getNombre());
		txtTelefonoDatosEmpresa.setText("" + conf.getTelefono());
		txtDireccionDatosEmpresa.setText("" + conf.getDireccion());
		txtRazonDatosEmpresa.setText("" + conf.getRazonSocial());
		
	}
	
	//-------------------------------------------------------------------------cleanTableVentas()

	public void cleanTableVentas() {

		for (int i = 0; i < modeloVentas.getRowCount(); i++) {
			modeloVentas.removeRow(i);
			i = i - 1;
		}
	}
	
	//--------------------------------------------------------------------------listarVentas()
	
	public void listarVentas() {

		List<Venta> ListarVenta = vDAO.listarVentas();

		modeloVentas = (DefaultTableModel) tablaVentas.getModel();

		Object[] obj = new Object[4];

		for (int i = 0; i < ListarVenta.size(); i++) {

			obj[0] = ListarVenta.get(i).getId();
			obj[1] = ListarVenta.get(i).getCliente();
			obj[2] = ListarVenta.get(i).getVendedor();
			obj[3] = ListarVenta.get(i).getTotal();

			modeloVentas.addRow(obj);
		}
		
		tablaVentas.setModel(modeloVentas);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	private void pdf() {
		
		try {
			
			int id = vDAO.idVenta();
			
			FileOutputStream archivo;
			File file = new File("src/pdf/Venta-00" + id +".pdf");
			archivo = new FileOutputStream(file);
			Document doc = new Document();
			PdfWriter.getInstance(doc, archivo);
			doc.open();
			Image img = Image.getInstance("src/img/Racoon Mario.png");
			
			Paragraph fecha = new Paragraph();
			com.itextpdf.text.Font LaPutaFUENTEEEEEEEEE = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
			fecha.add(Chunk.NEWLINE);
			Date date = new Date();
			fecha.add("Factura: " + id +"\n" + "Fecha: " + new SimpleDateFormat("dd.MMMMM.yyyy  hh:mm aaa").format(date) + "\n\n");
			
			PdfPTable encabezado = new PdfPTable(4);
			encabezado.setWidthPercentage(100);
			encabezado.getDefaultCell().setBorder(0);//Al pasar 0 en argumentos quita el borde del encabezado.
			float [] columnaEncabezado = new float[] {20f, 30f, 70f, 40f};
			encabezado.setWidths(columnaEncabezado);
			encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			encabezado.addCell(img);
			
			//Aquí van los datos de la empresa:
			String dni = txtDniDatosEmpresa.getText();
			String nom = txtNombreDatosEmpresa.getText();
			String tel = txtTelefonoDatosEmpresa.getText();
			String dir = txtDireccionDatosEmpresa.getText();
			String raz = txtRazonDatosEmpresa.getText();
			
			encabezado.addCell("");
			encabezado.addCell("DNI: " + dni + "\nNombre: " + nom + "\nTeléfono: " + tel + "\nDirección: " + dir + "\nRazon Social: " + raz);
			encabezado.addCell(fecha);
			doc.add(encabezado);
			
			Paragraph cliente = new Paragraph();
			cliente.add(Chunk.NEWLINE);
			cliente.add("Datos del cliente: " + "\n\n");
			doc.add(cliente);
			
			PdfPTable tablaCliente = new PdfPTable(4);
			tablaCliente.setWidthPercentage(100);
			tablaCliente.getDefaultCell().setBorder(0);//Al pasar 0 en argumentos quita el borde del encabezado.
			float [] columnaCliente= new float[] {20f, 50f, 30f, 40f};
			tablaCliente.setWidths(columnaCliente);
			tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPCell cliente1 = new PdfPCell(new Phrase("DNI", LaPutaFUENTEEEEEEEEE));
			PdfPCell cliente2 = new PdfPCell(new Phrase("Nombre", LaPutaFUENTEEEEEEEEE));
			PdfPCell cliente3 = new PdfPCell(new Phrase("Teléfono", LaPutaFUENTEEEEEEEEE));
			PdfPCell cliente4 = new PdfPCell(new Phrase("Dirección", LaPutaFUENTEEEEEEEEE));
			
			cliente1.setBorder(0);
			cliente2.setBorder(0);
			cliente3.setBorder(0);
			cliente4.setBorder(0);
			
			tablaCliente.addCell(cliente1);
			tablaCliente.addCell(cliente2);
			tablaCliente.addCell(cliente3);
			tablaCliente.addCell(cliente4);
			
			tablaCliente.addCell(txtDniClienteNuevaVenta.getText());
			tablaCliente.addCell(txtNombreClienteNuevaVenta.getText());
			tablaCliente.addCell(txtTelefonoClienteNuevaVenta.getText());
			tablaCliente.addCell(txtDireccionClienteNuevaVenta.getText());
			
			doc.add(tablaCliente);
			
			// PRODUCTOS
			
			PdfPTable tablaProductos = new PdfPTable(4);
			tablaProductos.setWidthPercentage(100);
			tablaProductos.getDefaultCell().setBorder(0);//Al pasar 0 en argumentos quita el borde del encabezado.
			float [] columnaProductos= new float[] {10f, 50f, 15f, 20f};
			tablaProductos.setWidths(columnaProductos);
			tablaProductos.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad", LaPutaFUENTEEEEEEEEE));
			PdfPCell producto2 = new PdfPCell(new Phrase("Descripción", LaPutaFUENTEEEEEEEEE));
			PdfPCell producto3 = new PdfPCell(new Phrase("Precio U.", LaPutaFUENTEEEEEEEEE));
			PdfPCell producto4 = new PdfPCell(new Phrase("Precio T.", LaPutaFUENTEEEEEEEEE));
			
			producto1.setBorder(0);
			producto2.setBorder(0);
			producto3.setBorder(0);
			producto4.setBorder(0);
			
			producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			
			tablaProductos.addCell(producto1);
			tablaProductos.addCell(producto2);
			tablaProductos.addCell(producto3);
			tablaProductos.addCell(producto4);
			
			for (int i = 0; i < tablaNuevaVenta.getRowCount(); i++) {
				
				String producto = tablaNuevaVenta.getValueAt(i, 1).toString();
				String cantidad = tablaNuevaVenta.getValueAt(i, 2).toString();
				String precio = tablaNuevaVenta.getValueAt(i, 3).toString();
				String total = tablaNuevaVenta.getValueAt(i, 4).toString();
				
				tablaProductos.addCell(cantidad);
				tablaProductos.addCell(producto);
				tablaProductos.addCell(precio);
				tablaProductos.addCell(total);
				
			}
			
			doc.add(tablaProductos);
			
			Paragraph info = new Paragraph();
			info.add(Chunk.NEWLINE);
			info.add("Total a pagar: " + totalAPagar);
			info.setAlignment(Element.ALIGN_RIGHT);
			doc.add(info);
			
			Paragraph firma = new Paragraph();
			firma.add(Chunk.NEWLINE);
			firma.add("Cancelación y firma: \n\n");
			firma.add("_________________________");
			firma.setAlignment(Element.ALIGN_CENTER);
			doc.add(firma);
			
			Paragraph mensaje = new Paragraph();
			mensaje.add(Chunk.NEWLINE);
			mensaje.add("Gracias por su compra!");
			mensaje.setAlignment(Element.ALIGN_CENTER);
			doc.add(mensaje);
			
			doc.close();
			archivo.close();
			
			Desktop.getDesktop().open(file);
			
		} catch (DocumentException | IOException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("No funca el pfd, wacho!");
		}
		
	}
	
}

