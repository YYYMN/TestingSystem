package com.testingSystem.model.entity;

public class Role {
    private int roleId;
    private int admin;
    private int tutor;
    private int user;

    public static String[] allRoles = {"Admin", "Tutor", "User"};

    /**
     * @param userRoles Статический мотед класса принимает строку,
     *                  содержащую все роли у пользователя, которого надо сохранить.
     * @return Возвращает массив объектов . будет использоваться
     * в RoleImpl в метододе getRoleId(... )
     */
    public static Object[] getRoleData(String[] userRoles) {
        Object[] roleData = {0, 0, 0};

        String admin = "Admin";
        String tutor = "Tutor";
        String user = "User";

        for (int i = 0; i <= userRoles.length - 1; i++) {

            if (admin.equalsIgnoreCase(userRoles[i])) {
                roleData[0] = 1;
            } else if (tutor.equalsIgnoreCase(userRoles[i])) {
                roleData[1] = 1;
            } else if (user.equalsIgnoreCase(userRoles[i])) {
                roleData[2] = 1;
            }
        }
        return roleData;
    }

    /**
     * @return вернёт строковое представление сущности, а именно список ролей. (Admin, Tutor или User)
     */
    public String[] getRoles() {

        String[] rolesStringList = null;
        int admin = this.getAdmin();
        int tutor = this.getTutor();
        int user = this.getUser();

        if (admin == 1 && tutor == 0 && user == 0) {
            rolesStringList = new String[]{"Admin"};
        } else if (admin == 0 && tutor == 1 && user == 0) {
            rolesStringList = new String[]{"Tutor"};
        } else if (admin == 0 && tutor == 0 && user == 1) {
            rolesStringList = new String[]{"User"};
        } else if (admin == 1 && tutor == 1 && user == 0) {
            rolesStringList = new String[]{"Admin", "Tutor"};
        } else if (admin == 0 && tutor == 1 && user == 1) {
            rolesStringList = new String[]{"Tutor", "User"};
        } else if (admin == 1 && tutor == 0 && user == 1) {
            rolesStringList = new String[]{"Admin", "User"};
        } else if (admin == 1 && tutor == 1 && user == 1) {
            rolesStringList = new String[]{"Admin", "Tutor", "User"};
        }

        return rolesStringList;
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

}
