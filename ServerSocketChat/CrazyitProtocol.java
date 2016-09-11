package ServerSocketChat;

/**
 * Created by Stefan on 2016/9/11.
 */
public interface CrazyitProtocol {
    int PROTOCOL_LEN = 2;
    String MEG_ROUND = "@@";
    String USER_ROUND = "$$";
    String LOGIN_SUCCESS = "1";
    String NAME_REP = "-1";
    String PRIVATE_ROUND = "&&";
    String SPLIT_SIGN = "*";
}
