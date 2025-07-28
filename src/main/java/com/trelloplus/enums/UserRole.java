package com.trelloplus.enums;

public enum UserRole {
    ADMIN, //Can create/delete any ticket/user
    DIRECTOR, //View all boards, assign work
    LEAD, //Moderate tickets, comment
    INTERNEE, //Work on tickets
    DEVELOPER, //Can only view tickets
}
