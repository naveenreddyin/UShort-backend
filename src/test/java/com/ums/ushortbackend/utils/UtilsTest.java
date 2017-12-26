package com.ums.ushortbackend.utils;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by Naveen on 25/07/2017.
 */
public class UtilsTest {
    @Test
    public void randomAlphaNumeric() throws Exception {

        String generatedString = Utils.randomAlphaNumeric(6);

        assertThat(generatedString.length()).isEqualTo(6);

    }

    @Test
    public void isValidURL() throws Exception{

        assertThat(Utils.isUrlValid("httsdfsd:sdfdsf.com")).isFalse();
    }





}