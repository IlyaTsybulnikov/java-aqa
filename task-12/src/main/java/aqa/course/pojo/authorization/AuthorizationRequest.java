package aqa.course.pojo.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRequest {
    private String email;
    @Setter private String password;

    public AuthorizationRequest withEmail(String email) {
        this.email = email;

        return this;
    }

    public AuthorizationRequest withPassword(String password) {
        this.password = password;

        return this;
    }
}