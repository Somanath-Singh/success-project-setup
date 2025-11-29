package com.aashdit.prod.heads.hims.umt.specs;

import lombok.Getter;

@Getter
public enum MailPriority {
    IMMEDIATE("IMMEDIATE"), HIGH("HIGH"), MEDIUM("MEDIUM"), LOW("LOW");

    private final String priority;

    MailPriority(String priority) {
        this.priority = priority;
    }

}
