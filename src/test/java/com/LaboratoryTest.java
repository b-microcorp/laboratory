package com;

import java.util.Base64;

public class LaboratoryTest {

    protected static final String USERNAME = "1@gmail.com";
    protected static final String PASSWORD = "test1";
    protected static final String BASIC_AUTH = "basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes());
}
