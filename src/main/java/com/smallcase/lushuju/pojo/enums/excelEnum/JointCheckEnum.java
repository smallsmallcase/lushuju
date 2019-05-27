package com.smallcase.lushuju.pojo.enums.excelEnum;

import lombok.Getter;

/**
 * package: com.smallcase.lushuju.pojo.enums.excelEnum
 * date: 2019/1/18 13:49
 *
 * @author smallcase
 * @since JDK 1.8
 */
@Getter
public enum JointCheckEnum {
    OPENTYPE("openType", "开口型"),
    OPENANGLE("openAngle", "开口度"),
    SNAP("snap", "弹响"),
    ACHE("ache","疼痛"),
    COLA("colA","字段A"),
    COLB("colB","字段B"),
    COLC("colC","字段C"),
    COLD("colD","字段D"),
    COLE("colE","字段E"),
    COLF("colF","字段F"),
    COLG("colG","字段G"),
    COLH("colH","字段H"),
    COLI("colI","字段I"),
    COLJ("colJ","字段J"),
    COLK("colK","字段K"),
    COLL("colL","字段L"),
    COLM("colM","字段M"),
    COLN("colN","字段N"),
    COLO("colO","字段O"),
    COLP("colP","字段P"),
    COLQ("colQ","字段Q"),
    COLR("colR","字段R"),
    COLS("colS","字段S"),
    COLT("colT","字段T"),
    COLU("colU","字段U"),
    COLV("colV","字段V"),
    COLW("colW","字段W"),
    COLX("colX","字段X"),
    COLY("colY","字段Y"),
    COLZ("colZ","字段Z"),
    ;

    private String enName;
    private String chName;

    JointCheckEnum(String enName, String chName) {
        this.enName = enName;
        this.chName = chName;
    }}
