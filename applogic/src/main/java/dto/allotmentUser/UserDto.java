package dto.allotmentUser;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.User;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto implements Serializable{

    private Integer idUser;
    private Boolean active;
    private String email;
    private String name;
    private String lastName;
    private String phone;
    private String role;

    public UserDto(User user) {
        this.idUser = user.getIdUser();
        this.active = user.getActive();
        this.email = user.getEmail();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.role = user.getRole().getName();
    }
}
