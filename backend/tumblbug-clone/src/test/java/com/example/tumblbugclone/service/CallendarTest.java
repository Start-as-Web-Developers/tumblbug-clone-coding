package com.example.tumblbugclone.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;


import java.text.ParseException;
import java.util.Date;

public class CallendarTest {


    @Test
    public void 오늘_날짜_출력() throws Exception{
        System.out.println(Callendar.getTodayString());
    }

    @Test
    public void 날짜_비교_1() throws Exception{
        //given
        Date date1 = Callendar.convertDate("2022-03-01");
        Date date2 = Callendar.convertDate("2023-01-31");
        //when

        boolean checkBoolean = date1.after(date2);

        //then
        Assertions.assertThat(checkBoolean).isFalse();
    }

    @Test
    public void 날짜_비교_성공2() throws Exception{
        //given
        Date date1 = Callendar.convertDate("2023-01-31");
        Date date2 = Callendar.convertDate("2023-01-21");

        //when
        boolean checkBoolean = date1.after(date2);

        //then
        Assertions.assertThat(checkBoolean).isTrue();
    }

    @Test
    public void 같은_날짜_비교() throws Exception{
        //given
        Date date1 = Callendar.convertDate("2023-03-01");
        Date date2 = Callendar.convertDate("2023-03-01");

        //when
        boolean checkBoolean = date1.equals(date2);

        //then
        Assertions.assertThat(checkBoolean).isTrue();
    }
    
    @Test(expected = ParseException.class)
    public void 날짜_포멧_오류() throws Exception{
        //given
        String dateString = "2023.01.23";
        //when

        Callendar.convertDate(dateString);
        //then
    }
}