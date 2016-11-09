package dao;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DaoFactory {

	private static DaoFactory df;
	private PackageDao pdao = null;
	static Log log = LogFactory.getLog(DaoFactory.class);
	
	public static DaoFactory getInstance() {
		
		if (df == null) {
			df = new DaoFactory();
		}
		return df;
	}
	
	public PackageDao getPackageDao() {
		
		if(pdao == null) {
			Properties properties = new Properties();
			try {
				properties.load(this.getClass().getResourceAsStream("/shipping.properties"));
				String className = properties.getProperty("dao.PackageDaoName");
				pdao = (PackageDao)Class.forName(className).newInstance();
				log.info("Using " + className + " to get Package Info...");
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return pdao;
	}
	
	public static void main(String[] args) {

		DaoFactory df = DaoFactory.getInstance();
	}
	
}
