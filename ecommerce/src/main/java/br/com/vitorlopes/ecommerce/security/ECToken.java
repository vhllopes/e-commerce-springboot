package br.com.vitorlopes.ecommerce.security;

public class ECToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ECToken(String token) {
        this.token = token;
    }

    public ECToken() {
    }
}
