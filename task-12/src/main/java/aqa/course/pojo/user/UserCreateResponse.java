package aqa.course.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
