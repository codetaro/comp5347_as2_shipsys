package dao;

import java.util.ArrayList;

import model.Package;

public interface PackageDao {

	public ArrayList<Package> getAllPackages();
	public void addPackage(Package pac);
	public Package getPackageById(int packageId);
	public Package getPackageByTrackingNum(int tracking_num);
	public void updatePackage(Package pac);
	public void deletePackage(Package pac);
}
