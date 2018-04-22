package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.User;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class AllotmentHistoryDto {

    private Integer idAllotment;
    private List<UserDto> userDtoList;

    public AllotmentHistoryDto(Integer idAllotment) {
        this.idAllotment = idAllotment;
        this.userDtoList = new LinkedList<>();
    }

    public AllotmentHistoryDto(Integer idAllotment, List<UserDto> userDtoList) {
        this.idAllotment = idAllotment;
        this.userDtoList = userDtoList;
    }

    public void addUser(User user){
        this.userDtoList.add(new UserDto(user));
    }
}
