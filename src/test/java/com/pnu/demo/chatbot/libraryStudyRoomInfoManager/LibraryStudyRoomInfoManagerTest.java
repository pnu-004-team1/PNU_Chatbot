package com.pnu.demo.chatbot.libraryStudyRoomInfoManager;

import com.pnu.demo.chatbot.readingRoom.LibraryStudyRoomInfoManager;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class LibraryStudyRoomInfoManagerTest {

    private static final Logger logger = Logger.getLogger(LibraryStudyRoomInfoManagerTest.class.getName());

    LibraryStudyRoomInfoManager service;

    @Before
    public void setUp() throws Exception {
        service = new LibraryStudyRoomInfoManager();
    }

    @Test
    public void testLibraryStudyRoomInfoManager_fail() {
        String result;
        String sut;

        // 잘못 된 정보 입력
        // given
        result = "";
        // when
        sut = service.getStringResult("잘못 된 입력");
        // then
        assertEquals(sut, result);

    }

    @Test
    public void testLibraryStudyRoomInfoManager_saebyeok() {
        String result;
        String sut;

        //새벽벌 열람실 정보
        // given
        result = "위치:    [새벽벌] 2층 1열람실\n" +
                "좌석:    357\n" +
                "잔여좌석: 357\n" +
                "운영상태: 자유이용\n" +
                "위치:    [새벽벌] 2층 1노트북 열람실\n" +
                "좌석:    188\n" +
                "잔여좌석: 33\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 3층 2-1열람실\n" +
                "좌석:    324\n" +
                "잔여좌석: 141\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 3층 2-2 열람실\n" +
                "좌석:    324\n" +
                "잔여좌석: 169\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 4층 3-1열람실\n" +
                "좌석:    318\n" +
                "잔여좌석: 254\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 4층 3-2열람실\n" +
                "좌석:    380\n" +
                "잔여좌석: 300\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 4층 대학원 열람실\n" +
                "좌석:    78\n" +
                "잔여좌석: 67\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 4층 2노트북 열람실\n" +
                "좌석:    348\n" +
                "잔여좌석: 124\n" +
                "운영상태: 운영중\n";
        // when
        sut = service.getStringResult("새벽벌");
        // then
        assertEquals(sut, result);

    }

    @Test
    public void testLibraryStudyRoomInfoManager_geonseol() {
        String result;
        String sut;

        //건설관 열람실 정보
        // given
        result = "위치:    [건설관] 3층 제1열람실\n" +
                "좌석:    168\n" +
                "잔여좌석: 158\n" +
                "운영상태: 운영중\n" +
                "위치:    [건설관] 3층 제2열람실\n" +
                "좌석:    294\n" +
                "잔여좌석: 263\n" +
                "운영상태: 운영중\n" +
                "위치:    [건설관] 4층 제3열람실\n" +
                "좌석:    260\n" +
                "잔여좌석: 200\n" +
                "운영상태: 운영중\n" +
                "위치:    [건설관] 4층 대학원/강사 열람실\n" +
                "좌석:    180\n" +
                "잔여좌석: 180\n" +
                "운영상태: 운영중\n";
        // when
        sut = service.getStringResult("건설관");
        // then
        assertEquals(sut, result);

    }

    @Test
    public void testLibraryStudyRoomInfoManager_nano() {
        String result;
        String sut;

        //나노생명과학 열람실 정보
        // given
        result = "위치:    [나노생명과학도서관] 제 1열람실\n" +
                "좌석:    108\n" +
                "잔여좌석: 108\n" +
                "운영상태: 운영중\n" +
                "위치:    [나노생명과학도서관] 제 2열람실\n" +
                "좌석:    72\n" +
                "잔여좌석: 72\n" +
                "운영상태: 운영중\n" +
                "위치:    [나노생명과학도서관] 제 3열람실\n" +
                "좌석:    252\n" +
                "잔여좌석: 252\n" +
                "운영상태: 자유이용\n";
        // when
        sut = service.getStringResult("나노생명");
        // then
        assertEquals(sut, result);

    }

    @Test
    public void testLibraryStudyRoomInfoManager_all() {
        String result;
        String sut;

        // 열람실 정보
        // given
        result = "위치:    [새벽벌] 2층 1열람실\n" +
                "좌석:    357\n" +
                "잔여좌석: 357\n" +
                "운영상태: 자유이용\n" +
                "위치:    [새벽벌] 2층 1노트북 열람실\n" +
                "좌석:    188\n" +
                "잔여좌석: 33\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 3층 2-1열람실\n" +
                "좌석:    324\n" +
                "잔여좌석: 141\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 3층 2-2 열람실\n" +
                "좌석:    324\n" +
                "잔여좌석: 169\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 4층 3-1열람실\n" +
                "좌석:    318\n" +
                "잔여좌석: 254\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 4층 3-2열람실\n" +
                "좌석:    380\n" +
                "잔여좌석: 300\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 4층 대학원 열람실\n" +
                "좌석:    78\n" +
                "잔여좌석: 67\n" +
                "운영상태: 운영중\n" +
                "위치:    [새벽벌] 4층 2노트북 열람실\n" +
                "좌석:    348\n" +
                "잔여좌석: 125\n" +
                "운영상태: 운영중\n" +
                "위치:    [건설관] 3층 제1열람실\n" +
                "좌석:    168\n" +
                "잔여좌석: 158\n" +
                "운영상태: 운영중\n" +
                "위치:    [건설관] 3층 제2열람실\n" +
                "좌석:    294\n" +
                "잔여좌석: 263\n" +
                "운영상태: 운영중\n" +
                "위치:    [건설관] 4층 제3열람실\n" +
                "좌석:    260\n" +
                "잔여좌석: 200\n" +
                "운영상태: 운영중\n" +
                "위치:    [건설관] 4층 대학원/강사 열람실\n" +
                "좌석:    180\n" +
                "잔여좌석: 180\n" +
                "운영상태: 운영중\n" +
                "위치:    [나노생명과학도서관] 제 1열람실\n" +
                "좌석:    108\n" +
                "잔여좌석: 108\n" +
                "운영상태: 운영중\n" +
                "위치:    [나노생명과학도서관] 제 2열람실\n" +
                "좌석:    72\n" +
                "잔여좌석: 72\n" +
                "운영상태: 운영중\n" +
                "위치:    [나노생명과학도서관] 제 3열람실\n" +
                "좌석:    252\n" +
                "잔여좌석: 252\n" +
                "운영상태: 자유이용\n";
        // when
        sut = service.getStringResult("all");
        // then
        assertEquals(sut, result);

    }

}