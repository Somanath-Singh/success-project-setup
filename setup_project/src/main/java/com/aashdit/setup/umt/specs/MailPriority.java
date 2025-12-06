package com.aashdit.setup.umt.specs;

import lombok.Getter;

@Getter
public enum MailPriority {
    IMMEDIATE("IMMEDIATE"), HIGH("HIGH"), MEDIUM("MEDIUM"), LOW("LOW");

    private final String priority;

    MailPriority(String priority) {
        this.priority = priority;
    }

}
