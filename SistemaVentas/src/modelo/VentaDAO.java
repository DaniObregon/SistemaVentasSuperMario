package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps;
	ResultSet rs;
	int respuesta;
	
	public int idVenta() {
		
		int id = 0;
		String sql = "SELECT MAX(id) FROM ventas";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				id = rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		return id;
		
	}

	public int registrarVenta(Venta v) {

		String sql = "INSERT INTO ventas (cliente, vendedor, total, fecha) VALUES (?,?,?,?)";

		try {

			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, v.getCliente());
			ps.setString(2, v.getVendedor());
			ps.setDouble(3, v.getTotal());
			ps.setString(4, v.getFecha());
			ps.execute();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				System.out.println(e2.toString());
			}
		}

		return respuesta;

	}

	public int registrarDetalle(Detalle dV) { // dV -> detalle venta

		String sql = "INSERT INTO detalle (cod_prod, cantidad, precio, id_venta) VALUES (?,?,?,?)";

		try {

			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, dV.getCod_prod());
			ps.setInt(2, dV.getCantidad());
			ps.setDouble(3, dV.getPrecio());
			ps.setInt(4, dV.getId());
			ps.execute();

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				System.out.println(e2.toString());
			}
		}

		return respuesta;

	}
	
	public boolean actualizarStock(int cant, String cod) {
		
		String sql = "UPDATE productos SET stock = ? WHERE codigo = ?";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, cant);
			ps.setString(2, cod);
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return false;
		}
	}
	
	public List listarVentas() {
		
		List<Venta> ListaVenta = new ArrayList();
		
		String sql = "SELECT * FROM ventas";
		
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Venta venta = new Venta();
				
				venta.setId(rs.getInt("id"));
				venta.setCliente(rs.getString("cliente"));
				venta.setVendedor(rs.getString("vendedor"));
				venta.setTotal(rs.getDouble("total"));
				
				ListaVenta.add(venta);
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		return ListaVenta;
		
	}
}







