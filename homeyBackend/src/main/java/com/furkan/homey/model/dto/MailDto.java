package com.furkan.homey.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MailDto extends BaseDto{

    private String from;

    private String to;

    private String subject;

    private String content;

    private Long apartId;

    @Override
    public String toString() {
        return "Mail{" + "from='" + from + '\'' + ", to='" + to + '\'' + ", subject='" + subject + '\'' + ", content='"
                + content + '\'' + '}';
    }
}
