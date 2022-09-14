package helper;

public class Validator {

    public boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }

        if (username.isEmpty()) {
            return false;
        }

        if (DIContainer.getCustomerDao().findUserByUsername(username)) {
            return false;
        }

        return true;
    }
}
