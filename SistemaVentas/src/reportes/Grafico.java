package reportes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import modelo.Conexion;

public class Grafico {

	
	public static void graficar(String fecha) {
		
		Connection con;
		Conexion cn = new Conexion();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			
			String sql = "SELECT total FROM ventas WHERE fecha = ?";
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, fecha);
			rs = ps.executeQuery();
			
			DefaultPieDataset dataSet = new DefaultPieDataset();
			
			while(rs.next()) {
				
				dataSet.setValue(rs.getString("total"), rs.getDouble("total"));
				
			}
			
			JFreeChart jF = ChartFactory.createPieChart("Reporte diario de venta", dataSet);
			
			ChartFrame cF = new ChartFrame("Total de ventas por día", jF);
			
			cF.setSize(1000, 500);
			cF.setLocationRelativeTo(null);
			cF.setVisible(true);
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
	}
	
}
