package com.mangosteen.app.model.vo;


public enum ItemType {
    INCOME(1),
    OUTCOME(0);

    private int type;

    ItemType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }


}
