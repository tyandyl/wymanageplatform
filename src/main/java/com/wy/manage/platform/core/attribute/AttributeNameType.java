package com.wy.manage.platform.core.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye on 2018/6/29.
 */
public enum AttributeNameType {

    WIDTH(1,"width"),
    HEIGHT(2,"height"),
    POSITION(3,"position"),
    DISPLAY(4,"display"),
    TOP(5,"top"),
    LEFT(6,"left"),
    RIGHT(7,"right"),
    BOTTOM(8,"bottom"),
    BACKGROUND(9,"background"),
    BORDERWIDTH(10,"border-width"),
    BORDERSTYLE(11,"border-style"),
    BORDERCOLOR(12,"border-color"),
    BACKGROUNDCOLOR(13,"background-color"),
    OVERFLOW(14,"overflow"),
    BORDERRADIUS(15,"border-radius"),
    CURSOR(16,"cursor"),
    FONT(17,"font"),
    COLOR(18,"color"),
    BORDER(19,"border"),
    TABLELAYOUT(20,"table-layout"),
    BORDERCOLLAPSE(21,"border-collapse"),
    FONTSIZE(22,"font-size"),
    TEXTALIGN(23,"text-align"),
    PADDINGTOP(24,"padding-top"),
    PADDINGRIGHT(25,"padding-right"),
    MARGINTOP(26,"margin-top"),
    MARGIN(27,"margin"),
    BACKGROUNDREPEAT(28,"background-repeat"),
    BACKGROUNDIMAGE(29,"background-image"),
    BACKGROUNDPOSITION(30,"background-position"),
    BORDERBOTTOM(31,"border-bottom"),
    BORDERRIGHT(32,"border-right"),
    BORDERLEFT(33,"border-left"),
    FLOAT(34,"float"),
    MAXHEIGHT(35,"max-height"),
    MARGINRIGHT(36,"margin-right"),
    MARGINLEFT(37,"margin-left")
    ;
    private int code;
    private String name;

    AttributeNameType(int code, String name){
        this.code=code;
        this.name=name;
    }

    public static AttributeNameType getAttributeNameType(String str){
        for (AttributeNameType c : AttributeNameType.values()) {
            if (str.equalsIgnoreCase(c.getName())) {
                return c;
            }
        }
        return null;
    }

    public static List<String> getNameList(){
        List<String> list=new ArrayList<String>();
        list.add(DISPLAY.getName());
        list.add(POSITION.getName());
        list.add(WIDTH.getName());
        list.add(HEIGHT.getName());
        list.add(TOP.getName());
        list.add(LEFT.getName());
        list.add(RIGHT.getName());
        list.add(BOTTOM.getName());
        list.add(BACKGROUND.getName());
        list.add(BORDERWIDTH.getName());
        list.add(BORDERSTYLE.getName());
        list.add(BORDERCOLOR.getName());
        list.add(BACKGROUNDCOLOR.getName());
        list.add(OVERFLOW.getName());
        list.add(BORDERRADIUS.getName());
        list.add(CURSOR.getName());
        list.add(FONT.getName());
        list.add(COLOR.getName());
        list.add(BORDER.getName());
        list.add(TABLELAYOUT.getName());
        list.add(BORDERCOLLAPSE.getName());
        list.add(FONTSIZE.getName());
        list.add(TEXTALIGN.getName());
        list.add(PADDINGTOP.getName());
        list.add(PADDINGRIGHT.getName());
        list.add(MARGINTOP.getName());
        list.add(MARGIN.getName());
        list.add(BACKGROUNDREPEAT.getName());
        list.add(BACKGROUNDIMAGE.getName());
        list.add(BACKGROUNDPOSITION.getName());
        list.add(BORDERBOTTOM.getName());
        list.add(BORDERRIGHT.getName());
        list.add(BORDERLEFT.getName());
        list.add(FLOAT.getName());
        list.add(MAXHEIGHT.getName());
        list.add(MARGINRIGHT.getName());
        list.add(MARGINLEFT.getName());
        return list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
