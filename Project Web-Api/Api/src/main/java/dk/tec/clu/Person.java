package dk.tec.clu;

public class Person
{
	public int perID;
	public String perName;
	public String perAddress;
	public String perPhone;
	public String perHair;
	public boolean favorite;
	public String interest1;
	public String interest2;
	public String interest3;
	public String interest4;
	
	public Person() {}
	
	public Person(int perID, String perName, String perAddress, String perPhone, String perHair, boolean favorite, String interest1, String interest2, String interest3, String interest4) 
	{
		super();
		this.perID = perID;
		this.perName = perName;
		this.perAddress = perAddress;
		this.perPhone = perPhone;
		this.perHair = perHair;
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
	public String getPerHair() {return perHair;}
	public void setperHair(String perHair) {this.perHair = perHair;}

	// Getter Setter for Favourite
	public boolean isFavorite() {return favorite;}
	public void setFavorite(boolean favorite) {this.favorite = favorite;}

	// Getter Setter for Interest 1
	public String getInterest1() {return interest1;}
	public void setInterest1(String interest1) {this.interest1 = interest1;}

	// Getter Setter for Interest 2
	public String getInterest2() {return interest2;}
	public void setInterest2(String interest2) {this.interest2 = interest2;}

	// Getter Setter for Interest 3
	public String getInterest3() {return interest3;}
	public void setInterest3(String interest3) {this.interest3 = interest3;}

	// Getter Setter for Interest 4
	public String getInterest4() {return interest4;}
	public void setInterest4(String interest4) {this.interest4 = interest4;}
}