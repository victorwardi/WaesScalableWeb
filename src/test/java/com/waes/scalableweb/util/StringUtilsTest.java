package com.waes.scalableweb.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.waes.scalableweb.model.DifferenceDetail;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @autor Victor Wardi - @victorwardi on 2/10/2020
 */
class StringUtilsTest {


    //Expecting 'STRING' - You probably have an extra comma at the end of your collection. Something like { "a": "b", }
   // Expecting 'STRING', 'NUMBER', 'NULL', 'TRUE', 'FALSE', '{', '[' - You probably have an extra comma at the end of your list. Something like: ["a", "b", ]
    //Enclosing your collection keys in quotes. Proper format for a collection is { "key": "value" }


    @Test
    void shouldReturnTrueIfStringIsValidJson() {

        assertEquals(true, StringUtils.isStringValidJson("{\"name\":\"Victor\",\"age\":35,\"hired\":true}"));

        assertEquals(true, StringUtils.isStringValidJson("{ \"techs\" : [ {\"name\" : \"Java\"},{\"name\" : \"JUnit\"} ]}"));

        assertEquals(true, StringUtils.isStringValidJson("{}"));
        assertEquals(true, StringUtils.isStringValidJson("{"));

        assertEquals(true, StringUtils.isStringValidJson("[]"));
        assertEquals(true, StringUtils.isStringValidJson("["));
        assertEquals(true, StringUtils.isStringValidJson("2"));
        assertEquals(true, StringUtils.isStringValidJson("null"));
    }

    @Test
    void shouldReturnFalseIfStringIsEmpty() {
        assertEquals(false, StringUtils.isStringValidJson(""));
    }

    @Test
    void shouldReturnFalseIfStringIsInvalidJson() {
        assertEquals(false, StringUtils.isStringValidJson("{\"nome\" :, \"victor\"}"));
    }

    @Test
    void shouldCompareText() {

        String a = "ABCDELMNVVV";
        String b = "ABCFGLMNABC";

        int offset = 0;
        int length = 0;
        StringBuilder diffLeft = new StringBuilder();
        StringBuilder diffRight = new StringBuilder();

        List<DifferenceDetail> details = new ArrayList<>();

        for(int i = 0 ; i < a.length(); i++) {




            while ( i < a.length() && (a.charAt(i) !=  b.charAt(i)) ){

                if(length == 0){
                    offset = i;
                }

                diffLeft.append(a.charAt(i));
                diffRight.append(b.charAt(i));

                length++;

                i++;
            }

            if(length > 0){
                details.add(DifferenceDetail.builder()
                    .offset(offset)
                    .length(length)
                    .left(diffLeft.toString())
                    .right(diffRight.toString())
                    .build());

                //reset length
                length = 0;
                diffLeft = new StringBuilder();
                diffRight = new StringBuilder();
            }

        }

        System.out.println(details);


    }


    @Test
    void compare() {

        DiffMatchPatch dmp = new DiffMatchPatch();
        LinkedList<DiffMatchPatch.Diff> diff = dmp.diffMain("1234", "1534");


        System.out.println(diff);

    }


    @Test
    void compare2(){

        String A = "ABCXXXXGHIBBL", B="ABCXXXBGHAHCL";
        int size = 0;
        StringBuilder resp = new StringBuilder();

        if (A.equals(B)) {
            System.out.print("EQUAL");
        } else if (A.length() != B.length()) {
            System.out.println("DIFERENTE SIZE");
        } else {


            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i)) {
                    size=1;
                    i++;
                    while (i < A.length() && A.charAt(i) != B.charAt(i)) {
                        size++;
                        i++;
                    }
                }

            }
        }
    }

}