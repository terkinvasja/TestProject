package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class UserDaoImpl implements UserDao
{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user)
    {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. User details: " + user);
    }

    public void updateUser(User user)
    {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully update. User details: " + user);
    }

    public void removeUser(int id)
    {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user != null) {
            session.delete(user);
        }
        logger.info("User successfully removed. User details: " + user);
    }

    public User getUserById(int id)
    {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User successfully loaded. User details: " + user);

        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsersByName(String name)
    {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = (List<User>) session.createQuery("FROM User u WHERE u.name = '" + name + "'").list();
        logger.info("Users successfully loaded. Users details: " + users);

        return users;
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers()
    {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("FROM User").list();

        for (User user : userList) {
            logger.info("User list: " + user);
        }

        return userList;
    }

    public void addGeneratedUsers(int n)
    {
        String names[] = {"Петя", "Коля", "Саша", "Маша", "Костя", "Иван", "Вася", "Надя", "Лера", "Вика"};
        boolean isAdmins[] = {true, false};

        Session session = this.sessionFactory.getCurrentSession();
        for (int i = 0 ; i < n; i++) {
            User user = new User();
            user.setName(names[(new Random()).nextInt(names.length)]);
            user.setAge((new Random()).nextInt(50) + 10);
            user.setAdmin(isAdmins[(new Random()).nextInt(isAdmins.length)]);
            session.persist(user);
            logger.info("User successfully saved. User details: " + user);
        }

    }
}
