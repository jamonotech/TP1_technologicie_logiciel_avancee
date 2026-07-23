package com.alumni.userservice.api;

import com.alumni.userservice.model.CreateUserRequest;
import com.alumni.userservice.model.ErrorResponse;
import org.springframework.lang.Nullable;
import java.util.UUID;
import com.alumni.userservice.model.UpdateUserRequest;
import com.alumni.userservice.model.User;
import com.alumni.userservice.model.UserPage;
import com.alumni.userservice.model.UserType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-23T09:01:43.370133340Z[Africa/Dakar]", comments = "Generator version: 7.24.0")
@Controller
@RequestMapping("${openapi.alumniPlatformUserService.base-path:}")
public class UsersApiController implements UsersApi {

    private final UsersApiDelegate delegate;

    public UsersApiController(@Autowired(required = false) UsersApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new UsersApiDelegate() {});
    }

    @Override
    public UsersApiDelegate getDelegate() {
        return delegate;
    }

}
