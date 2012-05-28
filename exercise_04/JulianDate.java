package exercise_04;

public class JulianDate {

	private int year;
	private int month;
	private int day;
	private float hour;
	private float minute;
	
	
	// constructor
	public JulianDate(int y, int mo, int d, float h, float mi) {
		if (y<-4712) {
			System.out.println("Das angegebene Datum ist zu niedrig");
		} else {
			year = y;
			month = mo;
			day = d;
			hour = h;
			minute = mi;
		}
	}
	
	private void calculate() {
		// if the month is jan or feb year is going down 1 and month gets 12 up
		// example 23.1.1997 = 23.13.1996
		
		if (month <= 2) {
			month += 12;
			year -= 1;
		}
		
		
		int variableA = 10000*year+100*month+day;
		int variableB;
		
		if (variableA > 15821004.1) {
			// gregorian calender
			variableB = (year/400-year/100+year/4);
		} else {
			// julian calender
			variableB = ((year+4716)/4)-1181;
		}

		double variableC = 365*year-679004; 
		int variableD = (int)(30.6001*(month+1));
		
		double variableHour = (hour+(minute/60))/24;

		// Formular for the modificated julian Date
		double mjd = variableC+variableB+variableD+day;
		
		double julianTime = mjd+2400000.5+variableHour;
		
		String birthDay;
		
		int intJulianTime = (int) julianTime;		
		
		birthDay = (intJulianTime%7 == 1) ? "Tuesday" : (intJulianTime%7 == 2) ? "Wednesday" : (intJulianTime%7 == 3) ? "Thursday" : (intJulianTime%7 == 4) ? "Friday" : (intJulianTime%7 == 5) ? "Saturday" : (intJulianTime%7 == 6) ? "Sunday" : "Monday";

		System.out.println("Days: " + julianTime);
		System.out.println("Weekday: " + birthDay);
		
	}
	
	
	
	
	public static void main(String[] args) {
		JulianDate reneGeb = new JulianDate(1987,5,7,14,8);
		reneGeb.calculate();
	}

}
