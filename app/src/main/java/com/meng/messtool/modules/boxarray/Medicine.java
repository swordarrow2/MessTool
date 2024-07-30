package com.meng.messtool.modules.boxarray;

class Medicine {
    public int id;
    public String name;
    public String describe;
    public int slotId;
    public byte[] picture;

    public Medicine(int id, String name, String describe, int slotId, byte[] picture) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.slotId = slotId;
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Medicine.class) {
            return false;
        }
        return id == ((Medicine) o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
