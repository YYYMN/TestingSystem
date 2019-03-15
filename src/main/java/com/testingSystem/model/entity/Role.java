package com.testingSystem.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private int roleId;
    private int admin;
    private int tutor;
    private int user;

    public static String[] allRoles = {"Admin","Tutor","User"};

    /**
     * @param userRoles Статический мотед класса принимает строку,
     * содержащую все роли у пользователя, которого надо сохранить.
     * @return Возвращает массив объектов . будет использоваться
     * в RoleImpl в метододе getRoleId(... )
     */
    public static Object[] getRoleData(String[] userRoles){
        Object[] roleData = {0,0,0};

        String admin = "Admin";
        String tutor = "Tutor";
        String user = "User";

        for (int i = 0; i <= userRoles.length - 1; i++) {

            if (admin.equalsIgnoreCase(userRoles[i])) { roleData[0] = 1; }
            else if (tutor.equalsIgnoreCase(userRoles[i])) { roleData[1] = 1; }
            else if (user.equalsIgnoreCase(userRoles[i])){roleData[2] = 1; }

        }

        return roleData;
    }

    /**
     * @return вернёт строковое представление сущности, а именно список ролей. (Admin, Tutor или User)
     */
    public  List<String> getRolesList(){

        List<String> rolesStringList = new ArrayList<>();
        int admin = this.getAdmin();
        int tutor = this.getTutor();
        int user = this.getUser();

        if (admin == 1){ rolesStringList.add("Admin"); }
        if (tutor == 1){ rolesStringList.add("Tutor"); }
        if (user == 1 ){ rolesStringList.add("User");  }
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
