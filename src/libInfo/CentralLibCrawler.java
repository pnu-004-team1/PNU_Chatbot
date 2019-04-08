package libInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CentralLibCrawler extends LibraryCrawler {
	private Document libDoc;
	private String title;
	private String openHour;
	private String note;
	private String semesterDaysOpenHour;
	private String semesterWeekOpenHour;
	private String vacationDaysOpenHour;
	private String vacationWeekOpenHour;
	private String noteInfo;
	private String stairsGuide;
	private ArrayList<String> eachStairs = new ArrayList<String>();
 	private ArrayList<String> eachStairsImg = new ArrayList<String>();
 	private Integer eachStairsIter = -1;
 	private Integer eachStairsImgIter= -1;
 	
 	
 	public CentralLibCrawler() {
 		
	}

 	
	public CentralLibCrawler(String url) throws IOException {
		super(url);
		libDoc = Jsoup.connect(url).get();
	}
	

	
	private void setLibDoc(Document libDoc) {
		this.libDoc = libDoc;
	}
	
	private void setTitle(String title) {
		this.title = title;
	}
	
	private void setOpenHour(String openHour) {
		this.openHour = openHour;
	}
	
	private void setNote(String note) {
		this.note = note;
	}
	
	private void setNoteInfo(String noteInfo) {
		this.noteInfo = noteInfo;
	}
	
	private void setSemesterDaysOpenHour(String semesterDaysOpenHour) {
		this.semesterDaysOpenHour = semesterDaysOpenHour;
	}
	
	private void setSemesterWeekOpenHour(String semesterWeekOpenHour) {
		this.semesterWeekOpenHour = semesterWeekOpenHour;
	}
	
	private void setVacationDaysOpenHour(String vacationDaysOpenHour) {
		this.vacationDaysOpenHour = vacationDaysOpenHour;
	}
	
	private void setVacationWeekOpenHour(String vacationWeekOpenHour) {
		this.vacationWeekOpenHour = vacationWeekOpenHour;
	}
	
	private void setStairsGuide(String stairsGuide) {
		this.stairsGuide = stairsGuide;
	}
	
	private void setEachStairs(String eachStair) {
		this.eachStairs.add(eachStair);
	}
	
	private void setEachStairsImg(String eachStairImg) {
		this.eachStairsImg.add(eachStairImg);
	}
	
	public Document getLibDoc() {
		return libDoc;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getOpenHour() {
		return openHour;
	}
	
	public String getNote() {
		return note;
	}
	
	public String getNoteInfo() {
		return noteInfo;
	}
	
	public String getSemesterDaysOpenHour() {
		return semesterDaysOpenHour;
	}
	
	public String getSemesterWeekOpenHour() {
		return semesterWeekOpenHour;
	}
	
	public String getVacationDaysOpenHour() {
		return vacationDaysOpenHour;
	}
	
	public String getVacationWeekOpenHour() {
		return vacationWeekOpenHour;
	}
	
	public String getStairsGuide() {
		return stairsGuide;
	}
	
	public String getEachStairs() {
		eachStairsIter++;
		return eachStairs.get(eachStairsIter);
	}
	
	public String getEachStairsImg() {
		eachStairsImgIter++;
		return eachStairsImg.get(eachStairsImgIter);
	}
	
	public JSONObject getResult() throws Exception {
		
		CentralLibCrawler centLibCrawler = new CentralLibCrawler("https://lib.pusan.ac.kr/intro/plot-plan/lib1-open-plot/");
		
		centLibCrawler.setTitle(centLibCrawler.libDoc.select("h1.entry-title").text());
		
		// 개관시간
		centLibCrawler.setOpenHour(centLibCrawler.libDoc.select("header.section-title").first().text());
		centLibCrawler.setNote(centLibCrawler.libDoc.select("tr.row-1 th.column-4").text());
		centLibCrawler.setNoteInfo(centLibCrawler.libDoc.select("tr.row-2 td.column-4").text());
		centLibCrawler.setSemesterDaysOpenHour(centLibCrawler.libDoc.select("tr.row-3 td.column-2").text());
		centLibCrawler.setSemesterWeekOpenHour(centLibCrawler.libDoc.select("tr.row-3 td.column-3").text());
		centLibCrawler.setVacationDaysOpenHour(centLibCrawler.libDoc.select("tr.row-4 td.column-2").text());
		centLibCrawler.setVacationWeekOpenHour(centLibCrawler.libDoc.select("tr.row-3 td.column-3").text());

		// 층별안내
		centLibCrawler.setStairsGuide(centLibCrawler.libDoc.select("header.section-title").last().text());
		for (Element element:centLibCrawler.libDoc.select("h3")){
			centLibCrawler.setEachStairs(element.text());
		}
		for (Element element:centLibCrawler.libDoc.select("h3 img")){
			centLibCrawler.setEachStairsImg(element.attr("src"));
		}
		
		JSONObject centLib = new JSONObject();
		JSONObject centLibOn = new JSONObject();
		JSONObject centLibOnSemeDays = new JSONObject();
		JSONObject centLibOnVacaDays = new JSONObject();
		JSONObject centLibStairs = new JSONObject();
		
		centLibOnSemeDays.put("평일 (월~금)",centLibCrawler.getSemesterDaysOpenHour());
		centLibOnSemeDays.put("토요일",centLibCrawler.getSemesterWeekOpenHour());
		centLibOnVacaDays.put("평일 (월~금)",centLibCrawler.getVacationDaysOpenHour());
		centLibOnVacaDays.put("토요일",centLibCrawler.getVacationWeekOpenHour());
		centLibOn.put("학기중",centLibOnSemeDays);
		centLibOn.put("방학중",centLibOnVacaDays);
		centLib.put(centLibCrawler.getOpenHour(), centLibOn);
		centLib.put(centLibCrawler.getNote(), centLibCrawler.getNoteInfo());
		// 1층 ~ 4층
		centLibStairs.put("1층",centLibCrawler.getEachStairsImg());
		centLibStairs.put("2층  문학예술자료관",centLibCrawler.getEachStairsImg());
		centLibStairs.put("3층 인문사회과학자료관",centLibCrawler.getEachStairsImg());
		centLibStairs.put("4층 과학기술자료관",centLibCrawler.getEachStairsImg());
		
		centLib.put("층별안내",centLibStairs);
		

		System.out.println(centLib);
		
		return centLib;
	}
	
	
	
	public static void main(String[] args) {
		
			
		CentralLibCrawler centLibCrawler = new CentralLibCrawler();
			
		try {
			centLibCrawler.getResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
}
