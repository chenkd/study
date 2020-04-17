package com.chenkeda.avro;

import example.avro.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void generatedUserTest() {
        User user = User.newBuilder().setName("chenkeda").setFavoriteColor("blue")
                .setFavoriteNumber(6).build();

        assertEquals("chenkeda", user.getName());
        assertEquals("blue", user.getFavoriteColor());
        assertEquals(6, (int) user.getFavoriteNumber());

    }
}
