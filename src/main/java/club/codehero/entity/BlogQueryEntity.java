package club.codehero.entity;

import lombok.Data;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Data
public class BlogQueryEntity {

    private String title;
    private Long typeId;
    private boolean recommend;


}
