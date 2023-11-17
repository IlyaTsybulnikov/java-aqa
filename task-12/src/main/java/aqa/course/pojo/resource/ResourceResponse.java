package aqa.course.pojo.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceResponse {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;
}
