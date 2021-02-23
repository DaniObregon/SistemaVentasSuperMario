package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
	
	Connection conexion;
	PreparedStatement ps;
	ResultSet rs;
	
	Conexion cn = new Conexion();
	
	public Login01 log(String correo, String pass) {
		
		Login01 log = new Login01();
		
		String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
		
		try {
			
			conexion = cn.getConnection();
			
			ps = conexion.prepareStatement(sql);
			
			ps.setString(1, correo);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				log.setId(rs.getInt("id"));
				log.setNombre(rs.getString("nombre"));
				log.setCorreo(rs.getString("correo"));
				log.setPass(rs.getString("pass"));
			}
			
		} catch (SQLException e) {
			
			System.out.println(e.toString());
			
		}
		
		return log;
		
	}
	
}
