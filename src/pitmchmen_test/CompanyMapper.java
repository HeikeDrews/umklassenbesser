package pitmchmen_test;


import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Die Klasse CompanyMapper bildet Company-Objekte auf einer relationale Datenbank ab. 
 * Ebenfalls ist es m�glich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende 
 * Methoden (Speichern, Suchen, L�schen, Bearbeiten).
 * 
 * @author Heike
 *
 */

public class CompanyMapper {

	/**
	 * Die Klasse CompanyMapper wird nur einmal instantiiert (Singelton-Eigenschaft). Die Variable ist
	 * mit static gekennzeichnet, da sie die einzige Instanz dieser Klasse speichert.
	 */

	private static CompanyMapper companyMapper = null;

	/**
	 * Ein gesch�tzter Konstrukter verhindert eine neue Instanz dieser Klasse zu erzeugen.
	 */

	protected CompanyMapper() {
	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Es wird somit sichergestellt, 
	 * dass nur eine einzige Instanz der CompanyMapper existiert.
	 * 
	 * @return companyMapper
	 */

	public static CompanyMapper companyMapper() {
		if (companyMapper == null){
			companyMapper = new CompanyMapper();
		}
		return companyMapper;
	}

	/**
	 * F�gt ein Company-Objekt der Datenbank hinzu. 
	 * Und gibt das korrigierte Customerobjekt zur�ck. 
	 * 
	 * @param company
	 * @return company
	 * @throws ClassNotFoundException 
	 */
	public Company insert(Company company) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/** Abfrage des als letztes hinzugef�gten Prim�rschl�ssels des Datensatzes.
			 * Der aktuelle Prim�rschl�ssel wird um eins erh�ht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM company");
			if (rs.next()) {
				company.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				/**
				 * Ausf�hren der Einf�geoperation
				 */
				stmt.executeUpdate("INSERT INTO company (id, name, description)"
				 + "VALUES ( " + company.getId() + ", '" + company.getName() + "' ,'" + company.getDescription() + "')");
			}
		} 
		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return company;
	}

	/**
	 * Aktuallisiert ein Company-Objekt in der Datenbank.
	 * 
	 * @param company
	 * @throws ClassNotFoundException 
	 * @return company
	 */
	public Company update(Company company) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE company SET Name='" + company.getName() + "', " 
			+ "description='" + company.getDescription() + "' " 
			+ "WHERE id=" + company.getId());
			}

		catch (SQLException e2){
			e2.printStackTrace();
		}	

		return company;
	}

	/**
	 * L�scht ein Company-Objekt aus der Datenbank.
	 * 
	 * @param company 
	 * @throws ClassNotFoundException 
	 */
	public void delete(Company company) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM company " 
			+ "WHERE id=" + company.getId());
			}

		catch (SQLException e2){
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Company-Objekt anhand der �bergebenen Id in der Datenbank.
	 * 
	 * @param id
 	 * @throws ClassNotFoundException 
	 * @return company
	 */
	public Company findById(int id) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("SELECT id, name, description FROM company " 
				+ "WHERE id=" + id);
				
			/**
			 * Der Prim�rschl�ssel (id) wird als eine Tupel zur�ckgegeben.
			 * Es wird gepr�ft ob ein Ergebnis vorliegt
			 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */			
			if (rs.next()){
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setDescription(rs.getString("description"));
				
				return company;
			}
		}
		catch (SQLException e2){
			e2.printStackTrace();
		}
	
		return null;
	}

		
	/**
	 * Findet alle Company-Objekte in der Datenbank.
	 * 
	 * @throws ClassNotFoundException
	 * @return ArrayList<Company>
	 */
	public ArrayList<Company> findAll() throws ClassNotFoundException {
		Connection con = DBConnection.connection();
		
		ArrayList<Company> result = new ArrayList<Company>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, name, description FROM company " 
			 + "ORDER BY id");
			
		/**
		 * Der Prim�rschl�ssel (id) wird als eine Tupel zur�ckgegeben.
		 * Es wird gepr�ft ob ein Ergebnis vorliegt
		 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
		 * 
		 */			
		while (rs.next()){
			Company company = new Company();
			company.setId(rs.getInt("id"));
			company.setName(rs.getString("name"));
			company.setDescription(rs.getString("description"));
			
			result.add(company);
		}
	}
	catch (SQLException e2){
		e2.printStackTrace();
	}

	return result;
	}

	/**
	 * Findet ein Company-Objekt anhand des �bergebenen Namens in der Datenbank.
	 * 
	 * @param name
	 * @throws ClassNotFoundException
	 * @return ArryList<company>
	 */
	public ArrayList<Company> findByName(String name) throws ClassNotFoundException {
		Connection con = DBConnection.connection();
		
		ArrayList<Company> result = new ArrayList<Company>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, name, description FROM company " 
			+ "WHERE name LIKE " + name + "ORDER BY id");
			
		/**
		 * Der Prim�rschl�ssel (id) wird als eine TUpel zur�ck gegeben.
		 * Das Ergebnis-Tupel wird in ein Objekt umgewandelt.
		 * 
		 */			
		while (rs.next()){
			Company company = new Company();
			company.setId(rs.getInt("id"));
			company.setName(rs.getString("name"));
			company.setDescription(rs.getString("description"));
			
			result.add(company);
		}
	}
	catch (SQLException e2){
		e2.printStackTrace();
	}

	return result;
	}

}
