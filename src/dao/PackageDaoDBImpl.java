package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.Package;

public class PackageDaoDBImpl implements PackageDao {

	private DataSource ds;
	
	private String getPackageById = "SELECT * FROM packages WHERE package_id=?";
	private String getPackageByTrackingNum = "SELECT * FROM packages WHERE tracking_num=?";
	private String getAllPackages = "SELECT * FROM packages";
	private String deletePackageById = "DELETE FROM packages WHERE package_id=?";
	private String insertPackage = "INSERT INTO packages(warehouse_add,delivery_add,items,tracking_num) VALUES (?,?,?,?)";
	private String updatePackageById =
			"UPDATE packages SET warehouse_add=?,delivery_add=?,items=?,status=?,tracking_num=? WHERE package_id=?";
	
	Log log = LogFactory.getLog(PackageDaoDBImpl.class);
	
	public PackageDaoDBImpl() throws Exception {
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/shipping");
		} catch(NamingException e) {
			throw new Exception("cannot find shipping.packages");
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public ArrayList<Package> getAllPackages() {
		
		ArrayList<Package> packages = new ArrayList<Package>();
		
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getAllPackages);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				packages.add(new Package(rs.getInt("package_id"),
						rs.getString("warehouse_add"),
						rs.getString("delivery_add"),
						rs.getString("items"),
						rs.getString("status"),
						rs.getInt("tracking_num")));
			}
			rs.close();
			ps.close();
			conn.close();
			return packages;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("cannot get packages");
			return null;
		}
	}

	@Override
	public void addPackage(Package pac) {
		
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertPackage);
			ps.setString(1, pac.getWarehouse_add());
			ps.setString(2, pac.getDelivery_add());
			ps.setString(3, pac.getItems());
			ps.setInt(4, pac.getTracking_num());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			log.error("cannot insert package@" + pac.getPackage_id());
		}
	}

	@Override
	public Package getPackageById(int packageId) {
		
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getPackageById);
			ps.setInt(1, packageId);
			ResultSet rs = ps.executeQuery();
			Package pac = null;
			if(rs.next()) {
				pac = new Package(rs.getInt("package_id"),
						rs.getString("warehouse_add"),
						rs.getString("delivery_add"),
						rs.getString("items"),
						rs.getString("status"),
						rs.getInt("tracking_num"));
			}
			rs.close();
			ps.close();
			conn.close();
			return pac;
		} catch(SQLException e) {
			e.printStackTrace();
			log.error("cannot get package@" + packageId);
			return null;
		}
	}

	@Override
	public Package getPackageByTrackingNum(int tracking_num) {

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getPackageByTrackingNum);
			ps.setInt(1, tracking_num);
			ResultSet rs = ps.executeQuery();
			Package pac = null;
			if(rs.next()) {
				pac = new Package(rs.getInt("package_id"),
						rs.getString("warehouse_add"),
						rs.getString("delivery_add"),
						rs.getString("items"),
						rs.getString("status"),
						rs.getInt("tracking_num"));
			}
			rs.close();
			ps.close();
			conn.close();
			return pac;
		} catch(SQLException e) {
			e.printStackTrace();
			log.error("cannot get package#" + tracking_num);
			return null;
		}
	}

	
	@Override
	public void updatePackage(Package pac) {

		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(updatePackageById);
			ps.setString(1, pac.getWarehouse_add());
			ps.setString(2, pac.getDelivery_add());
			ps.setString(3, pac.getItems());
			ps.setString(4, pac.getStatus());
			ps.setInt(5, pac.getTracking_num());
			ps.setInt(6, pac.getPackage_id());
			
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			log.error("cannot update package@" + pac.getPackage_id());
		}
	}

	@Override
	public void deletePackage(Package pac) {
		
		try {
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(deletePackageById);
			ps.setInt(1, pac.getPackage_id());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			log.error("cannot delete package@" + pac.getPackage_id());
		}
	}

}
