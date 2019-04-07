package libInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private String stairsGuide;
	private ArrayList<String> eachStairs = new ArrayList<String>();
 	private ArrayList<String> eachStairsImg = new ArrayList<String>();
	
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
	
	public List<String> getEachStairs() {
		return eachStairs;
	}
	
	public List<String> getEachStairsImg() {
		return eachStairsImg;
	}
	
	
	
	public static void main(String[] args) {
		
		try {

			CentralLibCrawler centLibCrawler = new CentralLibCrawler("https://lib.pusan.ac.kr/intro/plot-plan/lib1-open-plot/");
	
			centLibCrawler.setTitle(centLibCrawler.libDoc.select("h1.entry-title").text());
			
			// 개관 시간
			centLibCrawler.setOpenHour(centLibCrawler.libDoc.select("header.section-title").first().text());
			centLibCrawler.setNote(centLibCrawler.libDoc.select("tr.row-1 th.column-4").text());
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
			
			
			
			System.out.println(centLibCrawler.getTitle());
			
			System.out.println(centLibCrawler.getOpenHour());
			System.out.println(centLibCrawler.getNote());
			System.out.println(centLibCrawler.getSemesterDaysOpenHour());
			System.out.println(centLibCrawler.getSemesterWeekOpenHour());
			System.out.println(centLibCrawler.getVacationDaysOpenHour());
			
			System.out.println(centLibCrawler.getStairsGuide());
			System.out.println(centLibCrawler.getEachStairs());
			System.out.println(centLibCrawler.getEachStairsImg());
			
			
			// System.out.println(doc.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
