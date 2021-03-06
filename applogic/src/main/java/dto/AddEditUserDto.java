package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AddEditUserDto implements Serializable{

    private Integer idUser;
    private String email;
    private String name;
    private String username;
    private String lastName;
    private String phone;
    private Boolean active;
    private String roleName;
    private Integer allotmentId;
}
