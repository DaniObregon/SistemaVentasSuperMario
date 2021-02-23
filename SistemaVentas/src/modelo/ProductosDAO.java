package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class ProductosDAO {
	
	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps;
	ResultSet rs;
	
	public boolean registrarProductos(Productos prod) {
		
		String sql = "INSERT INTO productos (codigo, nombre, proveedor, stock, precio) VALUES (?,?,?,?,?)";
		
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, prod.getCodigo());
			ps.setString(2, prod.getNombre());
			ps.setString(3, prod.getProveedor());
			ps.setInt(4, prod.getStock());
			ps.setDouble(5, prod.getPrecio());
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("Error conexi�n registrarProductos()");
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				System.out.println(e2.toString());
				System.out.println("Error al cerrar la conexi�n ProductosDAO");
			}
		}
		
	}
	
	public void consultarProveedor(JComboBox proveedor) {
		
		String sql = "SELECT nombre FROM proveedores";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				proveedor.addItem(rs.getString("nombre"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("Falla base de datos comboBox");
		}
		
	}
	
	public List listarProductos() {
		
		List<Productos> ListaProd = new ArrayList();
		
		String sql = "SELECT * FROM productos";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Productos pro = new Productos();
				
				pro.setId(rs.getInt("id"));
				pro.setCodigo(rs.getString("codigo"));
				pro.setNombre(rs.getString("nombre"));
				pro.setProveedor(rs.getString("proveedor"));
				pro.setStock(rs.getInt("stock"));
				pro.setPrecio(rs.getInt("precio"));
				
				ListaProd.add(pro);
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		return ListaProd;
		
	}
	
	public boolean eliminarProducto(int id) {
		
		String sql = "DELETE FROM productos WHERE id = ?";
		
		try {
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.out.println(e2.toString());
			}
		}
		
	}
	
	public boolean modificarProducto(Productos prod) {
		
		String sql = "UPDATE productos SET codigo = ?, nombre = ?, proveedor = ?, stock = ?, precio = ? WHERE id = ?";
		
		try {
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1, prod.getCodigo());
			ps.setString(2, prod.getNombre());
			ps.setString(3, prod.getProveedor());
			ps.setInt(4, prod.getStock());
			ps.setDouble(5, prod.getPrecio());
			ps.setInt(6, prod.getId());
			ps.execute();
			System.out.println("It works...");
			return true;
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				System.out.println(e2.toString());
			}
		}
		
	}
	
	public Productos buscarProductos(String cod) {
		
		Productos producto = new Productos();
		
		String sql = "SELECT * FROM productos WHERE codigo = ?";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, cod);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				producto.setNombre(rs.getString("nombre"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setStock(rs.getInt("stock"));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		return producto;
		
	}
	
	public Config buscarDatosEmpresa() {
		
		Config conf = new Config();
		
		String sql = "SELECT * FROM config";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				conf.setId(rs.getInt("id"));
				conf.setDni(rs.getInt("dni"));
				conf.setNombre(rs.getString("nombre"));
				conf.setTelefono(rs.getInt("telefono"));
				conf.setDireccion(rs.getString("direccion"));
				conf.setRazonSocial(rs.getString("razonSocial"));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		return conf;
		
	}
	
	public boolean modificarDatosEmpresa(Config conf) {
		
		String sql = "UPDATE config SET dni = ?, nombre = ?, telefono = ?, direccion = ?, razonSocial = ? WHERE id = ?";
		
		try {
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, conf.getDni());
			ps.setString(2, conf.getNombre());
			ps.setInt(3, conf.getTelefono());
			ps.setString(4, conf.getDireccion());
			ps.setString(5, conf.getRazonSocial());
			ps.setInt(5, conf.getId());
			ps.execute();
			System.out.println("It works...");
			return true;
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				System.out.println(e2.toString());
			}
		}
		
	}

}





