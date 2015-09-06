package geoservice

class Geocatch {
	
	String title
	String description
	
	byte[] picture
	
	String address
	
	int radius
	
	double	lat 
	double	lon
	
	User author
	
	static belongsTo = User

	static hasMany = [visitors: User]

    static constraints = {
	    title blank: false, unique: true
		description blank: false
		address blank: true, nullable: true
		picture nullable: true, maxSize: 1000000000
		author nullable: false, display: false
		visitors nullable: true, display: false
		author display: false, nullable: true
		radius nullable: true, max: 350, min: 1
    }
}
