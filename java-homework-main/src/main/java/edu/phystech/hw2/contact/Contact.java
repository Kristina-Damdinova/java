package edu.phystech.hw2.contact;

import java.util.Objects;
import java.util.regex.Pattern;

record Contact(String username, String email) implements Comparable<Contact> {
    public static final String UNKNOWN_EMAIL = "unknown";

    Contact {
        if (username.isBlank()) {
            username = "username";
            throw new InvalidContactFieldException(username);
        }
        if (!Objects.equals(email, UNKNOWN_EMAIL) && (email.isBlank() || !Pattern.matches(".*@gmail\\.com$", email))) {
            email = "email";
            throw new InvalidContactFieldException(email);
        }
    }

    Contact(String username) {this(username, UNKNOWN_EMAIL);}


    @Override
    public int compareTo(Contact o) {
        return Integer.compare(this.username.length(), o.username.length());
    }
}