package services;

import daos.UserDao;
import pojos.User;

import java.util.List;

public class UserService {

    private UserDao userDao = new UserDao();

    private static class UserServiceHolder {
        private static UserService instance = new UserService();
    }
    public static UserService getInstance() {
        return UserServiceHolder.instance;
    }

    private UserService() {
    }


    public void addUser(User user){

        if (user==null){
            throw new IllegalArgumentException("Pas d'utilisateur...");
        }
        if (user.getNom()==null) {
            throw new IllegalArgumentException("Veuillez rentrer un nom.");
        }
        if (user.getPrenom()==null) {
            throw new IllegalArgumentException("Veuillez rentrer un prénom.");
        }
        if (user.getEmail()==null) {
            throw new IllegalArgumentException("Veuillez rentrer un email.");
        }
        if (user.getMotDePasse()==null) {
            throw new IllegalArgumentException("Veuillez rentrer un mot de passe.");
        }

        userDao.addUser(user);

    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public User getUserByEmailMDP(String email, String motDePasse){
        return userDao.getUserByEmailMDP(email,motDePasse);
    }

}
