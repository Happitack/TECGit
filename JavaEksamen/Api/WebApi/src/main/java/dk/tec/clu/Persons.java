package dk.tec.clu;

public class Persons
{
	public int perID;
	public String perName;
	public String perAddress;
	public int hairId;
	public String personTlf;
	public boolean favorite;
	public int interest1;
	public int interest2;
	public int interest3;
	public int interest4;
	
	public Persons() {}
	
	public Persons(int perID, String perName, String perAddress, int hairId, String personTlf, boolean favorite, int interest1, int interest2, int interest3, int interest4) 
	{
		super();
		this.perID = perID;
		this.perName = perName;
		this.perAddress = perAddress;
		this.hairId = hairId;
		this.personTlf = personTlf;
		this.favorite = favorite;
		this.interest1 = interest1;
		this.interest2 = interest2;
		this.interest3 = interest3;
		this.interest4 = interest4;
	}
}