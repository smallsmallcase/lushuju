package com.smallcase.lushuju.pojo.enums.excelEnum;

import lombok.Getter;

/**
 * package: com.smallcase.lushuju.pojo.enums.excelEnum
 * date: 2019/1/18 13:39
 *
 * @author smallcase
 * @since JDK 1.8
 */
@Getter
public enum FaceCheckEnum {
    FACESHAPEFRONT("faceShapeFront", "正面观面型"),
    FACESYMMETRY("faceSymmetry", "正面观面部对称性"),
    FACECONVEXITY("faceConvexity", "正面观面中间1/3凸度"),
    LIPS("lips", "口唇"),
    FACESHAPESIDE("faceShapeSide", "侧面观面型"),
    NASOLABIALANGLE("nasolabialAngle", "侧面观鼻唇角"),
    JAW("jaw", "侧面观颌骨（上下颌）"),
    SOFTTISSUE("softTissue","软组织")
    ;

    private String enName;
    private String chName;

    FaceCheckEnum(String enName, String chName) {
        this.enName = enName;
        this.chName = chName;
    }}
