package com.zzff.jms.others;
import jdk.nashorn.internal.objects.annotations.Getter;

public enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private Integer code;
    private String mess;

    CountryEnum(Integer code, String mess) {
        this.code = code;
        this.mess = mess;
    }

    public Integer getCode() {
        return code;
    }

    public String getMess() {
        return mess;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] enums = CountryEnum.values();
        for (CountryEnum item:enums) {
            if(index==item.getCode()){
                return item;
            }
        }
        return null;
    }
}
