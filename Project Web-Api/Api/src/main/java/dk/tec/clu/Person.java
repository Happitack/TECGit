package dk.tec.clu;

public class Person
{
	public int perID;
	public String perName;
	public String perAddress;
	public String perPhone;
	public int hairId;
	public boolean favorite;
	public int interest1;
	public int interest2;
	public int interest3;
	public int interest4;
	
	public Person() {}
	
	public Person(int perID, String perName, String perAddress, String perPhone, int hairId, boolean favorite, int interest1, int interest2, int interest3, int interest4) 
	{
		super();
		this.perID = perID;
		this.perName = perName;
		this.perAddress = perAddress;
		this.perPhone = perPhone;
		this.hairId = hairId;
		this.favorite = favorite;
		this.interest1 = interest1;
		this.interest2 = interest2;
		this.interest3 = interest3;
		this.interest4 = interest4;
	}

	// Getter Setter for ID
	public int getPerID() {return perID;}
	public void setPerID(int perID) {this.perID = perID;}
	
	// Getter Setter for Name
	public String getPerName() {return perName;	}
	public void setPerName(String perName) {this.perName = perName;	}

	// Getter Setter for Address
	public String getPerAddress() {return perAddress;}
	public void setPerAddress(String perAddress) {this.perAddress = perAddress;}
	
	// Getter Setter for Phone
	public String getPerPhone() {return perPhone;}
	public void setPerPhone(String perPhone) {this.perPhone = perPhone;}

	// Getter Setter for Hair
	public int getHairId() {return hairId;}
	public void setHairId(int hairId) {this.hairId = hairId;}

	// Getter Setter for Favourite
	public boolean isFavorite() {return favorite;}
	public void setFavorite(boolean favorite) {this.favorite = favorite;}

	// Getter Setter for Interest 1
	public int getInterest1() {return interest1;}
	public void setInterest1(int interest1) {this.interest1 = interest1;}

	// Getter Setter for Interest 2
	public int getInterest2() {return interest2;}
	public void setInterest2(int interest2) {this.interest2 = interest2;}

	// Getter Setter for Interest 3
	public int getInterest3() {return interest3;}
	public void setInterest3(int interest3) {this.interest3 = interest3;}

	// Getter Setter for Interest 4
	public int getInterest4() {return interest4;}
	public void setInterest4(int interest4) {this.interest4 = interest4;}
}