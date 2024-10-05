package model;

import java.util.List;

public class ClientData {

    private List<Clients> clients;

    public List<Clients> getClients() {
        return clients;
    }

    public static class Clients {

        private String name;
        private Integer age;
        private ClientsAdditionallyData additionally;

        public ClientsAdditionallyData getAdditionally() {
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