package constants;

import java.awt.*;

public class Constants {
    //file paths
    public static final String ADMIN_DB_PATH = "databases/admindb.txt";
    public static final String DB_PATH = "databases/customerdb.txt";
    //frame config
    public static final Dimension LOGINFRAME_SIZE = new Dimension(540,760);
    public static final Dimension MENUFRAME_SIZE = new Dimension(1000,760);
    public static final Dimension TEXTFIELD_SIZE = new Dimension((int) (LOGINFRAME_SIZE.width * 0.80),50);
    public static final Dimension BUTTON_SIZE = TEXTFIELD_SIZE;
    public static final Dimension RESULT_DIALOG_SIZE = new Dimension((int)(LOGINFRAME_SIZE.width*0.33),(int)(LOGINFRAME_SIZE.height *0.165));
    //register config
    public static final Dimension REGISTER_LABEL_SIZE = new Dimension(LOGINFRAME_SIZE.width,150);
    //login config
    public static final Dimension LOGIN_IMAGE_SIZE = new Dimension(255,262);
    // color configs
    public static final Color PRIMARY_COLOR = new Color(33, 53, 85);
    public static final Color SECONDARY_COLOR = new Color(62, 88, 121);
    public static final Color BUTTON_COLOR = new Color(216,196,182);
    // font config
    public static final Font font = new Font("Arial", Font.PLAIN, 14);

}
