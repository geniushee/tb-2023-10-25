package com.ll.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Quote {
    private int id;
    private String content;
    private String author;
}
