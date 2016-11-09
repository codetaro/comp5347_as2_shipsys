package controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.DaoFactory;
import dao.PackageDao;

import model.Package;

@Controller
@RequestMapping("/packages")
public class PackageController {

	private final PackageDao pdao = DaoFactory.getInstance().getPackageDao();
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllPackages(Model model) {
		
		ArrayList<Package> pacs = pdao.getAllPackages();
		model.addAttribute("pacs", pacs);
		return "packagesList";
	}
	
	@RequestMapping("/show")
	public String show() {
		
		return "packageQueryAjax";
	}
	
	@RequestMapping("/query/{tracking_num}")
	public String query(@PathVariable int tracking_num, Model model) {

		Package pac = pdao.getPackageByTrackingNum(tracking_num);

		if (pac == null) {
			model.addAttribute("error", 
					"Cannot retrieve a package with tracking number: " + tracking_num);
			return "packageQuerySub";
		} else {
			model.addAttribute("pac", pac);
			return "packageQuerySub";
		}
	}

	@RequestMapping("/update/{package_id}")
	public String updateStatus(@PathVariable int package_id, Model model) {

		Package pac = pdao.getPackageById(package_id);
		pac.setStatus("delivered");
		pdao.updatePackage(pac);
		
		ArrayList<Package> pacs = pdao.getAllPackages();
		model.addAttribute("pacs", pacs);
		
		return "packagesList";
	}
	
}
