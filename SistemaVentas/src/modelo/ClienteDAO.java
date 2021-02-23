package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ClienteDAO {
	
	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public boolean registrarCliente(Cliente cl) {
		
		String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion, razonSocial) VALUES (?, ?, ?, ?, ?)";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cl.getDni());
			ps.setString(2, cl.getNombre());
			ps.setInt(3, cl.getTelefono());
			ps.setString(4, cl.getDireccion());
			ps.setString(5, cl.getRazonSocial());
			ps.execute();
			
			return true;
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.toString());
			
			return false;
			
		}finally {
			
			try {
				
				con.close();
				
			} catch (SQLException e2) {
				
				System.out.println(e2.toString());
				
			}
		}
		
	}
	
	public List listarCliente() {
		
		List<Cliente> ListaCl = new ArrayList();
		String sql = "SELECT * FROM clientes";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Cliente cl = new Cliente();
				
				cl.setId(rs.getInt("id"));
				cl.setDni(rs.getInt("dni"));
				cl.setNombre(rs.getString("nombre"));
				cl.setTelefono(rs.getInt("telefono"));
				cl.setDireccion(rs.getString("direccion"));
				cl.setRazonSocial(rs.getString("razonSocial"));
				
				ListaCl.add(cl);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		return ListaCl;
		
	}
	
	public boolean eliminarCliente(int id) {
		
		String sql = "DELETE FROM clientes WHERE id = ?";
		
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
	
	public boolean modificarCliente(Cliente cl) {
		
		String sql = "UPDATE clientes SET dni = ?, nombre = ?, telefono = ?, direccion = ?, razonSocial = ? WHERE id = ?";
		
		try {
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, cl.getDni());
			ps.setString(2, cl.getNombre());
			ps.setInt(3, cl.getTelefono());
			ps.setString(4, cl.getDireccion());
			ps.setString(5, cl.getRazonSocial());
			ps.setInt(6, cl.getId());
			ps.execute();
			
			return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();// cerrar conexión
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.out.println(e2.toString());
			}
		}
	}
	
	public Cliente buscarCliente(int dni) {
		
		Cliente cl = new Cliente();
		
		String sql = "SELECT * FROM clientes WHERE dni = ?";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, dni);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cl.setNombre(rs.getString("nombre"));
				cl.setTelefono(rs.getInt("telefono"));
				cl.setDireccion(rs.getString("direccion"));
				cl.setRazonSocial(rs.getString("razonSocial"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		return cl;
		
	}

}









