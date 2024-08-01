package com.meng.messtool.modules.electronic.elementbox;

import java.io.*;
import java.util.*;

class Element implements Serializable {

//    private static final long serialVersionUID = 1L; //unnecessary

    //   id, name, type, describe, package, slot_id, shop_name, id_in_shop, picture file
    public int _id;
    public String _name;
    public String _print;
    public String _brand;
    public String _describe;
    public String _package;
    public String _slot_id;
    public String _shop_name;
    public String _id_in_shop;
    public int _rest;
    public byte[] _picture;

    Element(int _id, String _name, String _print, String _describe, String _package, String _slot_id, String _shop_name, String _id_in_shop, int _rest, byte[] _picture, String _brand) {
        this._id = _id;
        this._name = _name;
        this._print = _print;
        this._describe = _describe;
        this._package = _package;
        this._slot_id = _slot_id;
        this._shop_name = _shop_name;
        this._id_in_shop = _id_in_shop;
        this._rest = _rest;
        this._picture = _picture;
        this._brand = _brand;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Element{");
        sb.append("_id=").append(_id);
        sb.append(", _name='").append(_name).append('\'');
        sb.append(", _print='").append(_print).append('\'');
        sb.append(", _describe='").append(_describe).append('\'');
        sb.append(", _package='").append(_package).append('\'');
        sb.append(", _slot_id=").append(_slot_id);
        sb.append(", _shop_name='").append(_shop_name).append('\'');
        sb.append(", _id_in_shop='").append(_id_in_shop).append('\'');
        sb.append(", _rest='").append(_rest).append('\'');
        sb.append(", _picture=").append(_picture);
        sb.append(", _brand='").append(_brand).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == Element.class && _id == ((Element) o)._id;
    }

    @Override
    public int hashCode() {
        return _id;
    }
}
