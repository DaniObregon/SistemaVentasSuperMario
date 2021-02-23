package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
	
	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps;
	ResultSet rs;
	
	public boolean registrarProveedor(Proveedor pr) {
		
		String sql = "INSERT INTO proveedores (dni, nombre, telefono, direccion, razonSocial) VALUES (?, ?, ?, ?, ?) ";
		
		try {
			
			con = cn.getConnection();
			
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, pr.getDni());
			ps.setString(2, pr.getNombre());
			ps.setInt(3, pr.getTelefono());
			ps.setString(4, pr.getDireccion());
			ps.setString(5, pr.getRazonSocial());
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("Error en conexión ProveedorDAO");
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				System.out.println(e2.toString());
				System.out.println("Error al cerrar la conexión ProveedorDAO");
			}
		}
		
	}
	
	public List listarProveedor() {
		
		List<Proveedor> ListaPr = new ArrayList();
		
		String sql = "SELECT * FROM proveedores";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Proveedor pr = new Proveedor();
				pr.setId(rs.getInt("id"));
				pr.setDni(rs.getInt("dni"));
				pr.setNombre(rs.getString("Nombre"));
				pr.setTelefono(rs.getInt("telefono"));
				pr.setDireccion(rs.getString("direccion"));
				pr.setRazonSocial(rs.getString("razonSocial"));
				
				ListaPr.add(pr);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			System.out.println("Error en List<Proveedor>");
		}
		
		return ListaPr;
		
	}
	
	public boolean eliminarProveedor(int id) {
		
		String sql = "DELETE FROM proveedores WHERE id = ?";
		
		try {
			
			con = cn.getConnection();
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
				// TODO: handle exception
			}
		}
		
	}
	
	public boolean modificarProveedor(Proveedor pr) {
		
		String sql = "UPDATE proveedores SET dni = ?, nombre = ?, telefono = ?, direccion = ?, razonSocial = ? WHERE id = ?";
		
		try {
			
			con = cn.getConnection();
			
			ps = con.prepareStatement(sql);
			
			
			ps.setInt(1, pr.getDni());
			ps.setString(2, pr.getNombre());
			ps.setInt(3, pr.getTelefono());
			ps.setString(4, pr.getDireccion());
			ps.setString(5, pr.getRazonSocial());
			ps.setInt(6, pr.getId());
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






