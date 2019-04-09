package readingRoom;

public class ReadingRoomInfo {
	String Place;
	String Seat;
	String Residual;
	String Condition;
	
	public ReadingRoomInfo insert(String P, String S, String R, String C) {
		Place = P;
		Seat = S;
		Residual = R;
		Condition = C;
		
		return this;
	}
	
	public String string() {
		return "위치:    " + Place + "\n좌석:    " + Seat + "\n잔여좌석: " 
				+ Residual + "\n운영상태: " + Condition + "\n";
	}
	public boolean checking(String libaray) {
		String substr = this.Place.split("]")[0];
		boolean check = false;
		
		switch(libaray){
        	case "dawn": 
        		if(substr.equals("[새벽벌"))
        			check = true;
        		break;
        	case "archi": 
        		if(substr.equals("[건설관"))
        			check = true;
        		break;
        	case "nano" : 
        		if(substr.equals("[나노생명과학도서관"))
        			check = true;
        		break;
        	case "all":
        		check = true;
        		break;
        	default:
        		System.out.println("Could not find library");
        		break;
    }
		return check;
	}
}