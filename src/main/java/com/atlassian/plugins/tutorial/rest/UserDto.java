package com.atlassian.plugins.tutorial.rest;

import lombok.Builder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Builder
@XmlRootElement
public class UserDto {
    @XmlElement
    private String username;

    @XmlElement
    private String fullName;

    @XmlElement
    private String email;

    @XmlElement
    private Collection<String> groups;

}
