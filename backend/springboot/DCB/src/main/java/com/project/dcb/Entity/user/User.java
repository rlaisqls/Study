package com.project.dcb.Entity.user;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class User {
    @Enumerated(EnumType.STRING)
    private SchoolClass schoolClass;
}