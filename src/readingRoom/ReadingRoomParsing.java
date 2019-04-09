package readingRoom;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ReadingRoomParsing {
	public static void main(String[] args) {
		String html = "https://seat.pusan.ac.kr/Clicker/k/";
		List<ReadingRoomInfo> Structure = new LinkedList<ReadingRoomInfo>();
		
		//dummy는 나중에 parameter로 바꿀 것.
		String dummy = "all"; // all, dawn, archi, nano
		
		try {
			connecting(html, Structure);

			for(ReadingRoomInfo I : Structure) {
				if(I.checking(dummy))					//출력 도서관 확인
					System.out.println(I.string());		//나중에 바꿀 것.
			}
			
			
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	private static void connecting(String html, List<ReadingRoomInfo> Structure) throws IOException {
		String temp[] = new String[4];
		int ElementNum;
		int i = 0;
		
		Connection.Response response = Jsoup.connect(html)
		    .method(Connection.Method.GET)
		    .execute();
		Document googleDocument = response.parse();
		Elements textE = googleDocument.select("td");
		ElementNum = textE.size();

		while(i<ElementNum) {
			ReadingRoomInfo RoomTemp = new ReadingRoomInfo();
			
			temp[0] = textE.get(i++).text();
			temp[1] = textE.get(i++).text();
			temp[2] = textE.get(i++).text();
			i++;
			temp[3] = textE.get(i++).text();
			i++;
			RoomTemp.insert(temp[0],temp[1],temp[2],temp[3]);
			Structure.add(RoomTemp);
		}
	}
}


