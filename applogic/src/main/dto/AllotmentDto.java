package dto;

import lombok.Data;
import model.entity.Allotment;

@Data
public class AllotmentDto {

    private Integer idAllotment;
    private Boolean bower;
    private Boolean composter;
    private Integer number;
    private Integer squaremeter;
    private Integer treeNumber;

    public AllotmentDto(Allotment allotment) {
        this.idAllotment = allotment.getIdAllotment();
        this.bower = allotment.getBower();
        this.composter = allotment.getComposter();
        this.number = allotment.getNumber();
        this.squaremeter = allotment.getSquaremeter();
        this.treeNumber = allotment.getTreeNumber();
    }
}
