package pitmchmen_test;

	
	import java.util.ArrayList;

	
	public abstract class OrganisationUnit {

		private int id = 0;

		
		private String description = "";

		private String name = "";

		
			 
			public int getId() {
				return this.id;
			}

			
			public void setId(int id) {
				this.id = id;
			}

		
		public String getDescription() {
			return this.description;
		}

		
		public void setDescription(String description) {
			this.description = description;
		}

		
		public String getName() {
			return this.name;
		}

		
		public void setName(String name) {
			this.name = name;
		}

		public String toString(){
			return "(" + this.id + ": " + this.name + ", " + this.description + ")";
		}
}
