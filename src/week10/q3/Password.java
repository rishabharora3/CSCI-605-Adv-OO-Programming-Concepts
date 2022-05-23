package week10.q3;/*
 * Password.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
import java.io.Serial;
import java.io.Serializable;
/**
 * Password class with an extra field of hash id
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Password implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String password;
    private String hashId;

    public Password(String password) {
        this.password = password;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getPassword() {
        return password;
    }
}
