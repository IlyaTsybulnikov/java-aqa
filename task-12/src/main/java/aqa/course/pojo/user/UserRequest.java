package aqa.course.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String job;

    public UserRequest withName(String name) {
        this.name = name;

        return this;
    }

    public UserRequest withJob(String job) {
        this.job = job;

        return this;
    }
}