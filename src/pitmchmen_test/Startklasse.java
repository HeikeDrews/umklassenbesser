package pitmchmen_test;


import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

public class Startklasse {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement stmt = null;
		
		Company company = new Company();

//		company.setName("Abalon GmbH");
//		company.setDescription("Ballonbedarf");
		
		try{
	
			con = DBConnection.connection();
						
//			CompanyMapper.companyMapper().insert(company);

//			company.setName("Abalon GmbH & Co. KG");
//			CompanyMapper.companyMapper().update(company);
//			
			
//			System.out.println(CompanyMapper.companyMapper().findAll().toString());

			
//			Company a = CompanyMapper.companyMapper().findById(13);
//			System.out.println("Selected Company: "  + a);
			
			Company a = CompanyMapper.companyMapper().findById(22);
			CompanyMapper.companyMapper().delete(a);
			
			
//			stmt = con.createStatement();
//			stmt.executeUpdate("INSERT INTO pitchmen_itprojekt_schema.company(id, name, description)" 
//					+ "VALUES (4, 'Drews GmbH', 'Sportboutique')");
//		
			
		}
		finally{


			if(stmt != null)stmt.close();			
			if(con !=null)con.close();
		}	
	}
}

