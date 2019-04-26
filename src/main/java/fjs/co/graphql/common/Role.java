package fjs.co.graphql.common;

public enum Role {
    ROLE_ADMIN, ROLE_USER;

    @Override
    public String toString() {
        return name();
    }
}
