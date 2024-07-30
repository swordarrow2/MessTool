package com.meng.messtool.modules.electronic.elementbox;

class Use {

    public int _id;
    public int _element_id;
    public int _count;
    public String _way;
    public long _time;

    public Use(int _id, int _element_id, int _count, String _way, long _time) {
        this._id = _id;
        this._element_id = _element_id;
        this._count = _count;
        this._way = _way;
        this._time = _time;
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == Use.class && _id == ((Use) o)._id;
    }

    @Override
    public int hashCode() {
        return _id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Use{");
        sb.append("_id=").append(_id);
        sb.append(", _element_id=").append(_element_id);
        sb.append(", _count=").append(_count);
        sb.append(", _way='").append(_way).append('\'');
        sb.append(", _time=").append(_time);
        sb.append('}');
        return sb.toString();
    }
}