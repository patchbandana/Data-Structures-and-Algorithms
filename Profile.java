
public class Profile {
	
	public short xpos;
	public short ypos;
	private String name;
	private String description;
	private String race;
	private String cClass;
	private short strength;
	private short dexterity;
	private short intelligence;
	private short armor;
	private short hitPoints;
	
	public Profile()
	{
		
	}
	
	public Profile(short x, short y, String n, String d, String r, String c, short h, short s, short dx, short i, short a)
	{
		xpos = x;
		ypos = y;
		name = n;
		description = d;
		race = r;
		cClass = c;
		hitPoints = h;
		strength = s;
		dexterity = dx;
		intelligence = i;
		armor = a;
		
		System.out.println("New profile created: " + name + "\nPosition: " + xpos + "," + ypos);
	}
	
	
	public void setName(String n)
	{
		name = n;
	}

	public void setDesc(String d)
	{
		description = d;
	}
	
	public void setRace(String r)
	{
		race = r;
	}
	
	public void setcClass(String c)
	{
		cClass = c;
	}
	
	public void setHitPoints(short h)
	{
		hitPoints = h;
	}
	
	public void setStrength(short s)
	{
		strength = s;
	}
	
	public void setDexterity(short d)
	{
		dexterity = d;
	}
	
	public void setIntelligence(short i)
	{
		intelligence = i;
	}
	
	public void setArmor(short a)
	{
		armor = a;
	}

	public String getName()
	{
		return name;
	}
	
	public String getDesc()
	{
		return description;
	}
	
	public String getRace()
	{
		return race;
	}

	public String getcClass()
	{
		return cClass;
	}

	public short getHitPoints()
	{
		return hitPoints;
	}

	public short getStrength()
	{
		return strength;
	}

	public short getDexterity()
	{
		return dexterity;
	}

	public short getIntelligence()
	{
		return intelligence;
	}

	public short getArmor()
	{
		return armor;
	}


}
