package model;

import java.util.List;

public class UserData {

    private List<Clients> clients;

    public List<Clients> getClients() {
        return clients;
    }

    public static class Clients {

        private String name;
        private Integer age;
        private ClientsAdditionally additionally;

        public ClientsAdditionally getAdditionally() {
            return additionally;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}