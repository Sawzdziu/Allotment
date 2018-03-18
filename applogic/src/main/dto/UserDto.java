package dto;

import lombok.Data;
import model.entity.User;

import java.io.Serializable;

@Data
public class UserDto implements Serializable{

    private Integer idUser;
    private boolean active;
    private String email;
    private String name;
    private String surname;
    private String phone;

    public UserDto(User user) {
        this.idUser = user.getIdUser();
        this.active = user.getActive();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getLastName();
        this.phone = user.getPhone();
    }
}
