package com.pnu.demo.chatbot.libInfo;

import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class LibraryContactInfoManagerTest {

    LibraryContactInfoManager libraryContactInfoManager = new LibraryContactInfoManager();


    @Test
    public void testLibraryContactInfoManager_pusan() throws NullPointerException {


        String libContactInfo = libraryContactInfoManager.getStringResult("부산");

        Assert.assertThat(libContactInfo, CoreMatchers.is("부산도서관\n" +
                "항 목 전화번호 (051-510-****) 이메일 담당자\n" +
                "바로바로서비스 1800, 1310 e-mail 장은영 mailto:jangey@pusan.ac.kr\n" +
                "대출 / 반납 중앙도서관 1308 e-mail 박선화 mailto:psh8585@pusan.ac.kr\n" +
                "새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "고전자료 1824 e-mail 이철찬 mailto:cclee@pusan.ac.kr\n" +
                "과학기술자료 1821 e-mail 김수진 mailto:sojkim@pusan.ac.kr\n" +
                "그룹스터디룸 중앙도서관 1813 e-mail 오요환 mailto:pegasus@pusan.ac.kr\n" +
                "새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "기증 7600 e-mail 정영주 mailto:donation@pusan.ac.kr\n" +
                "도서관회원제 1308 e-mail 박선화 mailto:info@library.pusan.ac.kr\n" +
                "무선랜 및 노트북 이용 2000 ※ 정보전산원 헬프데스크\n" +
                "문학예술자료 어학 / 문학 1302 e-mail 윤현희 mailto:hyunhee@pusan.ac.kr\n" +
                "예술 / 체육 1814 e-mail 서소영 mailto:maeum1472@pusan.ac.kr\n" +
                "북한자료 1862 e-mail 김수선 mailto:sseonkim@pusan.ac.kr\n" +
                "열람실 새벽벌도서관 열람실 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "미리내열람실 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "이용교육 3159 e-mail 박지영 mailto:edu@librarypusan.ac.kr\n" +
                "인문사회과학자료 1305 e-mail 강정용 mailto:human@library.pusan.ac.kr\n" +
                "정리중 도서대출 1823 e-mail 장정순 mailto:jsjang@pusan.ac.kr\n" +
                "책읽는대학 1309 e-mail 최민영 mailto:whitemy37@pusan.ac.kr\n" +
                "취업지원자료 1304 e-mail 도양희 mailto:dyhee@pusan.ac.kr\n" +
                "캠퍼스간 자료대출 중앙도서관 1301 e-mail 여윤선 mailto:info@library.pusan.ac.kr\n" +
                "새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "타기관 자료이용 (문헌복사) 1815 e-mail 이정희 mailto:lhee85@pusan.ac.kr\n" +
                "홈페이지 (교외접속, 모바일이용증 등) 1817 e-mail 김왕종 mailto:wjkim@pusan.ac.kr\n" +
                "희망도서신청 1803 e-mail 정재훈 mailto:nomon@pusan.ac.kr\n"));

    }

    @Test
    public void testLibraryContactInfoManager_law() throws Exception {
        String libContactInfo2 = libraryContactInfoManager.getStringResult("법학");

        Assert.assertThat(libContactInfo2, CoreMatchers.is("법학도서관\n" +
                "항 목 전화번호 (051-510-****) 이메일 담당자\n" +
                "학술DB 이용안내 1579 e-mail 황은주 mailto:ejohwang@pusan.ac.kr\n" +
                "대출반납, 세미나실 관리 1583 e-mail 허성경 mailto:2222pink@pusan.ac.kr\n"));


    }

    @Test
    public void testLibraryContactInfoManager_yangsan() throws Exception {
        String libContactInfo3 = libraryContactInfoManager.getStringResult("양산");

        Assert.assertThat(libContactInfo3, CoreMatchers.is("양산도서관\n" +
                "항 목 전화번호 (051-510-****) 이메일 담당자\n" +
                "의생명과학도서관 8138 e-mail 김중학 mailto:jh.kim@pusan.ac.kr\n"));
    }

    @Test
    public void testLibraryContactInfoManager_ami() throws Exception {
        String libContactInfo4 = libraryContactInfoManager.getStringResult("아미");

        Assert.assertThat(libContactInfo4, CoreMatchers.is("아미도서관\n" +
                "항 목 전화번호 (051-240-****) 이메일 담당자\n" +
                "아미캠퍼스 의학정보검색실 7709 e-mail 이가현 mailto:pnuhlib@naver.com\n"));


    }

    @Test
    public void testLibraryContactInfoManager_milyang() throws Exception {
        String libContactInfo5 = libraryContactInfoManager.getStringResult("밀양");

        Assert.assertThat(libContactInfo5, CoreMatchers.is("밀양도서관\n" +
                "항 목 전화번호 (055-350-****) 이메일 담당자\n" +
                "나노생명과학도서관 5211 e-mail 장화옥 mailto:hwaogj@pusan.ac.kr\n"));
    }

    @Test
    public void testLibraryContactInfoManager_all() throws Exception {
        String libContactInfo5 = libraryContactInfoManager.getStringResult("all");

        Assert.assertThat(libContactInfo5, CoreMatchers.is("부산도서관\n" +
                "항 목 전화번호 (051-510-****) 이메일 담당자\n" +
                "바로바로서비스 1800, 1310 e-mail 장은영 mailto:jangey@pusan.ac.kr\n" +
                "대출 / 반납 중앙도서관 1308 e-mail 박선화 mailto:psh8585@pusan.ac.kr\n" +
                "새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "고전자료 1824 e-mail 이철찬 mailto:cclee@pusan.ac.kr\n" +
                "과학기술자료 1821 e-mail 김수진 mailto:sojkim@pusan.ac.kr\n" +
                "그룹스터디룸 중앙도서관 1813 e-mail 오요환 mailto:pegasus@pusan.ac.kr\n" +
                "새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "기증 7600 e-mail 정영주 mailto:donation@pusan.ac.kr\n" +
                "도서관회원제 1308 e-mail 박선화 mailto:info@library.pusan.ac.kr\n" +
                "무선랜 및 노트북 이용 2000 ※ 정보전산원 헬프데스크\n" +
                "문학예술자료 어학 / 문학 1302 e-mail 윤현희 mailto:hyunhee@pusan.ac.kr\n" +
                "예술 / 체육 1814 e-mail 서소영 mailto:maeum1472@pusan.ac.kr\n" +
                "북한자료 1862 e-mail 김수선 mailto:sseonkim@pusan.ac.kr\n" +
                "열람실 새벽벌도서관 열람실 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "미리내열람실 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "이용교육 3159 e-mail 박지영 mailto:edu@librarypusan.ac.kr\n" +
                "인문사회과학자료 1305 e-mail 강정용 mailto:human@library.pusan.ac.kr\n" +
                "정리중 도서대출 1823 e-mail 장정순 mailto:jsjang@pusan.ac.kr\n" +
                "책읽는대학 1309 e-mail 최민영 mailto:whitemy37@pusan.ac.kr\n" +
                "취업지원자료 1304 e-mail 도양희 mailto:dyhee@pusan.ac.kr\n" +
                "캠퍼스간 자료대출 중앙도서관 1301 e-mail 여윤선 mailto:info@library.pusan.ac.kr\n" +
                "새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\n" +
                "타기관 자료이용 (문헌복사) 1815 e-mail 이정희 mailto:lhee85@pusan.ac.kr\n" +
                "홈페이지 (교외접속, 모바일이용증 등) 1817 e-mail 김왕종 mailto:wjkim@pusan.ac.kr\n" +
                "희망도서신청 1803 e-mail 정재훈 mailto:nomon@pusan.ac.kr\n" +
                "법학도서관\n" +
                "항 목 전화번호 (051-510-****) 이메일 담당자\n" +
                "학술DB 이용안내 1579 e-mail 황은주 mailto:ejohwang@pusan.ac.kr\n" +
                "대출반납, 세미나실 관리 1583 e-mail 허성경 mailto:2222pink@pusan.ac.kr\n" +
                "양산도서관\n" +
                "항 목 전화번호 (051-510-****) 이메일 담당자\n" +
                "의생명과학도서관 8138 e-mail 김중학 mailto:jh.kim@pusan.ac.kr\n" +
                "아미도서관\n" +
                "항 목 전화번호 (051-240-****) 이메일 담당자\n" +
                "아미캠퍼스 의학정보검색실 7709 e-mail 이가현 mailto:pnuhlib@naver.com\n" +
                "밀양도서관\n" +
                "항 목 전화번호 (055-350-****) 이메일 담당자\n" +
                "나노생명과학도서관 5211 e-mail 장화옥 mailto:hwaogj@pusan.ac.kr\n"));
    }

    @Test
    public void testLibraryContactInfoManager_default() throws Exception {
        String libContactInfo5 = libraryContactInfoManager.getStringResult("노량진");

        Assert.assertThat(libContactInfo5, CoreMatchers.is("도서관 이름이 존재하지 않습니다."));
    }

//    @Test
//    public void testLibraryContactInfoManager_pusan_json() throws Exception {
//
//
//        JSONObject libContactInfo = libraryContactInfoManager.getStringResultJson("부산");
//
//        Assert.assertThat(libContactInfo, CoreMatchers.is("{\"data\":{\"library\":\"부산도서관\",\"telNums\":\"항 목 전화번호 (051-510-****) 이메일 담당자\\n바로바로서비스 1800, 1310 e-mail 장은영 mailto:jangey@pusan.ac.kr\\n대출 \\/ 반납 중앙도서관 1308 e-mail 박선화 mailto:psh8585@pusan.ac.kr\\n새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\\n고전자료 1824 e-mail 이철찬 mailto:cclee@pusan.ac.kr\\n과학기술자료 1821 e-mail 김수진 mailto:sojkim@pusan.ac.kr\\n그룹스터디룸 중앙도서관 1813 e-mail 오요환 mailto:pegasus@pusan.ac.kr\\n새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\\n기증 7600 e-mail 정영주 mailto:donation@pusan.ac.kr\\n도서관회원제 1308 e-mail 박선화 mailto:info@library.pusan.ac.kr\\n무선랜 및 노트북 이용 2000 ※ 정보전산원 헬프데스크\\n문학예술자료 어학 \\/ 문학 1302 e-mail 윤현희 mailto:hyunhee@pusan.ac.kr\\n예술 \\/ 체육 1814 e-mail 서소영 mailto:maeum1472@pusan.ac.kr\\n북한자료 1862 e-mail 김수선 mailto:sseonkim@pusan.ac.kr\\n열람실 새벽벌도서관 열람실 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\\n미리내열람실 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\\n이용교육 3159 e-mail 박지영 mailto:edu@librarypusan.ac.kr\\n인문사회과학자료 1305 e-mail 강정용 mailto:human@library.pusan.ac.kr\\n정리중 도서대출 1823 e-mail 장정순 mailto:jsjang@pusan.ac.kr\\n책읽는대학 1309 e-mail 최민영 mailto:whitemy37@pusan.ac.kr\\n취업지원자료 1304 e-mail 도양희 mailto:dyhee@pusan.ac.kr\\n캠퍼스간 자료대출 중앙도서관 1301 e-mail 여윤선 mailto:info@library.pusan.ac.kr\\n새벽벌도서관 1303 e-mail 백선웅 mailto:okican78@pusan.ac.kr\\n타기관 자료이용 (문헌복사) 1815 e-mail 이정희 mailto:lhee85@pusan.ac.kr\\n홈페이지 (교외접속, 모바일이용증 등) 1817 e-mail 김왕종 mailto:wjkim@pusan.ac.kr\\n희망도서신청 1803 e-mail 정재훈 mailto:nomon@pusan.ac.kr\\n\",\"type\":\"libTelNum\"}}"));
//
//    }
//
//    @Test
//    public void testLibraryContactInfoManager_law_json() throws Exception {
//        JSONObject libContactInfo2 = libraryContactInfoManager.getStringResultJson("법학");
//
//        Assert.assertThat(libContactInfo2, CoreMatchers.is("{\"data\":{\"library\":\"법학도서관\",\"telNums\":\"항 목 전화번호 (051-510-****) 이메일 담당자\\n학술DB 이용안내 1579 e-mail 황은주 mailto:ejohwang@pusan.ac.kr\\n대출반납, 세미나실 관리 1583 e-mail 허성경 mailto:2222pink@pusan.ac.kr\\n\",\"type\":\"libTelNum\"}}"));
//
//
//    }
//
//    @Test
//    public void testLibraryContactInfoManager_yangsan_json() throws Exception {
//        JSONObject libContactInfo3 = libraryContactInfoManager.getStringResultJson("양산");
//
//        Assert.assertThat(libContactInfo3, CoreMatchers.is("{\"data\":{\"library\":\"양산도서관\",\"telNums\":\"항 목 전화번호 (051-510-****) 이메일 담당자\\n의생명과학도서관 8138 e-mail 김중학 mailto:jh.kim@pusan.ac.kr\\n\",\"type\":\"libTelNum\"}}"));
//    }
//
//    @Test
//    public void testLibraryContactInfoManager_ami_json() throws Exception {
//        JSONObject libContactInfo4 = libraryContactInfoManager.getStringResultJson("아미");
//
//        Assert.assertThat(libContactInfo4, CoreMatchers.is("{\"data\":{\"library\":\"아미도서관\",\"telNums\":\"항 목 전화번호 (051-240-****) 이메일 담당자\\n아미캠퍼스 의학정보검색실 7709 e-mail 이가현 mailto:pnuhlib@naver.com\\n\",\"type\":\"libTelNum\"}}"));
//
//
//    }
//
//    @Test
//    public void testLibraryContactInfoManager_milyang_json() throws Exception {
//        JSONObject libContactInfo5 = libraryContactInfoManager.getStringResultJson("밀양");
//
//        Assert.assertThat(libContactInfo5, CoreMatchers.is("{\"data\":{\"library\":\"밀양도서관\",\"telNums\":\"항 목 전화번호 (055-350-****) 이메일 담당자\\n나노생명과학도서관 5211 e-mail 장화옥 mailto:hwaogj@pusan.ac.kr\\n\",\"type\":\"libTelNum\"}}"));
//    }
}
