package edu.fjnu501.crms.state;

public enum RoleType {

    registrar(0), teacher(1), student(2);

    private int type;

    RoleType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
