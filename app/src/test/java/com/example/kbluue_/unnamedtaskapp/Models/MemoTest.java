package com.example.kbluue_.unnamedtaskapp.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemoTest {

    @Test
    public void compareTo() {

        Memo memo1 = new Memo();
        memo1.setId("1");
        memo1.setTimeCreated(new Timestamp());

        Memo nullMemo1 = new Memo();
        Memo nullMemo2 = new Memo();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Memo memo2 = new Memo();
        memo2.setId("2");
        memo2.setTimeCreated(new Timestamp());

        compareTo(0, nullMemo1, nullMemo2);
        compareTo(-1, memo2, memo1);
        compareTo(1, memo1, nullMemo1);
    }

    private void compareTo(int expected,Memo memo1, Memo memo2){
        assertEquals(expected, memo1.compareTo(memo2));
        assertEquals(expected * -1, memo2.compareTo(memo1));
    }
}