package com.mangosteen.app.mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import java.util.List;

class DemoTest {
    @Test
    void testList() {
        List<String> mockList = mock();

        when(mockList.get(5)).thenReturn("Five");
        when(mockList.get(10))
            .thenThrow(new IndexOutOfBoundsException("Out of bound!"));


        // 不会真正调用方法本身，适合于方法有副作用的情况
        doReturn("One").when(mockList).get(1);
        doThrow(new IndexOutOfBoundsException("Out of index.")).when(mockList).get(6);

        System.out.println(mockList.get(5));
        System.out.println(mockList.get(5));
        System.out.println(mockList.get(1));



        //System.out.println(mockList.get(6));
        //System.out.println(mockList.get(10));


        // verify 验证模拟对象方法调用的情况
        verify(mockList, times(2)).get(eq(5));
        verify(mockList, atMostOnce()).get(eq(1)); // times: 1
        verify(mockList, never()).get(10);


    }
}
