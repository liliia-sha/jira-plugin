package com.atlassian.plugins.tutorial.rest;

import com.atlassian.jira.security.groups.GroupManager;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class MyRestResource {
    @ComponentImport
    UserManager userManager;

    @ComponentImport
    GroupManager groupManager;

    public MyRestResource(UserManager userManager, GroupManager groupManager) {
        this.userManager = userManager;
        this.groupManager = groupManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("username") String username) {
        ApplicationUser user = userManager.getUserByName(username);
        UserDto userDto = UserDto.builder().email(user.getEmailAddress())
                .fullName(user.getDisplayName())
                .username(user.getUsername())
                .groups(groupManager.getGroupNamesForUser(user))
                .build();
        return Response.ok(userDto).build();
    }
}