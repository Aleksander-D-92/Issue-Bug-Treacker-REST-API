package com.secure.secure_back_end.dto.authority;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserChangeAuthorityForm
{
    @NotNull
    @Min(value = 1, message = "authorityId id is invalid")
    private Long authorityId;

    public UserChangeAuthorityForm()
    {
    }

    public Long getAuthorityId()
    {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId)
    {
        this.authorityId = authorityId;
    }
}
