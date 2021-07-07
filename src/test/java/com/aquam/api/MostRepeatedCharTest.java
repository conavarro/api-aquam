package com.aquam.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.InjectMocks;

import java.util.concurrent.TimeUnit;


class MostRepeatedCharTest {

    @InjectMocks
    public MostRepeatedChar mostRepeatedChar = new MostRepeatedChar();

    @Timeout(value = 5, unit = TimeUnit.MILLISECONDS)
    @Test
    void method() {
        String result = this.mostRepeatedChar.method("afhiqpplblbblblafsdahqifhjal gfadlkh la l  al fa");
        Assertions.assertEquals("l : 9", result);
    }
}