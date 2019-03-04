package com.testingSystem.model.entity;

public class Role {
    private int roleId;
    private int admin;
    private int tutor;
    private int user;

    /**
     * @param role Статический мотед класса принимает экземпляр этого класса
     * @return вернёт строковое представление сущности. (Admin, Tutor или User)
     */
    public static String getRole(Role role){
        String roleString = null;
        int admin = role.getAdmin();
        int tutor = role.getTutor();
        int user = role.getUser();

        if (admin == 1 && tutor == 0 && user == 0 ){ roleString = "Admin"; }
        else if (admin == 0 && tutor == 1 && user == 0 ){ roleString = "Tutor"; }
        else if (admin == 0 && tutor == 0 && user == 1 ){ roleString = "User";  }
        return roleString;
    }

    /**
     * @param role Статический мотед класса принимает экземпляр этого класса
     * @return Возвращает массив объектов типа int. будет использоваться
     * в RoleImpl в метододе getRoleId(... )
     */
    public static Object[] getRoleData(String role){
        Object[] roles = null;
        switch (role) {
            case "Admin":
                roles = new Object[]{1, 0, 0};
                break;
            case "Tutor":
                roles = new Object[]{0, 1, 0};
                break;
            case "User":
                roles = new Object[]{0, 0, 1};
                break;
        }
        return roles;
    }

    public Role() {
    }

    public Role(int admin, int tutor, int user) {
        this.admin = admin;
        this.tutor = tutor;
        this.user = user;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getAdmin() {
        return admin;
    }

    public int getTutor() {
        return tutor;
    }

    public int getUser() {
        return user;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public void setTutor(int tutor) {
        this.tutor = tutor;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return Role.getRole(this);
    }
}
